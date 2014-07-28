import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class payment {
	public payment(String tid, String standingOrder, String username2, String password) throws ClassNotFoundException, SQLException{
		Scanner input = new Scanner(System.in);
		System.out.println("Choose your method of payment");
		System.out.println("1,Balance \n2,Credit \n3,Cash");
		int total = 0;
		String sql = "select price from `order` where tid = '"+tid+"';";
		commandLine.startSession();
		ArrayList<String> data = commandLine.executeSession(sql, 1);
		commandLine.endSession();
		for (int a =0;a<data.size();a++){
			total = total + Integer.parseInt(data.get(a));
		}
		System.out.println("Your total is '"+total+"'");
		sql = "update transaction set totalPrice = '"+total+"' where tid = '"+tid+"';";
		commandLine.startSession();
		commandLine.executeSession(sql, 2);
		commandLine.endSession();
		String choose = "";
		if (standingOrder.equals("0")){
			choose = input.nextLine();
		}
		else{
			sql = "select methodofpayment from transaction where tid = '"+tid+"';";
			commandLine.startSession();
			choose = commandLine.executeSession(sql, 1).get(0);
			 
		}
		
		while (true){
			if (choose.equals("1")){
				choose = "Balance";
				commandLine.startSession();
				sql = "select username from transaction where tid = '"+tid+"';";
				System.out.println(tid);
				String username = commandLine.executeSession(sql, 1).get(0);
				sql = "select balance from customers where Username = '"+username+"';";
				ArrayList<String> Data = commandLine.executeSession(sql, 1);
				String currentBalance = Integer.toString(Integer.parseInt(Data.get(0)) - total);
				sql = "update customers set balance = '"+currentBalance+"' where Username = '"+username+"';";
				commandLine.executeSession(sql, 2);
				System.out.println("You have current balance of "+ currentBalance);
				commandLine.endSession();
				choose = input.nextLine();
				break;
			}
			if (choose.equals("2")){
				choose = "Credit";
				commandLine.startSession();
				sql = "select username from transaction where tid = '"+tid+"';";
				String username = commandLine.executeSession(sql, 1).get(0);
				sql = "select creditCardNum from customers where Username = '"+username+"';";
				String creditCard = commandLine.executeSession(sql, 1).get(0);
				if (creditCard.equals("0")){
					System.out.println("enter your credit please");
					choose = input.nextLine();
					sql = "update customers set creditCardNum = '"+choose+"' where Username = '"+username+"';";
					commandLine.startSession();
					commandLine.executeSession(sql, 2);
					commandLine.endSession();
				}
				break;
			}
			if (choose.equals("3")){
				choose = "Cash";
				break;
			}
		}
		sql = "update transaction set methodOfPayment = '"+choose+"' where tid = '"+tid+"';";
		commandLine.startSession();
		commandLine.executeSession(sql, 2);
		commandLine.endSession();
<<<<<<< .mine
		new dispatchTicket(tid);
=======
		new customer(username2,password);
		new delivery();
>>>>>>> .r34
	}	
}
