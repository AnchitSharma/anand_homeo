package com.anchitsharma.project123;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.model.Constants;
import com.model.OpenFile;
import com.model.PdfModel;
import com.model.SearchModels;
import com.toedter.calendar.JDateChooser;
import com.utility.AutoComplete;
import com.utility.JTextFieldLimit;
import com.utility.MyTableModel;
import com.utility.Patient;
import com.utility.RowData;

import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.BevelBorder;
import javax.swing.JFormattedTextField;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PatientEntry extends JFrame implements DocumentListener, ActionListener {

	public static final String TAG = "PatientEntry";
	private JPanel contentPane;
	private JTextField txt_total_gst;
	private JTextField txt_payment;
	private JTextField txt_ref_id;
	private JTextField txt_page;
	private JTextField txt_padd;
	private JTextField txt_pname_tab2;
	private JTextField txt_invoice_tab2;
	private JTextField txt_regno_tab2;
	private JTextField txt_invoice_tab1;
	private JTextField txt_regno_tab1;
	private JTextField txt_pname_tab1;
	private JTextField txt_dname;
	private MyTableModel appoint_model;
	private JTextField txt_pmobile_tab1;
	private SearchModels sm = new SearchModels();
	private List<List<String>> selectdata;
	private Map<String, String> patientMap;
	List<String> columns;
	Map<String, Object> where;
	String col[] = { "S.NO.", "PT.NAME", "REG.NO.", "M.NO." };
	String col1[] = { "Date", "Doc Name", "Doc Path" };
	
	private JButton btnNewApp;
	private JButton btnPrint;
	private JDateChooser dateChooser_app;
	private Date date = new Date();
	private DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);
	private JPanel panel_appoint;
	private JTextFieldLimit txt_pmobile_tab2;
	private JComboBox cmbGender;
	private JComboBox cmbMarry;
	private JComboBox cmbBloodG;
	private JButton btnsaveshow;
	private JButton btnPrint_1;
	private JButton btnclear;
	private JButton btnclose;
	private JComboBox txt_ref_name;
	private JDateChooser invoice_date;
	private JDateChooser dobChooser;
	private PatientListener patientListener;
	private String reg_date;
	private JCheckBox chckbxRepeat;
	private JCheckBox chckbxOldPatient;
	private JCheckBox chckbxCourier;
	private JCheckBox chckbxOiling;
	private JButton btnClose;
	private JCheckBox chckbxNew;
	private JTabbedPane tabbedPane;
	private JButton btnUpdate;
	private String invoice_id_tab2;
	private JTextField txt_ref_mob;
	private JTextField txt_docname;
	private JTable doc_table;
	private JButton btnSubmit;
	private JButton btnbrowse;
	private JTextField txt_docpath;
	private MyTableModel docModel;
	private Map<String, Object> docMap;// insert values in the table document model
	private JTextField txt_occupation;
	private JTextField txt_district;
	private JTextField txt_pincode;
	private JTable table;
	private JTextField txt_new;
	private JTextField txt_repeat;
	private JTextField txt_old;
	private JTextField txt_courier;
	private JTextField txt_cancel;
	private JTextField txt_total;
	private JTextField txt_present;
	private JDateChooser appdateChooser;
	private JTextField txt_oil;
	private JTextArea textArea;
	private JButton btncancel;
	private JTextField txt_absent;
	private JButton btnTablePrint;
	/**
	 * Create the frame.
	 */
	public interface PatientListener {
		void onPatientRegister(Patient name);
	}

	public PatientEntry() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 0, 1000, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 974, 662);
		contentPane.add(tabbedPane);

		tab1();
		tab3();
		tab2();
	
		setListener();

	}

	@Override
	public void changedUpdate(DocumentEvent documentEvent) {
		// TODO Auto-generated method stub
		setUpDocumentListener(documentEvent);
	}

	@Override
	public void insertUpdate(DocumentEvent documentEvent) {
		// TODO Auto-generated method stub
		setUpDocumentListener(documentEvent);
	}

	@Override
	public void removeUpdate(DocumentEvent documentEvent) {
		// TODO Auto-generated method stub
		setUpDocumentListener(documentEvent);
	}

	public void setPatientListener(AnotherDash activity) {
		this.patientListener = (PatientListener) activity;
	}

	private void setUpDocumentListener(DocumentEvent documentEvent) {
		Object o1 = documentEvent.getDocument().getProperty("txt_ref_mob");
		if (o1 != null) {
			String mobile = txt_ref_mob.getText().trim();
			if (!mobile.isEmpty() && mobile.length() >= 10) {
				String name = sm.searchNameMobile(mobile);
				txt_ref_name.setSelectedItem(name);
			}
		}

		Object obj1 = documentEvent.getDocument().getProperty("txt_pmobile1");
		if (obj1 != null) {
			String mobile = txt_pmobile_tab2.getText().trim();
			if (!mobile.isEmpty() && mobile.length() >= 10) {
				// tableModel.addRow(sm.searchMobile(mobile));
				columns = new ArrayList<>();
				where = new HashMap<>();
				columns.add("p_name");
				where.put("p_mobile", mobile);
				selectdata = sm.selectData("patient_table", columns, where);
				if (!selectdata.isEmpty()) {
					setFormData(mobile);
					JOptionPane.showMessageDialog(null, "Aleady Registered Mobile Number");
					btnsaveshow.setEnabled(false);
				} else {
					btnsaveshow.setEnabled(true);
				}
			}
		}

		Object obj = documentEvent.getDocument().getProperty("patientID");
		if (obj != null) {
			String pid = txt_regno_tab1.getText();
			if (!pid.isEmpty() && null != pid) {
				// int id = Integer.valueOf(pid);

				// searchByID(id);
				// String sql = "select p_name,p_add from patient_table where p_id = ?";
				// String sql = "select p_name, d_name from patient_table inner join doctors on"
				// + " patient_table.p_doc_id = "
				// + "doctors.d_code where p_id = ?";

				columns = new ArrayList<>();
				where = new HashMap<>();
				columns.add("p_name");

				columns.add("p_doc_id");
				columns.add("p_mobile");
				columns.add("reg_date");
				where.put("p_id", pid);
				String pname = "", doc_id = "";
				selectdata = sm.selectData("patient_table", columns, where);
				// System.out.println("1--->" + selectdata);
				if (!selectdata.isEmpty()) {
					btnNewApp.setEnabled(true);
					for (List<String> strList : selectdata) {
						if (!strList.isEmpty()) {
							txt_pname_tab1.setText(strList.get(0));
							doc_id = strList.get(1);
							txt_pmobile_tab1.setText(strList.get(2));
							reg_date = strList.get(3);
						}
					}

					if (null != doc_id && !doc_id.isEmpty()) {
						columns = new ArrayList<>();
						columns.add("d_name");
						where = new HashMap<>();
						where.put("d_code", doc_id);
						selectdata = sm.selectData("doctors", columns, where);

						for (List<String> strls : selectdata) {
							txt_dname.setText(strls.get(0));
						}
					}
					
					columns.clear();
					where.clear();
					columns.add("app_date");
					columns.add("dept");
					where.put("p_id", pid);
					String grp = "order by id desc limit 1";
					selectdata = sm.selectData("appointment", columns, where, grp);
					if (!selectdata.isEmpty()) {
						textArea.setText(selectdata.get(0).get(0)+" : "+selectdata.get(0).get(1));
					}

				} else {
					btnNewApp.setEnabled(false);
				}

			}
		}
	}

	private void setFormData(String mobile) {

		// CREATE TABLE "patient_table" ( `p_id` TEXT, `p_name` TEXT, `p_mobile` TEXT
		// UNIQUE, `p_dob` TEXT, `p_add` TEXT, `p_blood` TEXT,
		// `p_gender` TEXT, `p_age` TEXT, `p_bill_amt` TEXT, `p_amt_due` TEXT,
		// `amt_paid` TEXT, `refer_id`
		// INTEGER, `last_visited` TEXT DEFAULT 0, `reg_date` TEXT, `p_doc_id` INTEGER )

		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("p_id");//0
		columns.add("p_name");//1
		columns.add("p_add");//2

		columns.add("p_blood");//3
		columns.add("p_gender");//4
		columns.add("p_age");//5
		columns.add("amt_paid");//6
		columns.add("refer_id");//7
		columns.add("occupation");//8
		columns.add("p_district");//9
		columns.add("pincode");//10
		columns.add("p_dob");//11
		columns.add("p_blood");//12
		columns.add("marry_stat");//13
		columns.add("refer_name");//14
		where.put("p_mobile", mobile);
		String p_id = "";
		selectdata = sm.selectData("patient_table", columns, where);
		
		
		for (List<String> strList : selectdata) {
			p_id = strList.get(0);
			txt_regno_tab2.setText(p_id);
			txt_pname_tab2.setText(strList.get(1));
			txt_padd.setText(strList.get(2));
			cmbBloodG.setSelectedItem(strList.get(3));
			
			txt_page.setText(strList.get(5));
			txt_payment.setText(strList.get(6));
			txt_ref_mob.setText(strList.get(7));
			txt_ref_name.setSelectedItem(strList.get(14));
			txt_ref_name.setSelectedItem(sm.searchNameMobile(strList.get(7)));
			txt_occupation.setText(strList.get(8));
			txt_district.setText(strList.get(9));
			txt_pincode.setText(strList.get(10));
			if(strList.get(4).equals("Female")||strList.get(4).equals("F")) {
				cmbGender.setSelectedItem("F");
			}else {
				cmbGender.setSelectedItem("M");
			}
			if(!strList.get(11).isEmpty()&&strList.get(11)!= null&&strList.get(11) != " ") {
				
				try {
					Date d;
					d = dateFormat.parse(strList.get(11));
					dobChooser.setDate(d);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			String blood = strList.get(12);
			if(blood != null && blood.equals("")) {
				cmbBloodG.setSelectedItem(blood);
			}
			String marry = strList.get(13);
			if(null != marry&&!marry.isEmpty()&&!marry.equals("")) {
				cmbMarry.setSelectedItem(marry);
			}
		}

		// I have used this function to get the invoice id on which appoint is being
		// made
		// but have no use so no matter
		columns.clear();
		where.clear();
		columns.add("invoice_id");
		where.put("p_mobile", mobile);
		selectdata = sm.selectData("invoice_table", columns, where);
		if (!selectdata.isEmpty()) {
			txt_invoice_tab2.setText(selectdata.get(0).get(0));
		}

		columns.clear();
		where.clear();
		if (!p_id.isEmpty()) {
			columns.add("date");
			columns.add("doc_name");
			columns.add("doc_path");
			where.put("p_id", p_id);
			String grpdate = " order by id";
			selectdata = sm.selectData("doc_table", columns, where, grpdate);

			if (!selectdata.isEmpty()) {
				docModel.removeAll();
				for (List<String> str : selectdata) {
					docMap = new HashMap<>();
					docMap.put(col1[0], str.get(0));
					docMap.put(col1[1], str.get(1));
					docMap.put(col1[2], Constants.doc_path+str.get(2));
					docModel.addRow(new RowData(docMap));
					docModel.fireTableDataChanged();
				}

			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// String col1[] = { "Date", "Doc Name", "Doc Path" };
		if (e.getSource() == btnSubmit) {
			String filename = txt_docname.getText();
			String filepath = txt_docpath.getText();
			String p_id = txt_regno_tab2.getText();
			String date = dateFormat.format(this.date);
			if (!filename.isEmpty() && !filepath.isEmpty()) {
				if (!p_id.isEmpty()) {
					docMap = new HashMap<>();
					docMap.put(col1[0], date);
					docMap.put(col1[1], filename);
					docMap.put(col1[2], filepath);
					docModel.addRow(new RowData(docMap));
					docModel.fireTableDataChanged();
					where = new HashMap<>();
					
					where.put("date", date);
					where.put("p_id", p_id);
					where.put("doc_name", filename);
					where.put("doc_path", filepath);
					sm.storeInDatabase("doc_table", where);
					showConformMessage(0, "Document saved successfully");
					txt_docname.setText(null);
					txt_docpath.setText(null);
				} else {
					showConformMessage(0, " Please Enter Registration Number First");
				}
			}
		}

		if (e.getSource() == btnbrowse) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File f = fileChooser.getSelectedFile();
			
				String filename = f.getAbsolutePath();
				System.out.println("filepath " + f.getName());
				txt_docpath.setText(filename);
			}

		}

		if (e.getSource() == btnUpdate) {
			String mobie = txt_pmobile_tab2.getText();
			
			if (!mobie.isEmpty()) {
				updatePatient(mobie);
			}

		}

		if (e.getSource() == btnclear) {
			refreshFields();
		}

		if (e.getSource() == chckbxNew) {
			tabbedPane.setSelectedIndex(2);
		}

		if (e.getSource() == txt_ref_name) {
			
		}

		if (e.getSource() == btnNewApp) {
			where = new HashMap<>();
			String pname = txt_pname_tab1.getText();
			String p_id = txt_regno_tab1.getText();
			Date date = dateChooser_app.getDate();
			String app_date = dateFormat.format(date);

			where.put("id", txt_invoice_tab1.getText());
			where.put("p_id", txt_regno_tab1.getText());

			where.put("app_date", app_date);
			where.put("reg_date", reg_date);
			where.put("p_mobile", txt_pmobile_tab1.getText());

			boolean change = false;
			if (chckbxRepeat.isSelected()) {
				where.put("dept", Constants.repeat);
				change = true;

			} else if (chckbxOldPatient.isSelected()) {
				where.put("dept", Constants.old);
				change = true;
			} else if (chckbxCourier.isSelected()) {
				where.put("dept", Constants.courier);
				change = true;
			} else if (chckbxOiling.isSelected()) {
				where.put("dept", Constants.oiling);
				change = true;
			} else {
				JOptionPane.showMessageDialog(null, "Please Select a Type of Appointment first");
				change = false;
			}

			if (change) {
				sm.storeInDatabase("appointment", where);
				if (app_date.equals(dateFormat.format(this.date))) {
					patientListener.onPatientRegister(
							new Patient(app_date, p_id, pname, "", "", "", "", "", "", txt_pmobile_tab1.getText()));
				}

				refreshFields();
			} else {
				JOptionPane.showMessageDialog(null,
						"Appointment is Already made..!!! \n Please Select a different date ");
			}

		}

		if (e.getSource() == btnPrint) {

		}

		if (e.getSource() == btnsaveshow) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String reg_date = dateFormat.format(invoice_date.getDate());
			String p_id = "";
			String referal = "";
			String mesg =null;
			if (!txt_regno_tab2.getText().isEmpty()) {
				p_id = txt_regno_tab2.getText();
			} else {
				p_id = "0000";
			}

			String p_name = txt_pname_tab2.getText();
			if(p_name.isEmpty()) {
				mesg = "Please Enter Patient Name \n";
			}
			String p_add = txt_padd.getText();
			String mobile = txt_pmobile_tab2.getText();
			if(mobile.length()!=10||!mobile.matches("\\d+")) {
				mesg = "Please Enter valid mobile number";
			}
			String p_bill_amt = txt_payment.getText();

			if (txt_ref_name.getSelectedItem() != null) {
				referal = "0";
			} else {
				referal = (String) txt_ref_name.getSelectedItem();
			}

			/*
			 * public Patient(String reg_date, String p_id, String p_name, String
			 * p_bill_amt, String p_amt_due, String amt_paid, String p_doc_name, String
			 * refer_name, String p_add)
			 */
			Patient p;
			p = new Patient(reg_date, p_id, p_name, p_bill_amt, "", "", "", referal, p_add,mobile, txt_district.getText(),
					txt_pincode.getText(), "", "", "");
			if (mesg == null) {
			
				invoice_id_tab2 = txt_invoice_tab2.getText();
				System.out.println("invoice id :" + invoice_id_tab2);
//				new PdfModel().prepareReceipt(invoice_id_tab2, p);
				loadDataInDatabase(p);
			}else {
				showConformMessage(1, mesg);
			}

		}

		if (e.getSource() == btnclose || e.getSource() == btnClose) {
			this.dispose();
		}
		
		if (e.getSource() == btncancel) {
			String str = textArea.getText();
			where = new HashMap<>();
			Map<String,Object> what = new HashMap<>();
			where = new HashMap<>();
			what = new HashMap<>();
			//update appointment set attend = 'cancel' where p_mobile = '656596859' and app_date = 'today';
			where.put("p_id", txt_regno_tab1.getText());
			where.put("app_date", str.substring(0,str.indexOf(" ")));
			what.put("attend", Constants.cancel);
			sm.updateDatabase("appointment", where, what);
			System.out.println("appointment cancel");
		}
		
		if(e.getSource() == btnTablePrint) {
			try {
				table.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("Appointments on:-"
			+dateFormat.format(appdateChooser.getDate())),
						new MessageFormat("Page {0}"),
				        true, null,
				        true, null);
			} catch (HeadlessException | PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void updatePatient(String mobile) {

		where = new HashMap<>();
		Map<String, Object> what = new HashMap<>();
		where.put("p_mobile", mobile);
		what.put("p_id", txt_regno_tab2.getText());
		what.put("occupation", txt_occupation.getText());
		what.put("p_add", txt_padd.getText());
		what.put("p_district", txt_district.getText());
		what.put("pincode", txt_pincode.getText());
		what.put("p_blood", cmbBloodG.getSelectedItem());
		what.put("p_gender", cmbGender.getSelectedItem());
		what.put("p_age", txt_page.getText());
		what.put("amt_paid", txt_payment.getText());
		what.put("refer_id", txt_ref_mob.getText());
		what.put("refer_name",txt_ref_name.getSelectedItem());
		if(dobChooser.getDate() != null) {
			what.put("p_dob", dateFormat.format(dobChooser.getDate()));
		}
		what.put("p_dob", "");
		what.put("marry_stat", cmbMarry.getSelectedItem());
		sm.updateDatabase("patient_table", where, what);

		// update appointment table
		where.clear();
		what.clear();
		where.put("p_mobile", mobile);
		what.put("p_id", txt_regno_tab2.getText());
		// sm.updateDatabase(tableName, where, what)
		int count = sm.updateDatabase("appointment", where, what);

		where.clear();
		what.clear();
		where.put("p_mobile", mobile);
		what.put("payment", txt_payment.getText());
		sm.updateDatabase("invoice_table", where, what);
		System.out.println("count :::" + count);
		showConformMessage(0, "Update Successfull");

	}

	private void showConformMessage(int count, String mesg) {
		// TODO Auto-generated method stub

		if(count == 1) {
			JOptionPane.showMessageDialog(null, mesg,"Alert",JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, mesg);
		}

	}

	public void printComponenet(final JPanel jPanel) {

		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setJobName(" Print Component ");

		pj.setPrintable(new Printable() {
			public int print(Graphics pg, PageFormat pf, int pageNum) {
				if (pageNum > 0) {
					return Printable.NO_SUCH_PAGE;
				}

				Graphics2D g2 = (Graphics2D) pg;
				g2.translate(pf.getImageableX(), pf.getImageableY());
				jPanel.printAll(pg);
				return Printable.PAGE_EXISTS;
			}
		});
		if (pj.printDialog() == false)
			return;

		try {
			pj.print();
		} catch (PrinterException ex) {
			// handle exception
		}
	}

	private void loadDataInDatabase(Patient p) {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		/*
		 * String sql = "insert into invoice_table (" + "date, p_id,doc_id," +
		 * "total_fee,discount,total_payable,payment,gst_apply,due) values " +
		 * "(?,?,?,?,?,?,?,?,?)";
		 */
		String date = dateFormat.format(invoice_date.getDate());
		Map<String, Object> columnsInsert = new HashMap<>();
		columnsInsert.put("invoice_id", invoice_id_tab2);
		columnsInsert.put("date", date);
		columnsInsert.put("p_id", txt_regno_tab2.getText());
		columnsInsert.put("p_mobile", txt_pmobile_tab2.getText());
		
		columnsInsert.put("payment", txt_payment.getText());
		

		sm.storeInDatabase("invoice_table", columnsInsert);
		

	

		columnsInsert = new HashMap<>();
		columnsInsert.put("p_id", txt_regno_tab2.getText());
		columnsInsert.put("p_name", txt_pname_tab2.getText().trim());
		columnsInsert.put("p_mobile", txt_pmobile_tab2.getText().trim());
		Date d1 = dobChooser.getDate();
		if (d1 != null) {
			columnsInsert.put("p_dob", dateFormat.format(d1));
		} else {
			columnsInsert.put("p_dob", "");
		}
		columnsInsert.put("occupation", txt_occupation.getText());
		columnsInsert.put("p_add", txt_padd.getText().trim());
		columnsInsert.put("p_district", txt_district.getText().trim());
		columnsInsert.put("p_district", txt_district.getText());
		columnsInsert.put("pincode", txt_pincode.getText());
		columnsInsert.put("p_blood", cmbBloodG.getSelectedItem().toString());
		columnsInsert.put("p_gender", cmbGender.getSelectedItem().toString());
		columnsInsert.put("p_age", txt_page.getText().trim());
		// columnsInsert.put("p_bill_amt", txt_total_price.getText().trim());
		// columnsInsert.put("p_amt_due", txt_due.getText().trim());
		columnsInsert.put("amt_paid", txt_payment.getText().trim());
		columnsInsert.put("reg_date", dateFormat.format(invoice_date.getDate()));
		if(dobChooser.getDate() != null) {
			columnsInsert.put("p_dob", dateFormat.format(dobChooser.getDate()));
		}
		columnsInsert.put("marry_stat", cmbMarry.getSelectedItem());
		// columnsInsert.put("p_doc_id", Integer.valueOf(txt_dcode.getText()));
		if (!txt_ref_mob.getText().isEmpty()&&txt_ref_name.getSelectedItem()!= "") {
			columnsInsert.put("refer_id", txt_ref_mob.getText());
			columnsInsert.put("refer_name", txt_ref_name.getSelectedItem());
		} else {
			columnsInsert.put("refer_id", 0);
		}
		sm.storeInDatabase("patient_table", columnsInsert);

		patientListener.onPatientRegister(p);

		// make appoint of newly register patient
		columnsInsert = new HashMap<>();
		columnsInsert.put("id", invoice_id_tab2);
		System.out.println("invoice id   " + invoice_id_tab2);
		columnsInsert.put("p_id", txt_regno_tab2.getText());
		columnsInsert.put("app_date", dateFormat.format(invoice_date.getDate()));
		columnsInsert.put("reg_date", dateFormat.format(invoice_date.getDate()));
		columnsInsert.put("p_mobile", txt_pmobile_tab2.getText());
		sm.storeInDatabase("appointment", columnsInsert);
		showConformMessage(0, "Data saved SuccessFully");
		refreshFields();
	}

	private void refreshFields() {
		txt_invoice_tab1.setText(JavaConnect.uniqueCurrentTimeStamp());
		txt_invoice_tab2.setText(JavaConnect.uniqueCurrentTimeStamp());
//		docModel.removeAll();
		txt_pname_tab1.setText("");
		txt_pname_tab2.setText("");
		txt_pmobile_tab1.setText("");
		txt_pmobile_tab2.setText("");
		txt_page.setText("");
		txt_padd.setText("");
		txt_payment.setText("");
		txt_regno_tab2.setText("");
		chckbxRepeat.setSelected(false);
		chckbxOldPatient.setSelected(false);
		chckbxCourier.setSelected(false);
		chckbxOiling.setSelected(false);
		txt_occupation.setText(null);
		txt_district.setText(null);
		txt_pincode.setText(null);
		txt_ref_id.setText(null);
		txt_ref_mob.setText(null);

	}

	// i have not used this yet because of if the user wants multiple appointments
	// on different depts
	private boolean checkAlreadyRegister() {

		if (dateChooser_app.getDate() != null) {
			String dateStr = dateFormat.format(dateChooser_app.getDate());
			List<String> columns = new ArrayList<>();
			Map<String, Object> where = new HashMap<>();
			columns.add("p_id");
			where.put("p_id", txt_regno_tab1.getText());
			where.put("app_date", dateStr);
			selectdata = sm.selectData("appointment", columns, where);
			if (!selectdata.isEmpty()) {
				return true;
			}

		}
		return false;
	}

	private class RowListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (e.getValueIsAdjusting()) {
				return;
			}

			String filepath = (String) docModel.getValueAt(doc_table.getSelectedRow(), 2);
			if (!filepath.isEmpty() && filepath != null) {
				new OpenFile().getFileByPath(filepath);
			}
		}

	}

	private void tab1() {
		panel_appoint = new JPanel();
		tabbedPane.addTab("Make Appointment", null, panel_appoint, null);
		panel_appoint.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, null, null, null));
		panel.setBounds(10, 11, 551, 109);
		panel_appoint.add(panel);

		JLabel label_26 = new JLabel("Invoice No.");
		label_26.setHorizontalAlignment(SwingConstants.CENTER);
		label_26.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_26.setBounds(0, 11, 123, 20);
		panel.add(label_26);

		txt_invoice_tab1 = new JTextField();
		txt_invoice_tab1.setText(JavaConnect.uniqueCurrentTimeStamp());
		txt_invoice_tab1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_invoice_tab1.setColumns(10);
		txt_invoice_tab1.setBounds(122, 11, 96, 25);
		panel.add(txt_invoice_tab1);

		JLabel label_27 = new JLabel("Patient Name");
		label_27.setHorizontalAlignment(SwingConstants.CENTER);
		label_27.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_27.setBounds(0, 40, 123, 20);
		panel.add(label_27);

		JLabel label_28 = new JLabel("Doctor Name");
		label_28.setHorizontalAlignment(SwingConstants.CENTER);
		label_28.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_28.setBounds(228, 40, 106, 20);
		panel.add(label_28);

		JLabel label_29 = new JLabel("Registartion No.");
		label_29.setHorizontalAlignment(SwingConstants.CENTER);
		label_29.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_29.setBounds(228, 11, 112, 20);
		panel.add(label_29);

		txt_regno_tab1 = new JTextField();
		txt_regno_tab1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_regno_tab1.setColumns(10);
		txt_regno_tab1.getDocument().putProperty("patientID", txt_regno_tab1);
		txt_regno_tab1.getDocument().addDocumentListener(this);
		txt_regno_tab1.setBounds(350, 10, 96, 25);
		panel.add(txt_regno_tab1);

		txt_pname_tab1 = new JTextField();
		txt_pname_tab1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_pname_tab1.setColumns(10);
		txt_pname_tab1.setBounds(122, 40, 96, 25);
		panel.add(txt_pname_tab1);

		txt_dname = new JTextField();
		txt_dname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_dname.setColumns(10);
		txt_dname.setBounds(350, 39, 96, 25);
		panel.add(txt_dname);

		JLabel lblMobileNo = new JLabel("Mobile No.");
		lblMobileNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMobileNo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMobileNo.setBounds(0, 71, 123, 20);
		panel.add(lblMobileNo);

		txt_pmobile_tab1 = new JTextField();
		txt_pmobile_tab1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_pmobile_tab1.setColumns(10);
		txt_pmobile_tab1.setBounds(122, 70, 96, 25);
		panel.add(txt_pmobile_tab1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 122, 551, 191);
		panel_appoint.add(panel_1);
		panel_1.setLayout(null);

		dateChooser_app = new JDateChooser();
		dateChooser_app.setDateFormatString("dd-MM-yyyy");
		dateChooser_app.setBounds(368, 44, 146, 24);
		panel_1.add(dateChooser_app);

		btnNewApp = new JButton("Make Appointment");
		btnNewApp.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewApp.setBounds(172, 73, 162, 25);
		btnNewApp.addActionListener(this);
		panel_1.add(btnNewApp);

		btnPrint = new JButton("Print");
		btnPrint.addActionListener(this);
		btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnPrint.setBounds(344, 73, 85, 25);
		panel_1.add(btnPrint);

		btnClose = new JButton("Close");
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnClose.addActionListener(this);
		btnClose.setBounds(439, 73, 92, 25);
		panel_1.add(btnClose);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_7.setBounds(0, 0, 162, 191);
		panel_1.add(panel_7);
		panel_7.setLayout(null);

		chckbxRepeat = new JCheckBox("Repeat");
		chckbxRepeat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxRepeat.setBounds(16, 51, 79, 23);
		panel_7.add(chckbxRepeat);

		chckbxOldPatient = new JCheckBox("Old Patient");
		chckbxOldPatient.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxOldPatient.setBounds(16, 77, 117, 23);
		panel_7.add(chckbxOldPatient);

		chckbxCourier = new JCheckBox("Courier");
		chckbxCourier.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxCourier.setBounds(16, 103, 117, 23);
		panel_7.add(chckbxCourier);

		chckbxOiling = new JCheckBox("Oiling");
		chckbxOiling.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxOiling.setBounds(16, 129, 117, 23);

		panel_7.add(chckbxOiling);

		JLabel lblNewLabel = new JLabel("Type Of Appointment");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 157, 29);
		panel_7.add(lblNewLabel);

		chckbxNew = new JCheckBox("New");
		chckbxNew.addActionListener(this);
		chckbxNew.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNew.setBounds(16, 155, 67, 23);
		panel_7.add(chckbxNew);

		JLabel lblNewLabel_1 = new JLabel("   Please do not Make Appointment Without Entering\r\n Registration Number");
		lblNewLabel_1.setBounds(169, 13, 372, 20);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_1.setForeground(Color.RED);

		JLabel lblDateOfAppointment = new JLabel("Date of Appointment");
		lblDateOfAppointment.setBounds(172, 44, 156, 25);
		panel_1.add(lblDateOfAppointment);
		lblDateOfAppointment.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateOfAppointment.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(571, 11, 388, 114);
		panel_appoint.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		btncancel = new JButton("Cancel Appointment");
		btncancel.addActionListener(this);
		btncancel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btncancel.setBounds(797, 136, 162, 25);
		panel_appoint.add(btncancel);
	}

	private void tab2() {

		JPanel panel_new_reg = new JPanel();
		tabbedPane.addTab("Patient Entry", null, panel_new_reg, null);
		panel_new_reg.setLayout(null);
		
		Object[] objects = sm.getPatientNames();

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 0, 951, 634);
		panel_new_reg.add(panel_5);
		panel_5.setLayout(null);

		cmbBloodG = new JComboBox();
		cmbBloodG.setModel(new DefaultComboBoxModel(new String[] { "A+", "B+", "B-", "0+", "O-" }));
		cmbBloodG.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cmbBloodG.setBounds(334, 227, 85, 25);
		panel_5.add(cmbBloodG);

		JLabel label_14 = new JLabel("Blood Group");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_14.setBounds(227, 227, 97, 25);
		panel_5.add(label_14);

		cmbMarry = new JComboBox();
		cmbMarry.setModel(new DefaultComboBoxModel(new String[] { "Unmarried", "Married", "Single" }));
		cmbMarry.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cmbMarry.setBounds(105, 227, 104, 25);
		panel_5.add(cmbMarry);

		JLabel label_15 = new JLabel("Martial Status");
		label_15.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_15.setBounds(10, 227, 97, 25);
		panel_5.add(label_15);

		JLabel lblYear = new JLabel("year");
		lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYear.setForeground(Color.RED);
		lblYear.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lblYear.setBounds(20, 212, 75, 15);
		panel_5.add(lblYear);

		txt_page = new JTextField();
		txt_page.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_page.setColumns(10);
		txt_page.setBounds(105, 193, 59, 25);
		panel_5.add(txt_page);

		JLabel label_17 = new JLabel("Age");
		label_17.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_17.setBounds(10, 193, 85, 25);
		panel_5.add(label_17);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] { "Year" }));
		comboBox_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox_4.setBounds(174, 193, 55, 26);
		panel_5.add(comboBox_4);

		JLabel label_18 = new JLabel("DOB");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_18.setBounds(230, 193, 45, 25);
		panel_5.add(label_18);

		dobChooser = new JDateChooser();
		dobChooser.setDateFormatString("dd-MM-yyyy");
		dobChooser.setBounds(285, 193, 139, 24);
		panel_5.add(dobChooser);

		JLabel label_19 = new JLabel("Gender");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_19.setBounds(417, 193, 68, 25);
		panel_5.add(label_19);

		cmbGender = new JComboBox();
		cmbGender.setModel(new DefaultComboBoxModel(new String[] { "M", "F" }));
		cmbGender.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cmbGender.setBounds(476, 196, 49, 20);
		panel_5.add(cmbGender);

		txt_padd = new JTextField();
		txt_padd.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_padd.setColumns(10);
		txt_padd.setBounds(105, 126, 186, 25);
		panel_5.add(txt_padd);

		JLabel label_20 = new JLabel("Address");
		label_20.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_20.setBounds(10, 126, 281, 25);
		panel_5.add(label_20);

		JLabel label_21 = new JLabel("Patient Name");
		label_21.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_21.setBounds(10, 95, 85, 25);
		panel_5.add(label_21);

		txt_pname_tab2 = new JTextField();
		txt_pname_tab2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_pname_tab2.setColumns(10);
		txt_pname_tab2.setBounds(105, 95, 186, 25);
		panel_5.add(txt_pname_tab2);

		JLabel label_22 = new JLabel("Mobile");
		label_22.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_22.setBounds(297, 95, 86, 25);
		panel_5.add(label_22);

		txt_pmobile_tab2 = new JTextFieldLimit(10);
		txt_pmobile_tab2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_pmobile_tab2.setColumns(10);
		txt_pmobile_tab2.getDocument().addDocumentListener(this);
		txt_pmobile_tab2.getDocument().putProperty("txt_pmobile1", txt_pmobile_tab2);
		txt_pmobile_tab2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String mobile = txt_pmobile_tab2.getText();
				if (mobile.length()<10) {
					showConformMessage(0, "Enter Valid Mobile Number..!!!");
					txt_pmobile_tab2.requestFocus();
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		txt_pmobile_tab2.setBounds(381, 95, 144, 25);
		panel_5.add(txt_pmobile_tab2);

		txt_invoice_tab2 = new JTextField();
		txt_invoice_tab2.setText(JavaConnect.uniqueCurrentTimeStamp());
		txt_invoice_tab2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_invoice_tab2.setEditable(false);
		txt_invoice_tab2.setColumns(10);
		txt_invoice_tab2.setBounds(105, 62, 129, 25);
		panel_5.add(txt_invoice_tab2);

		JLabel label_23 = new JLabel("Invoice");
		label_23.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_23.setBounds(10, 63, 97, 25);
		panel_5.add(label_23);

		JLabel label_24 = new JLabel("Reg. Date");
		label_24.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_24.setBounds(10, 32, 97, 25);
		panel_5.add(label_24);

		invoice_date = new JDateChooser();
		invoice_date.setDateFormatString("dd-MM-yyyy");
		invoice_date.getDateEditor().setDate(date);
		invoice_date.setBorder(new LineBorder(new Color(0, 0, 0)));
		invoice_date.setBounds(105, 32, 139, 24);
		invoice_date.setMinSelectableDate(date);
		panel_5.add(invoice_date);

		JLabel label_25 = new JLabel("Registartion No.");
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_25.setBounds(244, 59, 139, 25);
		panel_5.add(label_25);

		txt_regno_tab2 = new JTextField();
		txt_regno_tab2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_regno_tab2.setColumns(10);
		txt_regno_tab2.setBounds(381, 59, 144, 25);
		panel_5.add(txt_regno_tab2);

		txt_regno_tab2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String id = txt_regno_tab2.getText();
				if (sm.checkRegId(id)) {
					showConformMessage(0, "This Registration Number Already Exist");
					txt_regno_tab2.requestFocus();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JLabel lblBasicDetails = new JLabel("Basic Details");
		lblBasicDetails.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblBasicDetails.setBounds(10, 0, 219, 26);
		panel_5.add(lblBasicDetails);
		Image image = new ImageIcon(this.getClass().getResource("/attendence.png")).getImage();

		JLabel lblRefrence = new JLabel("Reference");
		lblRefrence.setBounds(10, 259, 97, 26);
		panel_5.add(lblRefrence);
		lblRefrence.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JLabel lblMobileNo_1 = new JLabel("Mobile No.");
		lblMobileNo_1.setBounds(10, 286, 111, 26);
		panel_5.add(lblMobileNo_1);
		lblMobileNo_1.setFont(new Font("Times New Roman", Font.BOLD, 12));

		txt_ref_mob = new JTextField();
		txt_ref_mob.getDocument().putProperty("txt_ref_mob", txt_ref_mob);
		txt_ref_mob.getDocument().addDocumentListener(this);
		txt_ref_mob.setBounds(122, 287, 115, 24);
		panel_5.add(txt_ref_mob);
		txt_ref_mob.setToolTipText("in %");
		txt_ref_mob.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_ref_mob.setColumns(10);

		JLabel label_12 = new JLabel("Refered ID");
		label_12.setBounds(10, 315, 102, 26);
		panel_5.add(label_12);
		label_12.setFont(new Font("Times New Roman", Font.BOLD, 11));

		txt_ref_id = new JTextField();
		txt_ref_id.setBounds(121, 318, 116, 24);
		panel_5.add(txt_ref_id);
		txt_ref_id.setToolTipText("in %");
		txt_ref_id.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_ref_id.setColumns(10);

		JLabel label_13 = new JLabel("Referal Name");
		label_13.setBounds(10, 351, 102, 26);
		panel_5.add(label_13);
		label_13.setFont(new Font("Times New Roman", Font.BOLD, 11));
		txt_ref_name = new AutoComplete(objects);
		txt_ref_name.setBounds(119, 352, 121, 25);
		panel_5.add(txt_ref_name);
		txt_ref_name.setEditable(true);

		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOccupation.setBounds(297, 126, 86, 25);
		panel_5.add(lblOccupation);

		txt_occupation = new JTextField();
		txt_occupation.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_occupation.setColumns(10);
		txt_occupation.setBounds(381, 126, 144, 25);
		panel_5.add(txt_occupation);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(535, 0, 406, 139);
		panel_5.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblSaveDocuments = new JLabel("Save Documents");
		lblSaveDocuments.setBounds(10, 0, 153, 26);
		panel_3.add(lblSaveDocuments);
		lblSaveDocuments.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JLabel lblDocName = new JLabel("Doc. Name");
		lblDocName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDocName.setBounds(10, 37, 129, 25);
		panel_3.add(lblDocName);

		txt_docname = new JTextField();
		txt_docname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_docname.setColumns(10);
		txt_docname.setBounds(147, 37, 249, 25);
		panel_3.add(txt_docname);

		JLabel lblDocPath = new JLabel("Doc. Path");
		lblDocPath.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDocPath.setBounds(10, 73, 129, 25);
		panel_3.add(lblDocPath);

		txt_docpath = new JTextField(10);
		txt_docpath.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_docpath.setColumns(10);
		txt_docpath.setBounds(147, 73, 197, 25);
		panel_3.add(txt_docpath);

		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSubmit.setBounds(309, 102, 87, 26);
		panel_3.add(btnSubmit);

		btnbrowse = new JButton("Broswe");
		btnbrowse.addActionListener(this);
		btnbrowse.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnbrowse.setBounds(354, 73, 42, 26);
		panel_3.add(btnbrowse);

		JLabel label_8 = new JLabel("Payment");
		label_8.setBounds(0, 520, 87, 26);
		panel_5.add(label_8);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Times New Roman", Font.BOLD, 12));

		JLabel label_10 = new JLabel("(Payment by Cash)");
		label_10.setBounds(0, 538, 96, 24);
		panel_5.add(label_10);
		label_10.setForeground(Color.RED);
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 9));

		txt_payment = new JTextField();
		txt_payment.setBounds(121, 520, 123, 24);
		panel_5.add(txt_payment);
		txt_payment.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_payment.setColumns(10);

		txt_total_gst = new JTextField();
		txt_total_gst.setBounds(122, 566, 119, 24);
		panel_5.add(txt_total_gst);
		txt_total_gst.setToolTipText("in %");
		txt_total_gst.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_total_gst.setColumns(10);

		JLabel label_1 = new JLabel("GST Applicable");
		label_1.setBounds(0, 565, 102, 26);
		panel_5.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 11));

		btnPrint_1 = new JButton("Print");
		btnPrint_1.setBounds(0, 602, 63, 26);
		panel_5.add(btnPrint_1);
		btnPrint_1.setFont(new Font("Times New Roman", Font.BOLD, 12));

		btnsaveshow = new JButton("Save & Show");
		btnsaveshow.setBounds(73, 602, 115, 26);
		panel_5.add(btnsaveshow);
		btnsaveshow.setFont(new Font("Times New Roman", Font.BOLD, 12));

		btnclear = new JButton("Clear");
		btnclear.setBounds(198, 602, 81, 26);
		panel_5.add(btnclear);
		btnclear.addActionListener(this);
		btnclear.setFont(new Font("Times New Roman", Font.BOLD, 12));

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(289, 602, 81, 26);
		panel_5.add(btnUpdate);
		btnUpdate.addActionListener(this);
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 12));

		JButton btndelete = new JButton("Delete");
		btndelete.setBounds(380, 602, 87, 26);
		panel_5.add(btndelete);
		btndelete.setFont(new Font("Times New Roman", Font.BOLD, 12));

		btnclose = new JButton("Close");
		btnclose.setBounds(476, 602, 96, 26);
		panel_5.add(btnclose);
		btnclose.setFont(new Font("Times New Roman", Font.BOLD, 12));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(535, 148, 406, 442);
		panel_5.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		doc_table = new JTable();

		docModel = new MyTableModel();
		docModel.setColumnNames(col1);
		doc_table.setModel(docModel);

		scrollPane.setViewportView(doc_table);
		JTableHeader hone = doc_table.getTableHeader();
		hone.setFont(new Font("Times New Roman", Font.BOLD, 15));
		doc_table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		doc_table.setRowHeight(20);
		doc_table.getColumnModel().getColumn(0).setPreferredWidth(10);
		doc_table.getColumnModel().getColumn(2).setPreferredWidth(20);
		doc_table.getSelectionModel().addListSelectionListener(new RowListener());
		panel_2.add(BorderLayout.NORTH, hone);

		JLabel lblDistrict = new JLabel("District");
		lblDistrict.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDistrict.setBounds(10, 162, 86, 25);
		panel_5.add(lblDistrict);

		txt_district = new JTextField();
		txt_district.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_district.setColumns(10);
		txt_district.setBounds(105, 162, 97, 25);
		panel_5.add(txt_district);

		JLabel lblPincode = new JLabel("Pincode");
		lblPincode.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPincode.setBounds(212, 162, 59, 25);
		panel_5.add(lblPincode);

		txt_pincode = new JTextField();
		txt_pincode.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txt_pincode.setColumns(10);
		txt_pincode.setBounds(285, 162, 134, 25);
		panel_5.add(txt_pincode);
		
		JLabel lblUseMobileNo = new JLabel("USE MOBILE NO. FOR REFRENCE");
		lblUseMobileNo.setForeground(Color.RED);
		lblUseMobileNo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUseMobileNo.setBounds(117, 259, 385, 26);
		panel_5.add(lblUseMobileNo);
		
		
		btnclose.addActionListener(this);
		btnsaveshow.addActionListener(this);
		txt_ref_name.addActionListener(this);
	}

	
	private void tab3() {
		JPanel appointment_stats = new JPanel();
		tabbedPane.addTab("Appointment Stats", null, appointment_stats, null);
		appointment_stats.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(5, 50, 761, 584);
		appointment_stats.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		appoint_model = new MyTableModel();
		appoint_model.setColumnNames(col);
		table.setModel(appoint_model);
		//table.setDefaultRenderer(Object.class, new AppointmentRenderer());
		
		table.getColumnModel().getColumn(0).setMaxWidth(55);
		table.getColumnModel().getColumn(2).setMinWidth(200);
		table.getColumnModel().getColumn(2).setMaxWidth(200);
		table.getColumnModel().getColumn(3).setMinWidth(200);
		table.getColumnModel().getColumn(3).setMaxWidth(200);
		//{ "S.NO.", "PT.NAME", "REG.NO.", "M.NO.", };
		
		
		scrollPane.setViewportView(table);
		JTableHeader hone = table.getTableHeader();
		hone.setFont(new Font("Times New Roman", Font.BOLD, 15));
		table.setRowHeight(20);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 10));

		panel_1.add(BorderLayout.NORTH, hone);

		JLabel lblNewLabel_3 = new JLabel("On Date");
		lblNewLabel_3.setBounds(379, 10, 54, 25);
		appointment_stats.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 12));

		appdateChooser = new JDateChooser();
		appdateChooser.setBounds(443, 10, 146, 24);
		appointment_stats.add(appdateChooser);
		appdateChooser.setDateFormatString("dd-MM-yyyy");
		appdateChooser.setDate(this.date);

		JLabel lblNewLabel_2 = new JLabel("    Appointment Sheet");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(5, 10, 364, 29);
		appointment_stats.add(lblNewLabel_2);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(776, 49, 183, 289);
		appointment_stats.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("NEW");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 63, 96, 20);
		panel.add(lblNewLabel_4);

		txt_new = new JTextField();
		txt_new.setHorizontalAlignment(SwingConstants.CENTER);
		txt_new.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txt_new.setBounds(116, 63, 57, 20);
		panel.add(txt_new);
		txt_new.setColumns(10);

		JLabel lblRepeat = new JLabel("REPEAT");
		lblRepeat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblRepeat.setBounds(10, 89, 96, 20);
		panel.add(lblRepeat);

		txt_repeat = new JTextField();
		txt_repeat.setHorizontalAlignment(SwingConstants.CENTER);
		txt_repeat.setBackground(Color.YELLOW);
		txt_repeat.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txt_repeat.setColumns(10);
		txt_repeat.setBounds(116, 89, 57, 20);
		panel.add(txt_repeat);

		JLabel lblOld = new JLabel("OLD");
		lblOld.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOld.setBounds(10, 113, 96, 20);
		panel.add(lblOld);

		txt_old = new JTextField();
		txt_old.setHorizontalAlignment(SwingConstants.CENTER);
		txt_old.setForeground(Color.BLACK);
		txt_old.setBackground(Color.WHITE);
		txt_old.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txt_old.setColumns(10);
		txt_old.setBounds(116, 113, 57, 20);
		panel.add(txt_old);

		JLabel lblCourier = new JLabel("COURIER");
		lblCourier.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCourier.setBounds(10, 140, 96, 20);
		panel.add(lblCourier);

		txt_courier = new JTextField();
		txt_courier.setHorizontalAlignment(SwingConstants.CENTER);
		txt_courier.setForeground(Color.BLACK);
		txt_courier.setBackground(Color.WHITE);
		txt_courier.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txt_courier.setColumns(10);
		txt_courier.setBounds(116, 140, 57, 20);
		panel.add(txt_courier);

		JLabel lblCancel = new JLabel("CANCEL");
		lblCancel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCancel.setBounds(10, 258, 96, 20);
		panel.add(lblCancel);

		txt_cancel = new JTextField();
		txt_cancel.setHorizontalAlignment(SwingConstants.CENTER);
		txt_cancel.setForeground(Color.WHITE);
		txt_cancel.setBackground(Color.LIGHT_GRAY);
		txt_cancel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txt_cancel.setColumns(10);
		txt_cancel.setBounds(116, 258, 57, 20);
		panel.add(txt_cancel);

		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTotal.setBounds(10, 11, 96, 20);
		panel.add(lblTotal);

		txt_total = new JTextField();
		txt_total.setHorizontalAlignment(SwingConstants.CENTER);
		txt_total.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txt_total.setColumns(10);
		txt_total.setBounds(116, 11, 57, 20);
		panel.add(txt_total);

		JLabel lblCome = new JLabel("COME");
		lblCome.setBackground(SystemColor.window);
		lblCome.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCome.setBounds(10, 35, 96, 20);
		panel.add(lblCome);

		txt_present = new JTextField();
		txt_present.setForeground(Color.WHITE);
		txt_present.setBackground(Color.RED);
		txt_present.setHorizontalAlignment(SwingConstants.CENTER);
		txt_present.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txt_present.setColumns(10);
		txt_present.setBounds(116, 35, 57, 20);
		panel.add(txt_present);
		
		JLabel lblOiling = new JLabel("Oiling");
		lblOiling.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOiling.setBounds(10, 169, 96, 20);
		panel.add(lblOiling);
		
		txt_oil = new JTextField();
		txt_oil.setHorizontalAlignment(SwingConstants.CENTER);
		txt_oil.setBackground(Color.WHITE);
		txt_oil.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txt_oil.setColumns(10);
		txt_oil.setBounds(116, 169, 57, 20);
		panel.add(txt_oil);
		
		JLabel lblAbsent = new JLabel("ABSENT");
		lblAbsent.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblAbsent.setBounds(10, 219, 96, 20);
		panel.add(lblAbsent);
		
		txt_absent = new JTextField();
		txt_absent.setHorizontalAlignment(SwingConstants.CENTER);
		txt_absent.setForeground(Color.WHITE);
		txt_absent.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txt_absent.setColumns(10);
		txt_absent.setBackground(Color.MAGENTA);
		txt_absent.setBounds(116, 219, 57, 20);
		panel.add(txt_absent);
		
		JLabel label_1 = new JLabel("");
		label_1.setBackground(Color.GREEN);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_1.setBounds(905, 459, 54, 20);
		appointment_stats.add(label_1);
		
		btnTablePrint = new JButton("PRINT TABLE");
		btnTablePrint.addActionListener(this);
		btnTablePrint.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnTablePrint.setBounds(786, 349, 173, 23);
		appointment_stats.add(btnTablePrint);
		setDataInAppointmentTable(dateFormat.format(appdateChooser.getDate()));

	}
