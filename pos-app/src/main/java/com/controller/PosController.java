package com.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.media.*;
import org.json.simple.*;

import com.model.Food;
import com.network.model.Message;
import com.utils.JsonParser;
import com.utils.Listener;
import com.network.Server;

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

	private final ArrayList<ObservableList<Message>> orderLists = new ArrayList<>();

	public PosController() {
		for (int i = 0; i < 5; i++) {
			ObservableList<Message> ov = FXCollections.observableArrayList();
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
		Runnable server = new Server();
		Listener listener = new Listener("127.0.0.1", "ADMIN", this);
		Thread serverThread = new Thread(server);
		Thread listenerThread = new Thread(listener);
		serverThread.start();
		listenerThread.start();
	}

	private void setGridLabel() throws Exception {
		List<Food> data = getFoodData();
		for (int i = 0; i < data.size(); i++) {
			Label label = new Label(data.get(i).getFoodName());
			menuGrid.add(label, i, 0);
		}
	}

	List<Food> getFoodData() throws Exception {
		JSONArray array = null;
		List<Food> foodList = new ArrayList<>();
		array = JsonParser.getDataList();
		for (Object o : array) {
			foodList.add(new Food((JSONObject) o));
		}
		return foodList;
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
				Objects.requireNonNull(
					getClass().getClassLoader().getResource("sounds/notification.wav")).toString());
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