package com.anchitsharma.project123;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;

import com.model.Constants;
import com.model.MedicineModel;
import com.model.PdfModel;
import com.model.SearchModels;
import com.toedter.calendar.JDateChooser;
import com.utility.AutoComplete;

import com.utility.ClientsTableButtonRenderer;
import com.utility.ClientsTableRenderer;
import com.utility.MyTableModel;
import com.utility.Patient;
import com.utility.RowData;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

public class MedicineBoard extends JFrame implements DocumentListener, ActionListener {
	private static final String TAG = "MedicineBoard";

	private JPanel contentPane;
	private JTextField txt_invoice;
	private JTextField txt_patientID;
	private JTextField txt_pname;
	private JTextField txt_pmobile;
	private JTextField txt_quant;
	private JTextField txt_price1;
	private JTextField txt_tab_num;
	SearchModels sm = new SearchModels();
	private JTable table_medicine;

	private MyTableModel medicine_model, model;
	private JComboBox cmb_medicine;
	private JButton btnAdd;
	private JPanel panel_2;

	private boolean tableUpdate = false;
	private Calendar calendar = Calendar.getInstance();
	private Date date = new Date();
	private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	private Map<String, Object> where, values, savedata;
	private List<List<String>> selectdata;
	List<String> columns;

	final String col[] = { "Date", "Med Name", "quant(.mg)", "Price/piece", "No. of Tabs", "Total item cost" };
	String col1[] = { "id", "Group name", "Medicine Name", "Type", "Price" };
	private JPanel panel;
	private JComboBox cmbxSelect;
	private JTextArea textAreaRemark;
	private JTextField txt_amt;
	private JTextField txt_t_disc;
	private JTextField txt_net_amt;
	private JTextField txt_gst;
	private JTextField txt_disc;
	private JTextField txt_return;
	private JTextField txt_search;
	private JComboBox cmb_searchby;
	private JPanel panel_5;
	private JTable table;
	private JTextField txt_medrate;
	private JButton btnSave;
	private JCheckBox chckbxCash;
	private JCheckBox chckbxByCourier;
	private JButton btnNewMedicine;
	private JComboBox cmb_grpname;
	private JComboBox cmb_medname;
	private JComboBox cmb_distType;
	private JTextField txt_padd;
	private JTextField txt_age;
	private JTextField txt_occupation;
	private JTextField txt_pincode;
	private JTextField txt_ref_name;
	private JTextField txt_ref_mobile;
	private JTextField txt_gender;
	
	private JMenuItem markpatient, markcancel,markDelete;

	private int stat = 0;

	private int stat2 = 0;
	private JButton button;
	/**
	 * Create the frame.
	 */
	public MedicineBoard() {
		setTitle("Medicose");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 0, 1019, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 11, 993, 664);
		contentPane.add(tabbedPane);
		panel = new JPanel();
		tabbedPane.addTab("Prescription Entry", null, panel, null);

		panel_5 = new JPanel();
		tabbedPane.addTab("Medicine Entry", null, panel_5, null);

