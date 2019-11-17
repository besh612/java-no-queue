package com.model;

import java.io.Serializable;
import javafx.beans.property.*;
import org.json.simple.JSONObject;

/**
 * 음식 Model Class
 *
 * @author 배성훈
 */

public class Food implements Serializable {

	private final IntegerProperty id;
	private final StringProperty name;
	private final StringProperty cornerName;
	private final IntegerProperty price;
	private final IntegerProperty cornerNum;
	private final StringProperty menuDetail;

	/**
	 * Constructor with some initial data.
	 *
	 * param name : 음식 이름 param cornerName : 코 이름 param price : 음식 가격 param cornerNum : 식당
	 * 번호(1:E스퀘어, 2:감성코어)
	 */
	public Food(JSONObject o) {
		this.id = new SimpleIntegerProperty(Integer.parseInt(o.get("id").toString()));
		this.name = new SimpleStringProperty(o.get("name").toString());
		this.cornerName = new SimpleStringProperty(o.get("corner_name").toString());
		this.price = new SimpleIntegerProperty(Integer.parseInt(o.get("price").toString()));
		this.cornerNum = new SimpleIntegerProperty(
			Integer.parseInt(o.get("corner_num").toString()));
		this.menuDetail = new SimpleStringProperty(o.get("detail").toString());
	}

	public int getId() {
		return id.get();
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public void setId(int id) {
		this.id.set(id);
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
		return cornerName.get();
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

	public String getMenuDetail() {
		return menuDetail.get();
	}

	public StringProperty menuDetailProperty() {
		return menuDetail;
	}

	public void setMenuDetail(String menuDetail) {
		this.menuDetail.set(menuDetail);
	}

}
