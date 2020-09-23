package com.utils;

import java.io.*;
import java.net.Socket;
import com.controller.PosController;
import com.network.ServerType;
import com.network.model.Message;

public class Listener implements Runnable {

	private static final String HASCONNECTED = "has connected";
	private static final Integer PORT = 4001;
	private static String userName = null;

	private static ObjectOutputStream oos;

	private Socket socket;
	private ObjectInputStream input;
	private final String hostName;
	private final PosController controller;

	public Listener(String hostName, String userName, PosController controller) {
		this.hostName = hostName;
		Listener.userName = userName;
		this.controller = controller;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			socket = new Socket(hostName, PORT);
			OutputStream outputStream = socket.getOutputStream();
			oos = new ObjectOutputStream(outputStream);
			InputStream is = socket.getInputStream();
			input = new ObjectInputStream(is);
		} catch (IOException | InterruptedException e) {
			System.out.println("연결 실패");
		}
		System.out
			.println("연결정보: " + socket.getInetAddress() + ":" + socket.getPort());

		try {
			connect();
			System.out.println("ADMIN 소켓 연결 성공");
			while (socket.isConnected()) {
				Message message = null;
				message = (Message) input.readObject();

				if (message.getType() == ServerType.USER) {
					controller.getOrderData(message);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void send(String msg) throws IOException {
		Message sendMsg = new Message();
		sendMsg.setType(ServerType.SERVER);
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
