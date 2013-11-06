package org.vivus.nda.tools.ui;

import java.awt.Component;
import java.awt.Insets;

import javax.swing.JTextArea;

import org.vivus.nda.tools.ddmlib.IMessageHandler;

public class Console implements IMessageHandler {
	private JTextArea textArea;

	public Console() {
		textArea = new JTextArea();
		int margin = 5;
		textArea.setMargin(new Insets(margin, margin, margin, margin));
		textArea.setEditable(false);
	}

	public Component getRootComponent() {
		return textArea;
	}

	public void clear() {
		textArea.setText("");
	}

	@Override
	public void handle(String message) {
		textArea.setText(textArea.getText() + message + System.lineSeparator());
	}
}