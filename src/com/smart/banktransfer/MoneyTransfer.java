package com.smart.banktransfer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MoneyTransfer {

	private static final String DISP="select * from user";
	private static final String S_AMOUNT="update user set balance=balance-? where accno=?";
	private static final String R_AMOUNT="update user set balance=balance+? where accno=?";

	private static Connection con;
	private static String url="jdbc:mysql://localhost:3306/bank";
	private static String username="root";
	private static String password="root";
	private static Statement stmt;
	private static ResultSet resultSet;
	private static Scanner scan=new Scanner(System.in);
	private static PreparedStatement pstmt;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		disp();
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Enter the AccountNo:");
		String saccno = scan.next();
		System.out.println("Enter the Amount:");
		String amount = scan.next();

		try {
			pstmt=con.prepareStatement(S_AMOUNT);
			pstmt.setString(1, amount);
			pstmt.setString(2, saccno);

			int x=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	System.out.println("Enter Receiver AccountNo:");
		String raccno=scan.next();
		
		try {
			pstmt=con.prepareStatement(R_AMOUNT);
			pstmt.setString(1, amount);
			pstmt.setString(2, raccno);
			int x=pstmt.executeUpdate();
	
		if(x==0) {

			System.out.println("Failure");
			}
		else {
				System.out.println("Success");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
	}
		Authentication.verify(con, scan);
		
		disp();
	}
	
	static void disp() {
		try { 
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(DISP);
			while(resultSet.next()) {
				System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)
				+" "+resultSet.getString(3)+" "+resultSet.getString(4));
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
