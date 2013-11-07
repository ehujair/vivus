package org.vivus.nda.tools.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vivus.nda.tools.IMacAddressService;
import org.vivus.nda.tools.Main;
import org.vivus.nda.tools.ddmlib.AdbHelper;
import org.vivus.nda.tools.ddmlib.IMessageHandler;
import org.vivus.nda.tools.entity.MacAddress;

public class WriteMac implements IMessageHandler, MenuActionFrame, ResetAware {
	static final Logger LOGGER = LoggerFactory.getLogger(WriteMac.class);
	public static final String PRODUCT_CODE = "产品编号";

	private AdbHelper adbHelper = new AdbHelper(this);
	private IMacAddressService macAddressService;

	private JPanel panel = new JPanel();
	private JTextField serialField;
	private JTextField macField;
	private JButton writeMacButton;
	private JTextArea textArea;

	public WriteMac() {
		panel.setLayout(new BorderLayout());
		buildInputPanel();
		buildConsole();
		initialize();
	}

	public IMacAddressService getMacAddressService() {
		if (macAddressService == null) {
			macAddressService = Main.getInstance().getEngine().getMacAddressService();
		}
		return macAddressService;
	}

	private void buildInputPanel() {
		JPanel inputPanel = new JPanel();
		JLabel serialLabel = new JLabel(PRODUCT_CODE + ": ");
		inputPanel.add(serialLabel);
		serialField = new JTextField(10);
		inputPanel.add(serialField);
		JLabel macLabel = new JLabel("MAC: ");
		inputPanel.add(macLabel);
		macField = new JTextField(10);
		macField.setEditable(false);
		inputPanel.add(macField);
		writeMacButton = new JButton("写MAC");
		inputPanel.add(writeMacButton);
		panel.add(inputPanel, BorderLayout.NORTH);
	}

	private void buildConsole() {
		JScrollPane scrollPane = new JScrollPane();
		textArea = new JTextArea();
		int margin = 5;
		textArea.setMargin(new Insets(margin, margin, margin, margin));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		panel.add(scrollPane, BorderLayout.CENTER);
	}

	private void initialize() {
		registerListener();
		reset();
	}

	private void registerListener() {
		ActionListener submitAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submitForm();
			}
		};
		writeMacButton.addActionListener(submitAction);
		serialField.addActionListener(submitAction);
	}

	public void reset() {
		serialField.setText("");
		macField.setText(Integer.toString(getMacAddressService().getMac()));
		serialField.grabFocus();
	}

	protected void submitForm() {
		if (StringUtils.isBlank(serialField.getText())) {
			clear();
			handle(PRODUCT_CODE + "不能为空，请扫描产品背后的二维码或手工输入");
			serialField.grabFocus();
			return;
		}
		clear();
		handle(PRODUCT_CODE + ": " + serialField.getText());
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				setEnable(false);
				try {
					MacAddress macAddress = new MacAddress();
					macAddress.setMac(Integer.parseInt(macField.getText().trim()));
					macAddress.setCode(serialField.getText().trim());
					macAddress.setWriteTime(new Date());
					getMacAddressService().writeMac(adbHelper, macAddress);
				} catch (Exception e) {
					LOGGER.error("", e);
				}
				setEnable(true);
				reset();
			}
		});
		thread.start();
	}

	protected void setEnable(boolean enable) {
		serialField.setEnabled(enable);
		macField.setEnabled(enable);
		writeMacButton.setEnabled(enable);
	}

	public void clear() {
		textArea.setText("");
	}

	@Override
	public void handle(String message) {
		textArea.setText(textArea.getText() + message + System.lineSeparator());
	}

	@Override
	public Container getContainer() {
		return panel;
	}
}