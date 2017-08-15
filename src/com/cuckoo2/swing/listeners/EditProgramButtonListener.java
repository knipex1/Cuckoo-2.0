package com.cuckoo2.swing.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.cuckoo2.Program;
import com.cuckoo2.ProgramManager;
import com.cuckoo2.swing.CuckooFrame;
import com.cuckoo2.swing.panels.EditProgramOptionPanel;

public class EditProgramButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Program p = CuckooFrame.getInstance().getProgramsPanel().getSelectedProgram();
		EditProgramOptionPanel panel = new EditProgramOptionPanel(p);
		int n = JOptionPane.showConfirmDialog(null, panel, "Edit Program", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (n == JOptionPane.OK_OPTION) {

			p.setName(panel.getNameField().getText());
			p.setIcon(panel.getIconLabel().getIcon());
			CuckooFrame.getInstance().getProgramsPanel().publishTableData();
			ProgramManager.updatePrefs();
		}

	}

}
