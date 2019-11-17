package com.controller;

import com.model.Food;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.utils.Listener;
import com.utils.StageStore;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class WaitingController implements Initializable {

	@FXML
	private Label lbFoodName;

	@FXML
	private Label lbTime;

	@FXML
	private Label lbOrderNum;

	@FXML
	private AnchorPane apWaitView;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnGetFood;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnBack.setOnAction(event -> handleBackPage());
	}

	public void initData(Food food, String userName) {
		lbTime.setText("주문시간 " +
			LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		lbFoodName.setText(food.getFoodName());
		lbOrderNum.setText(userName);
		btnGetFood.setOnAction(event -> handleBackPage());
	}

	private void handleBackPage() {
		AnchorPane root = (AnchorPane) StageStore.stage.getScene().getRoot();
		root.getChildren().remove(apWaitView);
	}
}
