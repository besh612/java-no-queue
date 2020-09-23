package com.utils;

import javafx.stage.Stage;

public class StageStore {
	public static Stage stage = null;

	private StageStore() {
		throw new IllegalStateException("Utility class");
	}
}