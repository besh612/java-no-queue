package com.controller;

import com.PosMain;
import com.model.Food;
import com.network.Listener;
import com.network.model.Message;
import com.views.CustomCell;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class UserController implements Initializable {

	@FXML
	private ListView listView;

	private ObservableList<Food> foodObservableList;

	private PosMain posMain;

	private String orderName = null;

	public UserController() throws Exception {
		posMain = new PosMain();
		foodObservableList = FXCollections.observableArrayList();
		foodObservableList.addAll(posMain.datas);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		orderName = randomId();
		System.out.println("user app initialize");
		listView.setItems(foodObservableList);
		listView.setCellFactory(foodListView -> new CustomCell(orderName));

		Listener listener = new Listener("127.0.0.1", orderName, this);
		Thread x = new Thread(listener);
		x.start();
	}

	public synchronized void getData(Message msg) {
		System.out.println("수신받은 데이터 USER : " + msg.getMsg());
		if (msg.getMsg().equals(orderName)) {
			handleAlertSound();
		}
	}

	private void handleAlertSound() {
		try {
			Media hit = new Media(
				getClass().getClassLoader().getResource("sounds/notification.wav").toString());
			MediaPlayer mediaPlayer = new MediaPlayer(hit);
			mediaPlayer.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String randomId() {
		Random rand = new Random();
		return String.format("%04d", rand.nextInt(10000));
	}
}
