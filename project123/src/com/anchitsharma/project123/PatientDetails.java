package com.anchitsharma.project123;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import com.model.Appointment;
import com.model.Constants;
import com.model.MedicineModel;
import com.model.PdfModel;
import com.model.SearchModels;
import com.utility.MyTableModel;
import com.utility.Patient;
import com.utility.RowData;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;

public class PatientDetails extends JFrame implements ActionListener {

	private JPanel contentPane;
	String col4[] = { "Reg.No.", "Name", "Mobile" };
	String col5[] = { "App date", "Type", "Status","Remarks" };
	String col6[] = { "Date","Name","drop by patient","drop by doctor" };
	private List<String> columns;
	private Map<String, Object> map;
	private List<List<String>> selectdata;
	private SearchModels sm = new SearchModels();
	private MyTableModel model,appModel,medModel;
	private JButton btnInActive;
	private JButton btnPrint;
	private JPanel panel_1;
	private JLabel label_3;
	private JTextField reg_id;
	private JLabel label_4;
	private JTextField pname;
	private JLabel label_5;
	private JTextField pmobile;
	private JLabel lblAddress;
	private JTextField padd;
	private JLabel lblDistrict;
	private JTextField district;
	private JLabel lblPincode;
	private JTextField pincode;
	private JLabel lblGender;
	private JTextField gender;
	private JLabel lblOccupation;
	private JTextField page;
	private JLabel lblOccupation_1;
	private JTextField poccupation;
	private JLabel lblReferBy;
	private JTextField ref_name;
	private JLabel lblRefmob;
	private JTextField ref_mob;
	private JTabbedPane tabbedPane;
	private JPanel panel_2;
	private JPanel panel_3;
	private JScrollPane scrollPane_1;
	private JTable app_table;
	private JScrollPane scrollPane_2;
	private JTable medicine_table;
	private JCheckBox chckbxActive;
	
	private Patient patient;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public PatientDetails(String id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 653);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		model = new MyTableModel();
		model.setColumnNames(col4);
		chckbxActive = new JCheckBox("Active");
		chckbxActive.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxActive.setBounds(21, 532, 97, 23);
		contentPane.add(chckbxActive);

		btnInActive = new JButton("Mark Inactive");
		btnInActive.addActionListener(this);
		btnInActive.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnInActive.setBounds(21, 566, 112, 23);
		contentPane.add(btnInActive);

		btnPrint = new JButton("Print");
		btnPrint.addActionListener(this);
		btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnPrint.setBounds(136, 566, 97, 23);
		contentPane.add(btnPrint);
		
		panel_1 = new JPanel();
		panel_1.setBounds(237, 11, 647, 604);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		label_3 = new JLabel("Registartion No.");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_3.setBounds(11, 12, 112, 20);
		panel_1.add(label_3);
		
		reg_id = new JTextField();
		reg_id.setFont(new Font("Times New Roman", Font.BOLD, 12));
		reg_id.setColumns(10);
		reg_id.setBounds(127, 11, 96, 20);
		panel_1.add(reg_id);
		
		label_4 = new JLabel("Patient Name");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_4.setBounds(233, 12, 96, 20);
		panel_1.add(label_4);
		
		pname = new JTextField();
		pname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		pname.setColumns(10);
		pname.setBounds(327, 12, 112, 20);
		panel_1.add(pname);
		
		label_5 = new JLabel("Mobile No.");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_5.setBounds(449, 12, 81, 20);
		panel_1.add(label_5);
		
		pmobile = new JTextField();
		pmobile.setFont(new Font("Times New Roman", Font.BOLD, 12));
		pmobile.setColumns(10);
		pmobile.setBounds(531, 12, 106, 20);
		panel_1.add(pmobile);
		
		lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAddress.setBounds(10, 37, 69, 20);
		panel_1.add(lblAddress);
		
		padd = new JTextField();
		padd.setFont(new Font("Times New Roman", Font.BOLD, 12));
		padd.setColumns(10);
		padd.setBounds(74, 36, 96, 20);
		panel_1.add(padd);
		
		lblDistrict = new JLabel("District");
		lblDistrict.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistrict.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDistrict.setBounds(180, 37, 62, 20);
		panel_1.add(lblDistrict);
		
		district = new JTextField();
		district.setFont(new Font("Times New Roman", Font.BOLD, 12));
		district.setColumns(10);
		district.setBounds(250, 37, 81, 20);
		panel_1.add(district);
		
		lblPincode = new JLabel("Pincode");
		lblPincode.setHorizontalAlignment(SwingConstants.CENTER);
		lblPincode.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPincode.setBounds(337, 37, 69, 20);
		panel_1.add(lblPincode);
		
