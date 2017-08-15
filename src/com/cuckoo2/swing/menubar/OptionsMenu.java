package com.cuckoo2.swing.menubar;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;

import com.cuckoo2.Icons;
import com.cuckoo2.swing.CuckooFrame;

public class OptionsMenu extends JMenu {
	private static final long serialVersionUID = 4670617107616555053L;
	private JCheckBoxMenuItem enableTray;
	private JCheckBoxMenuItem minimizeOnTray;
	private JCheckBoxMenuItem trayNotifications;

	public OptionsMenu() {
		super();
		setIcon(Icons.MENU_SETTINGS);
		setToolTipText("Settings");
		enableTray = new JCheckBoxMenuItem("Enable Tray");
		enableTray.setSelected(CuckooFrame.PREFERENCES.getBoolean("ENABLETRAY", true));
		enableTray.addActionListener(e -> {
			CuckooFrame.PREFERENCES.putBoolean("ENABLETRAY", enableTray.isSelected());
			checkAvailability();
			if (enableTray.isSelected())
				CuckooFrame.getInstance().addTray();
			else
				CuckooFrame.getInstance().disableTray();
		});
		minimizeOnTray = new JCheckBoxMenuItem("Enable Tray Minimize");
		minimizeOnTray.setEnabled(enableTray.isSelected());
		minimizeOnTray.setSelected(CuckooFrame.PREFERENCES.getBoolean("TRAYMINIMIZE", true));
		minimizeOnTray.addActionListener(e -> {
			CuckooFrame.PREFERENCES.putBoolean("TRAYMINIMIZE", minimizeOnTray.isSelected());
		});
		trayNotifications = new JCheckBoxMenuItem("Enable Tray Notifications");
		trayNotifications.setEnabled(enableTray.isSelected());
		trayNotifications.setSelected(CuckooFrame.PREFERENCES.getBoolean("TRAYNOTIFICATIONS", true));
		trayNotifications.addActionListener(e -> {
			CuckooFrame.PREFERENCES.putBoolean("TRAYNOTIFICATIONS", trayNotifications.isSelected());
		});

		JCheckBoxMenuItem playStartSound = new JCheckBoxMenuItem("Enable Start Sound");
		playStartSound.setSelected(CuckooFrame.PREFERENCES.getBoolean("STARTSOUND", true));
		playStartSound.addActionListener(e -> {
			CuckooFrame.PREFERENCES.putBoolean("STARTSOUND", playStartSound.isSelected());
		});
		JCheckBoxMenuItem playShutdownSound = new JCheckBoxMenuItem("Enable Shutdown Sound");
		playShutdownSound.setToolTipText("Play notification sound one minute before a program shutdowns.");
		playShutdownSound.setSelected(CuckooFrame.PREFERENCES.getBoolean("SHUTDOWNSOUND", true));
		playShutdownSound.addActionListener(e -> {
			CuckooFrame.PREFERENCES.putBoolean("SHUTDOWNSOUND", playShutdownSound.isSelected());
		});
		add(new JSeparator());
		add(enableTray);
		add(minimizeOnTray);
		add(trayNotifications);
		add(new JSeparator());
		add(playStartSound);
		add(playShutdownSound);
	}

	public void checkAvailability() {
		trayNotifications.setEnabled(enableTray.isSelected());
		minimizeOnTray.setEnabled(enableTray.isSelected());

	}
}
