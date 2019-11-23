package com.model;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Order implements Serializable {
	private final StringProperty orderName;
	private final IntegerProperty userNum;

	public Order(StringProperty orderName, IntegerProperty userNum) {
		this.orderName = orderName;
		this.userNum = userNum;
	}

	public String getOrderName() {
		return orderName.get();
	}

	public StringProperty orderNameProperty() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName.set(orderName);
	}

	public int getUserNum() {
		return userNum.get();
	}

	public IntegerProperty userNumProperty() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum.set(userNum);
	}
}
