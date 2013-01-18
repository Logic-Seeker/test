package com.brick.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Destination extends JPanel {
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblNewDestination = new JLabel("Add New Destination");
	private final JLabel lblDestination = new JLabel("Destination");
	private final JLabel lblRate = new JLabel("Vehicle Type");
	private final JTextField textField = new JTextField();
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblRate_1 = new JLabel("Rate");
	private final JTextField textField_2 = new JTextField();
	private final JButton btnNewButton = new JButton("Submit");

	/**
	 * Create the panel.
	 */
	public Destination() {
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField.setColumns(10);

		initGUI();
	}
	private void initGUI() {
		setLayout(new BorderLayout(0, 0));
		
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{120, 240};
		gbl_panel.rowHeights = new int[]{45, 45, 45, 80};
		//gbl_panel.columnWidths = new int[]{0, 0, 0};
		//gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_lblDestination = new GridBagConstraints();
		gbc_lblDestination.anchor = GridBagConstraints.WEST;
		gbc_lblDestination.insets = new Insets(0, 0, 5, 5);
		gbc_lblDestination.gridx = 0;
		gbc_lblDestination.gridy = 0;
		lblDestination.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblDestination, gbc_lblDestination);
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(7, 0, 7, 0);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		
		GridBagConstraints gbc_lblRate = new GridBagConstraints();
		gbc_lblRate.anchor = GridBagConstraints.WEST;
		gbc_lblRate.insets = new Insets(0, 0, 5, 5);
		gbc_lblRate.gridx = 0;
		gbc_lblRate.gridy = 1;
		lblRate.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblRate, gbc_lblRate);
		
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(7, 0, 7, 0);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel.add(textField_1, gbc_textField_1);
		
		GridBagConstraints gbc_lblRate_1 = new GridBagConstraints();
		gbc_lblRate_1.anchor = GridBagConstraints.WEST;
		gbc_lblRate_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblRate_1.gridx = 0;
		gbc_lblRate_1.gridy = 2;
		lblRate_1.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblRate_1, gbc_lblRate_1);
		
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(7, 0, 7, 0);
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panel.add(textField_2, gbc_textField_2);
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(btnNewButton, gbc_btnNewButton);
		panel_1.setBackground(Color.GRAY);
		
		add(panel_1, BorderLayout.NORTH);
		lblNewDestination.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewDestination.setForeground(new Color(0, 191, 255));
		
		panel_1.add(lblNewDestination);
	}

}
