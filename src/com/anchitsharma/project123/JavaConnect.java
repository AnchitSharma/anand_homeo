package com.anchitsharma.project123;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;

public class JavaConnect {

	// it is not neccessary to create a database
	static Connection conn = null;
	public static final String db_name = "employee.db";
	public static final String table_name = "emp";
	private static final AtomicLong LAST_TIME_MS = new AtomicLong();
	public static synchronized Connection getConnection() {
		// TODO Auto-generated method stub
		/*try {//136.243.177.87:3306
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return conn;*/
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager
					.getConnection("jdbc:sqlite:employee.db");
			
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

	public static String uniqueCurrentTimeStamp() {
		long now = System.currentTimeMillis();
		while(true) {
			long lastTime = LAST_TIME_MS.get();
			if (lastTime>= now) {
				now = lastTime +1;
			}
			if (LAST_TIME_MS.compareAndSet(lastTime, now)) {
				return String.valueOf(now);
			}
		}
	}

	

}
