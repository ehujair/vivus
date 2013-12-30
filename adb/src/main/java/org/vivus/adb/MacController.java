package org.vivus.adb;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MacController implements AdbHandler {
	MacHelper macHelper;

	@FXML
	Label console;

	@FXML
	void initialize() {
		if (macHelper == null) {
			macHelper = new MacHelper(this);
		}
		clear();
		macHelper.writeMac();
	}

	private void newLine() {
		console.setText(console.getText() + System.lineSeparator());
	}
	
	public void clear() {
		console.setText("");
	}

	@Override
	public void handleMessage(String msg) {
		console.setText(console.getText() + msg);
		newLine();
	}

	@Override
	public void handleInput(String input) {
		console.setText(console.getText() + input);
		newLine();
	}

	@Override
	public void handleError(String error) {
		console.setText(console.getText() + error);
		newLine();
	}

	@Override
	public void handleEnded(int exitValue) {
		if (exitValue == 0) {
			console.setText(console.getText() + "写入MAC地址成功");
		} else {
			console.setText(console.getText() + "写入MAC地址失败");
		}
		newLine();
	}
}
