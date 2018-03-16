package com.utility;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class FollowUpRenderer implements TableCellRenderer {
	
	JTextField label;
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	Date date = new Date();
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected
			, boolean hasFocus, int row,
			int column) {
		
		label = new JTextField();
		if (value != null) {
			label.setText(value.toString());
			Object val = table.getValueAt(row, 2);
			String strdate = dateFormat.format(date);
			System.out.println("Followup rendered"+val);
			try {
				Date date1 = dateFormat.parse(String.valueOf(val));
				if (date1.before(date)&&!val.equals(strdate)) {
					label.setBackground(Color.RED);
					label.setForeground(Color.WHITE);
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return label;
		
	}
	
	

}
