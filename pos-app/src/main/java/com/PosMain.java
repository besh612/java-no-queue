package com;

import com.utils.JsonParser;
import java.util.ArrayList;
import java.util.Objects;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.*;

import com.model.Food;

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
		Parent root = FXMLLoader.load(
			Objects.requireNonNull(getClass().getClassLoader().getResource("views/PosView.fxml")));
		primaryStage.setTitle("NoQueue POS");
		primaryStage.setScene(new Scene(root, 900, 600));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> Platform.exit());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
