package org.vivus.javafx2.style;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StyleTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		String selectedStyle = "-fx-border-color : green ;-fx-border-style: solid ;-fx-border-width : 2.0px;-fx-border-radius: 7.0px ;";
		root.setStyle(selectedStyle);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

}
