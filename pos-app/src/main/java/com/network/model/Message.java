package com.network.model;

import com.network.ServerType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Message implements Serializable {

	private String name;
	private String msg;
	private ServerType type;
	private int count;
	private ArrayList<User> list;
	private ArrayList<User> users;

	public Message() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public ServerType getType() {
		return type;
	}

	public void setType(ServerType type) {
		this.type = type;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<User> getUserlist() {
		return list;
	}

	public void setUserlist(HashMap<String, User> userList) {
		this.list = new ArrayList<>(userList.values());
	}

	public void setOnlineCount(int count) {
		this.count = count;
	}

	public int getOnlineCount() {
		return this.count;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}
