package org.vivus.swing.first;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class FirstApplication {
	
	
	public FirstApplication() {
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		Container contentPane = frame.getContentPane();
		ScrollPane scrollPane = new ScrollPane();
//		contentPane.add(scrollPane);
		JLabel label = new JLabel("dsfaaasdff ffffffffffffffffff GAAAAAAAAAAAAAADAG ADSGAADFASDGAD SGWEGQ WBQWEFGQEWGQWEGQWEGQWEGQWEGQWEfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
		label.setSize(350, 200);
		label.setText(
		"<html><p>This is a long paragraph and I want it to break on its own.  " + 
	    "This is a long paragraph and I want it to break on its own.  " +
	    "This is a long paragraph and I want it to break on its own.  " +
	    "This is a long paragraph and I want it to break on its own.</p></html>");
//		label.setText("test");
//		scrollPane.add(label);
		String myString = 
		    "<html><p>This is a long paragraph and I want it to break on its own.  " + 
		    "This is a long paragraph and I want it to break on its own.  " +
		    "This is a long paragraph and I want it to break on its own.  " +
		    "This is a long paragraph and I want it to break on its own.</p></html>";
		contentPane.add(new JLabel(myString));
//		scrollPane.add(new JLabel(myString));
//		contentPane.add(new Label("test label"));
		//Center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		frame.setVisible(true);
	}

	//Main method
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new FirstApplication();
	}
}
