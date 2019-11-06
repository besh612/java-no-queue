package com.noqueue.user;

import java.util.Objects;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserMain extends Application {

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(
			Objects.requireNonNull(getClass().getClassLoader().getResource("views/UserView.fxml")));
		primaryStage.setTitle("NoQueue APP");
		primaryStage.setScene(new Scene(root, 300, 600));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
