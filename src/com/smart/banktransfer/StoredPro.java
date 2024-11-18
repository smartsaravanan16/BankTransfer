package com.smart.banktransfer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class StoredPro {
	
	private static Connection con;
	private static String url="jdbc:mysql://localhost:3306/myntra";
	private static String username="root";
	private static String password="root";
	private static Scanner scan=new Scanner(System.in);
	private static java.sql.CallableStatement CallableStatement;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws SQLException {
		
		String sql="{call myn_amt(?,?)}";
		System.out.println("Emter the amount:");
		int amt=scan.nextInt();
		CallableStatement=con.prepareCall(sql);
		CallableStatement.setInt(1, amt);
		CallableStatement.registerOutParameter(2, Types.INTEGER);
		
		CallableStatement.execute();
		
		int count=CallableStatement.getInt(2);
		System.out.println(count);
	}

}
