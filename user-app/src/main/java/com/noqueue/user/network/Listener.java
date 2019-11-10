package com.noqueue.user.network;

import com.noqueue.user.controller.UserController;
import com.noqueue.user.model.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Listener implements Runnable {

	private static final String HASCONNECTED = "has connected";

	private static String picture;
	private Socket socket;
	public String hostname;
	public int port = 4001;
	public static String username;
	public UserController controller;
	private static ObjectOutputStream oos;
	private InputStream is;
	private ObjectInputStream input;
	private OutputStream outputStream;

	public Listener(String hostname, String username, UserController controller) {
		this.hostname = hostname;
		Listener.username = username;
		this.controller = controller;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(hostname, port);
//			LoginController.getInstance().showScene();
			outputStream = socket.getOutputStream();
			oos = new ObjectOutputStream(outputStream);
			is = socket.getInputStream();
			input = new ObjectInputStream(is);
		} catch (IOException e) {
//			LoginController.getInstance().showErrorDialog("연결 실패");
			System.out.println("연결 실패");
		}
		System.out
			.println("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
	/*
		try {
			connect();
			System.out.println("Sockets in and out ready!");
			while (socket.isConnected()) {
				Message message = null;
				message = (Message) input.readObject();

				if (message != null) {
					System.out.println(
						"Message recieved:" + message.getMsg() + "Name:" + message.getName());
					controller.addToChat(message);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
//			controller.logoutScene();
		}
		*/
	}

	/*
	public static void send(String msg) throws IOException {
		Message createMessage = new Message();
		createMessage.setName(username);
		createMessage.setMsg(msg);
		createMessage.setPicture(picture);
		oos.writeObject(createMessage);
		oos.flush();
	}

	public static void connect() throws IOException {
		Message createMessage = new Message();
		createMessage.setName(username);
		createMessage.setType(CONNECTED);
		createMessage.setMsg(HASCONNECTED);
		createMessage.setPicture(picture);
		oos.writeObject(createMessage);
	}
	*/
}
