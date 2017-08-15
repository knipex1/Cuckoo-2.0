package com.cuckoo2.swing;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;

public class Button extends JButton {
	private static final long serialVersionUID = 8947466405868583808L;
	private static final int BUTTONS_WIDTH = 120, BUTTONS_HEIGHT = 30;

	public Button(String text, Icon icon) {
		super(text, icon);
		setFont(CuckooFrame.MAIN_FONT);
		setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
	}
}
