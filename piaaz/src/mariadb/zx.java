package mariadb;

import java.sql.*;

public class zx {

	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost:3306/Customers";
	
	public static void main(String[] args) throws ClassNotFoundException {
		//Register JDBC driver
		Class.forName(dbClassName);
		//Database credentials
		final String USER = "root";
		final String PASS = "123";
		System.out.println("Connecting to database...");
		int qq= 2;
		try {
			//Establish connection
			Connection conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");
			
			//Execute a query
			System.out.println("Preparing a statement...");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO a (i)"+"VALUES('"+qq+"');";
			stmt.executeUpdate(sql);
			

			
			System.out.println("Closing connection...");
			stmt.close();
			conn.close();
			System.out.println("Success!");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}