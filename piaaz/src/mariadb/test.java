package mariadb;

import java.sql.*;
import java.util.Scanner;

import example.SQLController;

public class test {
    // 'sc' is needed in order to scan the inputs provided by the user
	private Scanner sc = null;
	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost:3306/Customers";
	
	public static void main(String[] args) throws ClassNotFoundException {
		//Register JDBC driver
		sqlcc.startSession();
			String sql = "SELECT * FROM customers WHERE Active =1 ";
			sqlcc.executeSession(sql,1);
		sqlcc.endSession();
			
	}
	}
	