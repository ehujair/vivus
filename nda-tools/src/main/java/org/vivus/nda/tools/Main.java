package org.vivus.nda.tools;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vivus.nda.tools.config.Configuration;
import org.vivus.nda.tools.ui.MenuActionListener;
import org.vivus.nda.tools.ui.WriteMac;
import org.vivus.nda.tools.util.ResourceUtil;

public class Main extends WindowAdapter implements WindowListener {
	static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	private static Main instance;

	protected IEngine engine;
	protected JFrame frame;

	private Main() {
	}

	private JFrame createFrame() {
		JFrame frame = new JFrame("Nr510 Tools");
		setIcon(frame);
		calculatePosition(frame);
		addMenuBar(frame);
		frame.addWindowListener(this);
		return frame;
	}

	private void calculatePosition(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension((int) (screenSize.width / 3),
				(int) (screenSize.height / 3));
		int x = (int) ((screenSize.width - frameSize.width) / 2);
		int y = (int) ((screenSize.height - frameSize.height) / 2);
		frame.setBounds(x, y, frameSize.width, frameSize.height);
	}

	private void setIcon(JFrame frame) {
		try {
			frame.setIconImage(ImageIO.read(ResourceUtil.getResourceAsStream("img/nda-tools.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized Main getInstance() {
		if (instance == null) {
			instance = new Main();
		}
		return instance;
	}

	public IEngine getEngine() {
		return engine;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void show() {
		frame.setVisible(true);
	}

	private void addMenuBar(JFrame frame) {
		JMenuBar menubar = new JMenuBar();
		addMenu(menubar, "运行",
				Arrays.asList(createMenuItem("写MAC", new MenuActionListener(new WriteMac()))));
		frame.setJMenuBar(menubar);
	}

	private void addMenu(JMenuBar menubar, String menuName, List<JMenuItem> menuItems) {
		JMenu menu = new JMenu(menuName);
		for (JMenuItem menuItem : menuItems) {
			menu.add(menuItem);
		}
		menubar.add(menu);
	}

	private JMenuItem createMenuItem(String menuItemName, ActionListener actionListener) {
		JMenuItem menuItem = new JMenuItem(menuItemName);
		menuItem.addActionListener(actionListener);
		return menuItem;
	}

	public synchronized void windowClosed(WindowEvent evt) {
		this.notifyAll();
		System.exit(0);
	}

	public synchronized void windowClosing(WindowEvent evt) {
		frame.setVisible(false);
		frame.dispose();
	}

	protected void initialize() {
		engine = new Configuration().buildEngine();
		frame = createFrame();
		getFrame().getJMenuBar().getMenu(0).getItem(0).doClick();
	}

	public static void main(String[] arg) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main instance = Main.getInstance();
				instance.initialize();
				instance.show();
			}
		});
	}
}
