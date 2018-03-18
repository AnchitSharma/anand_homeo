package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.anchitsharma.project123.JavaConnect;
import com.utility.AutoComplete;
import com.utility.Patient;

public class SearchModels {

	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private Map<String, Integer> medmap;
	private List<String> columns;
	private Map<String, Object> where;
	private List<List<String>> selectdata;

	public SearchModels() {
		this.conn = JavaConnect.getConnection();
		this.pst = null;
		this.rs = null;

	}

	public Patient getPatient(String pid) {
		//CREATE TABLE "patient_table" ( `p_id` TEXT, `p_name` TEXT, `p_mobile` 
		//TEXT UNIQUE, `p_dob` TEXT, `p_add` TEXT, `p_district` TEXT, `pincode` TEXT, 
		//`occupation` TEXT, `p_blood` TEXT, `p_gender` TEXT, `p_age` TEXT, `p_bill_amt` TEXT, 
		//`p_amt_due` TEXT, `amt_paid` TEXT, `refer_id` TEXT, `last_visited` TEXT DEFAULT 0, 
		//`reg_date` TEXT, `p_doc_id` INTEGER, `remarks` TEXT, `id` INTEGER PRIMARY KEY AUTOINCREMENT, 
		//`active` INTEGER DEFAULT 1 )
		
		columns = new ArrayList();
		where = new HashMap<>();
		Patient p = null;
		columns.add("p_id");//0
		columns.add("p_name");//1
		columns.add("p_mobile");//2
		columns.add("p_dob");//3
		columns.add("p_add");//4
		columns.add("p_district");//5
		columns.add("pincode");//6
		columns.add("occupation");//7
		columns.add("p_blood");//8
		columns.add("p_gender");//9
		columns.add("p_age");//10
		columns.add("refer_id");//11
		where.put("p_id", pid);//12
		
		selectdata = selectData("patient_table", columns, where);
		if(!selectdata.isEmpty()) {
			for(List<String>str :selectdata) {
				p = new Patient("", str.get(0), str.get(1), "", "", 
						"", "", str.get(11), str.get(4), str.get(2), str.get(5), str.get(6), str.get(9), 
						str.get(10), str.get(7));
			}
			
		}
		return p;
	}

	public String searchNameMobile(String mobile) {

		String data = "";
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("p_name");
		where.put("p_mobile", mobile);
		selectdata = selectData("patient_table", columns, where);
		if (!selectdata.isEmpty()) {
			data = selectdata.get(0).get(0);
		}

		return data;

	}

	public String getMobileNumber(String pid) {
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("p_mobile");
		where.put("p_id", pid);
		selectdata = selectData("patient_table", columns, where);
		if (!selectdata.isEmpty()) {
			return selectdata.get(0).get(0);
		}
		return "";
	}

