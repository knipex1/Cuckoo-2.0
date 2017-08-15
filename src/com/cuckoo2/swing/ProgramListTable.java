package com.cuckoo2.swing;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.cuckoo2.Icons;
import com.cuckoo2.Program;

public class ProgramListTable extends JTable {

	private static final long serialVersionUID = -5513719847433349552L;
	private static final int[] COLUMN_WIDTHS = { 5, 180, 105, 60, 25 };

	public ProgramListTable() {
		setModel(new ProgramListTableModel());
		this.setRowHeight(40);
		setUpColumns();
		setUpHeaders();
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addSelectionListener();
	}

	@Override
	public String getToolTipText(MouseEvent event) {
		Point p = event.getPoint();
		int row = rowAtPoint(p);
		if (row >= 0 && row < 10) {
			return "Press CTRL + F" + (row + 1) + " to schedule this program.";
		}
		return "No shortcut availale.";
	}

	private void addSelectionListener() {
		getSelectionModel().addListSelectionListener(e -> {
			int row = getSelectedRow();
			// When nothing is selected, hide buttons
			CuckooFrame.getInstance().getActionsPanel().updateButtonAvailability(row);
			if (row == -1) {
				CuckooFrame.getInstance().getScheduleShutdownPanel().updateStrartButtonAvailability(false);
				CuckooFrame.getInstance().getScheduleShutdownPanel().updateStopButtonVisibility(false);
				return;
			}
			Program program = ((ProgramListTableModel) getModel()).getProgramAt(row);
			CuckooFrame.getInstance().getScheduleShutdownPanel().updateStrartButtonAvailability(true);
			CuckooFrame.getInstance().getScheduleShutdownPanel()
					.updateStopButtonVisibility(program.getScheduledTime() > 0);
		});

	}

	private void setUpHeaders() {
		Border border = UIManager.getBorder("TableHeader.cellBorder");
		CenteredFontedLabel iconsColumnHeaderLabel = new CenteredFontedLabel("");
		CenteredFontedLabel programNamesColumnHeaderLabel = new CenteredFontedLabel("Program",
				Icons.TABLE_HEADER_PROGRAM);
		CenteredFontedLabel timesClosedHeaderLabel = new CenteredFontedLabel("", Icons.TABLE_HEADER_TIMESCLOSED);
		CenteredFontedLabel scheduledHeaderLabel = new CenteredFontedLabel("Schedule", Icons.TABLE_HEADER_SCHEDULE);
		CenteredFontedLabel runningHeader = new CenteredFontedLabel("Running", Icons.TABLE_HEADER_RUNNING);
		iconsColumnHeaderLabel.setBorder(border);
		programNamesColumnHeaderLabel.setBorder(border);
		timesClosedHeaderLabel.setBorder(border);
		scheduledHeaderLabel.setBorder(border);
		runningHeader.setBorder(border);
		timesClosedHeaderLabel.setToolTipText("Times Shutdowned");
		TableColumnModel model = getColumnModel();
		model.getColumn(0).setHeaderValue(iconsColumnHeaderLabel);
		model.getColumn(1).setHeaderValue(programNamesColumnHeaderLabel);
		model.getColumn(2).setHeaderValue(scheduledHeaderLabel);
		model.getColumn(3).setHeaderValue(runningHeader);
		model.getColumn(4).setHeaderValue(timesClosedHeaderLabel);

	}

	private void setUpColumns() {
		TableColumnModel model = getColumnModel();
		for (int i = 0; i < this.getColumnCount(); i++) {
			model.getColumn(i).setHeaderRenderer(new JLabelRenderer());
			model.getColumn(i).setCellRenderer(new JLabelRenderer());
		}
		for (int i = 0; i < COLUMN_WIDTHS.length; i++) {
			model.getColumn(i).setPreferredWidth(COLUMN_WIDTHS[i]);
		}
		setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		// Disable dragging columns
		getTableHeader().setReorderingAllowed(false);
	}
}

class JLabelRenderer implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JLabel l = (JLabel) value;
		l.setOpaque(true);
		if (isSelected) {
			l.setBackground(table.getSelectionBackground());
		}
		return l;
	}
}
