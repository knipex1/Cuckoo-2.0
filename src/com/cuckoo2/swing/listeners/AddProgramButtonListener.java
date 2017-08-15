package com.cuckoo2.swing.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.cuckoo2.IconLoader;
import com.cuckoo2.Program;
import com.cuckoo2.ProgramManager;
import com.cuckoo2.support.ProgramFileChooser;
import com.cuckoo2.swing.CuckooFrame;
import com.cuckoo2.swing.panels.AddProgramOptionPanel;

public class AddProgramButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// oldVersion()
		ProgramFileChooser gc = new ProgramFileChooser();
		if (gc.showDialog(null, "Add Selected Program") == ProgramFileChooser.APPROVE_OPTION) {
			try {
				gc.savePathAsLast(gc.getCurrentDirectory());
				File f = gc.getSelectedFile();
				String path = f.getPath();
				String name = path.substring(path.lastIndexOf('\\') + 1);
				Icon icon;
				icon = IconLoader.loadIconFromPath(path);
				Program p = new Program(name, path, icon);
				ProgramManager.addProgram(p);
				CuckooFrame.getInstance().getProgramsPanel().publishTableData();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		}
	}

	@Deprecated
	@SuppressWarnings("unused")
	private void oldVersion() {
		AddProgramOptionPanel panel = new AddProgramOptionPanel();
		ProgramFileChooser gc = new ProgramFileChooser();
		if (gc.showDialog(null, "Add Selected Program") == ProgramFileChooser.APPROVE_OPTION) {
			gc.savePathAsLast(gc.getCurrentDirectory());
			File f = gc.getSelectedFile();
			panel.getPathField().setText(f.getPath());
			String name = panel.getPathField().getText()
					.substring(panel.getPathField().getText().lastIndexOf('\\') + 1);
			try {
				panel.getIconLabel().setIcon(IconLoader.loadIconFromPath(f.getPath()));
				panel.getNameField().setText(name);
				panel.showFields(true);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} else
			return;
		int n = JOptionPane.showConfirmDialog(null, panel, "Add Program", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (n == JOptionPane.OK_OPTION) {
			String name = panel.getNameField().getText();
			Icon icon = panel.getIconLabel().getIcon();
			String path = panel.getPathField().getText();
			Program p = new Program(name, path, icon);
			ProgramManager.addProgram(p);
			CuckooFrame.getInstance().getProgramsPanel().publishTableData();
		}
	}
}
