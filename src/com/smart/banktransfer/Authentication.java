package com.smart.banktransfer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Authentication {
	
	private static int t_pin=12345;
	static void verify(Connection con,Scanner scan)
	{
		System.out.println("Do you realy want to send money? \nEnter the Transaction pin:");
		int pin=scan.nextInt();
		if(t_pin==pin) {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
