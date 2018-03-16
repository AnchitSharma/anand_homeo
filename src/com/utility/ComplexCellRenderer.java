package com.utility;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;


public class ComplexCellRenderer implements ListCellRenderer{

	Font font = new Font("Helvitica", Font.PLAIN, 20); 
	/*@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		JLabel label = (JLabel)super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
		label.setHorizontalTextPosition(JLabel.RIGHT);
		label.setFont(font);
		return null;
	}*/
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		JLabel label = (JLabel)defaultRenderer.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
		label.setHorizontalTextPosition(JLabel.RIGHT);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(font);
		return label;
	}

}
