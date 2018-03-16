package com.anchitsharma.project123;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ChangePassword() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 669, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 653, 37);
		contentPane.add(panel);
		int gap = 5;
		panel.setLayout(new BorderLayout(gap,gap));
		
		JLabel lblNewLabel = new JLabel("Change Password",SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 200, 37);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 37, 653, 324);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout());
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Current User ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(110, 44, 200, 30);
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(328, 44, 200, 30);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(255, 215, 0));
		textField_1.setColumns(10);
		textField_1.setBounds(328, 80, 200, 30);
		panel_2.add(textField_1);
		
		JLabel lblCurrentUserI = new JLabel("Current User Name");
		lblCurrentUserI.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentUserI.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCurrentUserI.setBounds(110, 80, 200, 30);
		panel_2.add(lblCurrentUserI);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(328, 119, 200, 30);
		panel_2.add(textField_2);
		
		JLabel lblCurrentPassword = new JLabel("Current Password");
		lblCurrentPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCurrentPassword.setBounds(110, 119, 200, 30);
		panel_2.add(lblCurrentPassword);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(328, 158, 200, 30);
		panel_2.add(textField_3);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewPassword.setBounds(110, 158, 200, 30);
		panel_2.add(lblNewPassword);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(328, 194, 200, 30);
		panel_2.add(textField_4);
		
		JLabel lblReenterNewPassword = new JLabel("Re-enter New password");
		lblReenterNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblReenterNewPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReenterNewPassword.setBounds(110, 194, 200, 30);
		panel_2.add(lblReenterNewPassword);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(253, 256, 126, 30);
		panel_2.add(btnNewButton);
		
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClose.setBounds(389, 256, 126, 30);
		panel_2.add(btnClose);
		
		
		
	}
}
