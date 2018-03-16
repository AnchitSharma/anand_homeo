package com.anchitsharma.project123;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;

public class DoctorEntry extends JFrame{

	private JFrame frmDoctorEntry;
	private JTable table;
	private Image image;
	private PreparedStatement pst;
	private Connection conn= null;
	private ResultSet rs = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField txt_doc_rate;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorEntry window = new DoctorEntry();
					window.frmDoctorEntry.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public DoctorEntry() {
		conn = new JavaConnect().getConnection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getContentPane().setForeground(Color.LIGHT_GRAY);
		this.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1038, 614);
		this.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Search & View", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 1013, 109);
		panel_2.setBorder(null);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 0, 135, 109);
		image = new ImageIcon(getClass().getResource("/search.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(image));
		panel_2.add(lblNewLabel);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblSearch.setForeground(Color.BLUE);
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setBounds(155, 23, 115, 30);
		panel_2.add(lblSearch);
		
		JLabel lblDoctorName = new JLabel("Doctor Name");
		lblDoctorName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDoctorName.setForeground(Color.RED);
		lblDoctorName.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctorName.setBounds(165, 55, 105, 14);
		panel_2.add(lblDoctorName);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(310, 37, 439, 30);
		panel_2.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 126, 1013, 452);
		table = new JTable();
		setDataOnTable();
		scrollPane.setViewportView(table);
		
		panel.add(scrollPane);
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Add", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.WHITE));
		panel_3.setBounds(10, 11, 401, 269);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblDoctorType = new JLabel("Doctor Type");
		lblDoctorType.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblDoctorType.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctorType.setBounds(10, 11, 76, 21);
		panel_3.add(lblDoctorType);
		
		JRadioButton rdbtnExternal = new JRadioButton("External");
		rdbtnExternal.setFont(new Font("Times New Roman", Font.BOLD, 11));
		rdbtnExternal.setBounds(164, 10, 76, 23);
		panel_3.add(rdbtnExternal);
		
		JRadioButton rdbtnInternal = new JRadioButton("Internal");
		rdbtnInternal.setFont(new Font("Times New Roman", Font.BOLD, 11));
		rdbtnInternal.setBounds(268, 10, 95, 23);
		panel_3.add(rdbtnInternal);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnExternal);
		bg.add(rdbtnInternal);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 43, 356, 215);
		panel_3.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JCheckBox chckbxRefferal = new JCheckBox("Refferal");
		chckbxRefferal.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_7.add(chckbxRefferal);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Reporting");
		chckbxNewCheckBox_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_7.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Corporate House");
		chckbxNewCheckBox_3.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_7.add(chckbxNewCheckBox_3);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("OPD Consultation");
		chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_7.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_7 = new JCheckBox("IPD Consultation");
		chckbxNewCheckBox_7.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_7.add(chckbxNewCheckBox_7);
		
		JCheckBox chckbxNewCheckBox_8 = new JCheckBox("EMR Consultation\r\n");
		chckbxNewCheckBox_8.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_7.add(chckbxNewCheckBox_8);
		
		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Primary Doctor");
		chckbxNewCheckBox_5.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_7.add(chckbxNewCheckBox_5);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Duty Doctor");
		chckbxNewCheckBox_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_7.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_9 = new JCheckBox("Anaestesiologist");
		chckbxNewCheckBox_9.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_7.add(chckbxNewCheckBox_9);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Surgeon");
		chckbxNewCheckBox_4.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_7.add(chckbxNewCheckBox_4);
		
		JCheckBox chckbxNewCheckBox_6 = new JCheckBox("All?");
		chckbxNewCheckBox_6.setFont(new Font("Times New Roman", Font.BOLD, 12));
		panel_7.add(chckbxNewCheckBox_6);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Personal Information", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_4.setBounds(421, 11, 602, 269);
		 ((javax.swing.border.TitledBorder) panel_4.getBorder()).
	        setTitleFont(new Font("Arial", Font.ITALIC, 15));
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblDoctorCode = new JLabel("Doctor Code");
		lblDoctorCode.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDoctorCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctorCode.setBounds(10, 20, 73, 20);
		panel_4.add(lblDoctorCode);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(86, 20, 96, 22);
		panel_4.add(textField);
		textField.setColumns(10);
		
		JLabel lblDoctorName_1 = new JLabel("Doctor Name");
		lblDoctorName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctorName_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDoctorName_1.setBounds(192, 20, 73, 20);
		panel_4.add(lblDoctorName_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(267, 19, 150, 22);
		panel_4.add(comboBox_1);
		
		JLabel lblDegree = new JLabel("Degree");
		lblDegree.setHorizontalAlignment(SwingConstants.CENTER);
		lblDegree.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDegree.setBounds(427, 20, 73, 20);
		panel_4.add(lblDegree);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(496, 20, 96, 22);
		panel_4.add(textField_1);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(10, 45, 582, 213);
		panel_4.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Address");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 67, 14);
		panel_9.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(87, 8, 194, 20);
		panel_9.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblFathersName = new JLabel("father Name");
		lblFathersName.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblFathersName.setBounds(10, 34, 67, 14);
		panel_9.add(lblFathersName);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(87, 31, 194, 20);
		panel_9.add(textField_3);
		
		JLabel lblMartialStatus = new JLabel("Martial Status");
		lblMartialStatus.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblMartialStatus.setBounds(10, 57, 75, 14);
		panel_9.add(lblMartialStatus);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(87, 53, 194, 22);
		panel_9.add(comboBox_2);
		
		JLabel lblPhoneHome = new JLabel("Phone Home");
		lblPhoneHome.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblPhoneHome.setBounds(10, 79, 67, 14);
		panel_9.add(lblPhoneHome);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(87, 76, 194, 20);
		panel_9.add(textField_4);
		
		JLabel lblPhoneOffice = new JLabel("Phone Office");
		lblPhoneOffice.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblPhoneOffice.setBounds(10, 100, 75, 14);
		panel_9.add(lblPhoneOffice);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(87, 97, 194, 20);
		panel_9.add(textField_5);
		
		JLabel lblEmail = new JLabel("Email Official");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblEmail.setBounds(10, 122, 75, 14);
		panel_9.add(lblEmail);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(87, 119, 194, 20);
		panel_9.add(textField_6);
		
		JLabel lblJobTitle = new JLabel("Job Title");
		lblJobTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJobTitle.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblJobTitle.setBounds(10, 147, 67, 14);
		panel_9.add(lblJobTitle);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(87, 144, 194, 20);
		panel_9.add(textField_7);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblGender.setBounds(10, 176, 75, 14);
		panel_9.add(lblGender);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(87, 172, 194, 22);
		panel_9.add(comboBox_3);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblNationality = new JLabel("Nationality");
		lblNationality.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNationality.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNationality.setBounds(10, 14, 67, 14);
		panel_10.add(lblNationality);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(87, 11, 194, 20);
		panel_10.add(textField_8);
		
		JLabel lblMotherName = new JLabel("Mother Name");
		lblMotherName.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotherName.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblMotherName.setBounds(10, 37, 77, 14);
		panel_10.add(lblMotherName);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(87, 34, 194, 20);
		panel_10.add(textField_9);
		
		JLabel lblSpouseName = new JLabel("Spouse Name");
		lblSpouseName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpouseName.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblSpouseName.setBounds(10, 60, 77, 14);
		panel_10.add(lblSpouseName);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(87, 57, 194, 20);
		panel_10.add(textField_10);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMobile.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblMobile.setBounds(10, 83, 67, 14);
		panel_10.add(lblMobile);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(87, 80, 194, 20);
		panel_10.add(textField_11);
		
		JLabel lblEmailPersno = new JLabel("Email Persno");
		lblEmailPersno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailPersno.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblEmailPersno.setBounds(10, 106, 67, 14);
		panel_10.add(lblEmailPersno);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(87, 103, 194, 20);
		panel_10.add(textField_12);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartment.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblDepartment.setBounds(10, 128, 67, 14);
		panel_10.add(lblDepartment);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(87, 125, 194, 20);
		panel_10.add(textField_13);
		
		JLabel lblJoiningDate = new JLabel("Joining date");
		lblJoiningDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoiningDate.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblJoiningDate.setBounds(10, 151, 67, 14);
		panel_10.add(lblJoiningDate);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(87, 148, 194, 20);
		panel_10.add(dateChooser);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemark.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblRemark.setBounds(10, 174, 67, 14);
		panel_10.add(lblRemark);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(87, 171, 194, 20);
		panel_10.add(textField_14);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.WHITE));
		panel_5.setBounds(10, 291, 401, 287);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblRate = new JLabel("rate");
		lblRate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblRate.setBounds(28, 36, 46, 14);
		panel_5.add(lblRate);
		
		txt_doc_rate = new JTextField();
		txt_doc_rate.setBounds(84, 33, 86, 20);
		panel_5.add(txt_doc_rate);
		txt_doc_rate.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setForeground(Color.WHITE);
		panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_6.setBounds(409, 291, 602, 287);
		panel_1.add(panel_6);
		panel_6.setLayout(null);
		
		JButton btnNew = new JButton("New");
		btnNew.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNew.setBounds(231, 253, 89, 23);
		panel_6.add(btnNew);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.setBounds(324, 253, 89, 23);
		panel_6.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBounds(414, 253, 89, 23);
		panel_6.add(btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClose.setBounds(503, 253, 89, 23);
		panel_6.add(btnClose);
		
		JCheckBox chckbxActive = new JCheckBox("Active");
		chckbxActive.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxActive.setBounds(373, 210, 70, 23);
		panel_6.add(chckbxActive);
		
		JLabel lblNewLabel_2 = new JLabel("This (*) Field must be Filled");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(449, 206, 143, 30);
		panel_6.add(lblNewLabel_2);
		
		
		this.setTitle("Doctor Entry");
		this.setBounds(100, 100, 1074, 674);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	private void setDataOnTable(){
		String sql = "select * from doctors";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			table.setModel(buildTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private DefaultTableModel buildTableModel(ResultSet rs)throws SQLException{
		ResultSetMetaData metaData = rs.getMetaData();
		
		//names of the columns
		Vector<String> columnNames = new Vector<>();
		int columnCount = metaData.getColumnCount();
		/*for (int column = 1; column <= columnCount ; column++) {
			columnNames.add(metaData.getColumnName(column));
			
		}*/
		columnNames.add("Doctor Code");
		columnNames.add("Doctor Name");
		columnNames.add("Degree");
		columnNames.add("Address");
		columnNames.add("Martial Status");
		columnNames.add("Phone Home");
		columnNames.add("Department");
		columnNames.add("Job Title");
		
		//data of the table 
		Vector<Vector<Object>> data = new Vector<>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}
		return new DefaultTableModel(data,columnNames);
	}
}
