package com.cuckoo2.swing.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.cuckoo2.ProgramManager;
import com.cuckoo2.swing.CuckooFrame;

public class FrameExitListener extends WindowAdapter {
	@Override
	public void windowClosing(WindowEvent e) {
		final CuckooFrame f = (CuckooFrame) e.getComponent();
		boolean somethingIsScheduled = !ProgramManager.schedules.isEmpty();
		if (somethingIsScheduled) {
			int n = JOptionPane.showConfirmDialog(null,
					"A program is scheduled to be shutdown are you sure you want to exit?", "Exit Confirmation",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (n != JOptionPane.OK_OPTION) {
				f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				return;
			}
		}
		f.saveFrameSize();
		System.exit(0);
	}
}