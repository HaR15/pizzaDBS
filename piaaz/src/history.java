import java.util.*;

public class history {
	
	private String username;
	
	public history(String username){
		
		Scanner input = new Scanner(System.in);
		String fillers1 = "----------------------------------------------------------------------";
		String fillers2 = "======================================================================";
		String sql = "";
		this.username = username;

		sql = "SELECT tid FROM transaction WHERE username = '" + this.username + "';";
		commandLine.startSession();
		ArrayList<String> userTids = commandLine.executeSession(sql, 1);
		
		System.out.println(fillers2);
		
		for(int i = 0; i < userTids.size(); i++){
			
			sql = "SELECT date FROM transaction WHERE tid = '" + userTids.get(i) +"';";
			String date = commandLine.executeSession(sql, 1).get(0);
			
			sql = "SELECT totalPrice FROM transaction WHERE tid = '" + userTids.get(i) +"';";
			String totalPrice = commandLine.executeSession(sql, 1).get(0);
			
			sql = "SELECT methodOfPayment FROM transaction WHERE tid = '" + userTids.get(i) +"';";
			String methodOfPayment = commandLine.executeSession(sql, 1).get(0);
			
			sql = "SELECT did FROM transaction WHERE tid = '" + userTids.get(i) +"';";
			String did = commandLine.executeSession(sql, 1).get(0);


			sql = "SELECT oid FROM pizzaOrder WHERE tid = '" + userTids.get(i) +"';";
			ArrayList<String> pizzaOIDs = commandLine.executeSession(sql, 1);
			
			System.out.println("Username: " + this.username);
			System.out.println("Transaction ID: " + userTids.get(i));
			System.out.println("Date: " + date.substring(0,10));
			System.out.println("Time: "+ date.substring(11,19));
			System.out.println(fillers1);
			
			System.out.printf("%2s%20s%10s%10s%15s\n", "Pizza#", "Product", "Quantity", "Price", "Subtotal");
			System.out.println("");
			
			float orderTotals = 0;
			for(int j = 0; j < pizzaOIDs.size(); j++){
				
				sql = "SELECT pizzaPID FROM pizzaOrder WHERE oid = '" + pizzaOIDs.get(j) +"';";
				String pizzaPID = commandLine.executeSession(sql, 1).get(0);
				
				sql = "SELECT name FROM product WHERE pid = '" + pizzaPID +"';";
				String pizzaName = commandLine.executeSession(sql, 1).get(0);
				
				sql = "SELECT quantity FROM pizzaOrder WHERE oid = '" + pizzaOIDs.get(j) + "';";
				String pizzaQuantity = commandLine.executeSession(sql, 1).get(0);
				
				sql = "SELECT price FROM product WHERE pid = '" + pizzaPID + "';";
				String pizzaPrice = commandLine.executeSession(sql, 1).get(0);
				
				float pizzaTotal = Integer.parseInt(pizzaQuantity)*Integer.parseInt(pizzaPrice);
				System.out.printf("%2d%20s%10s%10s%15.2f\n", j, pizzaName.toUpperCase(), pizzaQuantity, pizzaPrice, pizzaTotal);
				float totals = 0;
				totals += pizzaTotal;
				
				sql = "SELECT toppingID FROM toppingOrder WHERE pizzaOID = '" + pizzaOIDs.get(j) + "';";
				ArrayList<String> thisPizzaToppings = commandLine.executeSession(sql, 1);
				
				for(int k = 0; k < thisPizzaToppings.size(); k++){
					
					sql = "SELECT name FROM product WHERE pid = '"+ thisPizzaToppings.get(k) +"';";
					String toppingName = commandLine.executeSession(sql, 1).get(0);
					
					sql = "SELECT quantity FROM toppingOrder WHERE toppingID = '" + thisPizzaToppings.get(k) + "';";
					String toppingQuantity = commandLine.executeSession(sql, 1).get(0);
					
					sql = "SELECT price FROM product WHERE pid = '" + thisPizzaToppings.get(k) + "';";
					String toppingPrice = commandLine.executeSession(sql, 1).get(0);
					
					float toppingTotal = Integer.parseInt(toppingQuantity)*Integer.parseInt(toppingPrice);
					System.out.printf("%2s%20s%10s%10s%15.2f\n", "", toppingName, toppingQuantity, toppingPrice, toppingTotal);
					totals += toppingTotal;					
				}
				orderTotals += totals;
				System.out.printf("%2s%20s%10s%10s%15.2f\n", "", "", "", "", totals);
				System.out.println(fillers1);
			}
			System.out.printf("%2s%20s%10s%10s%15.2f\n", "", "", "", "TOTAL:", orderTotals);
			
			System.out.printf("%2s%20s%10s%10s%15s\n", "", "", "", "Method of Payment: ", methodOfPayment);
			System.out.printf("%2s%20s%10s%10s%15d\n", "", "", "", "Delivered by: ", did);
			System.out.println(fillers2);
		}
		commandLine.endSession();
		
		
					
	}
}