private String appdate = null;
private JTextField txt_reg_tab4;
private JTextField txt_name_tab4;
private JTextField txt_mobile_tab4;
private JTextField textField_3;
private JTable table_tab4;

	private void setListener() {
		appdateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent e) {
				// TODO Auto-generated method stub
				if ("date".equals(e.getPropertyName())) {
					appoint_model.removeAll();
					txt_total.setText(null);
					txt_present.setText(null);
					txt_old.setText(null);
					txt_oil.setText(null);
					txt_courier.setText(null);
					txt_repeat.setText(null);
					txt_cancel.setText(null);
					txt_new.setText(null);
					txt_absent.setText(null);
					setDataInAppointmentTable(dateFormat.format(e.getNewValue()));
				}
			}
		});
	}

	private void setDataInAppointmentTable(String date) {
		// String col[] = { "S.NO.","PT.NAME","REG.NO.","M.NO." };
		//{ "S.NO.", "PT.NAME", "REG.NO.", "M.NO.","status"
		//,"dept","attend" };
		appdate = date;
		columns = new ArrayList<>();
		where = new HashMap<>();
		Map<String, Object> tabledata;

		columns.add("p_id");//0
		columns.add("p_mobile");//1
		columns.add("dept");//2
		columns.add("attend");//3
		columns.add("status");//4
		where.put("app_date", date);
		selectdata = sm.selectData("appointment", columns, where);
		
		if (!selectdata.isEmpty()) {
			table.setDefaultRenderer(Object.class, new AppointmentRenderer(selectdata));
			int i = 0;
			int come = 0;
			int newp = 0, rep = 0,old =0, courier = 0,cancel = 0,oil =0, absent=0;;
			for (List<String> strList : selectdata) {
				tabledata = new HashMap<>();
				tabledata.put(col[0], ++i);
				String p_id = strList.get(0);
				if (!p_id.isEmpty()) {
					tabledata.put(col[1], sm.searchNameByID(p_id));
					tabledata.put(col[2], p_id);
				} else {//if the p_id is not present than search with mobile number
					String p_mobile = strList.get(1);
					tabledata.put(col[1], sm.searchNameMobile(p_mobile));
					tabledata.put(col[2], "new");

				}
				tabledata.put(col[3], strList.get(1));
			
				
				
				appoint_model.addRow(new RowData(tabledata));
				
				txt_total.setText(String.valueOf(i));
				String dept = strList.get(2);
				String attend = strList.get(3);
				String stat = strList.get(4);
				switch(dept) {
				case Constants.old:
					old++;
				break;
				case "new":
					newp++;
				break;
				case Constants.repeat:
					rep++;
				break;
				case Constants.courier:
					courier++;
				break;
				case Constants.oiling:
					oil++;
				}
				if (attend.equals(Constants.cancel)&&stat.equals("0")) {
					cancel++;
				}
				if (!attend.equals(Constants.cancel)&&stat.equals("0")) {
					absent++;
				}
				if (attend.equals(Constants.present)||stat.equals("1")) {
					come++;
				}
				txt_old.setText(String.valueOf(old));
				txt_repeat.setText(String.valueOf(rep));
				txt_courier.setText(String.valueOf(courier));
				txt_new.setText(String.valueOf(newp));
				txt_present.setText(String.valueOf(come));
				txt_cancel.setText(String.valueOf(cancel));
				txt_oil.setText(String.valueOf(oil));
				txt_absent.setText(String.valueOf(absent));
			}
		}else {
			showConformMessage(0, "No Data Avaliable..!!");
		}
	}
	
	public class AppointmentRenderer implements TableCellRenderer {
		
		JTextField label;
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		List<List<String>> selectdata;
		Date date = new Date();
		
		
		public AppointmentRenderer(List<List<String>> selectdata) {
			super();
			this.selectdata = selectdata;
		}


		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected
				, boolean hasFocus, int row,
				int column) {
			
			
			label = new JTextField();
			label.setFont(new Font("Times New Roman", Font.BOLD, 10));
			if (value != null) {
				label.setText(value.toString());
				//String col[] = { "S.NO.", "PT.NAME", "REG.NO.", "M.NO.","status","dept","attend" };
					Object val = table.getValueAt(row, 2);
				//564
					
					/*columns.add("p_id");//0
					columns.add("p_mobile");//1
					columns.add("dept");//2
					columns.add("attend");//3
					columns.add("status");//4
*/				
					String dept = selectdata.get(row).get(2);
					String attend = selectdata.get(row).get(3);
					String stat = selectdata.get(row).get(4);
					switch(dept) {
					case Constants.old:
						
					break;
					case "new":
						
					break;
					case Constants.repeat:
						label.setBackground(Color.YELLOW);
					break;
					case Constants.courier:
						
					break;
					case Constants.oiling:
						
						break;
					}
					System.out.println("value of stat"+stat);
					if (attend.equals(Constants.cancel)&&stat.equals("0")) {
						System.out.println("value cancelled"+val);
						label.setBackground(Color.GRAY);
						label.setForeground(Color.WHITE);
					}
					if (!attend.equals(Constants.cancel)&&stat.equals("0")) {
						label.setBackground(Color.MAGENTA);
						label.setForeground(Color.WHITE);
					}
					if (attend.equals(Constants.present)||stat.equals("1")) {
						System.out.println("value present "+val);
						label.setBackground(Color.RED);
						label.setForeground(Color.WHITE);
					}
				
				
				/*String strdate = dateFormat.format(date);
				System.out.println("Followup rendered"+val);*/
				
				/*try {
					Date date1 = dateFormat.parse(String.valueOf(val));
					if (date1.before(date)&&!val.equals(strdate)) {
						label.setBackground(Color.RED);
						label.setForeground(Color.WHITE);
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				
			}
			return label;
			
		}
		
		

	}
	
	
	public void remoteValueFetch(String mobile) {
		tabbedPane.setSelectedIndex(2);
		txt_pmobile_tab2.setText(mobile);
	}
}
