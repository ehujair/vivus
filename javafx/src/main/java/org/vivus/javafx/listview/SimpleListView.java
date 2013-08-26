package org.vivus.javafx.listview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class SimpleListView extends Application {

	private void init(Stage primaryStage) {
		Group root = new Group();
		primaryStage.setScene(new Scene(root));
		final ListView<Object> listView = new ListView<Object>();
		listView.setItems(FXCollections.observableArrayList(new Object(), new Object(),
				new Object(), new Object()));
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		root.getChildren().add(listView);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}