package com.utility;

import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class AutoComboBox extends JComboBox<String> {
	private AutoTextField editor;
	private boolean isFired;
	private AutoTextFieldEditor autoTextFieldEditor;
	private class AutoTextFieldEditor extends BasicComboBoxEditor{
		
		
		private AutoTextField getAutoTextFieldEditor(){
			return (AutoTextField) editor;
		}
		
		AutoTextFieldEditor(List<String> list){
			editor =new AutoTextField(list, AutoComboBox.this);
		}
	}
	
	public AutoComboBox(List list){
		this.isFired = false;
		this.autoTextFieldEditor = new AutoTextFieldEditor(list);
		
		
	}
	
	public boolean isCaseSensitive(){
		return autoTextFieldEditor.getAutoTextFieldEditor().isCaseSensitive();
	}
	
	public void setCaseSensitive(boolean flag){
		autoTextFieldEditor.getAutoTextFieldEditor().setCaseSensitive(flag);
	}
	
	public boolean isStrict(){
		return autoTextFieldEditor.getAutoTextFieldEditor().isStrict();
	}
	
	public void setStrict(boolean flag){
		autoTextFieldEditor.getAutoTextFieldEditor().isStrict();
	}
	
	public List<String> getDataList(){
		return autoTextFieldEditor.getAutoTextFieldEditor().getDataList();
	}
	
	public void setDataList(List<String> list){
		autoTextFieldEditor.getAutoTextFieldEditor().setDataList(list);
		setModel(new DefaultComboBoxModel(list.toArray()));
	}
	
	void setSelectedValue(Object obj){
		if (isFired) {
			return;
		}else{
			isFired = true;
			setSelectedItem(obj);
			fireItemStateChanged(new ItemEvent(this, 701, selectedItemReminder, 1));
			isFired = false;
			return;
		}
	}
	
	@Override
	protected void fireActionEvent() {
		// TODO Auto-generated method stub
		if(!isFired)
		super.fireActionEvent();
	}
}
