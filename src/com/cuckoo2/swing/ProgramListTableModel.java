package com.cuckoo2.swing;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

import com.cuckoo2.Icons;
import com.cuckoo2.Program;
import com.cuckoo2.ProgramManager;

public class ProgramListTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 4159513095237217665L;
	private List<Program> programs = ProgramManager.getPrograms();

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return programs.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		Program program = programs.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return new CenteredFontedLabel("", program.getIcon());
		case 1:
			return new CenteredFontedLabel(program.getName());
		case 2:
			long scheduledTime = program.getScheduledTime();
			long curTime = System.currentTimeMillis();
			long timeLeft = scheduledTime - curTime;
			if (timeLeft < 0)
				program.setScheduledTime(0);
			if (program.getScheduledTime() == 0)
				return new CenteredFontedLabel("", Icons.TABLE_PROGRAM_NOT_SCHEDULED);
			long hours = TimeUnit.MILLISECONDS.toHours(timeLeft);
			timeLeft = timeLeft - hours * 60 * 60 * 1000;
			long mins = TimeUnit.MILLISECONDS.toMinutes(timeLeft);
			timeLeft = timeLeft - mins * 60 * 1000;
			long secs = TimeUnit.MILLISECONDS.toSeconds(timeLeft);
			if (hours > 0)
				return new CenteredFontedLabel(
						"<html><center>" + hours + " Hour(s) " + mins + " Min(s)<br>" + secs + " Sec(s)");
			if (mins > 0)
				return new CenteredFontedLabel("<html><center>" + mins + " Min(s) " + secs + " Sec(s)");
			return new CenteredFontedLabel("<html><center>" + secs + " Sec(s)");
		case 3:
			Icon icon = program.isRunning() ? Icons.TABLE_PROGRAM_RUNNING : Icons.TABLE_PROGRAM_NOT_RUNNING;
			return new CenteredFontedLabel("", icon);
		case 4:
			return new CenteredFontedLabel(program.getTimesClosed() + "");

		}

		return value;

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Program.class;
	}

	public Program getProgramAt(int row) {
		return programs.get(row);
	}

}
