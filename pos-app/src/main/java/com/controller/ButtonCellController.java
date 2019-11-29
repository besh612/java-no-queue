package com.controller;

import com.network.model.Message;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class ButtonCellController extends ListCell<Message> {

	@FXML
	private Button orderBtn;
	@FXML
	private AnchorPane apCell;

	private FXMLLoader mLLoader;
	private PosController controller;

	ButtonCellController(PosController controller) {
		this.controller = controller;
	}

	@Override
	protected void updateItem(Message msg, boolean empty) {
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
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("조리완료 메세지 전송");
				alert.setHeaderText("조리완성 메세지를 전송하겠습니까?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					try {
						controller.handleOrderData(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("조리 완료: " + msg.getName() + "| 주문 : " + msg.getId());
				}
			});

			setText(null);
			setGraphic(apCell);
		}
	}
}
