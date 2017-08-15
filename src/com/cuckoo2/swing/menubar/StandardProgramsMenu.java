package com.cuckoo2.swing.menubar;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.cuckoo2.IconLoader;
import com.cuckoo2.Icons;
import com.cuckoo2.ProgramManager;
import com.cuckoo2.SampleProgram;
import com.cuckoo2.swing.CuckooFrame;

public class StandardProgramsMenu extends JMenu {
	private static final long serialVersionUID = -6689286714714050440L;
	private ArrayList<SampleProgramItem> samples;

	public StandardProgramsMenu() {
		setIcon(Icons.MENU_STANDARD_PROGRAM);
		setToolTipText("Standard Programs");
		samples = new ArrayList<SampleProgramItem>();
		for (SampleProgram sp : SampleProgram.class.getEnumConstants())
			samples.add(new SampleProgramItem(sp));
		for (SampleProgramItem item : samples)
			add(item);
	}

	public void checkAvailability() {
		for (SampleProgramItem item : samples)
			item.setEnabled(!ProgramManager.hasSample(item.getSampleProgram()));
	}
}

class SampleProgramItem extends JMenuItem {
	private static final long serialVersionUID = 5537871889667363201L;
	private SampleProgram sample;

	public SampleProgramItem(SampleProgram sample) {
		super(sample.getName(), IconLoader.resizeIcon(sample.getIcon(), 16, 16));
		this.sample = sample;
		// setFont(CuckooFrame.MAIN_FONT);
		addActionListener(e -> {
			ProgramManager.addProgram(sample.toProgram());
			CuckooFrame.getInstance().getProgramsPanel().publishTableData();
			this.setEnabled(false);
		});
	}

	public SampleProgram getSampleProgram() {
		return sample;
	}
}