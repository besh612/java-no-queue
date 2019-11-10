package com.noqueue.pos.server.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

	private String name;
	private int order;
	private int count;
	private ArrayList<User> list;
	private ArrayList<User> users;

	private Status status;
}