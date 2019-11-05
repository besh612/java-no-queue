package com.noqueue.pos.controller;

import com.noqueue.pos.PosMain;
import java.net.URL;
import java.util.ResourceBundle;
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

	private PosMain posMain;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("is called");
		try {
			setGridLabel();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}