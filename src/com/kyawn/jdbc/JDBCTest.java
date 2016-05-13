package com.kyawn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCTest {

	public static Connection getConnection() {

		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_db", "root", "kaiyaxiong123");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

	public static void insert() {

		Connection conn = getConnection();
		try {
			String sql = "INSERT INTO tbl_user(name,password,email)" + " VALUES('kk','123456','tom@gmail.com')";
			Statement st = conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("向用户表中插入了 " + count + " 条记录");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void update() {

		Connection conn = getConnection();
		try {
			String sql = "UPDATE tbl_user SET email='tom@yahoo.com' WHERE name = 'Tom'";
			Statement st = conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("向用户表中更新了 " + count + " 条记录");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delete() {

		Connection conn = getConnection();
		try {
			String sql = "DELETE FROM tbl_user WHERE name = 'Tom'";
			Statement st = conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("从用户表中删除了 " + count + " 条记录");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		 insert();
		// update();
//		delete();
	}

}