	public String searchName(String name) {
		String sql = "select p_id from patient_table where p_name = ?";
		String pid = "";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if (rs.next()) {
				pid = String.valueOf(rs.getInt("p_id"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pid;
	}

	public String searchNameByID(String id) {
		String sql = "select p_name from patient_table where p_id = ?";
		String pname = "";
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("p_name");
		where.put("p_id", id);
		List<List<String>> selectdata = selectData("patient_table", columns, where);
		for (List<String> strList : selectdata) {
			pname = strList.get(0);
		}
		return pname;
	}

	public String getMaxPID() {
		String sql1 = "select max(p_id) as p_id from patient_table";
		String pid = "";
		try {
			pst = conn.prepareStatement(sql1);
			rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getInt("p_id") == 0) {

					pid = String.valueOf(1);
				} else {
					pid = String.valueOf(rs.getInt("p_id") + 1);
				}
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pid;
	}

	public String getMaxPInvoice() {

		String sql2 = "select max(invoice_id) as i_id from invoice_table";
		String inv_id = "";
		try {
			pst = conn.prepareStatement(sql2);
			rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getInt("i_id") == 0) {
					inv_id = "1";
				} else {
					inv_id = String.valueOf(rs.getInt("i_id") + 1);
				}
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inv_id;
	}

	public Object[] getPatientNames() {
		// text referal text field
		String sql3 = "select p_name from patient_table";

		Object[] objects;
		ArrayList<String> list = new ArrayList<>();
		list.add("");

		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("p_name");
		selectdata = selectData("patient_table", columns, where);
		if (!selectdata.isEmpty()) {
			for (List<String> strList : selectdata) {
				list.add(strList.get(0));
			}
		}
		objects = new Object[list.size()];
		objects = list.toArray();

		return objects;

	}

	public Object[] getMedNames() {
		String sql = "select * from medicine_record";
		Object[] objects;

		ArrayList<String> list = new ArrayList<>();
		list.add("");

		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("med_name");
		String grpdate = " order by med_name ";
		selectdata = selectData("medicine_record", columns, where, grpdate);
		if (!selectdata.isEmpty()) {
			for (List<String> strList : selectdata) {
				list.add(strList.get(0));
			}
		}

		objects = new Object[list.size()];
		objects = list.toArray();
		return objects;

	}

	public int getMedId(String med_name) {
		int id = 0;
		if (medmap != null) {
			columns = new ArrayList<>();
			where = new HashMap<>();
			columns.add("id");
			where.put("med_name", med_name);
			selectdata = selectData("medicine_record", columns, where);
			if (!selectdata.isEmpty()) {
				id = Integer.parseInt(selectdata.get(0).get(0));
			}
		}
		return id;
	}

	private void getDoctorData(int code) {
		String sql = "select * from doctors where d_code= ?";
		try {
			if (code != 0) {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, code);
				rs = pst.executeQuery();
				if (rs.next()) {
					// txt_dname.setText(rs.getString("d_name"));
					/*
					 * txt_dname.setSelectedItem(rs.getString("d_name"));
					 * txt_degree.setText(rs.getString("degree"));
					 * txt_dadd.setText(rs.getString("address"));
					 * txt_dcontact.setText(rs.getString("phone_home"));
					 */
					// txt_dremark.setText(rs.getString(""));
				}

				pst.close();
				rs.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void storeInDatabase(String tableName, Map<String, Object> columns) {

		StringBuilder sql = new StringBuilder("insert into ");
		sql.append(tableName);
		sql.append(" ( ");
		for (String str : columns.keySet()) {
			sql.append(str);
			sql.append(",");
		}

		String str = sql.substring(0, sql.length() - 1);
		sql = new StringBuilder(str);
		sql.append(" ) values ");
		sql.append("(");
		for (String st : columns.keySet()) {
			String value = String.valueOf(columns.get(st));
			sql.append("\'");
			sql.append(value);
			sql.append("\'");
			sql.append(",");
		}

		String sq = sql.substring(0, sql.length() - 1);
		sql = new StringBuilder(sq);
		sql.append(" )");

		// //System.out.println(sql.toString());
		try {
			pst = conn.prepareStatement(sql.toString());
			pst.executeUpdate();
			// System.out.println("insert successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int updateDatabase(String tableName, Map<String, Object> where, Map<String, Object> what) {
		// String sql = "update emp set active = ? where username = ?";
		int count = 0;
		StringBuilder sql = new StringBuilder("update ");
		sql.append(tableName);
		sql.append(" set ");
		for (String str : what.keySet()) {
			sql.append(str);
			sql.append("=");
			sql.append("\'");
			sql.append(what.get(str));
			sql.append("\'");
			sql.append(",");
		}

		String s1 = sql.substring(0, sql.length() - 1);
		sql = new StringBuilder(s1);
		sql.append(" where ");
		for (String sq : where.keySet()) {
			sql.append(sq);
			sql.append("=");
			sql.append("\'");
			sql.append(where.get(sq));
			sql.append("\'");
			sql.append(" and ");
		}

		String s2 = sql.substring(0, sql.length() - "and".length() - 1);
		// System.out.println(s2);

		try {
			if (!conn.isClosed()) {
				pst = conn.prepareStatement(s2);

				count = pst.executeUpdate();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				// //System.out.println("pst call close in logoout ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return count;
	}

	public List<List<String>> selectData(String tableName,

			List<String> columns, Map<String, Object> where) {

		// String sql = "select p_name,p_add from patient_table where p_id = ?";
		List<List<String>> selectdata = new ArrayList<List<String>>();
		List<String> colData = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select ");
		for (String str : columns) {
			sql.append(str);
			sql.append(",");
		}
		String s1 = sql.substring(0, sql.length() - 1);
		sql = new StringBuilder(s1);
		sql.append(" from ");
		sql.append(tableName);

		sql.append(" where ");
		for (String s2 : where.keySet()) {
			sql.append(s2);
			sql.append(" = ");
			sql.append("\'");
			sql.append(where.get(s2));
			sql.append("\'");
			sql.append(" and ");
		}
		if (where.isEmpty()) {
			// //System.out.println("where is empty detecetd");
			s1 = sql.substring(0, sql.length() - "where ".length());
		} else {
			s1 = sql.substring(0, sql.length() - "and ".length());
		}

		System.out.println(s1);
		
		try {
			pst = conn.prepareStatement(s1);

			rs = pst.executeQuery();
			while (rs.next()) {
				for (String sq : columns) {
					colData.add(rs.getString(sq));
				}

				selectdata.add(colData);
				colData = new ArrayList<>();

			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return selectdata;
	}

	public List<List<String>> selectData(String tableName, List<String> columns, Map<String, Object> where,
			List<String> grpdate) {

		// String sql = "select p_name,p_add from patient_table where p_id = ?";
		// select date ,sum(payment) from invoice_table group by date;
		List<List<String>> selectdata = new ArrayList<List<String>>();
		List<String> colData = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select ");
		for (String str : columns) {
			sql.append(str);
			sql.append(",");
		}
		String s1 = sql.substring(0, sql.length() - 1);
		sql = new StringBuilder(s1);
		sql.append(" from ");
		sql.append(tableName);

		sql.append(" where ");
		for (String s2 : where.keySet()) {
			if (s2.contains("between")) {
				// select * from patient_table where reg_date between '30-01-2018' and
				// '31-01-2018';
				sql.append(where.get(s2));
				sql.append(" and ");
			} else if (s2.contains("like")) {
				sql.append(s2);
				sql.append("\'");
				sql.append(where.get(s2));
				sql.append("\'");
				sql.append(" and ");
			} else {
				sql.append(s2);
				sql.append(" = ");
				sql.append("\'");
				sql.append(where.get(s2));
				sql.append("\'");
				sql.append(" and ");
			}

		}
		if (where.isEmpty()) {
			// //System.out.println("where is empty detecetd");
			s1 = sql.substring(0, sql.length() - "where ".length());
		} else {
			s1 = sql.substring(0, sql.length() - "and ".length());
		}

		// System.out.println(s1);
		// select date ,sum(payment) from invoice_table group by date;
		// select date , sum(medcine_price) from medicine_order_table where medicine_id
		// like 'Oiling%' group by date;
		if (!grpdate.isEmpty()) {
			/*
			 * if(grpdate.get(0).contains(Constants.group_by)){ sql = new StringBuilder(s1);
			 * sql.append(" group by ");
			 * 
			 * sql.append(grpdate.get(1)); }
			 */

			sql = new StringBuilder(s1);
			sql.append(" group by ");

			sql.append(grpdate.get(0));

			// 2
			if (grpdate.size() > 1 && grpdate.get(1).equals(Constants.order_by)) {
				sql.append(" ");
				sql.append(Constants.order_by);
				sql.append(" ");
				sql.append(grpdate.get(2));
			}
		}
		s1 = sql.toString();
		// System.out.println(s1);
		try {
			pst = conn.prepareStatement(s1);

			rs = pst.executeQuery();
			while (rs.next()) {
				for (String sq : columns) {
					colData.add(rs.getString(sq));
				}

				selectdata.add(colData);
				colData = new ArrayList<>();

			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return selectdata;
	}

	public List<List<String>> selectData(String tableName, List<String> columns, Map<String, Object> where,
			String grpdate) {

		// String sql = "select p_name,p_add from patient_table where p_id = ?";
		// select date ,sum(payment) from invoice_table group by date;
		List<List<String>> selectdata = new ArrayList<List<String>>();
		List<String> colData = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select ");
		for (String str : columns) {
			sql.append(str);
			sql.append(",");
		}
		String s1 = sql.substring(0, sql.length() - 1);
		sql = new StringBuilder(s1);
		sql.append(" from ");
		sql.append(tableName);

		sql.append(" where ");
		for (String s2 : where.keySet()) {
			if (s2.contains("between")) {
				// select * from patient_table where reg_date between '30-01-2018' and
				// '31-01-2018';
				sql.append(where.get(s2));
				sql.append(" and ");
			} else if (s2.contains("like")) {
				sql.append(s2);
				sql.append("\'");
				sql.append(where.get(s2));
				sql.append("\'");
				sql.append(" and ");
			} else {
				sql.append(s2);
				sql.append(" = ");
				sql.append("\'");
				sql.append(where.get(s2));
				sql.append("\'");
				sql.append(" and ");
			}

		}
		if (where.isEmpty()) {
			// //System.out.println("where is empty detecetd");
			s1 = sql.substring(0, sql.length() - "where ".length());
		} else {
			s1 = sql.substring(0, sql.length() - "and ".length());
		}

		// System.out.println(s1);

		if (!grpdate.isEmpty()) {
			sql = new StringBuilder(s1);
			sql.append(grpdate);
		}
		s1 = sql.toString();
		// System.out.println(s1);
		try {
			pst = conn.prepareStatement(s1);

			rs = pst.executeQuery();
			while (rs.next()) {
				for (String sq : columns) {
					colData.add(rs.getString(sq));
				}

				selectdata.add(colData);
				colData = new ArrayList<>();

			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return selectdata;
	}

	public void deleteTable(String tablename, String where) {
		String sql = "delete from " + tablename + " " + where;
		// System.out.println(sql);
		try {
			pst = conn.prepareStatement(sql);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Object> userLoginStatus() {
		List<Object> data = new ArrayList<>();
		int i = 0;
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("max(active)");
		columns.add("username");
		selectdata = selectData("emp", columns, where);
		if (!selectdata.isEmpty()) {
			data.add(Integer.valueOf(selectdata.get(0).get(0)));
			data.add(selectdata.get(0).get(1));
		}

		return data;
	}

	public List<MedicineModel> getMedicines(String p_id) {
		List<MedicineModel> list = new ArrayList<>();
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("max(med_invoice_id)");
		columns.add("med_invoice_id");
		where = new HashMap<>();
		where.put("p_id", p_id);
		selectdata = selectData("medicine_order_entry", columns, where);
		if (!selectdata.isEmpty()) {
			String invoice = selectdata.get(0).get(1);
			if (null != invoice) {
				columns.clear();
				where.clear();
				columns.add("date");// 0
				columns.add("medicine_id");// 1
				columns.add("medcine_price");// 2
				columns.add("quantity");// 3
				columns.add("price_perp");// 4
				columns.add("tab_count");// 5
				columns.add("cancel_doctor");// 6
				columns.add("cancel_patient");// 7
				where.put("invoice_number", invoice);
				// where.put("cancel_doctor", 1); iwill have filter the data over my end
				selectdata = selectData("medicine_order_table", columns, where);
				MedicineModel med;
				if (!selectdata.isEmpty()) {
					for (List<String> strList : selectdata) {
						/*
						 * med = new MedicineModel(strList.get(0),strList.get(1) , strList.get(2),
						 * "",strList.get(4) , strList.get(5));
						 */
						med = new MedicineModel(strList.get(0), strList.get(1), strList.get(2), "", strList.get(4),
								strList.get(5), strList.get(7), strList.get(6));
						list.add(med);
					}
				}
			}
		}
		return list;
	}

	public String getMedName(String id) {
		String med_name = "";
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("med_name");
		where.put("id", id);
		selectdata = selectData("medicine_record", columns, where);
		if (!selectdata.isEmpty()) {
			med_name = selectdata.get(0).get(0);
		}

		return med_name;
	}

	public Object[] getGroupNames() {
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("grp_code");
		String grpdata = " group by grp_code";
		selectdata = selectData("medicine_record", columns, where, grpdata);
		columns.clear();
		if (!selectdata.isEmpty()) {
			for (List<String> strList : selectdata) {
				columns.add(strList.get(0));
			}
		}
		Object[] objects = new Object[columns.size()];
		objects = columns.toArray();
		return objects;
	}

	public boolean checkRegId(String id) {
		columns = new ArrayList<>();
		where = new HashMap<>();
		columns.add("p_id");
		where.put("p_id", id);
		selectdata = selectData("patient_table", columns, where);
		if (!selectdata.isEmpty()) {
			return true;
		}
		return false;
	}

	public List<MedicineModel> getMedicineHistory(String p_id) {
		List<MedicineModel> list = new ArrayList<>();
		columns = new ArrayList<>();
		where = new HashMap<>();

		columns.add("med_invoice_id");
		where = new HashMap<>();
		where.put("p_id", p_id);
		selectdata = selectData("medicine_order_entry", columns, where);
		if (!selectdata.isEmpty()) {
			for (List<String> str : selectdata) {
				String invoice = str.get(0);
				if (null != invoice) {
					columns.clear();
					where.clear();
					columns.add("date");// 0
					columns.add("medicine_id");// 1
					columns.add("medcine_price");// 2
					columns.add("quantity");// 3
					columns.add("price_perp");// 4
					columns.add("tab_count");// 5
					columns.add("cancel_doctor");// 6
					columns.add("cancel_patient");// 7
					where.put("invoice_number", invoice);

					selectdata = selectData("medicine_order_table", columns, where);
					MedicineModel med;
					if (!selectdata.isEmpty()) {
						for (List<String> strList : selectdata) {

							med = new MedicineModel(strList.get(0), strList.get(1), strList.get(2), "", strList.get(4),
									strList.get(5), strList.get(7), strList.get(6));
							list.add(med);
						}
					}
				}
			}

		}
		return list;
	}
}
