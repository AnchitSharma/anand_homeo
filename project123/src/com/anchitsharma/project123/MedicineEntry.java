package com.anchitsharma.project123;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import com.model.SearchModels;
import com.utility.AutoComplete;
import com.utility.MyTableModel;
import com.utility.RowData;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class MedicineEntry extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table_medicine_group;
	private JTable table_medicine;
	private MyTableModel medicine_model, medicine_group_model;
	private String col1[] = { "Med ID", "Med Group", "Med Name" };
	private String col2[] = { "Group Code", "Group Name" };
	private List<String> columns;
	private Map<String, Object> where;
	private List<List<String>> selectdata;
	private Map<String, Object> data, grpdata;

	private SearchModels sm = new SearchModels();
	private JTextField txt_med_id;
	private JTextField txt_med_grp;
	private JTextField txt_med_name;
	private JTextField txt_rate_tab;
	private JTextField txt_rate_ml;
	private JButton btngsave;
	private JButton btngDelete;
	private JButton btngExit;
	private JButton button_save;
	private JButton button_new;
	private JButton button_delete;
	private JButton button_exit;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox cmb_grp_name;
	private JComboBox cmb_grp_code;
	private JRadioButton rd_liquid;
	private JRadioButton rd_tabs;
	private ButtonGroup bg;

	/**
	 * Create the frame.
	 */
	public MedicineEntry() {
		setFont(new Font("Dialog", Font.PLAIN, 17));
		setTitle("Medicine Entry");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 964, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 938, 546);
		contentPane.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Medicine Entry", null, panel_1, null);
		panel_1.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(476, 23, 447, 495);
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1, BorderLayout.CENTER);
		medicine_model = new MyTableModel();
		medicine_model.setColumnNames(col1);
		table_medicine = new JTable();
		table_medicine.setModel(medicine_model);
		scrollPane_1.setViewportView(table_medicine);
		JTableHeader h1 = table_medicine.getTableHeader();
		h1.setFont(new Font("Helvetica", Font.BOLD, 15));
		table_medicine.setFont(new Font("Helvetica", Font.PLAIN, 15));
		table_medicine.setRowHeight(20);
		table_medicine.getSelectionModel().addListSelectionListener(new RowListener());
		JLabel lblMedicineShow = new JLabel("Medicine Show");
		lblMedicineShow.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMedicineShow.setBounds(478, 0, 154, 20);
		panel_1.add(lblMedicineShow);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(10, 25, 461, 493);
		panel_1.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblMedicineId = new JLabel("Medicine ID");
		lblMedicineId.setBounds(10, 10, 104, 25);
		lblMedicineId.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_5.add(lblMedicineId);

		txt_med_id = new JTextField();
		txt_med_id.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_med_id.setBounds(207, 12, 244, 25);
		panel_5.add(txt_med_id);
		txt_med_id.setColumns(10);

		JLabel lblMedicineType = new JLabel("Medicine Type");
		lblMedicineType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMedicineType.setBounds(10, 46, 116, 25);
		panel_5.add(lblMedicineType);

		txt_med_grp = new JTextField();
		txt_med_grp.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_med_grp.setColumns(10);
		txt_med_grp.setBounds(207, 48, 244, 25);
		panel_5.add(txt_med_grp);

		JLabel lblMedicineGroupCode = new JLabel("Medicine Group Code");
		lblMedicineGroupCode.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMedicineGroupCode.setBounds(10, 86, 164, 25);
		panel_5.add(lblMedicineGroupCode);

		cmb_grp_code = new JComboBox();
		cmb_grp_code.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmb_grp_code.setEditable(true);
		cmb_grp_code.setBounds(207, 86, 244, 25);
		panel_5.add(cmb_grp_code);

		JLabel lblMedicineGroupName_1 = new JLabel("Medicine Group Name");
		lblMedicineGroupName_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMedicineGroupName_1.setBounds(10, 118, 176, 25);
		panel_5.add(lblMedicineGroupName_1);
		grpdata = new HashMap<>();
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("grp_code");
		columns.add("grp_name");
		selectdata = sm.selectData("medicine_grp", columns, where);
		for (List<String> strList : selectdata) {
			grpdata.put(strList.get(1), strList.get(0));
		}
		columns.clear();
		for (String str : grpdata.keySet()) {
			columns.add(str);
		}

		cmb_grp_name = new AutoComplete(columns.toArray());
		cmb_grp_name.addActionListener(this);
		cmb_grp_name.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmb_grp_name.setEditable(true);
		cmb_grp_name.setBounds(207, 118, 244, 25);
		panel_5.add(cmb_grp_name);

		JLabel lblMedicineTitle = new JLabel("Medicine Title");
		lblMedicineTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMedicineTitle.setBounds(10, 149, 164, 25);
		panel_5.add(lblMedicineTitle);

		txt_med_name = new JTextField();
		txt_med_name.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_med_name.setColumns(10);
		txt_med_name.setBounds(207, 151, 244, 25);
		panel_5.add(txt_med_name);

		JLabel lblRateperTab = new JLabel("Rate(per tab.)");
		lblRateperTab.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRateperTab.setBounds(10, 222, 164, 25);
		panel_5.add(lblRateperTab);

		txt_rate_tab = new JTextField();
		txt_rate_tab.setEditable(false);
		txt_rate_tab.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_rate_tab.setColumns(10);
		txt_rate_tab.setBounds(207, 222, 244, 25);
		panel_5.add(txt_rate_tab);

		JLabel lblTypeOfDistribution = new JLabel("Type of Distribution");
		lblTypeOfDistribution.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTypeOfDistribution.setBounds(10, 186, 164, 25);
		panel_5.add(lblTypeOfDistribution);
		bg = new ButtonGroup();
		rd_liquid = new JRadioButton("Liquid");
		rd_liquid.addActionListener(this);
		rd_liquid.setFont(new Font("Tahoma", Font.BOLD, 11));
		rd_liquid.setBounds(206, 186, 76, 23);
		panel_5.add(rd_liquid);

		rd_tabs = new JRadioButton("Tablets");
		rd_tabs.addActionListener(this);
		rd_tabs.setFont(new Font("Tahoma", Font.BOLD, 11));
		rd_tabs.setBounds(308, 186, 76, 23);
		panel_5.add(rd_tabs);
		bg.add(rd_tabs);
		bg.add(rd_liquid);
		JLabel lblRateperMl = new JLabel("Rate(per ml.)");
		lblRateperMl.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRateperMl.setBounds(10, 257, 164, 25);
		panel_5.add(lblRateperMl);

		txt_rate_ml = new JTextField();
		txt_rate_ml.setEditable(false);
		txt_rate_ml.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_rate_ml.setColumns(10);
		txt_rate_ml.setBounds(207, 257, 244, 25);
		panel_5.add(txt_rate_ml);

		button_save = new JButton("SAVE");
		button_save.addActionListener(this);
		button_save.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_save.setBounds(35, 401, 171, 35);
		panel_5.add(button_save);

		button_new = new JButton("NEW");
		button_new.addActionListener(this);
		button_new.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_new.setBounds(229, 401, 184, 35);
		panel_5.add(button_new);

		button_delete = new JButton("DELETE");
		button_delete.addActionListener(this);
		button_delete.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_delete.setBounds(35, 447, 171, 35);
		panel_5.add(button_delete);

		button_exit = new JButton("EXIT");
		button_exit.addActionListener(this);
		button_exit.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_exit.setBounds(229, 447, 184, 35);
		panel_5.add(button_exit);

		JLabel lblMedicineNameSet = new JLabel("Medicine Name Set");
		lblMedicineNameSet.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMedicineNameSet.setBounds(10, 4, 154, 20);
		panel_1.add(lblMedicineNameSet);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Medicine Group Entry", null, panel, null);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(10, 21, 424, 497);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Medicine Group Code");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 11, 342, 25);
		panel_2.add(lblNewLabel_1);

		comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(10, 36, 390, 25);
		panel_2.add(comboBox);

		JLabel lblMedicineGroupName = new JLabel("Medicine Group Name");
		lblMedicineGroupName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMedicineGroupName.setBounds(10, 87, 342, 25);
		panel_2.add(lblMedicineGroupName);

		comboBox_1 = new JComboBox();
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(10, 112, 390, 25);
		panel_2.add(comboBox_1);

		btngsave = new JButton("SAVE");
		btngsave.addActionListener(this);

		btngsave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btngsave.setBounds(22, 372, 171, 35);
		panel_2.add(btngsave);

		btngExit = new JButton("EXIT");
		btngExit.addActionListener(this);
		btngExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btngExit.setBounds(216, 418, 184, 35);
		panel_2.add(btngExit);

		btngDelete = new JButton("DELETE");
		btngDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btngDelete.setBounds(22, 418, 171, 35);
		panel_2.add(btngDelete);

		JLabel lblNewLabel = new JLabel("Medicine Group Set");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 0, 154, 20);
		panel.add(lblNewLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBounds(444, 21, 479, 497);
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);

		medicine_group_model = new MyTableModel();
		medicine_group_model.setColumnNames(col2);

		table_medicine_group = new JTable();
		table_medicine_group.setModel(medicine_group_model);
		scrollPane.setViewportView(table_medicine_group);
		JTableHeader h2 = table_medicine_group.getTableHeader();
		h2.setFont(new Font("Helvetica", Font.BOLD, 15));
		table_medicine_group.setFont(new Font("Helvetica", Font.PLAIN, 15));
		table_medicine_group.setRowHeight(20);
		table_medicine_group.getSelectionModel().addListSelectionListener(new RowGrpListener());

		JLabel lblMedicineGroupShow = new JLabel("Medicine Group Show");
		lblMedicineGroupShow.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMedicineGroupShow.setBounds(444, 0, 154, 20);
		panel.add(lblMedicineGroupShow);
		setTableOne();
		setTableTwo();
	}

	private void setTableOne() {
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("id");
		columns.add("med_name");
		columns.add("grp_code");
		selectdata = sm.selectData("medicine_record", columns, where);
		System.out.println("data in medicine record table");
		System.out.println(selectdata);
		for (List<String> strList : selectdata) {
			data = new HashMap();
			data.put(col1[0], strList.get(0));
			System.out.println("grpdata");
			System.out.println(grpdata);
			if (!grpdata.isEmpty()) {
				for (String str : grpdata.keySet()) {
					if (grpdata.get(str).equals(strList.get(2))) {
						data.put(col1[1], grpdata.get(str));
					}
				}
			}

			data.put(col1[2], strList.get(1));
			medicine_model.addRow(new RowData(data));
		}
	}

	private void setTableTwo() {
		System.out.println("setTableTwo");
		System.out.println(grpdata);
		if (!grpdata.isEmpty()) {
			for (String str : grpdata.keySet()) {
				Map<String, Object> map = new HashMap<>();
				map.put(col2[0], grpdata.get(str).toString());
				map.put(col2[1], str);
				medicine_group_model.addRow(new RowData(map));
			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button_delete) {
			
			String id = (String)medicine_model.getValueAt(table_medicine.getSelectedRow(), 0);
			if (!id.isEmpty()) {
				String where = " where id = "+"\'"+id+"\'";
				sm.deleteTable("medicine_record", where);
			}
			medicine_model.removeAll();
			setTableOne();
			
		}
		
		if (e.getSource() == button_exit || e.getSource() == btngExit) {
			this.dispose();
		}

		if (e.getSource() == btngsave) {
			where = new HashMap<>();
			String str1 = (String) comboBox.getSelectedItem();
			String str2 = (String) comboBox_1.getSelectedItem();
			if (!str1.isEmpty() && !str2.isEmpty()) {
				where.put("grp_code", str1);
				where.put("grp_name", str2);
				MutableComboBoxModel<String> model_code = (MutableComboBoxModel<String>) cmb_grp_code.getModel();
				model_code.addElement(str1);
				MutableComboBoxModel<String> model_name = (MutableComboBoxModel<String>) cmb_grp_name.getModel();
				model_name.addElement(str2);
			}

			sm.storeInDatabase("medicine_grp", where);
		}

		if (e.getSource() == cmb_grp_name) {
			String str = (String) cmb_grp_name.getSelectedItem();
			if (!str.isEmpty() && !grpdata.isEmpty()) {
				cmb_grp_code.setSelectedItem(grpdata.get(str));

			}
		}
		if (e.getSource() == rd_liquid) {
			txt_rate_ml.setEditable(true);
			txt_rate_tab.setEditable(false);
		}
		if (e.getSource() == rd_tabs) {
			txt_rate_tab.setEditable(true);
			txt_rate_ml.setEditable(false);
		}
		if (e.getSource() == button_save) {
			where = new HashMap<>();
			Map<String, Object> what = new HashMap<>();
			if (!txt_med_id.getText().isEmpty()) {
				where.put("id", txt_med_id.getText());

			}
			if (!txt_med_name.getText().isEmpty()) {
				what.put("med_name", txt_med_name.getText());
			}
			String str1 = (String) cmb_grp_code.getSelectedItem();
			if (!str1.isEmpty()) {
				what.put("grp_code", str1);
			}
			if (rd_liquid.isSelected() && !txt_rate_ml.getText().isEmpty()) {
				what.put("dist_type", "liquid");
				what.put("rate", txt_rate_ml.getText());
			}
			if (rd_tabs.isSelected() && !txt_rate_tab.getText().isEmpty()) {
				what.put("dist_type", "tablets");
				what.put("rate", txt_rate_tab.getText());
			}
			sm.updateDatabase("medicine_record", where, what);
		}

		if (e.getSource() == button_new) {
			where = new HashMap<>();
			if (!txt_med_id.getText().isEmpty()) {
				where.put("id", txt_med_id.getText());

			}
			if (!txt_med_name.getText().isEmpty()) {
				where.put("med_name", txt_med_name.getText());
			}
			String str1 = (String) cmb_grp_code.getSelectedItem();
			if (!str1.isEmpty()) {
				where.put("grp_code", str1);
			}
			if (rd_liquid.isSelected() && !txt_rate_ml.getText().isEmpty()) {
				where.put("dist_type", "liquid");
				where.put("rate", txt_rate_ml.getText());
			}
			if (rd_tabs.isSelected() && !txt_rate_tab.getText().isEmpty()) {
				where.put("dist_type", "tablets");
				where.put("rate", txt_rate_tab.getText());
			}
			sm.storeInDatabase("medicine_record", where);
		}
	}

	private class RowListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (e.getValueIsAdjusting()) {
				return;
			}
			// System.out.println(medicine_model.getValueAt(table_medicine.getSelectedRow(),
			// 2));
				String id = (String) medicine_model.getValueAt(table_medicine.getSelectedRow(), 0);
			txt_med_id.setText(id);
			txt_med_name.setText((String) medicine_model.getValueAt(table_medicine.getSelectedRow(), 2));
			columns = new ArrayList<>();
			where = new HashMap<>();
			columns.add("grp_code");
			columns.add("dist_type");
			columns.add("rate");
			where.put("id", id);
			selectdata = sm.selectData("medicine_record", columns, where);
			if (!selectdata.isEmpty()) {
				if (!grpdata.isEmpty()) {
					for (String str : grpdata.keySet()) {
						if (grpdata.get(str).equals(selectdata.get(0).get(0))) {
							cmb_grp_name.setSelectedItem(str);
							cmb_grp_code.setSelectedItem(grpdata.get(str));
						}
					}
				}

				if (selectdata.get(0).get(1).equals("liquid")) {
					txt_rate_tab.setEditable(false);
					rd_liquid.setSelected(true);
					txt_rate_ml.setEditable(true);
					txt_rate_ml.setText(selectdata.get(0).get(2));
				}

				if (selectdata.get(0).get(1).equals("tablets")) {
					rd_tabs.setSelected(true);
					txt_rate_ml.setEditable(false);
					txt_rate_tab.setEditable(true);
					txt_rate_tab.setText(selectdata.get(0).get(2));
				}

			}

		}

	}

	private class RowGrpListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (e.getValueIsAdjusting()) {
				return;
			}
			System.out.println(medicine_group_model.getValueAt(table_medicine_group.getSelectedRow(), 0));
			/*String grp = (String)medicine_group_model.getValueAt(table_medicine_group.getSelectedRow(), 0);
			cmb_grp_code.setSelectedItem("gcvtcfcfrxcfcxfc");
			cmb_grp_name.setSelectedItem(medicine_group_model.getValueAt(table_medicine_group.getSelectedRow(), 1));*/
			MutableComboBoxModel<String> model_code = (MutableComboBoxModel<String>) comboBox.getModel();
			model_code.addElement((String)medicine_group_model.getValueAt(table_medicine_group.getSelectedRow(), 0));
			MutableComboBoxModel<String> model_code1 = (MutableComboBoxModel<String>) comboBox_1.getModel();
			model_code1.addElement((String)medicine_group_model.getValueAt(table_medicine_group.getSelectedRow(), 1));
		}

	}
}
