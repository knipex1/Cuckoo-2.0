package com.cuckoo2.swing;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

public class CenteredFontedLabel extends JLabel {
	private static final long serialVersionUID = -7107223256994407511L;
	private static final Font MAIN_FONT = new Font("Arial", Font.PLAIN, 15);

	public CenteredFontedLabel(String text, Icon icon) {
		super(text, icon, JLabel.CENTER);
		setFont(MAIN_FONT);
	}

	public CenteredFontedLabel(String text) {
		super(text, JLabel.CENTER);
		setFont(MAIN_FONT);
	}
}
