import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class placeOrder {
	public placeOrder(String username, String password) throws ClassNotFoundException, SQLException{
		// Setup scanner for inputs
		Scanner input = new Scanner(System.in);
		String choose = "";
		commandLine.startSession();
		String sql = "INSERT INTO `customers`.`transaction` (`date`, `totalPrice`, `tid`, `username`, `methodOfPayment`) VALUES (NOW(), '0', NULL, '"+username+"', 'nothing');";
		commandLine.executeSession(sql, 2);
		sql = "select tid from transaction where username = '"+username+"';";
		ArrayList<String> gtid = commandLine.executeSession(sql, 1);
		String tid = gtid.get(gtid.size()-1);
		commandLine.endSession();
		while (! choose.equals("6")){
			String fillers = "================================";
			System.out.println("1, Add Pizza");
			System.out.println("2, Add Side dishes and drinks");
			System.out.println("3, View Cart");
			System.out.println("4, Proceed to checkout");
			System.out.println("5, Cancel");
			choose = input.nextLine();
	        switch (choose) {
	            case "1":  {
	            	tid = pizza.pizzaa(username);
	            }
	                     break;
	            case "2":  {
	            	tid = sideOrder.sideOrderr(username);
	            }
	                     break;
	            case "3":  new viewCart(tid);
	                     break;
	            case "4":{
	            	new payment(tid);
	            }
	                     break;
	            case "5":  return;
	        }
		}
		
		
	}
}
