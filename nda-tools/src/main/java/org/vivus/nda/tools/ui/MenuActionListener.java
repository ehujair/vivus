package org.vivus.nda.tools.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.vivus.nda.tools.Main;

public class MenuActionListener implements ActionListener {
	MenuActionFrame menuActionFrame;

	public MenuActionListener(MenuActionFrame menuActionFrame) {
		this.menuActionFrame = menuActionFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (menuActionFrame instanceof ResetAware) {
			((ResetAware) menuActionFrame).reset();
		}
		JFrame frame = Main.getInstance().getFrame();
		frame.getContentPane().removeAll();
		frame.getContentPane().add(menuActionFrame.getContainer());
		SwingUtilities.updateComponentTreeUI(frame);
	}
}