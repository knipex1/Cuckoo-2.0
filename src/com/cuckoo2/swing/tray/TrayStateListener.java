package com.cuckoo2.swing.tray;

import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

import com.cuckoo2.swing.CuckooFrame;

public class TrayStateListener implements WindowStateListener {

	@Override
	public void windowStateChanged(WindowEvent e) {
		if (!CuckooFrame.PREFERENCES.getBoolean("TRAYMINIMIZE", true))
			return;
		if (e.getNewState() == JFrame.ICONIFIED) {
			CuckooFrame.getInstance().setVisible(false);
			CuckooFrame.getInstance().trayMessage("Running on Tray!", false);
		}

	}

}
