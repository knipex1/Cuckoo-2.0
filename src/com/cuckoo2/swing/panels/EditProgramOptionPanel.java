package com.cuckoo2.swing.panels;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.cuckoo2.Program;

public class EditProgramOptionPanel extends JPanel {

	private static final long serialVersionUID = -2203954500200095130L;
	private JTextField hintName;
	private JTextField nameField;
	private JLabel iconLabel;

	public EditProgramOptionPanel(Program p) {
		setLayout(null);
		setPreferredSize(new Dimension(321, 120));
		hintName = new JTextField();
		hintName.setHorizontalAlignment(SwingConstants.CENTER);
		hintName.setEditable(false);
		hintName.setText("Name:");
		hintName.setBorder(BorderFactory.createEmptyBorder());
		hintName.setBounds(40, 11, 43, 20);
		add(hintName);

		nameField = new JTextField(p.getName());
		nameField.setBounds(93, 11, 157, 20);
		add(nameField);

		iconLabel = new JLabel("", p.getIcon(), JLabel.CENTER);
		iconLabel.setBounds(139, 42, 32, 32);
		add(iconLabel);
		//TODO:
		JButton changeIconButton = new JButton("Change");
		changeIconButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		changeIconButton.setBounds(111, 79, 89, 23);
		add(changeIconButton);
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JLabel getIconLabel() {
		return iconLabel;
	}
}
