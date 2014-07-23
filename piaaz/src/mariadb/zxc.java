package mariadb;

import java.sql.*;

import example.SQLController;

public class zxc {

	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost:3306/Customers";
	private SQLController sqlMngr = null;
	public static void main(String[] args) throws ClassNotFoundException {
		//Register JDBC driver
		Class.forName(dbClassName);
		//Database credentials
		final String USER = "root";
		final String PASS = "123";
		System.out.println("Connecting to database...");
		
		try {
			//Establish connection
			Connection conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");
			
			//Execute a query
			System.out.println("Preparing a statement...");
			Statement stmt = conn.createStatement();
			String sql = "SELECT EXISTS(SELECT * FROM customers WHERE Active =1) ";
			ResultSet rs = stmt.executeQuery(sql);
			
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				System.out.println(rs.getString(1));
			}
			
			
			System.out.println("Closing connection...");
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Success!");
		} catch (SQLException e) {
			System.err.println("Connection error occured! "+e);
		}
	}

}