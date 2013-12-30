package org.vivus.javafx.example.fxml;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class FXMLExampleController {
	@FXML
	private Label buttonStatusText;
	@FXML
	private BorderPane resultPane;

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		buttonStatusText.setText("Submit button pressed");
	}

	@FXML
	protected void handlePasswordFieldAction(ActionEvent event) {
		buttonStatusText.setText("Enter key pressed");
	}

	@FXML
	protected void handleUserNameFieldAction(ActionEvent event) throws IOException {
		resultPane = FXMLLoader.load(getClass().getResource("result.fxml"),
				ResourceBundle.getBundle("org.vivus.javafx.example.fxml.fxml_example"));
	}
}