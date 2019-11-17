package com.controller;

import com.PosMain;
import com.model.Food;
import com.network.Listener;
import com.views.CustomCell;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class UserController implements Initializable {

	@FXML
	private ListView listView;

	private ObservableList<Food> foodObservableList;

	private PosMain posMain;

	public UserController() throws Exception {
		posMain = new PosMain();
		foodObservableList = FXCollections.observableArrayList();
		foodObservableList.addAll(posMain.datas);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		String orderName = randomId();
		System.out.println("user app initialize");
		listView.setItems(foodObservableList);
		listView.setCellFactory(foodListView -> new CustomCell(orderName));

		Listener listener = new Listener("127.0.0.1", orderName);
		Thread x = new Thread(listener);
		x.start();
	}

	private String randomId() {
		Random rand = new Random();
		return String.format("%04d", rand.nextInt(10000));
	}
}
