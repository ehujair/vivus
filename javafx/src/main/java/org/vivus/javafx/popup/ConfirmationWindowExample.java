package org.vivus.javafx.popup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ConfirmationWindowExample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Popup Example");
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		final ConfirmationWindow popup = new ConfirmationWindow(primaryStage, "show confirm");
		popup.setX(300);
		popup.setY(200);

		Button show = new Button("Show");
		show.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup.show();
			}
		});

		Button hide = new Button("Hide");
		hide.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup.hide();
			}
		});

		HBox layout = new HBox(10);
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		layout.getChildren().addAll(show, hide);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}

}
