package com.utility;

import java.util.HashMap;
import java.util.Map;

public class RowData {

	private Map<String, Object> values = new HashMap<>();
	
	public RowData() {}
	public RowData(Map<String,Object> values) {
		this.values = values;
	}
	public Object getValueForCol(String colName) {
		if (values.containsKey(colName)) {
			return values.get(colName);
		}
		return "";
	}
	
	public void setValueForCol(Object value, String columnName) {
		values.put(columnName, value);
	}
	
	
}
