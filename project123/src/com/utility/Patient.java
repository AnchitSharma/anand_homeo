package com.utility;

public class Patient  {
	String reg_date, p_id,p_name, p_bill_amt,p_amt_due,amt_paid 
	,p_doc_name,refer_name,p_add,p_mobile;
	String district,pincode,gender,age,occupation;
	public Patient(String reg_date, String p_id, String p_name, String p_bill_amt, String p_amt_due, String amt_paid,
			String p_doc_name, String refer_name, String p_add,String p_mobile) {
		super();
		this.reg_date = reg_date;
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_bill_amt = p_bill_amt;
		this.p_amt_due = p_amt_due;
		this.amt_paid = amt_paid;
		this.p_doc_name = p_doc_name;
		this.refer_name = refer_name;
		this.p_add = p_add;
		this.p_mobile = p_mobile;
	}

	
	
	public Patient(String reg_date, String p_id, String p_name, String p_bill_amt, String p_amt_due, String amt_paid,
			String p_doc_name, String refer_name, String p_add, String p_mobile, String district,String pincode, String gender,
			String age, String occupation) {
		super();
		this.reg_date = reg_date;
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_bill_amt = p_bill_amt;
		this.p_amt_due = p_amt_due;
		this.amt_paid = amt_paid;
		this.p_doc_name = p_doc_name;
		this.refer_name = refer_name;
		this.p_add = p_add;
		this.p_mobile = p_mobile;
		this.district = district;
		this.pincode = pincode;
		this.gender = gender;
		this.age = age;
		this.occupation = occupation;
	}



	public String getDistrict() {
		return district;
	}



	public String getPincode() {
		return pincode;
	}



	public String getGender() {
		return gender;
	}



	public String getAge() {
		return age;
	}



	public String getOccupation() {
		return occupation;
	}



	public String getP_mobile() {
		return p_mobile;
	}


	public String getReg_date() {
		return reg_date;
	}

	public String getP_id() {
		return p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public String getP_bill_amt() {
		return p_bill_amt;
	}

	public String getP_amt_due() {
		return p_amt_due;
	}

	public String getAmt_paid() {
		return amt_paid;
	}

	public String getP_doc_name() {
		return p_doc_name;
	}

	public String getRefer_name() {
		return refer_name;
	}

	public String getP_add() {
		return p_add;
	}



	@Override
	public String toString() {
		return "Patient [reg_date=" + reg_date + ", p_id=" + p_id + ", p_name=" + p_name + ", p_bill_amt=" + p_bill_amt
				+ ", p_amt_due=" + p_amt_due + ", amt_paid=" + amt_paid + ", p_doc_name=" + p_doc_name + ", refer_name="
				+ refer_name + ", p_add=" + p_add + ", p_mobile=" + p_mobile + ", district=" + district + ", pincode="
				+ pincode + ", gender=" + gender + ", age=" + age + ", occupation=" + occupation + "]";
	}

	
	
	
}
