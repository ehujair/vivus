/*
		Author : Ahmet DEMIR
		Version 1.0
		Date : May 2011
		Description : Java ADB Explorer allows you to explore your Anroid Phone.
		under License GPL: http://www.gnu.org/copyleft/gpl.html
		-----------------------------------------------------------
		Copyright (C) 2011 Ahmet DEMIR

 		This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */

package adbexplorer.main;

public class ADBExplorer extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L; // Auto-generated
	adbexplorer.util.Log4jInit log4jInit = new adbexplorer.util.Log4jInit();
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ADBExplorer.class);
	
	/* ADB Command */
	private adbexplorer.util.ADBCommand adb;
	private adbexplorer.util.LocalCommand lc;
	
	/* Device select */
	private javax.swing.JLabel labelDevice;
	private javax.swing.JComboBox selectDevice;
	private javax.swing.JButton mountRW;
	
	/* Action button */
	private javax.swing.JButton adbToLocal;
	private javax.swing.JButton deleteAdb;
	private javax.swing.JButton localToAdb;
	private javax.swing.JButton localRename;
	
	/* Execute Command */
	private javax.swing.JLabel labelExecuteCommand;
	private javax.swing.JTextField inputCommand;
	private javax.swing.JButton sendExecuteCommand;
	
	/* Output */
	private javax.swing.JLabel labelOutput;
	private javax.swing.JTextArea textAreaOutput;
	private javax.swing.JScrollPane scrollPaneTexteAreaOutput;
	
	/* Menu */
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenu menuFile;
	private javax.swing.JMenu menuHelp;
	private javax.swing.JMenuItem menuItemFileExit;
	private javax.swing.JMenuItem menuItemHelpAbout;
	private javax.swing.JMenuItem menuItemSave;
	
	/* Local list */
	private javax.swing.JLabel labelLocal;
	private javax.swing.JScrollPane scrollPaneLocalList;
	private javax.swing.DefaultListModel localListModel;
	private javax.swing.JList localList;
	private javax.swing.JButton backLocal;
	private javax.swing.JButton rootLocal;
	private javax.swing.JButton localNewDir;
	private javax.swing.JButton localNewFile;
	private javax.swing.JLabel localPathLabel;
	private javax.swing.JButton localRefresh;
	
	/* Remote list */
	private javax.swing.JLabel labelRemote;
	private javax.swing.JScrollPane scrollPaneRemoteList;
	private javax.swing.DefaultListModel remoteListModel;
	private javax.swing.JList remoteList;
	private javax.swing.JButton rootRemote;
	private javax.swing.JButton backRemote;
	private javax.swing.JButton remoteNewDir;
	private javax.swing.JButton remoteNewFile;
	private javax.swing.JLabel remotePathLabel;
	private javax.swing.JButton remoteRefresh;
	
	/* Working directory */
	private String remoteWorkingDir = "/";
	private String localWorkingDir = "/";
	
	/**
	 * Default Constructor
	 */
	public ADBExplorer() {
		if(canRunProgram()) {
			initVariables();
			initComponents();
			initGroupLayout();
			pack();
		}
		else
			System.exit(EXIT_ON_CLOSE);
	}
	
	/**
	 * Initialize Variables
	 */
	private void initVariables() {
		
		/* Remote list */
		remoteListModel = new javax.swing.DefaultListModel();
		remoteList = new javax.swing.JList(remoteListModel);
		scrollPaneRemoteList = new javax.swing.JScrollPane();
		labelRemote = new javax.swing.JLabel();
		backRemote = new javax.swing.JButton();
		rootRemote = new javax.swing.JButton();
		remotePathLabel = new javax.swing.JLabel();
		remoteRefresh = new javax.swing.JButton();
		remoteNewDir = new javax.swing.JButton();
		remoteNewFile = new javax.swing.JButton();
		
		/* Local list */
		localListModel = new javax.swing.DefaultListModel();
		localList = new javax.swing.JList(localListModel);
		scrollPaneLocalList = new javax.swing.JScrollPane();
		labelLocal = new javax.swing.JLabel();
		backLocal = new javax.swing.JButton();
		rootLocal = new javax.swing.JButton();
		localPathLabel = new javax.swing.JLabel();
		localRefresh = new javax.swing.JButton();
		localNewDir = new javax.swing.JButton();
		localNewFile = new javax.swing.JButton();
		
		/* ADB Command */
		adb = new adbexplorer.util.ADBCommand();
		lc = new adbexplorer.util.LocalCommand();
		
		/* Action button */
		localToAdb = new javax.swing.JButton();
		adbToLocal = new javax.swing.JButton();
		deleteAdb = new javax.swing.JButton();
		localRename = new javax.swing.JButton();
		
		/* Device select */
		selectDevice = new javax.swing.JComboBox();
		labelDevice = new javax.swing.JLabel();
		mountRW = new javax.swing.JButton();
		
		/* Output */
		scrollPaneTexteAreaOutput = new javax.swing.JScrollPane();
		textAreaOutput = new javax.swing.JTextArea();
		labelOutput = new javax.swing.JLabel();
		
		/* Execute command */
		inputCommand = new javax.swing.JTextField();
		labelExecuteCommand = new javax.swing.JLabel();
		sendExecuteCommand = new javax.swing.JButton();
		
		/* Menu */
		menuBar = new javax.swing.JMenuBar();
		menuFile = new javax.swing.JMenu();
		menuItemFileExit = new javax.swing.JMenuItem();
		menuItemSave = new javax.swing.JMenuItem();
		menuHelp = new javax.swing.JMenu();
		menuItemHelpAbout = new javax.swing.JMenuItem();
		
	}

	/**
	 * Initialize component Menu
	 */
	private void initMenu() {
		
		// File
		menuFile.setText("File");
		
		// File -> Exit
		menuItemFileExit.setText("Exit");
		menuItemFileExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
		menuItemFileExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		menuFile.add(menuItemFileExit);
		
		// File -> Save
		menuItemSave.setText("Save Output");
		menuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
		menuItemSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
				int returnVal = fc.showSaveDialog(ADBExplorer.this);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
        	java.io.File file = fc.getSelectedFile();
            try {
            	java.io.BufferedWriter out = new java.io.BufferedWriter(new java.io.FileWriter(file.getAbsolutePath()));
            	out.write(textAreaOutput.getText());
            	out.close();
            	refreshOutput("Saving output to " + file.getAbsolutePath() + "\n");
            }
            catch (Exception e) {
            	refreshOutput(e.toString());
						}
        }
			}
		});
    menuFile.add(menuItemSave);
    
		// ?
		menuHelp.setText("?");
		
		// ? -> About
		menuItemHelpAbout.setText("About");
    menuItemHelpAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
		menuItemHelpAbout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				javax.swing.JFrame frame = new javax.swing.JFrame("About Java ADB Explorer"); 
				frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
				javax.swing.JTextArea textarea = new javax.swing.JTextArea("Version 1.0\nLicence : GNU GPL\n Java ADB Explorer is a simple program who allows you to explore your Android");
				textarea.setPreferredSize(new java.awt.Dimension(540, 200)); 
				frame.getContentPane().add(textarea, java.awt.BorderLayout.CENTER); 
				frame.pack(); 
				frame.setVisible(true);
			}
		});
		menuHelp.add(menuItemHelpAbout);
		
		// Add to menu bar
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		
		// Set bar
		setJMenuBar(menuBar);
	}
	
	/**
	 * Initialize component Device
	 */
	private void initDevice() {
		labelDevice.setText("Device");
		selectDevice.setModel(new javax.swing.DefaultComboBoxModel(adb.getDevices()));
		selectDevice.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				adb.setDevice(selectDevice.getSelectedItem().toString());
				refreshOutput("Selected device "+selectDevice.getSelectedItem());
				remoteWorkingDir = "/";
				refreshRemoteList();
			}
		});
		
		// Initialize ADB Device
		if(selectDevice.getSelectedItem() != null)
			adb.setDevice(selectDevice.getSelectedItem().toString());
		else
			adb.setDevice(selectDevice.getItemAt(0).toString());
	}

	/**
	 * Initialize component Mount System developed
	 */
	private void initMountSystem() {
		mountRW.setText("Mount System R/W");
		mountRW.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				adbexplorer.util.MessageBoxes.ShowMessage("This feature will be developed in futur");
			}
		});
	}

	/**
	 * Initialize component Local
	 */
	private void initLocal() {
		refreshLocalList();
		
		labelLocal.setText("Local");
		rootLocal.setText("/");
		rootLocal.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				localWorkingDir = "/";
				refreshLocalList();
			}
		});
		backLocal.setText("Back");
		backLocal.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				manageBackLocalList();
			}
		});
		localRefresh.setText("Refresh");
		localRefresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				refreshLocalList();
			}
		});
		localNewDir.setText("New dir");
		localNewDir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String input = javax.swing.JOptionPane.showInputDialog(null, "Name ? ");
				if(input != null && !input.isEmpty()) {
					String file = (localWorkingDir + "/" + input).replace("//", "/");
					lc.exec("mkdir "+file);
				}
				refreshLocalList();
			}
		});
		localNewFile.setText("New file");
		localNewFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String input = javax.swing.JOptionPane.showInputDialog(null, "Name ? ");
				if(input != null && !input.isEmpty()) {
					String file = (localWorkingDir + "/" + input).replace("//", "/");
					lc.exec("\"echo > "+file+"\"");
				}
				refreshLocalList();
			}
		});
		localPathLabel.setText("/");
		localList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e){
				if(e.getClickCount() == 2){
					manageLocalList(localList.locationToIndex(e.getPoint()));
				}
			}
		});
		
		localList.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
					manageLocalList(localList.getSelectedIndex());
				}
				if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE) {
					manageBackLocalList();
				}
			}
		});
		scrollPaneLocalList.setViewportView(localList);
	}

	/**
	 * Initialize component Remote
	 */
	private void initRemote() {
refreshRemoteList();
		
		labelRemote.setText("Remote");
		
		rootRemote.setText("/");
		rootRemote.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				remoteWorkingDir = "/";
				refreshRemoteList();
			}
		});
		backRemote.setText("Back");
		backRemote.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				manageBackRemoteList();
			}
		});
		remoteRefresh.setText("Refresh");
		remoteRefresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				refreshRemoteList();
			}
		});
		remoteNewDir.setText("New dir");
		remoteNewDir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String input = javax.swing.JOptionPane.showInputDialog(null, "Name ? ");
				if(input != null && !input.isEmpty()) {
					String file = (remoteWorkingDir + "/" + input).replace("//", "/");
					refreshOutput(adb.exec("mkdir "+file));
				}
				refreshRemoteList();
			}
		});
		remoteNewFile.setText("New file");
		remoteNewFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String input = javax.swing.JOptionPane.showInputDialog(null, "Name ? ");
				if(input != null && !input.isEmpty()) {
					String file = (remoteWorkingDir + "/" + input).replace("//", "/");
					refreshOutput(adb.exec("echo > "+file));
					
				}
				refreshRemoteList();
			}
		});
		remotePathLabel.setText("/");
		remoteList.setSelectedIndex(0);
		remoteList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e){
				if(e.getClickCount() == 2){
					manageRemoteList(remoteList.locationToIndex(e.getPoint()));
				}
			}
		});
		remoteList.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) { // NO COFRIM
					if(remoteList.getSelectedIndex() > 0)
						manageRemoteList(remoteList.getSelectedIndex());
				}
				if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE) {
					manageBackRemoteList();
				}
				if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE ) {
					for(Object obj : remoteList.getSelectedValues()) {
						adbexplorer.util.ListImageText lit = (adbexplorer.util.ListImageText) obj;
						adbexplorer.util.FileType ft = lit.getFile();
						String file = (remoteWorkingDir + "/" + obj).replace("//", "/");
						if(confirm("Delete " + file) == 1) {
							if(ft.getType() == 1)
								refreshOutput(adb.rm(file));
							else if(ft.getType() == 2)
								refreshOutput(adb.rmdir(file));
							else if(ft.getType() == 3) 
								refreshOutput(adb.rm(file));
						}
						refreshRemoteList();
					}
				}
			}
		});
		scrollPaneRemoteList.setViewportView(remoteList);
	}

	/**
	 * Initialize component Action
	 */
	private void initAction() {
		localToAdb.setText(">");
		localToAdb.setToolTipText("Copy from local to remote");
		localToAdb.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				for(Object obj : localList.getSelectedValues()) {
					String file = (localWorkingDir + "/" + obj).replace("//", "/");
					String filedest = (remoteWorkingDir + "/" + obj).replace("//", "/");
					refreshOutput(adb.copyToRemote(file, filedest));
				}
				refreshRemoteList();
			}
		});
		
		adbToLocal.setText("<");
		adbToLocal.setToolTipText("Copy from remote to local");
		adbToLocal.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				for(Object obj : remoteList.getSelectedValues()) {
					String file = (remoteWorkingDir + "/" + obj).replace("//", "/");
					String filedest = (localWorkingDir + "/" + obj).replace("//", "/");
					refreshOutput(adb.copyToLocal(file, filedest));
				}
				refreshLocalList();
			}
		});
		
		deleteAdb.setText("D");
		deleteAdb.setToolTipText("Delete Remote File");
		deleteAdb.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				for(Object obj : remoteList.getSelectedValues()) {
					adbexplorer.util.ListImageText lit = (adbexplorer.util.ListImageText) obj;
					adbexplorer.util.FileType ft = lit.getFile();
					String file = (remoteWorkingDir + "/" + obj).replace("//", "/");
					if(confirm("Delete " + file) == 1) {
						if(ft.getType() == 1)
							refreshOutput(adb.rm(file));
						else if(ft.getType() == 2)
							refreshOutput(adb.rmdir(file));
						else if(ft.getType() == 3) 
							refreshOutput(adb.rm(file));
					}
					refreshRemoteList();
				}
			}
		});
		
		localRename.setText("R");
		localRename.setToolTipText("Rename Remote File");
		localRename.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				for(Object obj : remoteList.getSelectedValues()) {
					String input = javax.swing.JOptionPane.showInputDialog(null, "New name ? ");
					String file = (remoteWorkingDir + "/" + obj).replace("//", "/");
					String fileDest = (remoteWorkingDir + "/" + input).replace("//", "/");
					if(input != null && !input.isEmpty()) {
						refreshOutput(adb.rename(file, fileDest));
					}
					refreshRemoteList();
				}
			}
		});
	}

	/**
	 * Initialize component Execute Command
	 */
	private void initExecuteCommand() {
		labelExecuteCommand.setText("Execute command : adb shell ");
		sendExecuteCommand.setText("Send");
		sendExecuteCommand.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(!inputCommand.getText().isEmpty())
					refreshOutput(adb.exec(inputCommand.getText()));
			}
		});
		
		inputCommand.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
					if(!inputCommand.getText().isEmpty())
						refreshOutput(adb.exec(inputCommand.getText()));
				}
			}
		});
	}

	/**
	 * Initialize component Output
	 */
	private void initOutput() {
		labelOutput.setText("Output");
		textAreaOutput.setColumns(20);
		textAreaOutput.setRows(5);
		scrollPaneTexteAreaOutput.setViewportView(textAreaOutput);
	}
	
	/**
	 * Initialize components
	 */
	private void initComponents() {
		initMenu();
		initDevice();
		initMountSystem();
		initLocal();
		initRemote();
		initAction();
		initExecuteCommand();
		initOutput();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Java ADB Explorer");
	}
	
	/**
	 * Based on auto-generated Netbeans' method, can (must?) be rewrite
	 */
	private void initGroupLayout() {
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(labelLocal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rootLocal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(backLocal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(localRefresh)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(localNewDir)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(localNewFile))
                        .addComponent(scrollPaneLocalList, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                        .addComponent(localPathLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(8, 8, 8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(deleteAdb, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addComponent(adbToLocal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(localToAdb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(localRename, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPaneRemoteList, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(remotePathLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addGap(37, 37, 37))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelRemote)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rootRemote)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(backRemote)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(remoteRefresh)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(remoteNewDir)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(remoteNewFile))))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(labelDevice)
                    .addGap(6, 6, 6)
                    .addComponent(selectDevice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(mountRW))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(labelExecuteCommand)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(inputCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(sendExecuteCommand))
                .addComponent(labelOutput)
                .addComponent(scrollPaneTexteAreaOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(selectDevice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelDevice)
                .addComponent(mountRW))
            .addGap(6, 6, 6)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRemote)
                    .addComponent(rootRemote)
                    .addComponent(backRemote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(remoteRefresh)
                    .addComponent(remoteNewDir)
                    .addComponent(remoteNewFile))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backLocal)
                    .addComponent(rootLocal)
                    .addComponent(labelLocal)
                    .addComponent(localRefresh)
                    .addComponent(localNewDir)
                    .addComponent(localNewFile)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(localPathLabel)
                .addComponent(remotePathLabel))
            .addGap(8, 8, 8)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(localToAdb)
                    .addGap(5, 5, 5)
                    .addComponent(adbToLocal)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(deleteAdb)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(localRename))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(scrollPaneRemoteList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                        .addComponent(scrollPaneLocalList, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelExecuteCommand)
                        .addComponent(inputCommand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sendExecuteCommand))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(labelOutput)
                    .addGap(6, 6, 6)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(scrollPaneTexteAreaOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );
		
	}
	
	/**
	 * Refresh the output with the given message
	 * @param message message to show in the output
	 */
	private void refreshOutput(String message) {
		if(textAreaOutput.getText().isEmpty())
			textAreaOutput.setText(message);
		else
			textAreaOutput.setText((textAreaOutput.getText() + "\n" + message).replace("\n\n", "\n"));
	}
	
	/**
	 * Change the localWorkingDir to back action
	 */
	private void manageBackLocalList() {
		String[] tmp = localWorkingDir.split("/");
		String newlocalWorkingDir = "";
		for(int i=0; i<tmp.length-1; i++) {
			newlocalWorkingDir += tmp[i] + "/";
		}
		if(newlocalWorkingDir.isEmpty()) newlocalWorkingDir = "/";
		localWorkingDir = newlocalWorkingDir;
		refreshLocalList();
	}
	
	/**
	 *  Manage the local list with the given index of the item in the list
	 * @param index item in the list
	 */
	private void manageLocalList(int index) {
		javax.swing.ListModel dlm = localList.getModel();
		Object item = dlm.getElementAt(index);;
		localList.ensureIndexIsVisible(index);
		
		adbexplorer.util.ListImageText lit = (adbexplorer.util.ListImageText) item;
		adbexplorer.util.FileType obj = lit.getFile();
		
		localWorkingDir = obj.getPath();
		
		log.debug("Double clicked on " + obj.getName() + " and goto "+localWorkingDir);
		
		if(obj.getType() > 1) refreshLocalList();
		else if(obj.getType() < 0) refreshOutput("Permission denied !");
		else refreshOutput("Cannot enter in a file !");
	}
	
	/**
	 * Refresh the localList with the localWorkingDir
	 */
	public void refreshLocalList() { 
		
		log.debug("Refreshed in "+localWorkingDir);
		adbexplorer.util.ImageList imageList = new adbexplorer.util.ImageList(lc, localWorkingDir);
		
		if(imageList.lc.showDirectoryContent(localWorkingDir) != null) localListModel.removeAllElements();
		else refreshOutput("Permission denied or don't exist");
		
		for(int i=0; i<imageList.listModel.getSize(); i++) {
			localListModel.addElement(imageList.listModel.getElementAt(i));
		}
		//remoteList = new javax.swing.JList(remoteListModel); USELESS
		localList.setCellRenderer(new adbexplorer.util.ImageRenderer());
		
		localPathLabel.setText(localWorkingDir);
		localPathLabel.setForeground(java.awt.Color.BLUE);
	}
	
	/**
	 * Change the remoteWorkingDir to back action
	 */
	private void manageBackRemoteList() {
		String[] tmp = remoteWorkingDir.split("/");
		String newRemoteWorkingDir = "";
		for(int i=0; i<tmp.length-1; i++) {
			newRemoteWorkingDir += tmp[i] + "/";
		}
		if(newRemoteWorkingDir.isEmpty()) newRemoteWorkingDir = "/";
		remoteWorkingDir = newRemoteWorkingDir;
		refreshRemoteList();
	}
	
	/**
	 *  Manage the remote list with the given index of the item in the list
	 * @param index item in the list
	 */
	private void manageRemoteList(int index) {
		javax.swing.ListModel dlm = remoteList.getModel();
		Object item = dlm.getElementAt(index);;
		remoteList.ensureIndexIsVisible(index);
		
		adbexplorer.util.ListImageText lit = (adbexplorer.util.ListImageText) item;
		adbexplorer.util.FileType obj = lit.getFile();
		
		remoteWorkingDir = obj.getPath();
		
		log.debug("Double clicked on " + obj.getName() + " and goto "+remoteWorkingDir);
		
		if(obj.getType() > 1) refreshRemoteList();
		else if(obj.getType() < 0) refreshOutput("Permission denied !");
		else refreshOutput("Cannot enter in a file !");
	}
	
	/**
	 * Refresh the remoteList with the remoteWorkingDir
	 */
	public void refreshRemoteList() { 
		
		log.debug("Refreshed in "+remoteWorkingDir);
		adbexplorer.util.ImageList imageList = new adbexplorer.util.ImageList(adb, remoteWorkingDir);
		
		remoteListModel.removeAllElements();
		
		for(int i=0; i<imageList.listModel.getSize(); i++) {
			remoteListModel.addElement(imageList.listModel.getElementAt(i));
		}
		//remoteList = new javax.swing.JList(remoteListModel); USELESS
		remoteList.setCellRenderer(new adbexplorer.util.ImageRenderer());
		remotePathLabel.setText(remoteWorkingDir);
		remotePathLabel.setForeground(java.awt.Color.BLUE);
		
	}
	
	/**
	 * Test if all conditions are respected to run the program
	 * @return true if ok
	 */
	private boolean canRunProgram() {
		
		adbexplorer.util.TestBeforeStart tbs = new adbexplorer.util.TestBeforeStart();
		
		// ADB Link
		if(!tbs.linkToUsrBin()) {
			adbexplorer.util.MessageBoxes.ShowError("Java ADB Explorer - ADB Error", "Please make a symbolic link to your ADB.\nExample sudo ln -s /home/johndoe/android-sdk-linux_x86/platform-tools/adb /usr/bin/adb");
			log.error("Please make a symbolic link to your ADB.\nExample sudo ln -s /home/johndoe/android-sdk-linux_x86/platform-tools/adb /usr/bin/adb");
			return false;
		}
		
		// ADB
		int errorCode = tbs.adbRun();
		if(errorCode < 1) { 
			adbexplorer.util.MessageBoxes.ShowError("Java ADB Explorer - ADB Error", tbs.errorToString(errorCode));
			log.error(tbs.errorToString(errorCode));
			return false;
		}
		
		//DEVICES
		if(tbs.connectedDevices() <= 0) {
			adbexplorer.util.MessageBoxes.ShowError("Java ADB Explorer - Device Error", "No device connected");
			log.error("No device connected");
			return false;
		}
		return true;
	}
	
	private int confirm(String message) {
		javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);
		int response = javax.swing.JOptionPane.showConfirmDialog(null, message, "Confirm",
				javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
		if (response == javax.swing.JOptionPane.NO_OPTION) {
			return 0;
		} else if (response == javax.swing.JOptionPane.YES_OPTION) {
			return 1;
		} else if (response == javax.swing.JOptionPane.CLOSED_OPTION) {
			return 0;
		}
		return 0;
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				new ADBExplorer().setVisible(true);
			}
		});
	}
	
	
	
}
