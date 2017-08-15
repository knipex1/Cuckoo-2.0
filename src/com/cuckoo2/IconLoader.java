package com.cuckoo2;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconLoader {
	private static final String ICON_PACKAGE = "/com/cuckoo2/swing/icons/";
	private static final String ICONS_EXTENSION = ".png";

	/**
	 * Loads icon from resources.
	 * 
	 * @param iconName
	 * @param width
	 * @param height
	 * @return
	 */
	public static Icon loadIcon(String iconName, int width, int height) {
		if (!iconName.contains(ICONS_EXTENSION))
			iconName += ICONS_EXTENSION;
		Icon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(IconLoader.class.getResourceAsStream(ICON_PACKAGE + iconName))
					.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		} catch (IOException e1) {
			System.out.println("Cant load icon " + iconName);
			e1.printStackTrace();
		}
		return icon;
	}

	/**
	 * Loads Icon from resources 16x16.
	 */
	public static Icon loadIcon(String name) {
		return loadIcon(name, 16, 16);
	}

	/**
	 * Resizes an icon.
	 * 
	 * @param icon
	 * @param width
	 * @param height
	 * @return
	 */
	public static Icon resizeIcon(Icon icon, int width, int height) {
		Image image = ((ImageIcon) icon).getImage(); // transform it
		Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		return new ImageIcon(newimg);
	}

	/**
	 * Loads icon from a file.
	 * 
	 * @param path
	 * @param width
	 * @param height
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Icon loadIconFromPath(String path, int width, int height) throws FileNotFoundException {
		Icon icon = loadIconFromPath(path);
		Image image = ((ImageIcon) icon).getImage(); // transform it
		Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		return new ImageIcon(newimg);
	}

	/**
	 * @see #loadIconFromPath(String, int, int)
	 */
	public static Icon loadIconFromPath(String path) throws FileNotFoundException {
		File file = new File(path);
		// Get metadata and create an icon
		sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder(file);
		Icon icon = new ImageIcon(sf.getIcon(true));
		return icon;
	}
}
