package com.controller;

import com.network.model.Message;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class ButtonCellController extends ListCell<Message> {
	@FXML
	private Button orderBtn;
	@FXML
	private AnchorPane apCell;

	private FXMLLoader mLLoader;
	private PosController controller;

	ButtonCellController(PosController controller){
		this.controller = controller;
	}

	@Override
	protected void updateItem(Message msg, boolean empty){
		super.updateItem(msg, empty);

		if (empty || msg == null) {
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
			orderBtn.setText(msg.getName());

			orderBtn.setOnAction(event -> {
				try {
					controller.handleOrderData(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("조리 완료: " + msg.getName() + "| 주문 : " + msg.getId());
			});

			setText(null);
			setGraphic(apCell);
		}
	}
}
