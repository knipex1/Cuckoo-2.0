package com.cuckoo2.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.SystemTray;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.cuckoo2.ProgramManager;
import com.cuckoo2.swing.listeners.FrameExitListener;
import com.cuckoo2.swing.listeners.FrameKeyListener;
import com.cuckoo2.swing.menubar.MenuBar;
import com.cuckoo2.swing.panels.ActionsPanel;
import com.cuckoo2.swing.panels.MyProgramsPanel;
import com.cuckoo2.swing.panels.ShutdownSchedulePanel;
import com.cuckoo2.swing.tray.TrayIcon;
import com.cuckoo2.swing.tray.TrayStateListener;

public class CuckooFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int MIN_WIDTH = 600, MIN_HEIGHT = 500;
	private static final String FRAME_NAME = "Cuckoo 2.0";
	private ActionsPanel actionsPanel;
	private MyProgramsPanel programsPanel;
	private ShutdownSchedulePanel ssPanel;
	private MenuBar menuBar;
	private TrayIcon tray;
	public static final Preferences PREFERENCES = Preferences.userNodeForPackage(CuckooFrame.class);
	public static final Font MAIN_FONT = new Font("Arial", Font.PLAIN, 15);

	public CuckooFrame() {
		super(FRAME_NAME);
		ProgramManager.Initialize();
		updateLook();
		// Set minimum dimension
		setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		// Adjust frame dimension
		setSize(PREFERENCES.getInt("FRAMEWIDTH", MIN_WIDTH), PREFERENCES.getInt("FRAMEHEIGHT", MIN_HEIGHT));
		// Centerize the frame.
		setLocationRelativeTo(null);
		// Close app on exit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridBagLayout());
		setupPanels();
		addWindowListener(new FrameExitListener());
		menuBar = new MenuBar();
		setJMenuBar(menuBar);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new FrameKeyListener());
		if (PREFERENCES.getBoolean("ENABLETRAY", true)) {
			addTray();
		}
		setVisible(true);
	}

	public void addTray() {
		try {
			if (SystemTray.isSupported()) {
				SystemTray traySystem = SystemTray.getSystemTray();
				this.tray = new TrayIcon();
				traySystem.add(this.tray);
			}
		} catch (Exception e) {
			System.out.println("Error adding the tray.");
		}
		addWindowStateListener(new TrayStateListener());
	}

	public void disableTray() {
		if (SystemTray.isSupported()) {
			SystemTray traySystem = SystemTray.getSystemTray();
			traySystem.remove(tray);
		}
	}

	public void trayMessage(String Msg, boolean isWarning) {
		if (!CuckooFrame.PREFERENCES.getBoolean("ENABLETRAY", true))
			return;
		if (!CuckooFrame.PREFERENCES.getBoolean("TRAYNOTIFICATIONS", true))
			return; // Notifications closed
		tray.displayMessage("Cuckoo 2.0", Msg, isWarning ? TrayIcon.MessageType.WARNING : TrayIcon.MessageType.INFO);
	}

	public TrayIcon getTray() {
		return tray;
	}

	public MyProgramsPanel getProgramsPanel() {
		return programsPanel;
	}

	public ShutdownSchedulePanel getScheduleShutdownPanel() {
		return ssPanel;
	}

	public ActionsPanel getActionsPanel() {
		return actionsPanel;
	}

	public MenuBar getBarMenu() {
		return menuBar;
	}

	private void setupPanels() {
		programsPanel = new MyProgramsPanel();
		actionsPanel = new ActionsPanel();
		ssPanel = new ShutdownSchedulePanel();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 10);
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 25;
		add(programsPanel, c);
		c.gridy = 1;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 1;
		add(actionsPanel, c);
		c.gridy = 2;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 1;
		add(ssPanel, c);
	}

	private void updateLook() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			setIconImage(ImageIO.read(getClass().getResourceAsStream("/com/cuckoo2/swing/icons/favicon.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@link FrameExitListener}
	 */
	public void saveFrameSize() {
		CuckooFrame.PREFERENCES.putInt("FRAMEWIDTH", this.getWidth());
		CuckooFrame.PREFERENCES.putInt("FRAMEHEIGHT", this.getHeight());
	}

	public static CuckooFrame getInstance() {
		return SingletonHolder._instance;
	}

	private static class SingletonHolder {
		protected static final CuckooFrame _instance = new CuckooFrame();
	}
}
