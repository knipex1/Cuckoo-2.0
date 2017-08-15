package com.cuckoo2.swing.panels;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.cuckoo2.Program;
import com.cuckoo2.swing.CuckooFrame;
import com.cuckoo2.swing.ProgramListTable;
import com.cuckoo2.swing.ProgramListTableModel;

public class MyProgramsPanel extends JPanel {

	private static final long serialVersionUID = 8703194636120403638L;
	private ProgramListTable table;

	public MyProgramsPanel() {
		setBorder(BorderFactory.createTitledBorder("My Programs"));
		this.setLayout(new GridLayout(1, 1));
		table = new ProgramListTable();
		add(new JScrollPane(table));
	}

	public int getRowForProgram(Program p) {
		for (int i = 0; i < table.getRowCount(); i++) {
			if (p == ((ProgramListTableModel) table.getModel()).getProgramAt(i))
				return i;
		}
		return -1;
	}

	public int getSelectedRow() {
		return table.getSelectedRow();
	}

	public void selectRow(int row) {
		if (row == -1) {
			table.clearSelection();
			return;
		}
		table.setRowSelectionInterval(row, row);
	}

	/*
	 * Remove the old scrollpane and add a new one.
	 */
	public void publishTableData() {
		((ProgramListTableModel) table.getModel()).fireTableDataChanged();
		CuckooFrame.getInstance().getTray().update();
	}

	public void publishScheduleData(int row) {
		((ProgramListTableModel) table.getModel()).fireTableCellUpdated(row, 2);
	}

	public Program getSelectedProgram() {
		int row = table.getSelectedRow();
		if (row < 0)
			return null;
		ProgramListTableModel m = (ProgramListTableModel) table.getModel();
		return m.getProgramAt(row);
	}
}
