package com.views;

import com.model.Food;
import com.network.Listener;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

public class CustomCell extends ListCell<Food> {

	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private GridPane gridPane;
	@FXML
	private Button purchaseBtn;
	@FXML
	private Button moveBtn;

	private FXMLLoader mLLoader;

	@Override
	protected void updateItem(Food food, boolean empty) {
		super.updateItem(food, empty);

		if (empty || food == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(
					getClass().getClassLoader().getResource("views/CustomCell.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			label1.setText(food.getFoodName());
			label2.setText(food.getPrice() + "원");
			label3.setText(food.getCornerName());

			purchaseBtn.setOnAction(event -> {
				try {
					handlePurchaseButton(food);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			moveBtn.setOnAction(event -> {
				handleMovePage();
			});

			setText(null);
			setGraphic(gridPane);
		}
	}

	private void handlePurchaseButton(Food food) throws IOException {
		System.out.println("이름: " + food.getFoodName());
		Listener.send(food.getFoodName());
	}

	private void handleMovePage() {
		System.out.println("변라!");
	}
}
