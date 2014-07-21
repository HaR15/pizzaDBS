import java.util.Scanner;
import java.sql.*;

public class setting {
	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost:3306/Customers";
	private String username;
	public setting(String username) throws ClassNotFoundException{
		this.username = username;
		Scanner input = new Scanner(System.in);
		String choose = "";
		while (! choose.equals("6")){
			System.out.println("1, Update");
			System.out.println("2, Account Balance");
			System.out.println("3, Orders");
			System.out.println("4, Tranaction History");
			System.out.println("5, Deactivate");
			System.out.println("6,Back");
			choose = input.nextLine();
			options(choose);
		}
	}

	private void options(String choose) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName(dbClassName);
		//Database credentials
		final String USER = "root";
		final String PASS = "123";
		try {
			//Establish connection
			Connection conn = DriverManager.getConnection(CONNECTION,USER,PASS);			
			//Execute a query
			String sql = "";
			Statement stmt = conn.createStatement();
			if (choose.equals("5")){
				sql = "update customers set Active = 0 where Username = '"+this.username+"'";
				stmt.executeUpdate(sql);
			}
			if (choose.equals("1")){
				new update(this.username);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Connection error occured!" + e);
		}
	}
}
