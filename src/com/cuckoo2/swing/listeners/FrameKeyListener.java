package com.cuckoo2.swing.listeners;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import com.cuckoo2.ProgramManager;
import com.cuckoo2.swing.CuckooFrame;

public class FrameKeyListener implements KeyEventDispatcher {

	private Set<Integer> keys = new HashSet<Integer>();

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		int code = e.getKeyCode();
		if (keys.contains(code) && e.getID() == KeyEvent.KEY_RELEASED) {
			keys.remove(code);
		} else if (isFButton(code) && e.getID() == KeyEvent.KEY_PRESSED) {
			keys.add(code);
		}
		if (keys.size() == 2)
			for (int k : keys) {
				if (k != KeyEvent.VK_CONTROL) {
					int fbutton = traceWhichFButton(k);
					if (ProgramManager.getPrograms().size() > fbutton) {
						int row = CuckooFrame.getInstance().getProgramsPanel().getSelectedRow();
						CuckooFrame.getInstance().getProgramsPanel().selectRow(fbutton);
						boolean isScheduled = CuckooFrame.getInstance().getProgramsPanel().getSelectedProgram()
								.getScheduledTime() != 0;
						CuckooFrame.getInstance().getScheduleShutdownPanel().scheduleProgram(!isScheduled);
						CuckooFrame.getInstance().getProgramsPanel().selectRow(row);
					}
				}
			}
		return false;
	}

	private int traceWhichFButton(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_F1:
			return 0;
		case KeyEvent.VK_F2:
			return 1;
		case KeyEvent.VK_F3:
			return 2;
		case KeyEvent.VK_F4:
			return 3;
		case KeyEvent.VK_F5:
			return 4;
		case KeyEvent.VK_F6:
			return 5;
		case KeyEvent.VK_F7:
			return 6;
		case KeyEvent.VK_F8:
			return 7;
		case KeyEvent.VK_F9:
			return 8;
		case KeyEvent.VK_F10:
			return 9;
		}
		return -1;
	}

	private boolean isFButton(int keyCode) {
		return keyCode == KeyEvent.VK_F1 || keyCode == KeyEvent.VK_F2 || keyCode == KeyEvent.VK_F3
				|| keyCode == KeyEvent.VK_F4 || keyCode == KeyEvent.VK_F5 || keyCode == KeyEvent.VK_F6
				|| keyCode == KeyEvent.VK_F7 || keyCode == KeyEvent.VK_F8 || keyCode == KeyEvent.VK_F9
				|| keyCode == KeyEvent.VK_F10 || keyCode == KeyEvent.VK_CONTROL;
	}
}
