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
	private int id;
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

	public void setUserlist(HashMap<String, User> userList) {
		this.list = new ArrayList<>(userList.values());
	}

	public void setOnlineCount(int count) {
		this.count = count;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
