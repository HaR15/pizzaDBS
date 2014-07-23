import java.util.ArrayList;
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
			if (choose.equals("5")){
				commandLine.startSession();
				String sql = "update customers set Active = 0 where Username = '"+this.username+"'";
				commandLine.executeSession(sql, 2);
				commandLine.endSession();
			}
			if (choose.equals("2")){
				new balance();
				commandLine.startSession();
				String sql = "select balance from customers where Username = '"+this.username+"';";
				ArrayList<String> Data = commandLine.executeSession(sql, 1);
				System.out.println(Data.get(0));
				commandLine.endSession();
			}
			if (choose.equals("1")){
				new update(this.username);
			}
			if (choose.equals("6")){
				return;
			}

	}
}
