package org.vivus.adb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import org.vivus.adb.util.FxmlUtil;

public class MainController {
	@FXML
	BorderPane mainPane;

	@FXML
	void writeMac(ActionEvent event) {
		mainPane.setCenter((Node) FxmlUtil.load(Constants.FXML_MAC));
	}
}
