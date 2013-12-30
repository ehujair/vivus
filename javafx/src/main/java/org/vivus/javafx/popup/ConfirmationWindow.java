package org.vivus.javafx.popup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConfirmationWindow extends Stage {
	Stage owner;
	BorderPane root;
	static boolean postStatus;

	public ConfirmationWindow(Stage owner, String title) {
		root = new BorderPane();
		this.owner = owner;
		initModality(Modality.WINDOW_MODAL);
		initOwner(owner);
		initStyle(StageStyle.UTILITY);
		setTitle(title);
		setContents();
	}

	public void setContents() {

		Scene scene = new Scene(root, 250, 100);
		setScene(scene);

		Group groupInDialog = new Group();
		groupInDialog.getChildren().add(new Label("Do you really want to Finish this test ?"));
		root.setCenter(groupInDialog);

		Button yes = new Button("Yes");
		yes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				postStatus = true;
				hide();
//				close(); // Close the pop up. Transfer control to
							// PostTransaction.java and execute the
							// PostTransaction() method.

			}
		});

		Button no = new Button("No");
		no.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				postStatus = false;
				hide();
//				close(); // Close the pop up only
			}

		});

		HBox buttonPane = new HBox();
		buttonPane.setSpacing(10);
		buttonPane.getChildren().addAll(yes, no);
		root.setBottom(buttonPane);
//		show();
	}

	public static boolean confirmTranactionPosting(Stage owner, String title) {

		new ConfirmationWindow(owner, title);

		return postStatus;
	}

}