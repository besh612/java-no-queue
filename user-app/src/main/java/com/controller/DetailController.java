package com.controller;

import com.model.Food;
import com.network.Listener;
import com.utils.StageStore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

public class DetailController implements Initializable {

	@FXML
	private Label lbFoodName;

	@FXML
	private Label lbPrice;

	@FXML
	private Label lbFoodDetail;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnOrder;

	@FXML
	private AnchorPane apDetailView;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnBack.setOnAction(event -> handleBackPage());
	}

	public void initData(Food food) {
		lbFoodName.setText(food.getFoodName());
		lbPrice.setText(food.getPrice() + "원");
		lbFoodDetail.setText(food.getMenuDetail());
		lbFoodName.setTextAlignment(TextAlignment.JUSTIFY);
		lbPrice.setAlignment(Pos.BASELINE_RIGHT);

		btnOrder.setOnAction(event -> {
			try {
				handlePurchaseButton(food);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void handleBackPage() {
		AnchorPane root = (AnchorPane) StageStore.stage.getScene().getRoot();
		root.getChildren().remove(apDetailView);
	}

	private void handlePurchaseButton(Food food) throws IOException {
		Listener.send(food.getFoodName());
	}
}
