package org.vivus.javafx.example.my;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import org.vivus.javafx.util.PrintUtil;

public class MyApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Circle circ = new Circle(40, 40, 30);
		Group root = new Group(circ);
		Scene scene = new Scene(root, 400, 300);

		primaryStage.setTitle("My JavaFX Application");
		primaryStage.setScene(scene);
		primaryStage.show();
		PrintUtil.print(root, primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