		pincode = new JTextField();
		pincode.setFont(new Font("Times New Roman", Font.BOLD, 12));
		pincode.setColumns(10);
		pincode.setBounds(405, 38, 91, 20);
		panel_1.add(pincode);
		
		lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGender.setBounds(506, 36, 58, 20);
		panel_1.add(lblGender);
		
		gender = new JTextField();
		gender.setFont(new Font("Times New Roman", Font.BOLD, 12));
		gender.setColumns(10);
		gender.setBounds(568, 37, 69, 20);
		panel_1.add(gender);
		
		lblOccupation = new JLabel("Age");
		lblOccupation.setHorizontalAlignment(SwingConstants.CENTER);
		lblOccupation.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOccupation.setBounds(10, 60, 69, 20);
		panel_1.add(lblOccupation);
		
		page = new JTextField();
		page.setFont(new Font("Times New Roman", Font.BOLD, 12));
		page.setColumns(10);
		page.setBounds(74, 59, 62, 20);
		panel_1.add(page);
		
		lblOccupation_1 = new JLabel("Occupation");
		lblOccupation_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOccupation_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOccupation_1.setBounds(146, 60, 91, 20);
		panel_1.add(lblOccupation_1);
		
		poccupation = new JTextField();
		poccupation.setFont(new Font("Times New Roman", Font.BOLD, 12));
		poccupation.setColumns(10);
		poccupation.setBounds(250, 60, 81, 20);
		panel_1.add(poccupation);
		
		lblReferBy = new JLabel("Refer By");
		lblReferBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblReferBy.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblReferBy.setBounds(337, 60, 69, 20);
		panel_1.add(lblReferBy);
		
		ref_name = new JTextField();
		ref_name.setFont(new Font("Times New Roman", Font.BOLD, 12));
		ref_name.setColumns(10);
		ref_name.setBounds(405, 61, 81, 20);
		panel_1.add(ref_name);
		
		lblRefmob = new JLabel("Ref.Mob");
		lblRefmob.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefmob.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRefmob.setBounds(496, 59, 68, 20);
		panel_1.add(lblRefmob);
		
