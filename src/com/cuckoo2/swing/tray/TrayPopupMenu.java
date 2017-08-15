package com.cuckoo2.swing.tray;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import com.cuckoo2.Program;
import com.cuckoo2.ProgramManager;
import com.cuckoo2.swing.CuckooFrame;

public class TrayPopupMenu extends PopupMenu {
	private static final long serialVersionUID = -691242845529891728L;

	public TrayPopupMenu() {
		for (Program p : ProgramManager.getPrograms()) {

			MenuItem prog = new MenuItem(p.getScheduledTime() == 0 ? p.getName() : p.getName() + " (SCHEDULED)");
			prog.addActionListener(l -> {
				int row = CuckooFrame.getInstance().getProgramsPanel().getSelectedRow();
				int prog_row = CuckooFrame.getInstance().getProgramsPanel().getRowForProgram(p);
				CuckooFrame.getInstance().getProgramsPanel().selectRow(prog_row);
				CuckooFrame.getInstance().getScheduleShutdownPanel().scheduleProgram(p.getScheduledTime() == 0);
				CuckooFrame.getInstance().getProgramsPanel().selectRow(row);
				CuckooFrame.getInstance().getTray().update();
			});
			add(prog);
		}
		MenuItem mi = new MenuItem("Exit");
		mi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CuckooFrame.getInstance()
						.dispatchEvent(new WindowEvent(CuckooFrame.getInstance(), WindowEvent.WINDOW_CLOSING));
			}
		});
		add(mi);
	}
}
