package com.cuckoo2.swing.tray;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.cuckoo2.Program;
import com.cuckoo2.ProgramManager;
import com.cuckoo2.swing.CuckooFrame;

public class TrayIcon extends java.awt.TrayIcon {
	private static final String TRAY_NAME = "Cuckoo 2.0";

	public TrayIcon() throws IOException {
		super(ImageIO.read(TrayIcon.class.getResourceAsStream("/com/cuckoo2/swing/icons/favicon.png")), TRAY_NAME,
				new TrayPopupMenu());
		setImageAutoSize(true);
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// Open on double click
				if (e.getClickCount() >= 2) {
					CuckooFrame.getInstance().setVisible(true);
					CuckooFrame.getInstance().setExtendedState(JFrame.NORMAL);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	public void update() {
		setPopupMenu(new TrayPopupMenu());
		int c = 0;
		for (Program p : ProgramManager.getPrograms()) {
			c += p.getScheduledTime() != 0 ? 1 : 0;
		}
		setToolTip(TRAY_NAME + " (" + c + " Scheduled Program(s))");
	}
}
