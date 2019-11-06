package com.noqueue.user.controller;

import com.noqueue.pos.PosMain;
import com.noqueue.pos.model.Food;
import com.noqueue.user.views.CustomCell;
import java.net.URL;
import java.util.Map;
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

		for (int i = 0; i < posMain.datas.size(); i++) {
			System.out.println("add : " + posMain.datas.get(i).getFoodName());
			foodObservableList.add(posMain.datas.get(i));
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("user app initialize");
		listView.setItems(foodObservableList);
		listView.setCellFactory(foodListView -> new CustomCell());
	}

}
