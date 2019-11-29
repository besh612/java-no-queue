package com.controller;

import com.PosMain;
import com.network.model.Message;
import com.utils.Listener;
import com.network.Server;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PosController implements Initializable {

	@FXML
	private Button btnSetting;
	@FXML
	private Button callTestBtn;
	@FXML
	private GridPane menuGrid;
	@FXML
	private List<ListView> orderList;
	@FXML
	private Label orderCounter;
	@FXML
	private Label userCount;

	private ArrayList<ObservableList<Message>> orderLists = new ArrayList<>();
	private ObservableList<Message> ov;

	private PosMain posMain;
	private Runnable server;


	public PosController() {
		for (int i = 0; i < 5; i++) {
			ov = FXCollections.observableArrayList();
			orderLists.add(ov);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		int cnt = 0;

		System.out.println("pos system initialize");
		try {
			setGridLabel();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (ListView lv : orderList) {
			lv.setItems(orderLists.get(cnt++));
			lv.setCellFactory(orderListView -> new ButtonCellController(this));
		}
		orderCounter.setText(String.valueOf(0));
		server = new Server();
		Listener listener = new Listener("127.0.0.1", "ADMIN", this);
		Thread x = new Thread(server);
		Thread y = new Thread(listener);
		x.start();
		y.start();
	}

	private void setGridLabel() throws Exception {
		posMain = new PosMain();
		for (int i = 0; i < posMain.datas.size(); i++) {
			Label label = new Label(posMain.datas.get(i).getFoodName());
			menuGrid.add(label, i, 0);
		}
	}

	public synchronized void getOrderData(Message msg) {
		orderLists.get(msg.getId() - 1).add(msg);
		setOrderLabelCount(Integer.parseInt(orderCounter.getText()) + 1);
		handleAlertSound();
	}

	synchronized void handleOrderData(Message msg) throws IOException {
		Listener.send(msg.getName());
		setOrderLabelCount(Integer.parseInt(orderCounter.getText()) - 1);
		orderLists.get(msg.getId() - 1).remove(msg);
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


	@FXML
	private void handleButtonAction(ActionEvent event) {
		System.out.println("press!! : " + event.toString());
	}

	private void setOrderLabelCount(int orderCount) {
		Platform.runLater(() -> orderCounter.setText(String.valueOf(orderCount)));
	}
}