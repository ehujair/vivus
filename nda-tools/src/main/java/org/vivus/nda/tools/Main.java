package org.vivus.nda.tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.vivus.nda.tools.config.Configuration;
import org.vivus.nda.tools.context.ICommandExecutor;
import org.vivus.nda.tools.impl.Engine;
import org.vivus.nda.tools.ui.WriteMacActionListener;
import org.vivus.nda.tools.util.ResourceUtil;

public class Main extends WindowAdapter implements WindowListener {
	protected IEngine engine;
	protected ICommandExecutor commandExecutor;

	protected JFrame frame;

	public Main() {
		// create all components and add them
		frame = new JFrame("NDA Tools");
		try {
			frame.setIconImage(ImageIO.read(ResourceUtil.getResourceAsStream("img/nda-tools.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension((int) (screenSize.width / 2),
				(int) (screenSize.height / 2));
		int x = (int) (frameSize.width / 2);
		int y = (int) (frameSize.height / 2);
		frame.setBounds(x, y, frameSize.width, frameSize.height);

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("运行");
		JMenuItem menuItem = new JMenuItem("写MAC");
		menu.add(menuItem);
		menubar.add(menu);
		frame.setJMenuBar(menubar);

		frame.getContentPane().setLayout(new BorderLayout());
		JPanel inputPanel = new JPanel();
		JLabel serialLabel = new JLabel("序列号: ");
		inputPanel.add(serialLabel);
		JTextField serialField = new JTextField(10);
		inputPanel.add(serialField);
		JLabel macLabel = new JLabel("MAC: ");
		inputPanel.add(macLabel);
		JTextField macField = new JTextField(10);
		inputPanel.add(macField);
		JButton writeMacButton = new JButton("写MAC");
		inputPanel.add(writeMacButton);
		frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		frame.setVisible(true);
		frame.addWindowListener(this);
		menuItem.addActionListener(new WriteMacActionListener(getCommandExecutor(), scrollPane));
	}

	public IEngine getEngine() {
		if (engine == null) {
			engine = new Configuration().buildEngine();
		}
		return engine;
	}

	public ICommandExecutor getCommandExecutor() {
		if (commandExecutor == null) {
			commandExecutor = ((Engine) getEngine()).getConfiguration().getCommandExecutor();
		}
		return commandExecutor;
	}

	public synchronized void windowClosed(WindowEvent evt) {
		this.notifyAll();
		System.exit(0);
	}

	public synchronized void windowClosing(WindowEvent evt) {
		frame.setVisible(false);
		frame.dispose();
	}

	public static void main(String[] arg) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}
}
