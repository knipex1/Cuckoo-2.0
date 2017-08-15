package com.cuckoo2.support;

import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import com.cuckoo2.Program;
import com.cuckoo2.ProgramManager;

public class ProgramFileChooser extends JFileChooser {
	private static final long serialVersionUID = -8790431414472839724L;
	public static Preferences prefs = Preferences.userNodeForPackage(ProgramFileChooser.class);
	private final static String desktop_path = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();

	public ProgramFileChooser() {
		super(getLastPath());
		// First filter, initial one, shortcuts
		FileFilter filter2 = new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				String fileName = pathname.getPath();
				for (Program p : ProgramManager.getPrograms()) {
					if (p.getExecutablePath().equalsIgnoreCase(fileName))
						return false;
				}
				return pathname.getPath().endsWith(".exe") || pathname.isDirectory();
			}

			@Override
			public String getDescription() {
				return "Executable files (.exe)";
			}
		};
		addChoosableFileFilter(filter2);
		setFileFilter(filter2);

		// Disable "all files" filter
		setAcceptAllFileFilterUsed(false);
		// files and folders selection mode
		setFileSelectionMode(JFileChooser.FILES_ONLY);
		// disable new folder icon
		disableNewFolderButton(this);
		// No editable textfiled on file name into filechooser
		JTextField jTextField = SwingUtils.getDescendantOfType(JTextField.class, this, "Text", "");
		jTextField.setEditable(false);
		// Remove right click in file chooser
		@SuppressWarnings("rawtypes")
		final JList list = SwingUtils.getDescendantOfType(JList.class, this, "Enabled", true);
		JPopupMenu popup = list.getComponentPopupMenu();
		for (Component c : popup.getComponents())
			popup.remove(c);
	}

	public void savePathAsLast(File file) {
		prefs.put("LAST_PATH", file.getAbsolutePath());
	}

	private static File getLastPath() {
		return new File(prefs.get("LAST_PATH", desktop_path));
	}

	private static void disableNewFolderButton(Container c) {
		int len = c.getComponentCount();
		for (int i = 0; i < len; i++) {
			Component comp = c.getComponent(i);
			if (comp instanceof JButton) {
				JButton b = (JButton) comp;
				Icon icon = b.getIcon();
				if (icon != null && icon == UIManager.getIcon("FileChooser.newFolderIcon"))
					b.setEnabled(false);
			} else if (comp instanceof Container) {
				disableNewFolderButton((Container) comp);
			}
		}
	}
}