		ref_mob = new JTextField();
		ref_mob.setFont(new Font("Times New Roman", Font.BOLD, 12));
		ref_mob.setColumns(10);
		ref_mob.setBounds(568, 60, 69, 20);
		panel_1.add(ref_mob);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 91, 627, 502);
		panel_1.add(tabbedPane);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Appointment History", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		appModel = new MyTableModel();
		appModel.setColumnNames(col5);
		app_table = new JTable();
		app_table.setModel(appModel);
		scrollPane_1.setViewportView(app_table);
		JTableHeader h1 = app_table.getTableHeader();
		panel_2.add(BorderLayout.NORTH,h1);
		panel_3 = new JPanel();
		tabbedPane.addTab("Medical History", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		scrollPane_2 = new JScrollPane();
		panel_3.add(scrollPane_2, BorderLayout.CENTER);
		medModel = new MyTableModel();
		medModel.setColumnNames(col6);
		medicine_table = new JTable();
		medicine_table.setModel(medModel);
		scrollPane_2.setViewportView(medicine_table);
		JTableHeader h2 = medicine_table.getTableHeader();
		panel_3.add(BorderLayout.NORTH,h2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnInActive) {
			String pid = reg_id.getText();
			if (!pid.isEmpty()) {
				map = new HashMap<>();
				Map<String, Object> what = new HashMap<>();
				map.put("p_id", pid);
				what.put("active", 0);
				sm.updateDatabase("patient_table", map, what);
				showConfromMessage("details updated successfully");
			}
		}
		
		if (e.getSource() == btnPrint) {
			int count = app_table.getRowCount();
			List<Appointment> appList = new ArrayList<>();
			List<MedicineModel> medList = new ArrayList<>();
			for(int i=0;i<count;i++) {
				String date = (String)appModel.getValueAt(i, 0);
				String type = (String)appModel.getValueAt(i, 1);
				String status = (String)appModel.getValueAt(i, 2);
				String remarks = (String)appModel.getValueAt(i, 3);
				appList.add(new Appointment(date, type, status, remarks));
			}
			count = medicine_table.getRowCount();
			for(int j=0; j<count;j++) {
				String date = (String)medModel.getValueAt(j, 0);
				String type = (String)medModel.getValueAt(j, 1);
				String drop_patient = (String)medModel.getValueAt(j, 2);
				String drop_doctor = (String)medModel.getValueAt(j, 3);
				medList.add(new MedicineModel(date, type, "", "", "", "", drop_patient, drop_doctor));
			}
			if (patient != null) {
				new PdfModel().printHistory(patient.getP_id(), appList, medList);
			}
			
		}
	}

	private void searchPatient(String where, String what) {
		columns = new ArrayList<>();
		map = new HashMap<>();
		columns.add("p_id");
		columns.add("p_name");
		columns.add("p_mobile");
		map.put(where, what);
		selectdata = sm.selectData("patient_table", columns, map);
		// refreshFields();
		if (!selectdata.isEmpty()) {
			for (List<String> strList : selectdata) {
				map.clear();
				map.put(col4[0], strList.get(0));
				map.put(col4[1], strList.get(1));
				map.put(col4[2], strList.get(2));
				model.addRow(new RowData(map));
			}
		}

	}

	private void showConfromMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	

	

	public void setDataInTable(String pid) {
		if (!pid.isEmpty()) {
			columns = new ArrayList<>();
			map = new HashMap<>();
			columns.add("p_id");//0
			columns.add("p_name");//1
			columns.add("p_mobile");//2
			columns.add("p_dob");//3
			columns.add("p_add");//4
			columns.add("p_district");//5
			columns.add("pincode");//6
			columns.add("occupation");//7
			columns.add("p_blood");//8
			columns.add("p_gender");//9
			columns.add("p_age");//10
			columns.add("refer_id");//11
			columns.add("last_visited");//12
			columns.add("reg_date");//13
			columns.add("active");//14
			map.put("p_id", pid);
			selectdata = sm.selectData("patient_table", columns, map);
			if (!selectdata.isEmpty()) {
				for(List<String> strList:selectdata) {
					patient = new Patient(strList.get(13),strList.get(0), strList.get(1), "", "", "", "",
							strList.get(11), strList.get(4), strList.get(2), strList.get(5), strList.get(6)
							, strList.get(9), strList.get(10), strList.get(7));
				setDataInTextArea(patient);
				if (strList.get(14).equals("0")) {
					chckbxActive.setSelected(false);
				}else {
					chckbxActive.setSelected(true);
				}
				setDataInAppointment(pid);
				setMedicineTable(pid);
				}
			}
		}else {
			
		}
	}
	public void setDataInTextArea(Patient pat) {
		// TODO Auto-generated method stub
	reg_id.setText(pat.getP_id());
	pname.setText(pat.getP_name());
	pmobile.setText(pat.getP_mobile());
	padd.setText(pat.getP_add());
	district.setText(pat.getDistrict());
	pincode.setText(pat.getPincode());
	gender.setText(pat.getGender());
	page.setText(pat.getAge());
	poccupation.setText(pat.getOccupation());
	ref_name.setText(sm.searchNameMobile(pat.getRefer_name()));
	ref_mob.setText(pat.getRefer_name());
	
		
	}
	
	private void setDataInAppointment(String pid) {
		//String col5[] = { "App date", "Type", "Status","Remarks" };
//		String col6[] = { "Name","Date","drop by patient","drop by doctor" };
		appModel.removeAll();
		columns = new ArrayList<>();
		map = new HashMap<>();
		
		columns.add("app_date");//0
		columns.add("reg_date");//1
		columns.add("dept");//2
		columns.add("status");//3
		columns.add("attend");//4
		columns.add("remarks");//5
		map.put("p_id", pid);
		selectdata = sm.selectData("appointment", columns, map);
		if (!selectdata.isEmpty()) {
			for(List<String> strList:selectdata) {
				map = new HashMap<>();
				map.put(col5[0], strList.get(0));
				map.put(col5[1], strList.get(2));
				String st = strList.get(3);
				String attend = strList.get(4);
				if (st.equals("1")) {
					map.put(col5[2], "present");
				}else if (attend.equals(Constants.cancel)) {
					map.put(col5[2], "cancel");
				}else {
					map.put(col5[2], "absent");
				}
				map.put(col5[3], strList.get(5));
				appModel.addRow(new RowData(map));
			}
		}
		
	}
	
	private void setMedicineTable(String p_id) {
//		String col6[] = { "Name","Date","drop by patient","drop by doctor" };
		medModel.removeAll();
		List<MedicineModel> medicineModels = sm.getMedicineHistory(p_id);
		if (!medicineModels.isEmpty()) {
			for(MedicineModel model: medicineModels) {
				map = new HashMap<>();
				map.put(col6[0], model.getDate());
				map.put(col6[1], model.getMed_name());
				if(model.getDrop_by_patient().equals("0")) {
					map.put(col6[2], "by patient");
				}else {
					map.put(col6[2], "");
				}
				if (model.getDrop_by_doctor().equals("0")) {
					map.put(col6[3], "cancel");
				}else {
					map.put(col6[3], "");
				}
				
				medModel.addRow(new RowData(map));
			}
		}
	}
}
