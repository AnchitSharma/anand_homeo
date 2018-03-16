package com.utility;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	private static final String TAG = "MyTableModel";
	int colIndex = 0;
	private List<String> cols = new ArrayList<>();
	private List<RowData> rows = new ArrayList<>();

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

		return true;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return cols.get(column);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows.size();
	}

	public void addRow(RowData newRow) {
		rows.add(newRow);
		fireTableRowsInserted(rows.size(), rows.size());

	}
	
	public void addRow(int index,RowData newRow) {
		rows.add(index,newRow);
		fireTableRowsInserted(rows.size(), rows.size());
	}

	public void removeRow(int selectedRow) {
		rows.remove(selectedRow);
		fireTableRowsDeleted(selectedRow, selectedRow);
	}

	public void removeAll() {
		int count = getRowCount();
		if (count > 0) {
			System.out.println(TAG+" "+count);
			System.out.println(rows);
			rows.clear();
		}
		fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return getValueAt(0, columnIndex).getClass();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		RowData row = rows.get(rowIndex);
		return row.getValueForCol(cols.get(columnIndex));
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		RowData rowData = rows.get(rowIndex);
		rowData.setValueForCol(aValue, cols.get(columnIndex));
	}

	public void setColumnNames(String[] colNames) {
		for (int i = 0; i < colNames.length; i++) {
			cols.add(colNames[i]);
		}
	}
}
