package org.vivus.javafx.popup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PopupControlExample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final PopupControl popup = new PopupControl();
		Group root = new Group();
		root.getChildren().add(new Circle(25, 25, 50, Color.AQUAMARINE));
		popup.getScene().setRoot(root);

		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		Button show = new Button("Show");
		show.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup.show(primaryStage, primaryStage.getX() + 100, primaryStage.getY() + 100);
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
