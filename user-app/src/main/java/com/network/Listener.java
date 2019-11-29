package com.network;

import com.controller.UserController;
import com.network.model.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Listener implements Runnable {

	private static final String HASCONNECTED = "has connected";
	private static final Integer PORT = 4001;

	private static String userName;
	private static ObjectOutputStream oos;

	private Socket socket;
	private String hostName;
	private ObjectInputStream input;
	private UserController controller;

	public Listener(String hostName, String userName, UserController controller) {
		this.hostName = hostName;
		Listener.userName = userName;
		this.controller = controller;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(hostName, PORT);
			OutputStream outputStream = socket.getOutputStream();
			oos = new ObjectOutputStream(outputStream);
			InputStream is = socket.getInputStream();
			input = new ObjectInputStream(is);
		} catch (IOException e) {
			System.out.println("연결 실패");
		}
		System.out
			.println("연결정보: " + socket.getInetAddress() + ":" + socket.getPort());

		try {
			connect();
			System.out.println("소켓 연결 성공");
			while (socket.isConnected()) {
				Message message = null;
				message = (Message) input.readObject();

				if (message.getType() == ServerType.SERVER) {
					controller.getData(message);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void send(String name, int id) throws IOException {
		Message sendMsg = new Message();
		sendMsg.setType(ServerType.USER);
		sendMsg.setName(userName);
		sendMsg.setMsg(name);
		sendMsg.setId(id);
		oos.writeObject(sendMsg);
		oos.flush();
	}

	private static void connect() throws IOException {
		Message sendMsg = new Message();
		sendMsg.setName(userName);
		sendMsg.setMsg(HASCONNECTED);
		oos.writeObject(sendMsg);
	}
}
