package com.noqueue.pos.server;

import com.noqueue.pos.model.Food;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Handler;

public class Server {

	/* 채팅 설정 */
	private static final int PORT = 4001;
	private static final HashMap<String, Food> foods = new HashMap<>();

	public static void main(String[] args) throws Exception {
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

	private static class Handler extends Thread {

		private String name;
		private Socket socket;

		public Handler(Socket socket) {
			this.socket = socket;
		}
		/*
		private void run() {
			System.out.println("연결 중");
			try {
			}
		}*/
	}
}