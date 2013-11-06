package org.vivus.nda.tools.swing;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
import org.jdesktop.xswingx.PromptSupport;

public class PromptText extends JFrame {
	private static final String PROMPT_TEXT = "prompt text";

	private static final long serialVersionUID = 4178412426313677411L;

	private JTextField textField, textField2;

	public PromptText() {
		createAndShowUI();
	}

	private void createAndShowUI() {
		setTitle("Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createComponents();
		addComponentsToContentPane();
//		addListeners();

		pack();
		setVisible(true);
	}

	private void addComponentsToContentPane() {
		getContentPane().setLayout(new GridLayout(2, 1));

		getContentPane().add(textField);
		getContentPane().add(textField2);
	}

	private void createComponents() {
		textField = new JTextField(10);
//		textField.setToolTipText("tool tip text");
		PromptSupport.setPrompt(PROMPT_TEXT, textField);
		textField2 = new JTextField("Click here to lose focus of above textField");
	}

	private void addListeners() {
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {
				JTextField source = (JTextField) fe.getSource();
				if (PROMPT_TEXT.equals(source.getText())) {
					source.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent fe) {
				JTextField source = (JTextField) fe.getSource();
				if (StringUtils.isEmpty(source.getText())) {
					source.setText(PROMPT_TEXT);
				}
//				if (textField.getText().length() >= 1) {
//					JOptionPane.showMessageDialog(null, "You entered valid data");
//					textField.setText("");
//				} else {
//					JOptionPane.showMessageDialog(null, "You entered invalid data");
//					textField.grabFocus();// make the textField in foucs again
//				}
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new PromptText();
			}
		});
	}
}