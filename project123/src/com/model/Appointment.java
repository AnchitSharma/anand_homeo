package com.model;

public class Appointment {
	String date,type,status,remarks;

	public Appointment(String date, String type, String status, String remarks) {
		super();
		this.date = date;
		this.type = type;
		this.status = status;
		this.remarks = remarks;
	}

	public String getDate() {
		return date;
	}

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	public String getRemarks() {
		return remarks;
	}
	
	

}
