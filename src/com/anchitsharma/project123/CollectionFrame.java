package com.anchitsharma.project123;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import com.utility.MyTableModel;
import com.utility.RowData;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.model.Constants;
import com.model.SearchModels;
import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class CollectionFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private MyTableModel collection_model;
	private String cols[] = { "Date", "Fees", "Medicine", "Oiling" };
	private List<String> columns, grpdate;
	private Map<String, Object> where, tabledata;
	private List<List<String>> selectdata;
	private SearchModels sm = new SearchModels();
	private JTextField txt_total_fees, txt_username;
	private JTextField txt_total_med;
	private JTextField txt_total_oil;
	private JTextField txt_grand_total;

	private double tFees, tMedicine, tOiling, tGrand;
	Calendar cal = Calendar.getInstance();
	private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private Date date = new Date();
	private JDateChooser fromDateChooser;
	private JDateChooser toDateChooser;
	private JButton btnSearch;
	/*
		*//**
			 * Launch the application.
			 *//*
				 * public static void main(String[] args) { EventQueue.invokeLater(new
				 * Runnable() { public void run() { try { CollectionFrame frame = new
				 * CollectionFrame(); frame.setVisible(true); } catch (Exception e) {
				 * e.printStackTrace(); } } }); }
				 */

	/**
	 * Create the frame.
	 */
	public CollectionFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 0, 1036, 716);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, null, null, null));
		panel.setBounds(10, 55, 1000, 534);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		collection_model = new MyTableModel();
		collection_model.setColumnNames(cols);
		table = new JTable();
		table.setModel(collection_model);
		scrollPane.setViewportView(table);
		JTableHeader header = table.getTableHeader();
		table.setRowHeight(20);
		table.setFont(new Font("Helvetica", Font.PLAIN, 15));
		header.setFont(new Font("Helvetica", Font.BOLD, 15));
		panel.add(BorderLayout.NORTH, header);

		JLabel label = new JLabel("From Date");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(408, 11, 94, 25);
		contentPane.add(label);

		toDateChooser = new JDateChooser();
		toDateChooser.setDateFormatString("dd-MM-yyyy");
		toDateChooser.setBounds(507, 11, 143, 25);
		contentPane.add(toDateChooser);

		JLabel label_1 = new JLabel("To Date");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(673, 11, 94, 25);
		contentPane.add(label_1);

		fromDateChooser = new JDateChooser();
		fromDateChooser.setDateFormatString("dd-MM-yyyy");
		fromDateChooser.setBounds(782, 11, 143, 25);
		contentPane.add(fromDateChooser);

		JLabel lblCollection = new JLabel("Collection ");
		lblCollection.setBackground(new Color(255, 255, 255));
		lblCollection.setForeground(new Color(255, 0, 0));
		lblCollection.setHorizontalAlignment(SwingConstants.CENTER);
		lblCollection.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblCollection.setBounds(10, 11, 131, 33);
		contentPane.add(lblCollection);

		JButton button = new JButton("Save & Print");
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(789, 641, 115, 26);
		contentPane.add(button);

		JButton button_2 = new JButton("Close");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CollectionFrame.this.dispose();
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(914, 641, 96, 26);
		contentPane.add(button_2);

		JLabel lblFeesTotal = new JLabel("Fees Total");
		lblFeesTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblFeesTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFeesTotal.setBounds(198, 592, 86, 25);
		contentPane.add(lblFeesTotal);

		txt_total_fees = new JTextField();
		txt_total_fees.setEditable(false);
		txt_total_fees.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_total_fees.setBounds(339, 592, 86, 25);
		contentPane.add(txt_total_fees);
		txt_total_fees.setColumns(10);

		JLabel lblMedicineTotal = new JLabel("Medicine Total");
		lblMedicineTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicineTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMedicineTotal.setBounds(493, 592, 101, 25);
		contentPane.add(lblMedicineTotal);

		txt_total_med = new JTextField();
		txt_total_med.setEditable(false);
		txt_total_med.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_total_med.setColumns(10);
		txt_total_med.setBounds(649, 592, 86, 25);
		contentPane.add(txt_total_med);

		JLabel lblOilingTotal = new JLabel("Oiling Total");
		lblOilingTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblOilingTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOilingTotal.setBounds(776, 592, 96, 25);
		contentPane.add(lblOilingTotal);

		txt_total_oil = new JTextField();
		txt_total_oil.setEditable(false);
		txt_total_oil.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_total_oil.setColumns(10);
		txt_total_oil.setBounds(905, 592, 86, 25);
		contentPane.add(txt_total_oil);

		JLabel label_2 = new JLabel("\u20B9");
		label_2.setForeground(new Color(255, 0, 0));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_2.setBounds(310, 592, 28, 25);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u20B9");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_3.setBounds(620, 592, 28, 25);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("\u20B9");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_4.setBounds(876, 592, 28, 25);
		contentPane.add(label_4);

		JLabel lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGrandTotal.setBounds(188, 642, 96, 25);
		contentPane.add(lblGrandTotal);

		txt_grand_total = new JTextField();
		txt_grand_total.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_grand_total.setEditable(false);
		txt_grand_total.setColumns(10);
		txt_grand_total.setBounds(339, 642, 86, 25);
		contentPane.add(txt_grand_total);

		JLabel label_5 = new JLabel("\u20B9");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_5.setBounds(310, 641, 28, 25);
		contentPane.add(label_5);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String top = dateFormat.format(toDateChooser.getDate());
				String from = dateFormat.format(fromDateChooser.getDate());
				if (!top.isEmpty() && !from.isEmpty()) {

					collection_model.removeAll();
					tFees = 0;
					tMedicine = 0;
					tOiling = 0;
					tGrand = 0;
					setTableData(top, from);

				} else {
					JOptionPane.showMessageDialog(null, "Please select to and from dates");
				}
			}
		});
		btnSearch.setForeground(UIManager.getColor("Button.foreground"));
		btnSearch.setBackground(UIManager.getColor("Button.background"));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSearch.setBounds(932, 11, 78, 26);
		contentPane.add(btnSearch);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(179, 592, 131, 25);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(479, 592, 131, 25);
		contentPane.add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(761, 592, 115, 25);
		contentPane.add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(179, 641, 131, 25);
		contentPane.add(panel_4);

		cal.setTime(date);
		cal.add(Calendar.DATE, 7);
		// setTableData("01-01-2018", "05-02-2018");
		// setTableData("28-01-2018","01-02-2018");
		// dateFormat.format(cal.getTime())
		setTableData("28-01-2018", dateFormat.format(cal.getTime()));
		setDialogWindow();
	}

	private void setTableData(String toDate, String fromDate) {

		try {
			Date date1 = dateFormat.parse(toDate);
			toDate = String.valueOf(date1.getTime());
			Date date2 = dateFormat.parse(fromDate);
			fromDate = String.valueOf(date2.getTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		columns = new ArrayList<>();
		grpdate = new ArrayList<>();
		where = new HashMap<>();

		columns.add("date");
		columns.add("sum(payment)");
		grpdate.add("date");
		grpdate.add(Constants.order_by);
		grpdate.add("invoice_id");
		String sql = "invoice_id between \'" + toDate + "\' and \'" + fromDate + "\' ";
		where.put("between", sql);
		selectdata = sm.selectData("invoice_table", columns, where, grpdate);

		for (List<String> str : selectdata) {
			tabledata = new HashMap<>();
			columns.clear();
			grpdate.clear();
			where.clear();
			// select sum(bill_total_amt) from medicine_order_entry where date =
			// '29-01-2018' group by date;
			tabledata.put(cols[0], str.get(0));// date
			tabledata.put(cols[1], str.get(1));// payment fees
			double fes = Double.parseDouble(str.get(1));
			tFees = tFees + fes;
			columns.add("sum(bill_total_amt)");
			where.put("date", str.get(0));
			grpdate.add("date");
			List<List<String>> temp = sm.selectData("medicine_order_entry", columns, where, grpdate);
			// select date , sum(medcine_price) from medicine_order_table where medicine_id
			// like 'Oiling%' group by date;
			columns.clear();
			where.clear();
			grpdate.clear();
			columns.add("sum(medcine_price)");
			where.put("medicine_id like ", "Oiling%");
			where.put("date", str.get(0));
			grpdate.add("date");
			List<List<String>> temp1 = sm.selectData("medicine_order_table", columns, where, grpdate);

			columns.clear();
			where.clear();
			grpdate.clear();
			if (!temp.isEmpty() && !temp1.isEmpty()) {
				double med = Double.parseDouble(temp.get(0).get(0));
				double oil = Double.parseDouble(temp1.get(0).get(0));
				double mednet = med - oil;
				tMedicine = tMedicine + mednet;
				tOiling = tOiling + oil;
				tabledata.put(cols[2], String.format("%.2f", mednet));
				tabledata.put(cols[3], String.format("%.2f", (oil)));
			}

			// tabledata.put(cols[2], "");//medicine collection on this date
			// tabledata.put(cols[3], "");
			collection_model.addRow(new RowData(tabledata));

		}

		txt_total_fees.setText(String.format("%.2f", tFees));
		txt_total_med.setText(String.format("%.2f", tMedicine));
		txt_total_oil.setText(String.format("%.2f", tOiling));
		tGrand = tFees + tMedicine + tOiling;
		txt_grand_total.setText(String.format("%.2f", tGrand));
	}

	private void setDialogWindow() {

		final JPanel contentPanel = new JPanel();
		final JDialog dialog = new JDialog();
		dialog.setUndecorated(true);
		dialog.setVisible(true);
		dialog.setBounds(100, 100, 519, 313);
		dialog.getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 255));
		panel.setBounds(2, 2, 517, 47);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblClose = new JLabel("X");
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblClose.setForeground(Color.WHITE);
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setBounds(445, 0, 62, 47);
		lblClose.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose();

			}
		});
		panel.add(lblClose);

		JLabel lblNewLabel_3 = new JLabel("Anand Homeo Hall");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 0, 183, 47);
		panel.add(lblNewLabel_3);

		JLabel lblwelcome = new JLabel("Welcome");
		lblwelcome.setForeground(new Color(0, 153, 255));
		lblwelcome.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblwelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblwelcome.setBounds(184, 57, 120, 30);
		contentPanel.add(lblwelcome);

		JLabel lblock = new JLabel("");
		lblock.setHorizontalAlignment(SwingConstants.CENTER);
		lblock.setBounds(10, 102, 172, 164);
		Image image = new ImageIcon(this.getClass().getResource("/lock.png")).getImage();
		lblock.setIcon(new ImageIcon(image));
		contentPanel.add(lblock);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(184, 139, 100, 25);
		contentPanel.add(lblNewLabel_1);

		txt_username = new JTextField();
		txt_username.setBounds(282, 139, 200, 25);
		contentPanel.add(txt_username);
		txt_username.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPassword.setBounds(184, 175, 100, 25);
		contentPanel.add(lblPassword);

		final JPasswordField txt_pass = new JPasswordField();
		txt_pass.setColumns(10);
		txt_pass.setBounds(282, 175, 200, 25);
		contentPanel.add(txt_pass);

		JButton okButton = new JButton("Login");
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		okButton.setBounds(218, 230, 254, 25);
		contentPanel.add(okButton);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dialog.setLocation((int) (dim.getWidth() / 2 - dialog.getWidth() / 2),
				(int) (dim.getHeight() / 2 - dialog.getHeight() / 2));

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String user = txt_username.getText().trim();

						String pass = txt_pass.getText().trim();
						if (!user.isEmpty() && !pass.isEmpty()) {
							List<String> columns = new ArrayList<>();
							where = new HashMap<>();
							columns.add("username");
							where.put("username", user);
							where.put("password", pass);
							selectdata = sm.selectData("emp", columns, where);
							if (!selectdata.isEmpty()) {
								dialog.dispose();
								CollectionFrame.this.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "invalid Login details ", "Login Error ",
										JOptionPane.ERROR_MESSAGE);

							}

							
						}
					}
				}).start();
			}
		});

	}
}
