package com.noqueue.pos;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.*;

import com.noqueue.pos.model.Food;
import com.noqueue.pos.utils.JsonParser;

public class PosMain extends Application {

	public ArrayList<Food> datas = new ArrayList<>();

	public PosMain() throws Exception {
		JSONArray array = null;
		array = JsonParser.getDataList();
		for (Object o : array) {
			datas.add(new Food((JSONObject) o));
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/PosView.fxml"));
		primaryStage.setTitle("NoQueue POS");
		primaryStage.setScene(new Scene(root, 900, 600));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
