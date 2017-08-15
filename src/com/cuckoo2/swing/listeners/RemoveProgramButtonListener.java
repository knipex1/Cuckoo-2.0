package com.cuckoo2.swing.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.cuckoo2.Program;
import com.cuckoo2.ProgramManager;
import com.cuckoo2.swing.CuckooFrame;

public class RemoveProgramButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Program p = CuckooFrame.getInstance().getProgramsPanel().getSelectedProgram();
		if (p == ProgramManager.getPrograms().get(0) && p.getTimesClosed() > 0) {
			int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the most used program?",
					"Remove Program", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (n == JOptionPane.CANCEL_OPTION || n == JOptionPane.CLOSED_OPTION)
				return;
		}
		ProgramManager.removeProgram(p);
		// Update menu bar in case of sample program
		if (p.isSample()) {
			CuckooFrame.getInstance().getBarMenu().updateMenusAvailability();
		}
		CuckooFrame.getInstance().getProgramsPanel().publishTableData();
		if (p.getScheduledTime() != 0) {
			ProgramManager.schedules.get(p).interrupt();
			ProgramManager.schedules.remove(p);
		}
	}

}
