package com.bsu.spezkurs.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDB {
	private static final String URL = "jdbc:mysql://localhost:3306/mysql";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static final String CREATE_DB = "CREATE DATABASE IF NOT EXISTS SPEZ";
	private static final String CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS SPEZ.PRODUCTS "
			+ "(ID INT NOT NULL AUTO_INCREMENT,PRIMARY KEY(ID),NAME VARCHAR(255),"
			+ "DESCRIPTION TEXT(65534), PRICE INT, NUMBER INT);";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;

		// static void connect () {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException sqle) {
			System.out.println("SQL exception1: " + sqle.getMessage());
			System.out.println("SQLState1: " + sqle.getSQLState());
			System.out.println("VendorError1: " + sqle.getErrorCode());
			System.exit(-1);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		//	stmt.execute("drop database spez");
			stmt.execute(CREATE_DB);
			stmt.execute(CREATE_PRODUCT_TABLE);
//			stmt
//					.execute("INSERT INTO SPEZ.PRODUCTS (NAME,DESCRIPTION,PRICE,NUMBER) VALUES('product','first','100','6');");
			System.out.print("success!!!");
		} catch (SQLException sqle) {
			System.out.println("SQL exception2: " + sqle.getMessage());
		} catch (Exception sqle) {
		}
	}

}
