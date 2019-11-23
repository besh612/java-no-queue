package com.controller;

import com.model.Order;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class ButtonCellController extends ListCell<Order> {
	@FXML
	private Button orderBtn;
	@FXML
	private AnchorPane apCell;

	private FXMLLoader mLLoader;

	@Override
	protected void updateItem(Order order, boolean empty){
		super.updateItem(order, empty);

		if (empty || order == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(
					getClass().getClassLoader().getResource("views/ButtonCell.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			orderBtn.setText(order.getOrderName());

			orderBtn.setOnAction(event -> {
				System.out.println("조리 완료");
			});

			setText(null);
			setGraphic(apCell);
		}
	}
}
