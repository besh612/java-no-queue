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

	private final transient IntegerProperty id;
	private final transient StringProperty name;
	private final transient StringProperty cornerName;
	private final transient IntegerProperty price;
	private final transient IntegerProperty cornerNum;
	private final transient StringProperty menuDetail;

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

	public String getFoodName() {
		return name.get();
	}

	public int getPrice() {
		return price.get();
	}

	public String getMenuDetail() {
		return menuDetail.get();
	}


}
