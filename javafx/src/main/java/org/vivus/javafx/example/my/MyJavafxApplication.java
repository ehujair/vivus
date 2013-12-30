package org.vivus.javafx.example.my;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MyJavafxApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("primary stage");
		primaryStage.setScene(new Scene(new Group(), 800, 600, Color.BLACK));
		primaryStage.show();
		
		// it will create a new window
		Stage stage = new Stage();
		stage.setTitle("new stage");
		stage.setScene(new Scene(new Group(), 800, 600, Color.WHITE));
		stage.show();
//		stage.close();
//		primaryStage.close(); // close window
//		Platform.exit();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
