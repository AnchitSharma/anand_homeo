package com.utility;

import java.util.List;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class AutoTextField extends JTextField {
	private AutoComboBox autoComboBox;
	private List<String> dataList;
	private boolean isCaseSensitive;
	private boolean isStrict;

	public AutoTextField(List<String> list, AutoComboBox autoComboBox) {
		super();
		this.autoComboBox = autoComboBox;
		this.dataList = list;
		isCaseSensitive = false;
		isStrict = true;
		autoComboBox = null;
		if (list == null) {
			throw new IllegalArgumentException("values cannot be null");
		} else {
			dataList = list;
			this.autoComboBox = autoComboBox;
			init();
			return;
		}
	}

	class AutoDocument extends PlainDocument {

		public void replace(int i, int j, String s, AttributeSet attribute)
				throws BadLocationException {
			super.remove(i, j);
			insertString(i, s, attribute);

		}

		public void insertString(int i, String s, AttributeSet attributeSet)
				throws BadLocationException {
			if (s == null || "".equals(s)) {
				return;
			}

			String s1 = getText(0, i);
			String s2 = getMatch(s1 + s);
			int j = (i + s.length()) - 1;

			if (isStrict && s2 == null) {
				s2 = getMatch(s1);
				j--;
			} else if (!isStrict && s2 == null) {
				super.insertString(i, s, attributeSet);
				return;
			}

			if (autoComboBox != null && s2 != null)
				autoComboBox.setSelectedValue(s2);
			super.remove(0, getLength());
			super.insertString(0, s2, attributeSet);
			setSelectionStart(j + 1);
			setSelectionEnd(getLength());

		}

		public void remove(int i, int j) throws BadLocationException {
			int k = getSelectionStart();
			if (k > 0)
				k--;
			String s = getMatch(getText(0, k));
			if (!isStrict && s == null) {
				super.remove(i, j);
			} else {
				super.remove(0, getLength());
				super.insertString(0, s, null);
			}

			if (autoComboBox != null && s != null)
				autoComboBox.setSelectedValue(s);
			try {
				setSelectionStart(k);
				setSelectionEnd(getLength());
			} catch (Exception e) {

			}

		}
	}

	public AutoTextField(List<String> list) {
		isCaseSensitive = false;
		isStrict = true;
		autoComboBox = null;
		if (list == null) {
			throw new IllegalArgumentException("Values can not be null");
		} else {
			dataList = list;
			init();
			return;
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		setDocument(new AutoDocument());
		if (isStrict && dataList.size() > 0)
			setText(dataList.get(0).toString());
	}

	private String getMatch(String s) {
		for (int i = 0; i < dataList.size(); i++) {
			String s1 = dataList.get(i).toString();
			if (s1 != null) {
				if (!isCaseSensitive
						&& s1.toLowerCase().startsWith(s.toLowerCase()))
					return s1;
				if (isCaseSensitive && s1.startsWith(s))
					return s1;
			}
		}

		return null;
	}
	
	public void replaceSelection(String s){
		AutoDocument _lb = (AutoDocument)getDocument();
		if (_lb != null) {
			try{
				int i = Math.min(getCaret().getDot(), getCaret().getMark());
				int j = Math.max(getCaret().getDot(),getCaret().getMark());
				_lb.replace(i, j-i, s, null);
			}catch(Exception e){
				
			}
		}
	}
	
	public boolean isCaseSensitive(){
		return isCaseSensitive;
	}
	
	public void setCaseSensitive(boolean flag){
		isCaseSensitive = flag;
	}
	
	public boolean isStrict(){
		return isStrict;
	}
	
	public void setStrict(boolean flag){
		isStrict = flag;
	}
	
	public List<String> getDataList(){
		return dataList;
	}
	
	public void setDataList(List<String> list){
		if (list == null) {
			throw new IllegalArgumentException("values cannot be null");
		}else{
			dataList = list;
			return;
		}
	}
	

}
