package com.noqueue.pos.model;

import javafx.beans.property.*;
import org.json.simple.JSONObject;

/**
 * 음식 Model Class
 *
 * @author 배성훈
 */

public class Food {

	private final StringProperty name;
	private final StringProperty cornerName;
	private final IntegerProperty price;
	private final IntegerProperty cornerNum;

	/**
	 * Constructor with some initial data.
	 *
	 * param name : 음식 이름
	 * param cornerName : 음식 이름
	 * param price : 음식 가격
	 * param cornerNum : 식당
	 * 번호(1:E스퀘어, 2:감성코어)
	 */
	public Food(JSONObject o) {
		this.name = new SimpleStringProperty(o.get("name").toString());
		this.cornerName = new SimpleStringProperty(o.get("corner_name").toString());
		this.price = new SimpleIntegerProperty(Integer.parseInt(o.get("price").toString()));
		this.cornerNum = new SimpleIntegerProperty(
			Integer.parseInt(o.get("corner_num").toString()));
	}

	public String getFoodName() {
		return name.get();
	}

	public void setFoodName(String name) {
		this.name.set(name);
	}

	public StringProperty FoodNameProperty() {
		return name;
	}

	public String getCornerName() {
		return name.get();
	}

	public void setCornerName(String name) {
		this.cornerName.set(name);
	}

	public StringProperty CornerNameProperty() {
		return cornerName;
	}

	public int getPrice() {
		return price.get();
	}

	public void setPrice(Integer price) {
		this.price.set(price);
	}

	public IntegerProperty FoodPriceProperty() {
		return price;
	}

	public int getCorner() {
		return cornerNum.get();
	}

	public void setCorner(Integer corner) {
		this.cornerNum.set(corner);
	}

	public IntegerProperty CornerProperty() {
		return cornerNum;
	}
}
