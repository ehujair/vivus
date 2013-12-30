package org.vivus.javafx.example.fxml;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLExample extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("FXML Example");

		Parent root = FXMLLoader.load(getClass().getResource("fxml_example.fxml"),
				ResourceBundle.getBundle("org.vivus.javafx.example.fxml.fxml_example"));
		Scene scene = new Scene(root, 226, 264);
		scene.getStylesheets().add("org/vivus/javafx/example/fxml/fxmlstylesheet.css");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}