package com.cuckoo2.swing.panels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.cuckoo2.Icons;
import com.cuckoo2.swing.Button;
import com.cuckoo2.swing.listeners.AddProgramButtonListener;
import com.cuckoo2.swing.listeners.EditProgramButtonListener;
import com.cuckoo2.swing.listeners.RemoveProgramButtonListener;

public class ActionsPanel extends JPanel {

	private static final long serialVersionUID = 1438130240776788615L;

	private Button removeProgramButton;
	private Button editProgramButton;

	public ActionsPanel() {
		setBorder(BorderFactory.createTitledBorder("Actions"));
		Button addProgramButton = new Button("Add", Icons.BUTTON_ADD_PROGRAM);
		addProgramButton.addActionListener(new AddProgramButtonListener());
		editProgramButton = new Button("Edit", Icons.BUTTON_EDIT_PROGRAM);
		editProgramButton.addActionListener(new EditProgramButtonListener());
		removeProgramButton = new Button("Remove", Icons.BUTTON_REMOVE_PROGRAM);
		removeProgramButton.addActionListener(new RemoveProgramButtonListener());
		updateButtonAvailability(-1); // Initial state - disables
		add(addProgramButton);
		add(editProgramButton);
		add(removeProgramButton);
	}

	public void updateButtonAvailability(int rowIndex) {
		editProgramButton.setEnabled(rowIndex > -1);
		removeProgramButton.setEnabled(rowIndex > -1);
	}
}
