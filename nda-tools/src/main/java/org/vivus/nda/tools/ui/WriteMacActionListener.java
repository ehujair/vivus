package org.vivus.nda.tools.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vivus.nda.tools.cmd.WriteMacCmd;
import org.vivus.nda.tools.context.ICommandExecutor;
import org.vivus.nda.tools.ddmlib.AdbHelper;

public class WriteMacActionListener implements ActionListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(WriteMacActionListener.class);

	ICommandExecutor commandExecutor;
	JScrollPane scrollPane;
	Console console = new Console();

	public WriteMacActionListener(ICommandExecutor commandExecutor, JScrollPane scrollPane) {
		this.commandExecutor = commandExecutor;
		this.scrollPane = scrollPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					console.clear();
					scrollPane.setViewportView(console.getRootComponent());
					commandExecutor.execute(new WriteMacCmd(new AdbHelper(console)));
				} catch (Exception e) {
					LOGGER.error("", e);
					console.handle(ExceptionUtils.getStackTrace(e));
				}
			}
		}).start();
	}

}
