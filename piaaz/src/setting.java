import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class setting {
	private String username;
	public setting(String username) throws ClassNotFoundException{
		this.username = username;
		Scanner input = new Scanner(System.in);
		String choose = "";
		while (! choose.equals("6")){
			System.out.println("1, Update");
			System.out.println("2, Account Balance");
			System.out.println("3, Orders");
			System.out.println("4, Transaction History");
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
			if (choose.equals("4")){
				new history(this.username);
			}
			if (choose.equals("2")){
				new balance(this.username);
			}
			if (choose.equals("1")){
				new update(this.username);
			}
			if (choose.equals("6")){
				return;
			}

	}
}
