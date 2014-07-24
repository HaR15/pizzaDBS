import java.util.ArrayList;
import java.util.Scanner;


public class balance {
	public balance(String username){
		Scanner input = new Scanner(System.in);
		String choose ="";;
		while (!choose.equals("3")){
			System.out.println("1, Show Balance");
			System.out.println("2, Add Balance");
			System.out.println("3, Back");
			choose = input.nextLine();
			if (choose.equals("1")){
				commandLine.startSession();
				String sql = "select balance from customers where Username = '"+username+"';";
				ArrayList<String> Data = commandLine.executeSession(sql, 1);
				System.out.println("Balance = "+Data.get(0));
				commandLine.endSession();
			}
			if (choose.equals("2")){
				System.out.println("Enter the amount of balance to add in");
				choose = input.nextLine();
				commandLine.startSession();
				String sql = "update `customers` set balance = balance + '"+Integer.parseInt(choose)+"' where Username = '"+username+"';";
				commandLine.executeSession(sql, 2);
				sql = "select balance from customers where Username = '"+username+"';";
				ArrayList<String> Data = commandLine.executeSession(sql, 1);
				System.out.println("Current Balance = "+Data.get(0));
				commandLine.endSession();
			} 
		}
	}
}
