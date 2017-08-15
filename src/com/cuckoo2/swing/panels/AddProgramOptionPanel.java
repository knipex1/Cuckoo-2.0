package com.cuckoo2.swing.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.cuckoo2.IconLoader;
import com.cuckoo2.support.ProgramFileChooser;

public class AddProgramOptionPanel extends JPanel {
	private static final long serialVersionUID = -2047713124633454451L;
	private JTextField pathHint;
	private JTextField pathField;
	private JTextField nameHint;
	private JTextField nameField;
	private JTextField iconHint;
	private JLabel iconLabel;
	private JButton selectIconButton;

	@Deprecated
	public AddProgramOptionPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(440, 140));
		pathHint = new JTextField();
		pathHint.setEditable(false);
		pathHint.setText("Program Path:");
		pathHint.setBounds(10, 11, 77, 20);
		pathHint.setBorder(BorderFactory.createEmptyBorder());
		add(pathHint);

		pathField = new JTextField();
		pathField.setEditable(false);
		pathField.setBounds(97, 11, 234, 20);
		add(pathField);

		JButton selectButton = new JButton("Select...");
		selectButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		selectButton.setBounds(341, 10, 89, 23);
		selectButton.addActionListener(e -> {
			ProgramFileChooser gc = new ProgramFileChooser();
			if (gc.showDialog(this, "Add Selected Program") == ProgramFileChooser.APPROVE_OPTION) {
				gc.savePathAsLast(gc.getCurrentDirectory());
				File f = gc.getSelectedFile();
				pathField.setText(f.getPath());
				String name = pathField.getText().substring(pathField.getText().lastIndexOf('\\') + 1);
				try {
					iconLabel.setIcon(IconLoader.loadIconFromPath(f.getPath()));
					nameField.setText(name);
					showFields(true);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(selectButton);

		nameHint = new JTextField();
		nameHint.setHorizontalAlignment(SwingConstants.CENTER);
		nameHint.setText("Custom Name:");
		nameHint.setBorder(BorderFactory.createEmptyBorder());
		nameHint.setEditable(false);
		nameHint.setBounds(10, 42, 77, 20);
		add(nameHint);

		nameField = new JTextField();
		nameField.setBounds(97, 42, 234, 20);
		add(nameField);

		iconHint = new JTextField();
		iconHint.setEditable(false);
		iconHint.setText("Custom Icon:");
		iconHint.setHorizontalAlignment(SwingConstants.CENTER);
		iconHint.setBounds(10, 89, 77, 20);
		iconHint.setBorder(BorderFactory.createEmptyBorder());
		add(iconHint);

		iconLabel = new JLabel("");
		iconLabel.setBounds(97, 80, 32, 32);
		add(iconLabel);

		selectIconButton = new JButton("Select...");
		selectIconButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		selectIconButton.setBounds(139, 88, 89, 23);
		add(selectIconButton);
		showFields(false);
	}

	public JTextField getPathField() {
		return pathField;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JLabel getIconLabel() {
		return iconLabel;
	}

	public void showFields(boolean b) {
		iconLabel.setVisible(b);
		iconHint.setVisible(b);
		selectIconButton.setVisible(b);
		nameField.setVisible(b);
		nameHint.setVisible(b);
	}
}
