package com.model;

public class MedicineModel {

	String date;
	String med_name;
	Object med_price;
	String quantity,price_per_piece,tabCount;
	String drop_by_patient, drop_by_doctor;
	
	
	public MedicineModel(String med_name, Object med_price, String quantity, 
			String price_per_piece,String tabCount) {
		super();
		this.med_name = med_name;
		this.med_price = med_price;
		this.quantity = quantity;
		this.price_per_piece = price_per_piece;
		this.tabCount = tabCount;
	}
	
	
	public MedicineModel(String date, String med_name, Object med_price, String quantity, String price_per_piece,
			String tabCount) {
		super();
		this.date = date;
		this.med_name = med_name;
		this.med_price = med_price;
		this.quantity = quantity;
		this.price_per_piece = price_per_piece;
		this.tabCount = tabCount;
	}


	public MedicineModel(String date, String med_name, Object med_price, String quantity, String price_per_piece,
			String tabCount, String drop_by_patient, String drop_by_doctor) {
		super();
		this.date = date;
		this.med_name = med_name;
		this.med_price = med_price;
		this.quantity = quantity;
		this.price_per_piece = price_per_piece;
		this.tabCount = tabCount;
		this.drop_by_patient = drop_by_patient;
		this.drop_by_doctor = drop_by_doctor;
	}


	public String getDrop_by_patient() {
		return drop_by_patient;
	}


	public String getDrop_by_doctor() {
		return drop_by_doctor;
	}


	public String getDate() {
		return date;
	}


	public String getTabCount() {
		return tabCount;
	}
	public String getMed_name() {
		return med_name;
	}
	public Object getMed_price() {
		return med_price;
	}
	public String getQuantity() {
		return quantity;
	}
	public String getPrice_per_piece() {
		return price_per_piece;
	}
	
	
	
}
