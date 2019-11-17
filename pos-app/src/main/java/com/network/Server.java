package com.network;

import com.network.model.Message;
import com.network.model.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Server implements Runnable {

	/* 채팅 설정 */
	private static final int PORT = 4001;
	private static final HashMap<String, User> names = new HashMap<>();
	private static HashSet<ObjectOutputStream> writers = new HashSet<>();
	private static ArrayList<User> users = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		startServer();
	}

	private static void startServer() throws IOException {
		System.out.println("server is running");
		ServerSocket listener = new ServerSocket(PORT);

		try {
			while (true) {
				new Handler(listener.accept()).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			listener.close();
		}
	}

	@Override
	public void run() {
		try {
			startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class Handler extends Thread {

		private String name;
		private Socket socket;
		private User user;
		private ObjectInputStream input;
		private OutputStream os;
		private ObjectOutputStream output;
		private InputStream is;

		Handler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			System.out.println("사용자 연결을 시도하는 중...");
			try {
				is = socket.getInputStream();
				input = new ObjectInputStream(is);
				os = socket.getOutputStream();
				output = new ObjectOutputStream(os);

				Message init = (Message) input.readObject();
				addNewUser(init);
				writers.add(output);
				System.out.println("리스트에 추가");

				while (socket.isConnected()) {
					Message inputMsg = (Message) input.readObject();
					switch (inputMsg.getType()) {
						case USER:
							System.out.println("get message from " + inputMsg.getName() +
								" 나는 " + inputMsg.getMsg() + " 주문할게요 ");
							write(inputMsg);
							break;
						case CONNECTED:
							System.out.println("처음 접속");
							break;
						case SERVER:
							System.out.println("서버님이 말씀하십니다.");
							write(inputMsg);
							break;
						default:
							throw new IllegalStateException(
								"Unexpected value: " + inputMsg.getType());
					}
				}
			} catch (SocketException socketException) {
				System.out.println("exception user " + name);
			} catch (Exception e) {
				System.out.println("exception run() user: " + name + " | ex: " + e);
			} finally {
				closeConnections();
			}
		}

		private synchronized void addNewUser(Message init) {
			System.out.println(init.getName() + "가 접속시도 중");
			if (!names.containsKey(init.getName())) {
				this.name = init.getName();
				user = new User();
				user.setName(init.getName());
				users.add(user);
				names.put(name, user);
				System.out.println(name + "가 접속..");
			}
		}

		private void write(Message msg) throws IOException {
			for (ObjectOutputStream writer : writers) {
				msg.setUserlist(names);
				msg.setUsers(users);
				msg.setOnlineCount(names.size());
				writer.writeObject(msg);
				writer.reset();
			}
		}

		private synchronized void closeConnections() {
			if (user != null) {
				users.remove(user);
			}
			if (output != null) {
				writers.remove(output);
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}