package com.views;

import com.controller.DetailController;
import com.model.Food;
import com.utils.StageStore;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomCell extends ListCell<Food> {

	@FXML
	private Label lbFoodName;
	@FXML
	private Label lbFoodDetail;
	@FXML
	private Label lbFoodPrice;
	@FXML
	private AnchorPane apCell;
	@FXML
	private Button purchaseBtn;

	private final String userName;
	private FXMLLoader mLLoader;
	private Stage stage = StageStore.stage;

	public CustomCell(String orderName) {
		this.userName = orderName;
	}

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
			lbFoodName.setText(food.getFoodName());
			lbFoodPrice.setText("• " + food.getPrice() + "원");
			lbFoodDetail.setText(food.getMenuDetail());

			purchaseBtn.setOnAction(event -> {
				try {
					handleMovePage(food);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			setText(null);
			setGraphic(apCell);
		}
	}

	private void handleMovePage(Food food) throws IOException {
		FXMLLoader loader = new FXMLLoader(Objects
			.requireNonNull(getClass().getClassLoader().getResource("views/DetailView.fxml")));
		Parent detailView = loader.load();
		DetailController detailController = loader.getController();
		detailController.initData(food, userName);

		AnchorPane root = (AnchorPane) stage.getScene().getRoot();
		root.getChildren().add(detailView);
	}
}
