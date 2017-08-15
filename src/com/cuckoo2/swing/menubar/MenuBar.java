package com.cuckoo2.swing.menubar;

import javax.swing.Box;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = -3474567597743530318L;
	private StandardProgramsMenu programs = new StandardProgramsMenu();
	private OptionsMenu options = new OptionsMenu();

	public MenuBar() {
		super();

		add(Box.createHorizontalStrut(15));
		add(programs);
		add(options);
		updateMenusAvailability();
	}

	public void updateMenusAvailability() {
		programs.checkAvailability();
		options.checkAvailability();
	}
}