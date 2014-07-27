import java.util.ArrayList;
import java.util.Scanner;


public class pizza {
	public static String pizzaa(String username){
 
		String sql = "select name from product where category = 'pizza'";
		commandLine.startSession();
		// get list of all pizzas in products table
		ArrayList<String> pizza = commandLine.executeSession(sql, 1);
		commandLine.endSession();
		// setup Scanner input
		Scanner input = new Scanner(System.in);
		String choose = "";
		// total price
		int total =0 ;
		// the transaction id
		String tid = null;
		while (! choose.equals(Integer.toString((pizza.size())))){
			for (int i=0; i < pizza.size();i++){
				// Output the pizzas available in db with corresponding number
				System.out.println(i + ")"+pizza.get(i)) ;
			}
			System.out.println("type the number of the pizza you want or type '"+(pizza.size())+"' to quit");
			choose = input.nextLine();
			// Check user input
			if (choose.equals(Integer.toString(pizza.size()))){
				break;
			}
			// User chose a pizza

			// Get the pizza's pid
			sql = "select pid from product where name = '"+pizza.get(Integer.parseInt(choose))+"';";
			commandLine.startSession();
			String pid = commandLine.executeSession(sql, 1).get(0);
			
			//Get the transaction id (tid)
			sql = "select tid from transaction where username = '"+username+"';";
			ArrayList<String> gtid = commandLine.executeSession(sql, 1);
			tid = gtid.get(gtid.size()-1);
			
			// Get the pizza's price
			sql = "select price from product where pid = '"+pid+"';";
			String price = commandLine.executeSession(sql, 1).get(0);
			total = Integer.parseInt(price);
			
			//Insert information about this pizza order in orders table
			sql = "insert into `order` (tid,pid,quantity,price) VALUES ('"+tid+"','"+pid+"',1,'"+price+"');";
			commandLine.executeSession(sql, 2);
			
			// Get this pizza order's oid
			sql = "select oid from `order` where tid='"+tid+"' and pid='"+pid+"';";
			ArrayList<String> userOids = commandLine.executeSession(sql, 1);
			String oid = userOids.get(userOids.size()-1);
			
			// Insert into pizzaOrder info about his pizza order
			sql = "insert into `pizzaOrder` (oid, tid,pID) VALUES ('"+oid+"','"+tid+"','"+pid+"');";
			commandLine.executeSession(sql, 2);
			commandLine.endSession();
			
			// Get information about the toppings for this pizza
			topping(total, username, pizza.get(Integer.parseInt(choose)), oid);
		}
		//Take user back to customer screen
		return tid;
	}
	public static void topping(int total, String username, String pizza, String pizzaOID){
		// Get all the toppings in the database
		String sql = "select name from product where category = 'topping'";
		commandLine.startSession();
		ArrayList<String> data = commandLine.executeSession(sql, 1);
		commandLine.endSession();
		
		//Setup Scanner input
		Scanner input = new Scanner(System.in);
		String choose = "";
		while (!choose.equals(Integer.toString(data.size()))){
			//Display all toppings to be chosen with corresponding number 
			for (int a= 0;a< data.size();a++){
				System.out.println(a +")"+data.get(a));
			}
			
			System.out.println("type the number of the topping you want on your '"+pizza+"' or press '"+data.size()+"' to quit");
			choose = input.nextLine();
			if (choose.equals(Integer.toString(data.size()))){
				// User wants to go back
				break;
			}
			// User chose a topping 
			
			// Get the name of the topping
			sql = "select pid from product where name = '"+data.get(Integer.parseInt(choose))+"';";
			commandLine.startSession();
			String pid = commandLine.executeSession(sql, 1).get(0);
			
			// Get the transaction id (tid) of this order
			sql = "select tid from transaction where username = '"+username+"';";
			ArrayList<String> gtid = commandLine.executeSession(sql, 1);
			String tid = gtid.get(gtid.size()-1);
			
			
			// Check if this topping has already been used for this transaction 
			sql = "select exists(select * from `order` where tid = '"+tid+"' and pid = '"+pid+"');";
			String check = commandLine.executeSession(sql, 1).get(0);
			
			// Get the price of this topping
			sql = "select price from product where pid = '"+pid+"';";
			String price = commandLine.executeSession(sql, 1).get(0);
			total = Integer.parseInt(price);
			
			if (check.equals("1")){
				// The topping has been used before in this transaction
				
				// Check to see if this topping has already been used on the same pizza
				sql = "select exists(select * from `toppingOrder` where tid = '"+tid+"' and pizzaOID = '"+pizzaOID+"' and toppingoID='"+pid+"');";
				String check2 = commandLine.executeSession(sql, 1).get(0);
				
				if(check2.equals("1")){
					//topping has been used on the same pizza before in this transaction
					
					// Increment the quantities and update the price in order and toppingOrder table
					sql = "update `order` set quantity = quantity +1 where tid = '"+tid+"' and pid = '"+pid+"';";
					commandLine.executeSession(sql, 2);

				}
				else{
					// topping has been used on another pizza previously in this transaction
					
					// Insert another order in `order` table with this topping and pizza values
					sql = "insert into `order` (tid,pid,quantity,price) VALUES ('"+tid+"','"+pid+"',1,'"+price+"');";
					commandLine.executeSession(sql, 2);
					
					// Get the order id with this topping 
					sql = "select oid from `order` where tid='"+tid+"' and pid='"+pid+"';";
					ArrayList<String> userOids = commandLine.executeSession(sql, 1);
					String oid = userOids.get(userOids.size()-1);
					
					//Insert this order details in toppingOrder along with oid
					sql = "insert into `toppingOrder` (oid,tid,pizzaOID,pID) VALUES ('"+oid+"','"+tid+"','"+pizzaOID+"','"+pid+"');";
				}
				commandLine.executeSession(sql, 2);
			}
			else{
				// topping has not been previously used for any orders in this transaction 
				
				// Insert into order the details about this transaction
				sql = "insert into `order` (tid,pid,quantity,price) VALUES ('"+tid+"','"+pid+"',1,'"+price+"');";
				commandLine.executeSession(sql, 2);
				
				// Get the oid of this order from orders table
				sql = "select oid from `order` where tid='"+tid+"' and pid='"+pid+"';";
				ArrayList<String> userOids = commandLine.executeSession(sql, 1);
				String oid = userOids.get(userOids.size()-1);
				
				// Insert info about this order in toppingOrder table, including oid
				sql = "insert into `toppingOrder` (oid,tid,pizzaOID,pid) VALUES ('"+oid+"','"+tid+"','"+pizzaOID+"','"+pid+"');";
				commandLine.executeSession(sql, 2);
			}
			//Execute query
			commandLine.endSession();
		}
		//Take user back to choosing another pizza
	}
}
