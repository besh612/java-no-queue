package com.network;

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

	public Listener(String hostName, String userName) {
		this.hostName = hostName;
		Listener.userName = userName;
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
				System.out.println("is connected: " + input.readObject().toString());

				if (message != null) {
					System.out.println("수신: " + message.getMsg());
					System.out.println("타입: " + message.getType());
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void send(String msg) throws IOException {
		Message sendMsg = new Message();
		sendMsg.setName(userName);
		sendMsg.setMsg(msg);
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
