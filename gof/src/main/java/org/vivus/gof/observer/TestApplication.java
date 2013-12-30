package org.vivus.gof.observer;

/**
 * <p>Title: ExecCommander</p>
 * <p>Description: This project serves as a launchpad for development and tests of a component to make use of process execution easier</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: N/A</p>
 * @author Doron Barak
 * @version 1.0
 */
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.UIManager;

public class TestApplication {
	private boolean packFrame = false;
	//Construct the application
	public TestApplication() {
		IntervalWindowRF frame = new IntervalWindowRF();
		//Validate frames that have preset sizes
		//Pack frames that have useful preferred size info, e.g. from their layout
		if (packFrame) {
			frame.pack();
		} else {
			frame.validate();
		}
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		new TestApplication();
	}
}