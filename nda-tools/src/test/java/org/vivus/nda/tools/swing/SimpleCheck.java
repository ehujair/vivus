package org.vivus.nda.tools.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleCheck implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		System.out.println("do something");
	}

	public static void main(String[] args) {
		SimpleCheck test = new SimpleCheck();
		JCheckBox checkBox = new JCheckBox("check box");
		checkBox.addActionListener(test);
		JPanel north = new JPanel();
		north.add(checkBox);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(north, "First");
		f.setSize(200, 100);
		f.setLocation(200, 200);
		f.setVisible(true);
	}
}