		tab1();
		tab2();
		setListener();
	}

	private void tab2() {
		panel_5.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 107, 968, 518);
		panel_5.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		model = new MyTableModel();
		model.setColumnNames(col1);
		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		JTableHeader header = table.getTableHeader();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.setRowHeight(20);
		panel_1.add(BorderLayout.NORTH, header);
		
		final JPopupMenu popupmenu = new JPopupMenu("Edit");
		markDelete = new JMenuItem("Delete");
		markDelete.addActionListener(this);
		popupmenu.add(markDelete);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.isPopupTrigger()) {
					popupmenu.show(table, e.getX(), e.getY());
				}
			}
			
		});
		
		popupmenu.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						  int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(popupmenu, new Point(0, 0), table));
	                        if (rowAtPoint > -1) {
	                        	table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
	                        }
					}
				});
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		cmb_grpname = new AutoComplete(sm.getGroupNames());// new JComboBox<>();
		cmb_grpname.setEditable(true);
		cmb_grpname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cmb_grpname.setBounds(10, 43, 160, 34);
		panel_5.add(cmb_grpname);

		cmb_medname = new AutoComplete(sm.getMedNames());
		cmb_medname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cmb_medname.setEditable(true);
		cmb_medname.setBounds(180, 43, 160, 34);
		panel_5.add(cmb_medname);

		txt_medrate = new JTextField();
		txt_medrate.setFont(new Font("Times New Roman", Font.BOLD, 11));
		txt_medrate.setBounds(539, 43, 142, 34);
		panel_5.add(txt_medrate);
		txt_medrate.setColumns(10);

		cmb_distType = new JComboBox();
		cmb_distType.setModel(new DefaultComboBoxModel(new String[] { "Tablet", "Liquid" }));
		cmb_distType.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cmb_distType.setEditable(true);
		cmb_distType.setBounds(356, 43, 142, 34);
		panel_5.add(cmb_distType);

		btnNewMedicine = new JButton("Add Medicine");
		btnNewMedicine.addActionListener(this);
		btnNewMedicine.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewMedicine.setBounds(705, 43, 120, 29);
		panel_5.add(btnNewMedicine);

		JLabel lblNewLabel_2 = new JLabel("Enter Group Name");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 11, 160, 23);
		panel_5.add(lblNewLabel_2);

		JLabel lblEnterMedicineName = new JLabel("Enter Medicine Name");
		lblEnterMedicineName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterMedicineName.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblEnterMedicineName.setBounds(180, 11, 160, 23);
		panel_5.add(lblEnterMedicineName);

		JLabel lblLiquidtablet = new JLabel("Liquid/Tablet");
		lblLiquidtablet.setHorizontalAlignment(SwingConstants.CENTER);
		lblLiquidtablet.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblLiquidtablet.setBounds(356, 11, 142, 23);
		panel_5.add(lblLiquidtablet);

		JLabel lblRate = new JLabel("Rate");
		lblRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblRate.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblRate.setBounds(539, 11, 142, 23);
		panel_5.add(lblRate);
		header.setFont(new Font("Times New Roman", Font.BOLD, 15));
		setTableData(model);

	}

	private void setListener() {

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String med_name = (String) cmb_medicine.getSelectedItem();
				String med_type = (String) cmbxSelect.getSelectedItem();
				String qunt = txt_quant.getText().isEmpty() ? "" : txt_quant.getText();
				String price = txt_price1.getText().isEmpty() ? "" : txt_price1.getText();
				String tab_count = txt_tab_num.getText().isEmpty() ? "" : txt_tab_num.getText();
				double pp = Double.parseDouble(price);
				double c = Double.parseDouble(tab_count);
				double tcost = pp * c;

				for (int i = 0; i < medicine_model.getRowCount(); i++) {
					if (medicine_model.getValueAt(i, 1).equals(med_name)) {
						medicine_model.removeRow(i);
					}
				}
				/*
				 * medicine_model.addRow(new Object[] {med_name,qunt, price,tab_count, tcost});
				 */
				values = new HashMap<>();
				values.put(col[0], dateFormat.format(date));
				values.put(col[1], med_name);
				if (med_type.equals("Tablets")) {
					values.put(col[2], qunt + " mg");
				}
				if (med_type.equals("Liquid")) {
					values.put(col[2], qunt + " ml");
				}
				values.put(col[3], price);
				values.put(col[4], tab_count);
				values.put(col[5], tcost);

				medicine_model.addRow(new RowData(values));

				tableUpdate = true;
				medicine_model.fireTableDataChanged();
				/*
				 * Map<String, Object> columns = new HashMap<>(); columns.put("med_name",
				 * med_name); columns.put("rate", price); // {"Tablets", "Liquid"}));// {"mg",
				 * "ml"}));
				 * 
				 * // String quant_type = (String)cmbxQuantType.getSelectedItem(); if
				 * (med_type.equals("Tablets")) { columns.put("dist_type", "tablets"); } if
				 * (med_type.equals("Liquid")) { columns.put("dist_type", "liquid"); }
				 * 
				 * MutableComboBoxModel<String> model = (MutableComboBoxModel<String>)
				 * cmb_medicine.getModel();
				 * 
				 * if (!checkModel(med_name, model)) { model.addElement(med_name);
				 * sm.storeInDatabase("medicine_record", columns); }
				 */

				txt_quant.setText(null);
				txt_price1.setText(null);
				

			}

			private boolean checkModel(String med_name, MutableComboBoxModel<String> model) {
				for (int i = 0; i < model.getSize(); i++) {
					String str = model.getElementAt(i);
					if (str.equals(med_name)) {
						return true;
					}
				}
				return false;
			}
		});

		table_medicine.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				if (tableUpdate) {
					tableUpdate = false;
					double total = 0;
					AbstractTableModel model = (AbstractTableModel) e.getSource();
					String columnName = model.getColumnName(4);
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
						double temp = Double.parseDouble(model.getValueAt(i, 5).toString());
						System.out.println("row number " + temp);
						total = total + temp;
					}

					System.out.println(columnName + " = " + total);
					txt_amt.setText(String.format("%.2f", total));

				}

			}
		});

	}

	@Override
	public void changedUpdate(DocumentEvent documentEvent) {
		setUpDocListener(documentEvent);

	}

	@Override
	public void insertUpdate(DocumentEvent documentEvent) {
		setUpDocListener(documentEvent);
	}

	@Override
	public void removeUpdate(DocumentEvent documentEvent) {
		// TODO Auto-generated method stub
		setUpDocListener(documentEvent);
	}

	private void setUpDocListener(DocumentEvent documentEvent) {
		Object ob3 = documentEvent.getDocument().getProperty("txt_disc");
		if (ob3 != null) {
			String disc = txt_disc.getText();
			if (!disc.isEmpty()) {
				double dis = Double.parseDouble(disc);
				if (!txt_amt.getText().isEmpty()) {
					double net = Double.parseDouble(txt_amt.getText());
					net = net - dis;
					txt_net_amt.setText(String.format("%.2f", net));
					txt_t_disc.setText(String.format("%.2f", dis));
				}
			}
		}

		Object ob2 = documentEvent.getDocument().getProperty("txt_gst");
		if (ob2 != null) {
			String net_amt = txt_net_amt.getText();
			String gst = txt_gst.getText();
			if (!net_amt.isEmpty() && !gst.isEmpty()) {
				double txt_amt = Double.parseDouble(net_amt);
				double g1 = Double.parseDouble(gst);
				txt_amt = txt_amt + calculateGST(txt_amt, g1);
//				txt_t_total.setText(String.format("%.2f", txt_amt));
			}
		}

		Object ob1 = documentEvent.getDocument().getProperty("txt_amt");
		if (ob1 != null) {
			String amt = txt_amt.getText();
			if (!amt.isEmpty()) {
				txt_net_amt.setText(amt);
			}
		}

		Object obj = documentEvent.getDocument().getProperty("txt_search");
		if (obj != null) {
			medicine_model.removeAll();
			String str = (String) cmb_searchby.getSelectedItem();
			String searchText = txt_search.getText();
			columns = new ArrayList<>();
			where = new HashMap<>();
			columns.add("p_id");// 0
			columns.add("p_name");// 1
			columns.add("p_mobile");// 2
			columns.add("occupation");// 3
			columns.add("p_age");// 4
			columns.add("p_add");// 5
			columns.add("pincode");// 6
			columns.add("refer_id");// 7
			columns.add("p_gender");// 8
			columns.add("p_district");//9
			switch (str) {
			case "Registration No.":
				// push object of patient

				where.put("p_id", searchText);
				selectdata = sm.selectData("patient_table", columns, where);
				if (!selectdata.isEmpty()) {
					String p_id = selectdata.get(0).get(0);
					fillPatientDetials(
							new Patient("", p_id, selectdata.get(0).get(1), "", "", "", "", selectdata.get(0).get(7),
									selectdata.get(0).get(5), selectdata.get(0).get(2), selectdata.get(0).get(9),selectdata.get(0).get(6),
									selectdata.get(0).get(8), selectdata.get(0).get(4), selectdata.get(0).get(3)));
					fillMediTable(sm.getMedicines(p_id));
				}

				break;
			case "Mobile":
				if (searchText.length() >= 10) {
					where.put("p_mobile", searchText);
					selectdata = sm.selectData("patient_table", columns, where);
					if (!selectdata.isEmpty()) {
						String pat_id = selectdata.get(0).get(0);
						// String reg_date, String p_id, String p_name, String p_bill_amt, String
						// p_amt_due, String amt_paid,
						// String p_doc_name, String refer_name, String p_add, String p_mobile, String
						// pincode, String gender,
						// String age, String occupation

						// new Patient("", pat_id, selectdata.get(0).get(1), "", "", "", "",
						// sm.searchNameMobile(selectdata.get(0).get(7)), selectdata.get(0).get(5),
						// selectdata.get(0).get(2))

						// send only ref mobile i will fetch the refral name
						fillPatientDetials(new Patient("", pat_id, selectdata.get(0).get(1), "", "", "", "",
								selectdata.get(0).get(7), selectdata.get(0).get(5), selectdata.get(0).get(2),
								selectdata.get(0).get(9),selectdata.get(0).get(6), selectdata.get(0).get(8), selectdata.get(0).get(4),
								selectdata.get(0).get(3)));
						tableUpdate = true;
						fillMediTable(sm.getMedicines(pat_id));
					}
				}

				break;
			}

			// push object of medicine order
		}
	}

	private double calculateGST(double amt, double rate) {
		double tx = amt * rate / 100;
		return tx;
	}

	private boolean checkModel(String med_name, MutableComboBoxModel<String> model) {
		for (int i = 0; i < model.getSize(); i++) {
			String str = model.getElementAt(i);
			if (str.equals(med_name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		
		
		if (event.getSource() == cmb_medicine) {
			String med_name = (String) cmb_medicine.getSelectedItem();
			if (!med_name.isEmpty()) {
				columns = new ArrayList<>();
				where = new HashMap<>();
				columns.add("rate");
				where.put("med_name", med_name);
				selectdata = sm.selectData("medicine_record", columns, where);
				if (!selectdata.isEmpty()) {
					txt_price1.setText(selectdata.get(0).get(0));
				}
			}
		}
		if (event.getSource() == btnNewMedicine) {// tab2

			where = new HashMap<>();
			savedata = new HashMap<>();
			String med_name = (String) cmb_medname.getSelectedItem();
			String grp_code = (String) cmb_grpname.getSelectedItem();
			int i = table.getRowCount();
			savedata.put(col1[0], i++);
			where.put("med_name", med_name);
			savedata.put(col1[1], grp_code);
			savedata.put(col1[2], med_name);
			where.put("grp_code", grp_code);
			where.put("dist_type", cmb_distType.getSelectedItem());
			where.put("rate", txt_medrate.getText());
			savedata.put(col1[3], cmb_distType.getSelectedItem());
			savedata.put(col1[4], txt_medrate.getText());
			MutableComboBoxModel<String> medmodel = (MutableComboBoxModel<String>) cmb_medname.getModel();
			MutableComboBoxModel<String> grpmodel = (MutableComboBoxModel<String>) cmb_grpname.getModel();
			MutableComboBoxModel<String> tab1model = (MutableComboBoxModel<String>) cmb_medicine.getModel();
			boolean bmed = checkModel(med_name, medmodel);
			boolean bgrp = checkModel(grp_code, grpmodel);
			if (bgrp && !bmed) {
				medmodel.addElement(med_name);
				sm.storeInDatabase("medicine_record", where);
				tab1model.addElement(med_name);
				model.addRow(0,new RowData(savedata));
				
			}
			if (!bgrp && bmed) {
				grpmodel.addElement(grp_code);
				sm.storeInDatabase("medicine_record", where);
				model.addRow(0,new RowData(savedata));
			}
			if (!bgrp && !bmed) {
				medmodel.addElement(med_name);
				grpmodel.addElement(grp_code);
				tab1model.addElement(med_name);
				sm.storeInDatabase("medicine_record", where);
				model.addRow(0,new RowData(savedata));
			}
		}

		/*
		 * /*Map<String, Object> columns = new HashMap<>(); columns.put("med_name",
		 * med_name); columns.put("rate", price); // {"Tablets", "Liquid"}));// {"mg",
		 * "ml"}));
		 * 
		 * // String quant_type = (String)cmbxQuantType.getSelectedItem(); if
		 * (med_type.equals("Tablets")) { columns.put("dist_type", "tablets"); } if
		 * (med_type.equals("Liquid")) { columns.put("dist_type", "liquid"); }
		 * 
		 * MutableComboBoxModel<String> model = (MutableComboBoxModel<String>)
		 * cmb_medicine.getModel();
		 * 
		 * if (!checkModel(med_name, model)) { model.addElement(med_name);
		 * sm.storeInDatabase("medicine_record", columns); }
		 */

		if (event.getSource() == cmbxSelect) {
			List<String> columns = new ArrayList<>();
			columns.add("med_name");
			values = new HashMap<>();

			// sm.selectData("medicine_record", columns, where)
			// cmb_medicine = new AutoComplete(items);
		}

		if (event.getSource() == btnSave) {
			List<MedicineModel> medicineModels = new ArrayList<>();
			if (!txt_return.getText().isEmpty()) {
				for (int i = 0; i < table_medicine.getRowCount(); i++) {
					savedata = new HashMap<>();
					savedata.put("invoice_number", txt_invoice.getText());
					savedata.put("date", dateFormat.format(date));
					String medname = (String) medicine_model.getValueAt(i, 1);

					savedata.put("medicine_id", medname);


					Object med_price = medicine_model.getValueAt(i, 5);
					String quant = (String) medicine_model.getValueAt(i, 2);
					savedata.put("quantity", quant);
					String price_piece = (String) medicine_model.getValueAt(i, 3);
					savedata.put("price_perp", price_piece);
					String tab_count = (String) medicine_model.getValueAt(i, 4);
					savedata.put("tab_count", tab_count);
					savedata.put("medcine_price", med_price);
					savedata.put("cancel_doctor", 1);
					savedata.put("cancel_patient", 1);
				
					medicineModels.add(
								new MedicineModel(medname, med_price, quant, String.valueOf(price_piece), tab_count));
					sm.storeInDatabase("medicine_order_table", savedata);
				}

				savedata = new HashMap<>();
				savedata.put("med_invoice_id", txt_invoice.getText());
				savedata.put("p_id", txt_patientID.getText());
				savedata.put("doc_id", "");
				savedata.put("date", dateFormat.format(date));

				int again = Integer.parseInt(txt_return.getText());
				calendar.setTime(date);
				calendar.add(Calendar.DATE, again);
				savedata.put("return_date", dateFormat.format(calendar.getTime()));
				if (chckbxCash.isSelected()) {
					savedata.put("by_cash", 1);
					savedata.put("by_courier", "");
				}
				if (chckbxByCourier.isSelected()) {
					savedata.put("by_cash", "");
					savedata.put("by_courier", 1);
				}
				savedata.put("item_amt", txt_net_amt.getText());
				savedata.put("gst_apply", txt_gst.getText());
				
				sm.storeInDatabase("medicine_order_entry", savedata);
				savedata.clear();// used as where
				savedata.put("p_id", txt_patientID.getText());
				Map<String, Object> what = new HashMap<>();
				what.put("last_visited", dateFormat.format(date));
				enterDiagnos();
				sm.updateDatabase("patient_table", savedata, what);

				
					Patient pat = new Patient("", txt_patientID.getText(), txt_pname.getText(), "", "", "", "",txt_ref_mobile.getText(),
							txt_padd.getText(), txt_pmobile.getText(), "", txt_pincode.getText(), txt_gender.getText(), txt_age.getText(), txt_occupation.getText());
							/*new Patient("", txt_patientID.getText(), txt_pname.getText(), "", "", "", "", "", "",
							txt_pmobile.getText());*/
					PdfModel pdfModel = new PdfModel();
					pdfModel.prepareReceipt(txt_invoice.getText(), medicineModels, txt_patientID.getText());
				
				if (chckbxByCourier.isSelected()) {
					pdfModel.courierReceipt("courier_invoice", txt_patientID.getText());
				}
				resetTextFields();
				txt_invoice.setText(JavaConnect.uniqueCurrentTimeStamp());
			} else {
				JOptionPane.showMessageDialog(null, "Please Fill the prescription time");
			}
		}
		
		if (event.getSource()== markpatient) {
			alterMedicineColumn(Constants.cancel_by_pat);
			
		}
		
		if (event.getSource()==markcancel) {
			alterMedicineColumn(Constants.cancel_by_doc);
		}
		if(event.getSource()==markDelete) {
			String medicine = (String)model.getValueAt(table.getSelectedRow(), 2);
			
			model.removeRow(table.getSelectedRow());
			String where = " where med_name = \'"+medicine+"\'";
			sm.deleteTable("medicine_record", where);
		}
		
		if(event.getSource() == button) {
			MedicineBoard.this.dispose();
		}

	}

	private void enterDiagnos() {
		// TODO Auto-generated method stub
		if(textAreaRemark.getText().isEmpty())
			return;
		where = new HashMap<>();
		columns = new ArrayList<>();
		where.put("p_id", txt_patientID.getText());
		columns.add("id");
		String grpdate = " order by id LIMIT 1";
		selectdata = sm.selectData("appointment", columns, where, grpdate);
		if(!selectdata.isEmpty()) {
			where.clear();
			Map<String,Object> what = new HashMap<>();
			where.put("id", selectdata.get(0).get(0));
			
			what.put(Constants.remarks, dateFormat.format(this.date)+" "+textAreaRemark.getText());
			sm.updateDatabase("appointment", where, what);
		}
	}

	private void resetTextFields() {

		medicine_model.removeAll();
		// txt_patientID.setText(null);
		txt_pname.setText(null);
		txt_amt.setText(null);
		txt_net_amt.setText(null);
		// txt_t_pay.setText(null);
		txt_return.setText(null);
		txt_t_disc.setText(null);
		textAreaRemark.setText(null);

	}

	private void displayConfMessage(String mesg) {
		JOptionPane.showMessageDialog(null, mesg);
	}

	private void setTableData(MyTableModel model) {// used in tab2
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("id");// 0
		columns.add("grp_code");// 1
		columns.add("med_name");// 2
		columns.add("dist_type");// 3
		columns.add("rate");// 4
		String grpdate = " order by med_name ";
		selectdata = sm.selectData("medicine_record", columns, where,grpdate);
		if (!selectdata.isEmpty()) {
			for (List<String> strList : selectdata) {
				values = new HashMap<>();
				values.put(col1[0], strList.get(0));
				values.put(col1[1], strList.get(1));
				values.put(col1[2], strList.get(2));
				values.put(col1[3], strList.get(3));
				values.put(col1[4], strList.get(4));
				model.addRow(new RowData(values));
			}
		}
	}

	private void fillPatientDetials(Patient pat) {
		txt_patientID.setText(pat.getP_id());
		txt_pname.setText(pat.getP_name());
		txt_pmobile.setText(pat.getP_mobile());
		txt_padd.setText(pat.getP_add());
		txt_pincode.setText(pat.getPincode());
		txt_gender.setText(pat.getGender());
		txt_age.setText(pat.getAge());
		txt_occupation.setText(pat.getOccupation());
		txt_ref_name.setText(sm.searchNameMobile(pat.getRefer_name()));
		txt_ref_mobile.setText(pat.getRefer_name());

	}

	// final String col[] = { "Date", "Med Name", "quant(.mg)", "Price/piece", "No.
	// of Tabs", "Total item cost",
	// "Remove" };
	private void fillMediTable(List<MedicineModel> list) {
		for (MedicineModel model : list) {
			if (model.getDrop_by_doctor().equals("1")) {
				values = new HashMap<>();
				values.put(col[0], model.getDate());
				values.put(col[1], model.getMed_name());
				values.put(col[2], model.getQuantity());
				values.put(col[3], model.getPrice_per_piece());
				values.put(col[4], model.getTabCount());
				values.put(col[5], model.getMed_price());
				//values.put(col[6], Boolean.FALSE);
				tableUpdate = true;
				medicine_model.addRow(new RowData(values));
			}
			
		}
	}
	
	private void alterAmount() {
		double d = Double.parseDouble((String)medicine_model.getValueAt(table_medicine.getSelectedRow(), 5));
		
		System.out.println("txt_amt==" + d);
		double amt = Double.parseDouble(txt_amt.getText());
		amt = amt - d;
		String str = String.format("%.2f", amt);
		txt_amt.setText(str);
		
	}

	private void alterMedicineColumn(String colName) {
		alterAmount();
		String medicine = (String)medicine_model.getValueAt(table_medicine.getSelectedRow(), 1);
		String priceP = String.valueOf(medicine_model.getValueAt(table_medicine.getSelectedRow(), 3));
		String med_price = String.valueOf(medicine_model.getValueAt(table_medicine.getSelectedRow(), 5));
		medicine_model.removeRow(table_medicine.getSelectedRow());
		savedata = new HashMap();
		savedata.put("invoice_number", txt_invoice.getText());
		savedata.put("medicine_id", medicine);
		savedata.put("date", dateFormat.format(this.date));
		savedata.put("price_perp", priceP);
		savedata.put("medcine_price", med_price);
		savedata.put(colName, 0);
		sm.storeInDatabase("medicine_order_table", savedata);
		//invoice id
		//medicine_id
		//price_per peice
		//medicine_price
		//date
		//change cancel_patient =0
	}
	private void tab1() {

		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, null, null, null));
		panel_1.setBounds(5, 11, 554, 185);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Invoice No.");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 37, 112, 20);
		panel_1.add(lblNewLabel);

		txt_invoice = new JTextField();
		txt_invoice.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_invoice.setBounds(122, 37, 143, 20);
		panel_1.add(txt_invoice);
		txt_invoice.setText(JavaConnect.uniqueCurrentTimeStamp());
		txt_invoice.setColumns(10);

		JLabel lblFromDate = new JLabel("Patient Name");
		lblFromDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFromDate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblFromDate.setBounds(0, 66, 123, 20);
		panel_1.add(lblFromDate);

		JLabel lblToDate = new JLabel("Mobile");
		lblToDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblToDate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblToDate.setBounds(275, 66, 106, 20);
		panel_1.add(lblToDate);

		JLabel lblPatientId = new JLabel("Registartion No.");
		lblPatientId.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatientId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPatientId.setBounds(274, 37, 117, 20);
		panel_1.add(lblPatientId);

		txt_patientID = new JTextField();
		txt_patientID.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_patientID.setColumns(10);
		txt_patientID.setBounds(391, 37, 143, 20);

		panel_1.add(txt_patientID);

		txt_pname = new JTextField();
		txt_pname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_pname.setColumns(10);
		txt_pname.setBounds(122, 66, 143, 20);
		panel_1.add(txt_pname);

		txt_pmobile = new JTextField();
		txt_pmobile.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_pmobile.setColumns(10);
		txt_pmobile.setBounds(391, 66, 143, 20);
		panel_1.add(txt_pmobile);

		cmb_searchby = new JComboBox();
		cmb_searchby.setModel(new DefaultComboBoxModel(new String[] { "Registration No.", "Mobile" }));
		cmb_searchby.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cmb_searchby.setBounds(122, 5, 143, 20);
		panel_1.add(cmb_searchby);

		JLabel lblSearchBy = new JLabel("Search By");
		lblSearchBy.setForeground(Color.RED);
		lblSearchBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchBy.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSearchBy.setBounds(0, 5, 112, 20);
		panel_1.add(lblSearchBy);

		txt_search = new JTextField();
		txt_search.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_search.setColumns(10);
		txt_search.getDocument().putProperty("txt_search", txt_search);
		txt_search.getDocument().addDocumentListener(this);
		txt_search.setBounds(272, 5, 143, 20);
		panel_1.add(txt_search);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAddress.setBounds(6, 97, 106, 20);
		panel_1.add(lblAddress);

		txt_padd = new JTextField();
		txt_padd.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_padd.setColumns(10);
		txt_padd.setBounds(122, 97, 143, 20);
		panel_1.add(txt_padd);

		JLabel lblOccupation = new JLabel("Age");
		lblOccupation.setHorizontalAlignment(SwingConstants.CENTER);
		lblOccupation.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOccupation.setBounds(6, 128, 106, 20);
		panel_1.add(lblOccupation);

		txt_age = new JTextField();
		txt_age.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_age.setColumns(10);
		txt_age.setBounds(122, 129, 36, 20);
		panel_1.add(txt_age);

		txt_occupation = new JTextField();
		txt_occupation.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_occupation.setColumns(10);
		txt_occupation.setBounds(267, 129, 114, 20);
		panel_1.add(txt_occupation);

		JLabel lblPincode = new JLabel("Pincode");
		lblPincode.setHorizontalAlignment(SwingConstants.CENTER);
		lblPincode.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPincode.setBounds(275, 97, 74, 20);
		panel_1.add(lblPincode);

		txt_pincode = new JTextField();
		txt_pincode.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_pincode.setColumns(10);
		txt_pincode.setBounds(346, 97, 74, 20);
		panel_1.add(txt_pincode);

		JLabel lblOccupation_1 = new JLabel("Occupation");
		lblOccupation_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOccupation_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOccupation_1.setBounds(168, 128, 92, 20);
		panel_1.add(lblOccupation_1);

		JLabel lblRefralName = new JLabel("Referal Name");
		lblRefralName.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefralName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRefralName.setBounds(6, 153, 106, 20);
		panel_1.add(lblRefralName);

		txt_ref_name = new JTextField();
		txt_ref_name.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_ref_name.setColumns(10);
		txt_ref_name.setBounds(122, 154, 143, 20);
		panel_1.add(txt_ref_name);

		JLabel lblRefName = new JLabel("Ref. Mobile");
		lblRefName.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRefName.setBounds(275, 152, 106, 20);
		panel_1.add(lblRefName);

		txt_ref_mobile = new JTextField();
		txt_ref_mobile.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_ref_mobile.setColumns(10);
		txt_ref_mobile.setBounds(391, 153, 143, 20);
		panel_1.add(txt_ref_mobile);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGender.setBounds(435, 97, 63, 20);
		panel_1.add(lblGender);

		txt_gender = new JTextField();
		txt_gender.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_gender.setColumns(10);
		txt_gender.setBounds(508, 97, 36, 20);
		panel_1.add(txt_gender);

		panel_2 = new JPanel();
		panel_2.setBounds(569, 22, 409, 174);
		panel.add(panel_2);
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, null, null, null));
		panel_2.setLayout(null);

		JLabel lblMediname = new JLabel("Medi-Name");
		lblMediname.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblMediname.setBounds(20, 63, 135, 20);
		panel_2.add(lblMediname);

		JLabel lblQuant = new JLabel("Quant");
		lblQuant.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuant.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblQuant.setBounds(235, 124, 58, 20);
		panel_2.add(lblQuant);

		JLabel lblPrice = new JLabel("Price/piece");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPrice.setBounds(155, 124, 70, 20);
		panel_2.add(lblPrice);

		cmb_medicine = new JComboBox(sm.getMedNames());
		AutoCompleteDecorator.decorate(cmb_medicine);
		cmb_medicine.addActionListener(this);
		cmb_medicine.setEditable(true);
		cmb_medicine.setBounds(10, 86, 309, 30);
		panel_2.add(cmb_medicine);

		txt_quant = new JTextField();
		txt_quant.setEditable(false);
		txt_quant.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_quant.setBounds(235, 143, 58, 20);
		panel_2.add(txt_quant);
		txt_quant.setColumns(10);

		txt_price1 = new JTextField();
		txt_price1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_price1.setBounds(155, 143, 70, 20);
		panel_2.add(txt_price1);
		txt_price1.setColumns(10);

		btnAdd = new JButton("ADD");
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setBounds(329, 86, 70, 30);
		panel_2.add(btnAdd);

		JLabel lblNoOfTabs = new JLabel("No. Of Tabs");
		lblNoOfTabs.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoOfTabs.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNoOfTabs.setBounds(303, 124, 76, 20);
		panel_2.add(lblNoOfTabs);

		txt_tab_num = new JTextField();
		txt_tab_num.setText("1");
		txt_tab_num.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_tab_num.setColumns(10);
		txt_tab_num.setBounds(313, 143, 58, 20);
		panel_2.add(txt_tab_num);

		cmbxSelect = new JComboBox();
		cmbxSelect.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cmbxSelect.addActionListener(this);
		cmbxSelect.setModel(new DefaultComboBoxModel(new String[] {"", "Tablets", "Liquid"}));// {"mg", "ml"}));
		cmbxSelect.setBounds(10, 38, 135, 20);
		panel_2.add(cmbxSelect);

		JLabel lblMedicineType = new JLabel("Medicine Type");
		lblMedicineType.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicineType.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblMedicineType.setBounds(10, 11, 135, 20);
		panel_2.add(lblMedicineType);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(5, 222, 968, 195);
		panel.add(panel_3);
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, null, null, null));
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(BorderLayout.CENTER, scrollPane);
		medicine_model = new MyTableModel();
		
		medicine_model.setColumnNames(col);

		table_medicine = new JTable();
		table_medicine.setModel(medicine_model);
		scrollPane.setViewportView(table_medicine);
		JTableHeader med_header = table_medicine.getTableHeader();
		table_medicine.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_medicine.getColumnModel().getColumn(0).setPreferredWidth(50);

		table_medicine.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table_medicine.setRowHeight(20);
		//TableColumn colb = table_medicine.getColumnModel().getColumn(6);
		panel_3.add(BorderLayout.NORTH, med_header);
		med_header.setFont(new Font("Times New Roman", Font.BOLD, 15));
		final JCheckBox cb = new JCheckBox("remove");
		cb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*double d = Double.parseDouble((String)medicine_model.getValueAt(table_medicine.getSelectedRow(), 5));
				System.out.println("txt_amt==" + d);
				double amt = Double.parseDouble(txt_amt.getText());
				amt = amt - d;
				String str = String.format("%.2f", amt);
				txt_amt.setText(str);*/
				alterAmount();
			}
		});
		//colb.setCellEditor(new DefaultCellEditor(cb));
		//TableColumn colc = table_medicine.getColumnModel().getColumn(7);
		final JCheckBox c2 = new JCheckBox();
		c2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				alterAmount();
			}
		});
		//colc.setCellEditor(new DefaultCellEditor(c2));
		final JPopupMenu popupmenu = new JPopupMenu("Edit");
		markpatient = new JMenuItem("Cancel By Patient");
		markcancel = new JMenuItem("Cancel By Doctor");
		markcancel.addActionListener(this);
		markpatient.addActionListener(this);
		popupmenu.add(markpatient);
		popupmenu.add(markcancel);
		table_medicine.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.isPopupTrigger()) {
					popupmenu.show(table_medicine, e.getX(), e.getY());
				}
			}
			
		});
		
		popupmenu.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						  int rowAtPoint = table_medicine.rowAtPoint(SwingUtilities.convertPoint(popupmenu, new Point(0, 0), table_medicine));
	                        if (rowAtPoint > -1) {
	                        	table_medicine.setRowSelectionInterval(rowAtPoint, rowAtPoint);
	                        }
					}
				});
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JLabel lblRemarks = new JLabel("Test Entry");
		lblRemarks.setToolTipText("Fill the Type of Test if Doctor Prescribe Any");
		lblRemarks.setBounds(476, 428, 154, 24);
		panel.add(lblRemarks);
		lblRemarks.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemarks.setFont(new Font("Times New Roman", Font.BOLD, 16));

		textAreaRemark = new JTextArea();
		textAreaRemark.setToolTipText("Fill the Type of Test if Doctor Prescribe Any");
		textAreaRemark.setBounds(633, 428, 345, 24);
		panel.add(textAreaRemark);
		textAreaRemark.setFont(new Font("Monospaced", Font.PLAIN, 18));

		JLabel lblNewLabel_1 = new JLabel("Medicine Entry");
		lblNewLabel_1.setBounds(569, 0, 94, 24);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		JLabel lblPrescription = new JLabel("Prescription");
		lblPrescription.setBounds(5, 196, 126, 24);
		panel.add(lblPrescription);
		lblPrescription.setForeground(Color.BLACK);
		lblPrescription.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		JLabel lblUseRemoveOnly = new JLabel("Use Remove only at Last");
		lblUseRemoveOnly.setBounds(141, 196, 236, 24);
		panel.add(lblUseRemoveOnly);
		lblUseRemoveOnly.setForeground(Color.RED);
		lblUseRemoveOnly.setFont(new Font("Times New Roman", Font.BOLD, 12));

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, null, null, null));
		panel_4.setBounds(5, 500, 983, 136);
		panel.add(panel_4);

		btnSave = new JButton("Save & Show");
		btnSave.addActionListener(this);
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSave.setBounds(174, 103, 115, 26);
		panel_4.add(btnSave);

		button = new JButton("Close");
		button.addActionListener(this);
		button.setFont(new Font("Times New Roman", Font.BOLD, 12));
		button.setBounds(299, 103, 96, 26);
		panel_4.add(button);

		JLabel label = new JLabel("Total Item");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 11));
		label.setBounds(298, 11, 96, 26);
		panel_4.add(label);

		txt_amt = new JTextField();

		txt_amt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_amt.setColumns(10);
		txt_amt.setBounds(410, 13, 75, 24);
		txt_amt.getDocument().addDocumentListener(this);
		txt_amt.getDocument().putProperty("txt_amt", txt_amt);
		panel_4.add(txt_amt);

		JLabel label_1 = new JLabel("Total Discount");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		label_1.setBounds(530, 38, 102, 26);
		panel_4.add(label_1);

		txt_t_disc = new JTextField();
		txt_t_disc.setText("0.00");
		txt_t_disc.setToolTipText("in %");
		txt_t_disc.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_t_disc.setColumns(10);
		txt_t_disc.setBounds(637, 41, 115, 24);
		panel_4.add(txt_t_disc);

		JLabel label_2 = new JLabel("Total Amount");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		label_2.setBounds(530, 11, 102, 26);
		panel_4.add(label_2);

		txt_net_amt = new JTextField();
		txt_net_amt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_net_amt.setColumns(10);
		txt_net_amt.setBounds(637, 13, 115, 24);
		panel_4.add(txt_net_amt);

		JLabel label_3 = new JLabel("GST Applicable");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 11));
		label_3.setBounds(530, 66, 102, 26);
		panel_4.add(label_3);

		txt_gst = new JTextField();
		txt_gst.setText("0");
		txt_gst.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_gst.getDocument().addDocumentListener(this);
		txt_gst.getDocument().putProperty("txt_gst", txt_gst);
		txt_gst.setColumns(10);
		txt_gst.setBounds(637, 68, 115, 24);
		panel_4.add(txt_gst);

		JLabel label_5 = new JLabel("Disc. Amount");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_5.setBounds(290, 37, 115, 26);
		panel_4.add(label_5);

		txt_disc = new JTextField();
		txt_disc.setText("0");
		txt_disc.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_disc.getDocument().addDocumentListener(this);
		txt_disc.getDocument().putProperty("txt_disc", txt_disc);
		txt_disc.setColumns(10);
		txt_disc.setBounds(410, 40, 75, 24);
		panel_4.add(txt_disc);

		JLabel label_9 = new JLabel("No. of Days Medicine");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Times New Roman", Font.BOLD, 11));
		label_9.setBounds(272, 66, 140, 26);
		panel_4.add(label_9);

		txt_return = new JTextField();
		txt_return.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_return.setColumns(10);
		txt_return.setBounds(410, 67, 75, 24);
		panel_4.add(txt_return);
		
		chckbxCash = new JCheckBox("By CASH");
		chckbxCash.setSelected(true);
		chckbxCash.setFont(new Font("Times New Roman", Font.BOLD, 11));
		chckbxCash.setBounds(758, 68, 97, 23);
		panel_4.add(chckbxCash);

		chckbxByCourier = new JCheckBox("By COURIER");
		chckbxByCourier.setFont(new Font("Times New Roman", Font.BOLD, 11));
		chckbxByCourier.setBounds(858, 68, 97, 23);
		panel_4.add(chckbxByCourier);
		final ButtonGroup bg = new ButtonGroup();
		bg.add(chckbxCash);
		bg.add(chckbxByCourier);
		
	}
}
