package com.noqueue.pos;

import java.util.ArrayList;
import java.util.Objects;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
		Parent root = FXMLLoader.load(
			Objects.requireNonNull(getClass().getClassLoader().getResource("views/PosView.fxml")));
		primaryStage.setTitle("NoQueue POS");
//		primaryStage.getIcons()
//			.add(new Image("https://img.icons8.com/dotty/80/000000/pos-terminal.png"));
		primaryStage.setScene(new Scene(root, 900, 600));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> Platform.exit());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
