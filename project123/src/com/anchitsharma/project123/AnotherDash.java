package com.anchitsharma.project123;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter.Entry;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.util.Rotation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.anchitsharma.project123.PatientEntry.PatientListener;
import com.model.Constants;
import com.model.PdfModel;
import com.model.SearchModels;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.AttributedCharacterIterator;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.utility.MyTableModel;
import com.utility.Patient;
import com.utility.RowData;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

public class AnotherDash implements ActionListener, PatientListener {

	Map<String, Object> what;
	Map<String, Object> where;
	List<String> columns;
	List<List<String>> selectdata;
	private SearchModels sm = new SearchModels();
	private DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);
	private Date date = new Date();
	private JFrame frame;
	private static JDialog dialog;
	private JTextField txt_username, txt_pass;
	private static String curr_user;
	private JTextField txt_con_new;
	private JTextField tct_con_old;
	private JTextField txt_con_rep;
	private JTextField txt_con_oil;
	private JTextField tct_con_courier;
	private JLabel lblUsername;
	private JLabel labelLoginTime;
	private JTable table_oiling;
	DefaultTableModel tableModel;
	MyTableModel recent_model, repeat_model, courier_model, newModel, oiling_model, old_model;
	private TableRowSorter<DefaultTableModel> sorter;
	String[] s3 = { "Reg No.", "Patient Name", "Register date ", "Mobile" };
	String[] col = { "Mob.", "Name", "Remove" };
	private JTable table_old;
	private JTable table_courier;
	private JTable table_repeat;
	private JTextField textField;
	private JTable table_new;
	private JTextField txt_search;
	private JTable table;
	private JTable table_recent;
	final JPopupMenu popupmenu = new JPopupMenu("Edit");
	final JPopupMenu popup = new JPopupMenu("search");
	private JMenuItem markprsent, markcancel, patdetails, updatedetails;
	JCheckBox rem;
	private JMenuItem mntmDoctorEntry;
	private JMenuItem mntmChnagePassword;
	private JMenuItem mntmLogout;
	private JMenuItem mntmExit;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmCollection;
	private JMenuItem mntmPrescriptionEntry;
	private JButton btnRefresh;
	private JComboBox cmbSearchType;
	private JRadioButton rdbSearchDate;
	private JDateChooser toDateChooser;
	private JDateChooser fromDateChooser;
	private JButton btnSearch;
	private JDateChooser newDateChooser;
	private JDateChooser dateRepeat;
	private JButton btnprint;
	private JTabbedPane tabbedPane_1;
	private int printerCount = 0;
	private JDateChooser dateCourier;
	private JDateChooser dateChooser_old;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", false);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					AnotherDash window = new AnotherDash();
					SearchModels sm = new SearchModels();
					List<Object> data = sm.userLoginStatus();
					if ((int) data.get(0) == 0) {
						window.setDialogWindow();
					} else {
						curr_user = String.valueOf(data.get(1));

						window.frame.setVisible(true);
						window.setDataInDash();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnotherDash() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 938, 539);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addComponent(panel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
				.addGap(5)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE).addGap(2)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)));

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setOpaque(false);
		panel_4.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, null, null, null));

		JLabel label_7 = new JLabel("Anand Homeo Hall");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label_7.setBounds(10, 11, 347, 44);
		panel_4.add(label_7);

		labelLoginTime = new JLabel("Md. Amzad Hussain (Samule) ");
		labelLoginTime.setForeground(Color.WHITE);
		labelLoginTime.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JLabel label_10 = new JLabel("Login Time:");
		label_10.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JLabel label_8 = new JLabel("User:");
		label_8.setFont(new Font("Times New Roman", Font.BOLD, 14));

		lblUsername = new JLabel("Md. Amzad Hussain (Samule) ");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 14));

		btnprint = new JButton();
		btnprint.addActionListener(this);
		Image printIcon = new ImageIcon(this.getClass().getResource("/fax-machine.png")).getImage();
		btnprint.setIcon(new ImageIcon(printIcon));
		btnprint.setToolTipText("Print All Patient List");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnprint, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(labelLoginTime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_8, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
								.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
								.addComponent(btnprint, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(labelLoginTime, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_10, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
					.addGap(7))
		);
		panel.setLayout(gl_panel);
		panel_1.setLayout(null);

		JLabel label = new JLabel("NEW");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label.setBounds(10, 5, 53, 25);
		panel_1.add(label);

		txt_con_new = new JTextField();
		txt_con_new.setHorizontalAlignment(SwingConstants.CENTER);
		txt_con_new.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txt_con_new.setEditable(false);
		txt_con_new.setColumns(10);
		txt_con_new.setBounds(73, 5, 40, 25);
		panel_1.add(txt_con_new);

		JLabel label_1 = new JLabel("OLD");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_1.setBounds(127, 5, 53, 25);
		panel_1.add(label_1);

		tct_con_old = new JTextField();
		tct_con_old.setHorizontalAlignment(SwingConstants.CENTER);
		tct_con_old.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tct_con_old.setEditable(false);
		tct_con_old.setColumns(10);
		tct_con_old.setBounds(190, 5, 40, 25);
		panel_1.add(tct_con_old);

		JLabel label_2 = new JLabel("REPEAT");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_2.setBounds(249, 5, 74, 25);
		panel_1.add(label_2);

		txt_con_rep = new JTextField();
		txt_con_rep.setHorizontalAlignment(SwingConstants.CENTER);
		txt_con_rep.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txt_con_rep.setEditable(false);
		txt_con_rep.setColumns(10);
		txt_con_rep.setBounds(333, 5, 40, 25);
		panel_1.add(txt_con_rep);

		JLabel label_3 = new JLabel("Oiling");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_3.setBounds(393, 5, 62, 25);
		panel_1.add(label_3);

		txt_con_oil = new JTextField();
		txt_con_oil.setHorizontalAlignment(SwingConstants.CENTER);
		txt_con_oil.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txt_con_oil.setEditable(false);
		txt_con_oil.setColumns(10);
		txt_con_oil.setBounds(477, 5, 40, 25);
		panel_1.add(txt_con_oil);

		JLabel label_4 = new JLabel("Courier");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_4.setBounds(535, 5, 62, 25);
		panel_1.add(label_4);

		tct_con_courier = new JTextField();
		tct_con_courier.setHorizontalAlignment(SwingConstants.CENTER);
		tct_con_courier.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tct_con_courier.setEditable(false);
		tct_con_courier.setColumns(10);
		tct_con_courier.setBounds(619, 5, 40, 25);
		panel_1.add(tct_con_courier);

		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(this);
		btnRefresh.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnRefresh.setBounds(669, 5, 89, 23);
		panel_1.add(btnRefresh);

		JLabel label_5 = new JLabel("Onistech Info Systems ");
		label_5.setForeground(Color.BLUE);
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 21));
		label_5.setBounds(1020, 0, 250, 20);
		panel_1.add(label_5);

		JLabel label_6 = new JLabel("Version 1.0");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 10));
		label_6.setBounds(1280, 0, 70, 20);
		panel_1.add(label_6);

		JPanel panel_patient_list = new JPanel();
		tabbedPane_1.addTab("Patient List", null, panel_patient_list, null);

		txt_search = new JTextField();
		txt_search.setColumns(10);

		JLabel label_17 = new JLabel("From Date");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("Times New Roman", Font.BOLD, 14));

		toDateChooser = new JDateChooser();
		toDateChooser.setDateFormatString("dd-MM-yyyy");

		JLabel label_18 = new JLabel("To Date");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Times New Roman", Font.BOLD, 14));

		fromDateChooser = new JDateChooser();
		fromDateChooser.setDateFormatString("dd-MM-yyyy");

		JPanel panel_10 = new JPanel();

		btnSearch = new JButton("");
		Image im = new ImageIcon(this.getClass().getResource("/seek.png")).getImage();
		btnSearch.setIcon(new ImageIcon(im));
		btnSearch.addActionListener(this);
		cmbSearchType = new JComboBox();
		cmbSearchType.setFont(new Font("Times New Roman", Font.BOLD, 11));
		cmbSearchType.setModel(new DefaultComboBoxModel(
				new String[] { "Select For Search", "Reg. No.", "Mobile.", "District.", "Name." }));

		rdbSearchDate = new JRadioButton("Between Dates");
		rdbSearchDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GroupLayout gl_panel_patient_list = new GroupLayout(panel_patient_list);
		gl_panel_patient_list.setHorizontalGroup(gl_panel_patient_list.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_patient_list.createSequentialGroup().addContainerGap().addGroup(gl_panel_patient_list
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_patient_list.createSequentialGroup()
								.addComponent(cmbSearchType, 0, 91, Short.MAX_VALUE).addGap(18)
								.addComponent(txt_search, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE).addGap(33)
								.addComponent(rdbSearchDate, GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE).addGap(18)
								.addComponent(label_17, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(toDateChooser, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE).addGap(18)
								.addComponent(label_18, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(fromDateChooser, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSearch))
						.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)).addContainerGap()));
		gl_panel_patient_list.setVerticalGroup(gl_panel_patient_list.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_patient_list.createSequentialGroup().addContainerGap().addGroup(gl_panel_patient_list
						.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_patient_list.createParallelGroup(Alignment.BASELINE)
								.addComponent(txt_search, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbSearchType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbSearchDate))
						.addComponent(toDateChooser, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(fromDateChooser, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
						.addComponent(label_18, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)));
		panel_10.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_5 = new JScrollPane();
		panel_10.add(scrollPane_5, BorderLayout.CENTER);
		String cols[] = { "Reg. No.", "Reg. Date", "Name", " Mobile", "Address","District" ,"Refer By", "Last Visit Date" };

		tableModel = new DefaultTableModel(0, 0);
		tableModel.setColumnIdentifiers(cols);
		sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		table = new JTable();
		scrollPane_5.setViewportView(table);
		table.setModel(tableModel);
		table.setRowSorter(sorter);

		JTableHeader header = table.getTableHeader();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		header.setFont(new Font("Times New Roman", Font.BOLD, 10));
		table.setRowHeight(20);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);

		updatedetails = new JMenuItem("Update Patient");
		patdetails = new JMenuItem("View History");
		updatedetails.addActionListener(this);
		patdetails.addActionListener(this);
		popup.add(patdetails);
		popup.add(updatedetails);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.isPopupTrigger()) {
					popup.show(table, e.getX(), e.getY());
				}
			}

		});
		setAdapter(popup, table);
		panel_10.add(BorderLayout.NORTH, header);
		panel_patient_list.setLayout(gl_panel_patient_list);

		JPanel panel_newp = new JPanel();
		tabbedPane_1.addTab("New Patients", null, panel_newp, null);

		JLabel label_14 = new JLabel("Registartion No.");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setFont(new Font("Times New Roman", Font.BOLD, 14));

		textField = new JTextField();
		textField.setColumns(10);

		JLabel label_15 = new JLabel("New Patient on:");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("Times New Roman", Font.BOLD, 14));

		newDateChooser = new JDateChooser();

		newDateChooser.setDateFormatString("dd-MM-yyyy");

		JPanel panel_9 = new JPanel();
		GroupLayout gl_panel_newp = new GroupLayout(panel_newp);
		gl_panel_newp.setHorizontalGroup(gl_panel_newp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_newp.createSequentialGroup().addContainerGap(24, Short.MAX_VALUE)
						.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE).addGap(5)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE).addGap(32)
						.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(newDateChooser, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addGap(25))
				.addGroup(gl_panel_newp.createSequentialGroup().addContainerGap()
						.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE).addContainerGap()));
		gl_panel_newp.setVerticalGroup(gl_panel_newp.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_newp
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_newp.createParallelGroup(Alignment.LEADING)
						.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(newDateChooser, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE).addContainerGap()));
		panel_9.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_4 = new JScrollPane();
		panel_9.add(scrollPane_4, BorderLayout.CENTER);
		newModel = new MyTableModel();
		newModel.setColumnNames(s3);
		table_new = new JTable();
		scrollPane_4.setViewportView(table_new);
		table_new.setModel(newModel);
		JTableHeader h_new = table_new.getTableHeader();
		h_new.setFont(new Font("Times New Roman", Font.BOLD, 10));
		table_new.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		table_new.setRowHeight(20);
		panel_9.add(BorderLayout.NORTH, h_new);
		panel_newp.setLayout(gl_panel_newp);

		JPanel panel_repeat = new JPanel();
		tabbedPane_1.addTab("Repeat", null, panel_repeat, null);

		JLabel label_13 = new JLabel("Date");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Times New Roman", Font.BOLD, 14));

		dateRepeat = new JDateChooser();
		dateRepeat.setDateFormatString("dd-MM-yyyy");

		JPanel panel_8 = new JPanel();
		GroupLayout gl_panel_repeat = new GroupLayout(panel_repeat);
		gl_panel_repeat.setHorizontalGroup(gl_panel_repeat.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_repeat.createSequentialGroup().addContainerGap(213, Short.MAX_VALUE)
						.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE).addGap(5)
						.addComponent(dateRepeat, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addGap(232))
				.addGroup(gl_panel_repeat.createSequentialGroup().addContainerGap()
						.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE).addContainerGap()));
		gl_panel_repeat.setVerticalGroup(gl_panel_repeat.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_repeat.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_repeat.createParallelGroup(Alignment.LEADING)
								.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateRepeat, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)));
		panel_8.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_3 = new JScrollPane();
		panel_8.add(scrollPane_3, BorderLayout.CENTER);
		repeat_model = new MyTableModel();
		repeat_model.setColumnNames(s3);
		table_repeat = new JTable();
		scrollPane_3.setViewportView(table_repeat);
		table_repeat.setModel(repeat_model);
		JTableHeader header_repeat = table_repeat.getTableHeader();
		table_repeat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		header_repeat.setFont(new Font("Times New Roman", Font.BOLD, 15));
		table_repeat.setRowHeight(20);

		panel_8.add(BorderLayout.NORTH, header_repeat);
		ModuleSet(repeat_model, dateFormat.format(date), Constants.repeat);
		panel_repeat.setLayout(gl_panel_repeat);

		JPanel panel_courier = new JPanel();
		tabbedPane_1.addTab("Courier", null, panel_courier, null);

		JLabel label_12 = new JLabel("Date");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Times New Roman", Font.BOLD, 14));

		dateCourier = new JDateChooser();
		dateCourier.setDateFormatString("dd-MM-yyyy");

		JPanel panel_7 = new JPanel();
		GroupLayout gl_panel_courier = new GroupLayout(panel_courier);
		gl_panel_courier.setHorizontalGroup(gl_panel_courier.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_courier.createSequentialGroup().addContainerGap(193, Short.MAX_VALUE)
						.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE).addGap(5)
						.addComponent(dateCourier, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addGap(252))
				.addGroup(gl_panel_courier.createSequentialGroup().addContainerGap()
						.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE).addContainerGap()));
		gl_panel_courier.setVerticalGroup(gl_panel_courier.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_courier.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_courier.createParallelGroup(Alignment.LEADING)
								.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateCourier, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE).addContainerGap()));
		panel_7.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		panel_7.add(scrollPane_2, BorderLayout.CENTER);
		courier_model = new MyTableModel();
		courier_model.setColumnNames(s3);
		table_courier = new JTable(courier_model);
		scrollPane_2.setViewportView(table_courier);
		JTableHeader header_courier = table_courier.getTableHeader();
		table_courier.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		header_courier.setFont(new Font("Times New Roman", Font.BOLD, 10));
		panel_7.add(BorderLayout.NORTH, header_courier);
		// Map<String, String> cc1 = new HashMap<>();
		// cc1.put("by_courier", "1");
		ModuleSet(courier_model, dateFormat.format(date), Constants.courier);
		panel_courier.setLayout(gl_panel_courier);

		JPanel panel_old = new JPanel();
		tabbedPane_1.addTab("Old Patient", null, panel_old, null);

		JLabel label_11 = new JLabel("Date");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Times New Roman", Font.BOLD, 14));

		dateChooser_old = new JDateChooser();
		dateChooser_old.setDateFormatString("dd-MM-yyyy");

		JPanel panel_6 = new JPanel();
		GroupLayout gl_panel_old = new GroupLayout(panel_old);
		gl_panel_old.setHorizontalGroup(gl_panel_old.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_old
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_old.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_old.createSequentialGroup()
								.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(dateChooser_old, GroupLayout.PREFERRED_SIZE, 143,
										GroupLayout.PREFERRED_SIZE)
								.addGap(222))
						.addGroup(gl_panel_old.createSequentialGroup()
								.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
								.addContainerGap()))));
		gl_panel_old.setVerticalGroup(gl_panel_old.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_old
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_old.createParallelGroup(Alignment.LEADING)
						.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooser_old, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE).addContainerGap()));
		panel_6.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_6.add(scrollPane_1, BorderLayout.CENTER);
		old_model = new MyTableModel();
		old_model.setColumnNames(s3);
		table_old = new JTable();
		table_old.setModel(old_model);
		scrollPane_1.setViewportView(table_old);
		JTableHeader header_old = table_old.getTableHeader();
		header_old.setFont(new Font("Times New Roman", Font.BOLD, 10));
		table_old.setFont(new Font("Times New Roman", Font.PLAIN, 10));

		table_old.setRowHeight(20);
		panel_6.add(BorderLayout.NORTH, header_old);
		ModuleSet(old_model, dateFormat.format(this.date), Constants.old);
		panel_old.setLayout(gl_panel_old);

		JPanel panel_oiling = new JPanel();
		tabbedPane_1.addTab("Oiling", null, panel_oiling, null);

		JLabel label_9 = new JLabel("Date");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JDateChooser dateChooser_oil = new JDateChooser();
		dateChooser_oil.setDateFormatString("dd-MM-yyyy");

		JPanel panel_5 = new JPanel();
		GroupLayout gl_panel_oiling = new GroupLayout(panel_oiling);
		gl_panel_oiling
				.setHorizontalGroup(gl_panel_oiling.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_oiling.createSequentialGroup().addContainerGap(224, Short.MAX_VALUE)
								.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(dateChooser_oil, GroupLayout.PREFERRED_SIZE, 143,
										GroupLayout.PREFERRED_SIZE)
								.addGap(216))
						.addGroup(gl_panel_oiling.createSequentialGroup().addContainerGap()
								.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
								.addContainerGap()));
		gl_panel_oiling.setVerticalGroup(gl_panel_oiling.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_oiling
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_oiling.createParallelGroup(Alignment.TRAILING)
						.addComponent(dateChooser_oil, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE).addGap(12)));
		panel_5.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane, BorderLayout.CENTER);
		oiling_model = new MyTableModel();
		oiling_model.setColumnNames(s3);
		table_oiling = new JTable();
		table_oiling.setModel(oiling_model);
		scrollPane.setViewportView(table_oiling);
		panel_oiling.setLayout(gl_panel_oiling);
		JTableHeader hoil = table_oiling.getTableHeader();
		table_oiling.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		hoil.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ModuleSet(oiling_model, dateFormat.format(this.date), Constants.oiling);
		table_oiling.setRowHeight(20);
		panel_5.add(BorderLayout.NORTH, hoil);
		JPanel panel_pgraph = new JPanel();
		tabbedPane_1.addTab("Patient Graph", null, panel_pgraph, null);
		JFreeChart lineChart = ChartFactory.createLineChart("No. of Patients vs days", "Days", "Number of Patients",
				createDataset(), PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new Dimension(600, 310));
		GroupLayout gl_panel_pgraph = new GroupLayout(panel_pgraph);
		gl_panel_pgraph.setHorizontalGroup(gl_panel_pgraph.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_pgraph.createSequentialGroup().addContainerGap()
						.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE).addContainerGap()));
		gl_panel_pgraph.setVerticalGroup(gl_panel_pgraph.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_pgraph.createSequentialGroup().addGap(5)
						.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE).addContainerGap()));
		panel_pgraph.setLayout(gl_panel_pgraph);

		JPanel panel_mgraph = new JPanel();
		tabbedPane_1.addTab("Medicine Graph", null, panel_mgraph, null);
		PieDataset dataset = cDataset();
		JFreeChart chart = createChart(dataset, "Which Medicine is at inflation ?");
		ChartPanel cp = new ChartPanel(chart);
		cp.setPreferredSize(new Dimension(600, 310));
		GroupLayout gl_panel_mgraph = new GroupLayout(panel_mgraph);
		gl_panel_mgraph.setHorizontalGroup(
				gl_panel_mgraph.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_mgraph.createSequentialGroup()
						.addContainerGap().addComponent(cp, GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)));
		gl_panel_mgraph.setVerticalGroup(gl_panel_mgraph.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_mgraph.createSequentialGroup().addGap(5)
						.addComponent(cp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		panel_mgraph.setLayout(gl_panel_mgraph);
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Recent", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_6 = new JScrollPane();
		panel_2.add(scrollPane_6, BorderLayout.CENTER);
		recent_model = new MyTableModel();

		recent_model.setColumnNames(col);
		table_recent = new JTable();
		table_recent.setModel(recent_model);
		scrollPane_6.setViewportView(table_recent);

		markprsent = new JMenuItem("Mark Present");
		markcancel = new JMenuItem("Cancel Appointment");
		markcancel.addActionListener(this);
		markprsent.addActionListener(this);
		popupmenu.add(markprsent);
		popupmenu.add(markcancel);
		table_recent.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.isPopupTrigger()) {
					popupmenu.show(table_recent, e.getX(), e.getY());
				}
			}

		});
		setAdapter(popupmenu, table_recent);
		/*
		 * popupmenu.addPopupMenuListener(new PopupMenuListener() {
		 * 
		 * @Override public void popupMenuWillBecomeVisible(PopupMenuEvent e) { // TODO
		 * Auto-generated method stub setAdapter(popupmenu, table_recent); }
		 * 
		 * @Override public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { //
		 * TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void popupMenuCanceled(PopupMenuEvent e) { // TODO
		 * Auto-generated method stub
		 * 
		 * } });
		 */
		JTableHeader h1 = table_recent.getTableHeader();

		table_recent.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		table_recent.setRowHeight(20);
		rem = new JCheckBox();
		rem.addActionListener(this);
		TableColumn c1 = table_recent.getColumnModel().getColumn(2);
		c1.setCellEditor(new DefaultCellEditor(rem));
		h1.setFont(new Font("Times New Roman", Font.BOLD, 10));

		table_recent.getColumnModel().getColumn(0).setPreferredWidth(10);
		table_recent.getColumnModel().getColumn(2).setPreferredWidth(10);
		// set the button inside table

		panel_2.add(BorderLayout.NORTH, h1);
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Follow up", null, panel_3, null);
		frame.getContentPane().setLayout(groupLayout);

		setList1("", "", "");
		setRecent();
		setMenuBar();
		setNewModel(dateFormat.format(this.date));
		setListener();
	}

	private void newFilter(final int where, final String what) {
		// RowFilter<DefaultTableModel, Object> rf = null;
		// If current expression doesn't parse, don't update.
		RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
			public boolean include(Entry entry) {
				String population = (String) entry.getValue(where);
				return population.toLowerCase().equals(what);
			}
		};
		sorter.setRowFilter(filter);
	}

	private PieDataset cDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("AT-100", 29);
		result.setValue("Peniciline", 20);
		result.setValue("GT-100", 51);
		return result;
	}

	private JFreeChart createChart(PieDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}

	private void setDialogWindow() {
		frame.setVisible(false);
		final JPanel contentPanel = new JPanel();
		dialog = new JDialog();
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
		lblClose.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblClose.setForeground(Color.WHITE);
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setBounds(445, 0, 62, 47);
		lblClose.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);

			}
		});
		panel.add(lblClose);

		JLabel lblNewLabel_3 = new JLabel("Anand Homeo Hall");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
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
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(184, 139, 100, 25);
		contentPanel.add(lblNewLabel_1);

		txt_username = new JTextField();
		txt_username.setBounds(282, 139, 200, 25);
		contentPanel.add(txt_username);
		txt_username.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblPassword.setBounds(184, 175, 100, 25);
		contentPanel.add(lblPassword);

		txt_pass = new JPasswordField();
		txt_pass.setColumns(10);
		txt_pass.setBounds(282, 175, 200, 25);
		contentPanel.add(txt_pass);

		JButton okButton = new JButton("Login");
		okButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		okButton.setBounds(218, 230, 254, 25);
		contentPanel.add(okButton);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);

		/*
		 * JButton = new JButton("OK"); okButton.setActionCommand("OK");
		 * buttonPane.add(okButton); dialog.getRootPane().setDefaultButton(okButton);
		 */

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
							curr_user = user;
							System.out.println(user);

							what = new HashMap<>();
							what.put("active", 1);
							String timeStamp = (new Timestamp(date.getTime())).toString();
							what.put("login_time", timeStamp);

							where = new HashMap<>();
							where.put("username", user);
							where.put("password", pass);
							int count = sm.updateDatabase("emp", where, what);
							if (count > 0) {
								dialog.dispose();

								frame.setVisible(true);
								lblUsername.setText(curr_user);
								setDataInDash();
							} else {
								JOptionPane.showMessageDialog(null, "invalid Login details ", "Login Error ",
										JOptionPane.ERROR_MESSAGE);
								curr_user = "";
							}

							System.out.println("set up dialog pst close");
						}
					}
				}).start();
			}
		});

	}

	private void setAdapter(final JPopupMenu popupmenu, final JTable table) {

		popupmenu.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int rowAtPoint = table
								.rowAtPoint(SwingUtilities.convertPoint(popupmenu, new Point(0, 0), table));
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
		/*
		 * SwingUtilities.invokeLater(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub int
		 * rowAtPoint = table_recent .rowAtPoint(SwingUtilities.convertPoint(popupmenu,
		 * new Point(0, 0), table)); if (rowAtPoint > -1) {
		 * table_recent.setRowSelectionInterval(rowAtPoint, rowAtPoint); } } });
		 */
	}

	private void setDataInDash() {
		String sql = "select * from emp  where active = ?";
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("name");
		columns.add("surname");
		columns.add("login_time");
		where.put("active", 1);
		selectdata = sm.selectData("emp", columns, where);
		if (!selectdata.isEmpty()) {
			for (List<String> strList : selectdata) {
				lblUsername.setText(strList.get(0) + "" + strList.get(1));
				labelLoginTime.setText(strList.get(2));
			}
		}

	}

	private void userLogout() {
		if (curr_user != null) {

			what = new HashMap<>();
			what.put("active", 0);
			where = new HashMap<>();
			where.put("username", curr_user);

			if (sm.updateDatabase("emp", where, what) == 1) {
				setDialogWindow();
			}

		}
	}

	private void setRecent() {
		Map<String, Object> recent_vec = new HashMap<>();
		// String[] col = { "Reg No.", "Name", "Remove" };
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("p_id");
		columns.add("p_mobile");
		where.put("app_date", dateFormat.format(date));
		where.put("status", 0);
		// select p_id from appointment where app_date = '05-02-2018' and status = '0'
		// group by order by
		String ordby = "and attend != \'cancel\' order by id";

		selectdata = sm.selectData("appointment", columns, where, ordby);
		if (!selectdata.isEmpty()) {
			for (List<String> strList : selectdata) {
				recent_vec = new HashMap<>();
				recent_vec.put(col[0], strList.get(1));
				recent_vec.put(col[1], sm.searchNameMobile(strList.get(1)));// fill name here
				recent_vec.put(col[2], new Boolean(false));
				recent_model.addRow(new RowData(recent_vec));
				recent_model.fireTableDataChanged();
			}
		}
		refreshCounters();

	}

	private void refreshCounters() {
		Map<String, String> num = getNumPatient();
		if (num.containsKey(Constants.repeat)) {
			txt_con_rep.setText(num.get(Constants.repeat));
		} else {
			txt_con_rep.setText("0");
		}
		if (num.containsKey(Constants.courier)) {
			tct_con_courier.setText(num.get(Constants.courier));
		} else {
			tct_con_courier.setText("0");
		}
		if (num.containsKey(Constants.oiling)) {
			txt_con_oil.setText(num.get(Constants.oiling));
		} else {
			txt_con_oil.setText("0");
		}
		if (num.containsKey("new")) {
			txt_con_new.setText(num.get("new"));
		} else {
			txt_con_new.setText("0");
		}

		if (num.containsKey(Constants.old)) {
			tct_con_old.setText(num.get(Constants.old));
		} else {
			tct_con_old.setText("0");
		}
	}

	private Map<String, String> getNumPatient() {
		Map<String, String> num = new HashMap<>();// size of this map is changing dyanimcally//check its value first
													// before using it
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("count(*)");
		columns.add("dept");
		where.put("app_date", dateFormat.format(this.date));
		where.put("status", 0);
		String grpdate = " and attend != \'cancel\' group by dept";
		selectdata = sm.selectData("appointment", columns, where, grpdate);
		for (List<String> strList : selectdata) {
			num.put(strList.get(1), strList.get(0));
		}
		return num;
	}

	private void setNewModel(String date) {
		// String[] s3 = { "Reg No.", "Patient Name", "Doctor Name", "Last Visit ",
		// "Expected Visit" };
		// String[] s3 = { "Reg No.", "Patient Name", "Register date " };
		columns = new ArrayList<>();
		where = new HashMap<>();

		// date = dateFormat.format(new Date());
		columns.add("p_id");
		columns.add("p_name");
		columns.add("reg_date");
		columns.add("p_mobile");
		where.put("reg_date", date);
		selectdata = sm.selectData("patient_table", columns, where);
		if (!selectdata.isEmpty()) {
			for (List<String> strlist : selectdata) {
				what = new HashMap<>();
				what.put(s3[0], strlist.get(0));
				what.put(s3[1], strlist.get(1));

				what.put(s3[2], strlist.get(2));
				what.put(s3[3], strlist.get(3));
				newModel.addRow(new RowData(what));
			}
		}

	}

	private void setList1(String p_id, String toDate, String fromDate) {

		columns = new ArrayList<>();
		Vector<Object> data;
		where = new HashMap<>();

		columns.add("p_id");
		columns.add("reg_date");
		columns.add("p_name");
		columns.add("p_mobile");
		columns.add("p_add");
		columns.add("p_district");
		columns.add("p_doc_id");
		columns.add("refer_id");
		columns.add("last_visited");
		String grpdata = " order by id desc";
		selectdata = sm.selectData("patient_table", columns, where,grpdata);

		for (List<String> list1 : selectdata) {
			int i = 0;
			data = new Vector<>();
			// String cols[] = { "Reg. No.","Reg. Date", "Name", " Mobile", "Address",
			// "Refer By", "Last Visit Date" };
			data.add(list1.get(i));// id

			data.add(list1.get(++i));// date
			String pname = list1.get(++i);
			data.add(pname);// name
			data.add(list1.get(++i));// mobile
			data.add(list1.get(++i));// address
			List<String> c1 = new ArrayList<>();
			data.add(list1.get(++i));//district
			++i;
			String pid = list1.get(++i);// refer_id

			data.add(sm.searchNameMobile(pid));
			data.add(list1.get(++i));// lastvisited
			tableModel.addRow(data);

		}

	}

	private DefaultCategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// dataset.addValue(value, rowKey, columnKey);
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("count(p_id)");
		columns.add("reg_date");
		String grpdate = " group by reg_date order by id";
		// select count(p_id) ,reg_date from patient_table group by reg_date;
		selectdata = sm.selectData("patient_table", columns, where, grpdate);
		for (List<String> strList : selectdata) {
			dataset.addValue(Integer.parseInt(strList.get(0)), "patients", strList.get(1));
		}
		/*
		 * dataset.addValue( 15 , "schools" , "1970" ); dataset.addValue( 30 , "schools"
		 * , "1980" ); dataset.addValue( 60 , "schools" , "1990" ); dataset.addValue(
		 * 120 , "schools" , "2000" ); dataset.addValue( 240 , "schools" , "2010" );
		 * dataset.addValue( 300 , "schools" , "2014" );
		 */
		return dataset;
	}

	// uses for repeat model
	private void ModuleSet(MyTableModel tableModel, String date, String dept) {
		// String[] s3 = { "Reg No.", "Patient Name", "Register date " };
		columns = new ArrayList<>();
		where = new HashMap<>();
		Map<String, Object> tempVector;
		columns.add("p_id");
		columns.add("reg_date");
		columns.add("p_mobile");

		where.put("app_date", date);
		where.put("dept", dept);

		// String cc= " and reg_date != \'"+dateFormat.format(new Date())+"\'";
		selectdata = sm.selectData("appointment", columns, where);
		System.out.println("------------------------------------------------------------------------------------");
		if (!selectdata.isEmpty()) {
			for (List<String> strList : selectdata) {
				tempVector = new HashMap<>();
				tempVector.put(s3[0], strList.get(0));
				tempVector.put(s3[1], sm.searchNameMobile(strList.get(2)));
				tempVector.put(s3[2], strList.get(1));
				tempVector.put(s3[3], strList.get(2));
				tableModel.addRow(new RowData(tempVector));
			}
		}

	}

	private void setListener() {
		dateCourier.setDate(this.date);
		dateCourier.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if ("date".equals(evt.getPropertyName())) {
					courier_model.removeAll();
					ModuleSet(courier_model, dateFormat.format(evt.getNewValue()), Constants.courier);
				}
			}
		});
		
		dateChooser_old.setDate(this.date);
		dateChooser_old.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if ("date".equals(evt.getPropertyName())) {
					old_model.removeAll();
					ModuleSet(old_model, dateFormat.format(evt.getNewValue()), Constants.old);
				}
			}
		});

		newDateChooser.setDate(this.date);
		newDateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if ("date".equals(evt.getPropertyName())) {
					newModel.removeAll();
					setNewModel(dateFormat.format(evt.getNewValue()));
				}
			}
		});

		dateRepeat.setDate(this.date);
		dateRepeat.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if ("date".equals(evt.getPropertyName())) {
					repeat_model.removeAll();
					ModuleSet(repeat_model, dateFormat.format(evt.getNewValue()), Constants.repeat);
				}
			}
		});
	}

	private void setMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLUE);
		frame.setJMenuBar(menuBar);
		Component horizontalStrut = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut);

		JMenu mnSetupSecurity = new JMenu("Setup & Security");
		mnSetupSecurity.setForeground(Color.BLACK);
		mnSetupSecurity.setFont(new Font("Times New Roman", Font.BOLD, 15));
		mnSetupSecurity.setBackground(Color.BLUE);
		menuBar.add(mnSetupSecurity);

		mntmDoctorEntry = new JMenuItem("Doctor Entry");
		mntmDoctorEntry.addActionListener(this);
		mntmDoctorEntry.setForeground(Color.BLACK);
		mntmDoctorEntry.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mntmDoctorEntry.setBackground(new Color(0, 51, 255));
		mnSetupSecurity.add(mntmDoctorEntry);

		mntmChnagePassword = new JMenuItem("Change Password");
		mntmChnagePassword.addActionListener(this);
		mntmChnagePassword.setForeground(Color.BLACK);
		mntmChnagePassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mntmChnagePassword.setBackground(new Color(0, 51, 255));
		mnSetupSecurity.add(mntmChnagePassword);

		mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(this);
		mntmLogout.setForeground(Color.BLACK);
		mntmLogout.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mntmLogout.setBackground(new Color(0, 51, 255));
		mnSetupSecurity.add(mntmLogout);

		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mntmExit.setForeground(Color.BLACK);
		mntmExit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mntmExit.setBackground(new Color(0, 51, 255));
		mnSetupSecurity.add(mntmExit);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut_1);

		JMenu mnNewMenu = new JMenu("Patient Registartion");

		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Times New Roman", Font.BOLD, 15));
		mnNewMenu.setBackground(new Color(0, 51, 255));
		menuBar.add(mnNewMenu);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);

		mntmNewMenuItem = new JMenuItem("Make Appointment");
		mntmNewMenuItem.addActionListener(this);
		mntmNewMenuItem.setForeground(Color.BLACK);
		mntmNewMenuItem.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mntmNewMenuItem.setBackground(new Color(0, 51, 255));
		mnNewMenu.add(mntmNewMenuItem);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut_2);

		JMenu mnTranscations = new JMenu("Transcations");
		mnTranscations.setForeground(Color.BLACK);
		mnTranscations.setFont(new Font("Times New Roman", Font.BOLD, 15));
		mnTranscations.setBackground(new Color(0, 51, 255));
		menuBar.add(mnTranscations);

		mntmCollection = new JMenuItem("Total Collection");
		mntmCollection.addActionListener(this);
		mntmCollection.setForeground(Color.BLACK);
		mntmCollection.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mntmCollection.setBackground(new Color(0, 51, 255));
		mnTranscations.add(mntmCollection);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut_3);

		JMenu mnPharmacyManagement = new JMenu("Medicose");
		mnPharmacyManagement.setForeground(Color.BLACK);
		mnPharmacyManagement.setFont(new Font("Times New Roman", Font.BOLD, 15));
		mnPharmacyManagement.setBackground(new Color(0, 51, 255));
		menuBar.add(mnPharmacyManagement);

		mntmPrescriptionEntry = new JMenuItem("Prescription Entry");
		mntmPrescriptionEntry.addActionListener(this);
		mntmPrescriptionEntry.setForeground(Color.BLACK);
		mntmPrescriptionEntry.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mntmPrescriptionEntry.setBackground(new Color(0, 51, 255));
		mnPharmacyManagement.add(mntmPrescriptionEntry);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == mntmDoctorEntry) {
			DoctorEntry docEntry = new DoctorEntry();
			docEntry.setVisible(true);
		}
		if (e.getSource() == mntmChnagePassword) {
			ChangePassword pass = new ChangePassword();
			pass.setVisible(true);
		}
		if (e.getSource() == mntmLogout) {
			userLogout();
		}

		if (e.getSource() == mntmExit) {
			System.exit(0);
		}

		if (e.getSource() == mntmNewMenuItem) {
			PatientEntry OrderEntry = new PatientEntry();
			OrderEntry.setVisible(true);
			OrderEntry.setPatientListener(AnotherDash.this);
		}
		if (e.getSource() == mntmCollection) {
			JFrame frame = new CollectionFrame();
			// frame.setVisible(true);
		}

		if (e.getSource() == mntmPrescriptionEntry) {
			MedicineBoard med_board = new MedicineBoard();
			med_board.setVisible(true);
		}

		if (e.getSource() == markprsent) {
			where = new HashMap<>();
			Map<String, Object> what = new HashMap<>();
			Object mobile = recent_model.getValueAt(table_recent.getSelectedRow(), 0);
			String todate = dateFormat.format(this.date);
			where.put("p_mobile", mobile);
			where.put("app_date", todate);
			what.put("attend", Constants.present);

			sm.updateDatabase("appointment", where, what);

		}
		if (e.getSource() == markcancel) {
			where = new HashMap<>();
			what = new HashMap<>();
			// update appointment set attend = 'cancel' where p_mobile = '656596859' and
			// app_date = 'today';
			Object mobile = recent_model.getValueAt(table_recent.getSelectedRow(), 0);
			String todate = dateFormat.format(this.date);
			where.put("p_mobile", mobile);
			where.put("app_date", todate);
			what.put("attend", Constants.cancel);
			sm.updateDatabase("appointment", where, what);
			System.out.println("appointment cancel");
		}

		if (e.getSource() == btnRefresh) {
			recent_model.removeAll();
			sorter.setRowFilter(null);
			setRecent();
			if (tableModel.getRowCount() > 0) {
				for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
					tableModel.removeRow(i);
				}
			}
			setList1("", "", "");
			newModel.removeAll();
			setNewModel(dateFormat.format(this.date));
			System.out.println(recent_model.getRowCount());
		}

		if (e.getSource() == rem) {
			int sr = table_recent.getSelectedRow();

			where = new HashMap<>();
			what = new HashMap<>();

			where.put("p_mobile", recent_model.getValueAt(sr, 0));
			what.put("status", 1);
			sm.updateDatabase("appointment", where, what);
		}

		if (e.getSource() == btnSearch) {
			if (rdbSearchDate.isSelected()) {
				long toDate = toDateChooser.getDate().getTime();
				long fromDate = fromDateChooser.getDate().getTime();
				searchInTable(toDate, fromDate);

			} else {
				String text = (String) cmbSearchType.getSelectedItem();
				if (text.equals("Reg. No.")) {
					newFilter(0, txt_search.getText());
				}
				if (text.equals("Mobile.")) {
					newFilter(3, txt_search.getText());
				}
				if (text.equals("Name.")) {
					newFilter(2, txt_search.getText());
				}
				if(text.equals("District.")) {
					newFilter(5, txt_search.getText().toLowerCase());
				}
			}
		}

		if (e.getSource() == updatedetails) {
			final String mobile = (String) tableModel.getValueAt(table.getSelectedRow(), 3);

			if (mobile != null) {

				EventQueue.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						PatientEntry f = new PatientEntry();
						f.setVisible(true);
						f.remoteValueFetch(mobile);
					}
				});

			}
		}

		if (e.getSource() == patdetails) {
			final String id = (String) tableModel
					.getValueAt(table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()), 0);
			final String i = String.valueOf(table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()));

			if (id != null) {
				EventQueue.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						PatientDetails fr = new PatientDetails(id);
						fr.setDataInTable(id);
						fr.setVisible(true);
					}
				});

			}

		}
		if (e.getSource() == btnprint) {
//			try {
//				table.print();
//
//			} catch (PrinterException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
//			print(tabbedPane_1.getSelectedComponent());
			
			int tab = tabbedPane_1.getSelectedIndex();
			List<Patient> patientList = new ArrayList<>();
			try {
			switch(tab) {
			case 0:
				/*table.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("ALL PATIENT LIST"),
						new MessageFormat("Page {0}"),
                        true, null,
                        true, null);*/
				String cols[] = { "Reg. No.","Reg. Date", "Name", " Mobile", "Address","District",
						"Refer By", "Last Visit Date" };
				tableModel = (DefaultTableModel) table.getRowSorter().getModel();
				System.out.println("Row Total "+table.getRowSorter().getViewRowCount());
				if(printerCount ==0) {
					printerCount = table.getRowSorter().getViewRowCount();
				}
				for(int i =0;i<printerCount;i++) {

					
					patientList.add(new Patient((String)table.getValueAt(i, 1), (String)table.getValueAt(i, 0),
							(String)table.getValueAt(i, 2), "", "", "", "", (String)table.getValueAt(i, 5), (String)table.getValueAt(i, 4), (String)table.getValueAt(i, 3), "", "", "", "", ""));
				}
				new PdfModel().printPatientList(cols, patientList);
				break;
			case 1:
				table_new.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("NEW PATIENTS"),
						new MessageFormat("Page {0}"),
                        true, null,
                        true, null);
				break;
			case 2:
				table_repeat.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("REPEAT PATIENTS"),
						new MessageFormat("Page {0}"),
                        true, null,
                        true, null);
				break;
			case 3:
				table_courier.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("COURIER PATIENTS"),
						new MessageFormat("Page {0}"),
                        true, null,
                        true, null);
				break;
			case 4:
				table_old.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("OLD PATIENTS"),
						new MessageFormat("Page {0}"),
                        true, null,
                        true, null);
				break;
			case 5:
				table_oiling.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("OILING APPOINTMENTS"),
						new MessageFormat("Page {0}"),
                        true, null,
                        true, null);
				break;
			}
			}catch(PrinterException e1){
				e1.printStackTrace();
			}
			/*
			 * tableModel = (DefaultTableModel) table.getRowSorter().getModel();
			 * System.out.println("Row Total "+table.getRowSorter().getViewRowCount());
			 * for(int i =0;i<table.getRowSorter().getViewRowCount();i++) { //
			 * System.out.println(i);
			 * System.out.println(tableModel.getValueAt(table.getRowSorter().
			 * convertRowIndexToModel(i), 2)); try { table.print(); } catch
			 * (PrinterException e1) { // TODO Auto-generated catch block
			 * e1.printStackTrace(); } } }
			 */
			// tabbedPane_1.getSelectedComponent().print(new Graphics);

		}

	}

	public static void print(final Component comp) {
		final float SCALE = .5f;
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(new Printable() {

			@Override
			public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
				// TODO Auto-generated method stub
				if (page*pf.getImageableHeight()>=SCALE*comp.getHeight()) 
					return NO_SUCH_PAGE;
					((Graphics2D)g).translate(pf.getImageableX(),pf.getImageableY()-page*pf.getImageableHeight());
					((Graphics2D)g).scale(SCALE, SCALE);
					comp.printAll(g);
					
				return 0;
			}
		});
		
		if(job.printDialog()) {
			try {
				job.print();
			}catch(PrinterException e) {
				e.printStackTrace();
			}
		}
	}

	private void searchInTable(final long toDate, final long fromDate) {
		RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
			public boolean include(Entry entry) {
				String date = (String) entry.getValue(1);
				try {
					Date d = dateFormat.parse(date);
					long dl = d.getTime();
					if (dl > toDate && dl < fromDate) {
						return true;
					}

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		};

		sorter.setRowFilter(filter);
	}

	@Override
	public void onPatientRegister(Patient name) {
		// TODO Auto-generated method stub
		Vector<Object> data;
		if (name.getReg_date().equals(dateFormat.format(this.date))) {
			Map<String, Object> recent_vec = new HashMap<>();
			recent_vec.put(col[0], name.getP_mobile());
			recent_vec.put(col[1], name.getP_name());
			recent_vec.put(col[2], new Boolean(false));
			recent_model.addRow(new RowData(recent_vec));
			recent_model.fireTableDataChanged();
		}

		/*
		 * columns.add("p_id"); columns.add("p_name"); columns.add("p_mobile");
		 * columns.add("p_add"); columns.add("p_doc_id"); columns.add("refer_id");
		 * columns.add("last_visited");
		 */
		int count = tableModel.getRowCount();
		boolean update = false;
		for (int i = 0; i < count; i++) {
			if (name.getP_id().equals(tableModel.getValueAt(i, 0))) {
				update = true;
			}
		}
		if (!update) {
			data = new Vector<>();
			data.add(name.getP_id());
			data.add(name.getReg_date());
			data.add(name.getP_name());
			data.add(name.getP_mobile());
			data.add(name.getDistrict());
			data.add(name.getP_add());
			// data.add(name.getP_doc_name());
			data.add(name.getRefer_name());
			data.add("");
			tableModel.addRow(data);
			
			System.out.println("name = " + name.getP_name());
		}

	}
}
