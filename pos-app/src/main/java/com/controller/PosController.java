package com.controller;

import com.PosMain;
import com.network.Server;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PosController implements Initializable {

	@FXML
	private Button btnSetting;
	@FXML
	private GridPane menuGrid;
	@FXML
	private Label orderCounter;

	private PosMain posMain;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("pos system initialize");
		try {
			setGridLabel();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Runnable server = new Server();
		Thread x = new Thread(server);
		x.start();
	}

	private void setGridLabel() throws Exception {
		posMain = new PosMain();
		for (int i = 0; i < posMain.datas.size(); i++) {
			Label label = new Label(posMain.datas.get(i).getFoodName());
			menuGrid.add(label, i, 0);
		}
	}

	public void setPosMain(PosMain posMain) {
		this.posMain = posMain;
	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		System.out.println("press!! : " + event.toString());
	}

	public void setOrderLabelCount(String orderCount) {
		Platform.runLater(() -> orderCounter.setText(orderCount));
	}
}