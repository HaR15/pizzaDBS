import java.util.*;

public class viewCart {
	
	private String sql = null;
	
	public viewCart(String tid){
		
		Scanner input = new Scanner(System.in);
		if(tid == null){
			System.out.println("Error");
			return;
		}
		String fillers = "======================================================================";
		
		sql = "SELECT oid FROM pizzaOrder WHERE tid = '"+tid+"';";
		commandLine.startSession();
		// All pizza of this transaction
		ArrayList<String> transactionPizzas = commandLine.executeSession(sql, 1);
		
		String product = "Product";
		String quantity = "Quantity";
		String price = "Price";
		String totalPrice = "Subtotal";
		
		System.out.printf("%2s%20s%10s%10s%15s\n", "#", product, quantity, price, totalPrice);
	
		System.out.println(fillers);
		if(transactionPizzas.size() != 0){
			float orderTotals = 0;
			for(int i = 0; i < transactionPizzas.size(); i++){				
				
				sql = "SELECT pizzaPID FROM pizzaOrder WHERE oid = '" + transactionPizzas.get(i) + "';";
				String pizzaPID = commandLine.executeSession(sql, 1).get(0);
				
				sql = "SELECT name FROM product WHERE pid = '"+ pizzaPID+"';";
				String pizzaName = commandLine.executeSession(sql, 1).get(0);
				
				sql = "SELECT quantity FROM pizzaOrder WHERE oid = '" + transactionPizzas.get(i) + "';";
				String pizzaQuantity = commandLine.executeSession(sql, 1).get(0);
				
				sql = "SELECT price FROM product WHERE pid = '" + pizzaPID + "';";
				String pizzaPrice = commandLine.executeSession(sql, 1).get(0);
				
				sql = "SELECT toppingID FROM toppingOrder WHERE pizzaOID = '" + transactionPizzas.get(i) + "';";
				ArrayList<String> thisPizzaToppings = commandLine.executeSession(sql, 1);
				
				float pizzaTotal = Integer.parseInt(pizzaQuantity)*Integer.parseInt(pizzaPrice);
				System.out.printf("%2d%20s%10s%10s%15.2f\n", i, pizzaName.toUpperCase(), pizzaQuantity, pizzaPrice, pizzaTotal);
				float totals = 0;
				totals += pizzaTotal;
				
				ArrayList<String> toppingNames = new ArrayList<String>();
				ArrayList<String> toppingQuantities = new ArrayList<String>();
				ArrayList<String> toppingPrices = new ArrayList<String>();
				for(int j = 0; j < thisPizzaToppings.size(); j++){
					
					sql = "SELECT name FROM product WHERE pid = '"+ thisPizzaToppings.get(j) +"';";
					String toppingName = commandLine.executeSession(sql, 1).get(0);
					toppingNames.add(toppingName);
					
					sql = "SELECT quantity FROM toppingOrder WHERE toppingID = '" + thisPizzaToppings.get(j) + "';";
					String toppingQuantity = commandLine.executeSession(sql, 1).get(0);
					toppingQuantities.add(toppingQuantity);
					
					sql = "SELECT price FROM product WHERE pid = '" + thisPizzaToppings.get(j) + "';";
					String toppingPrice = commandLine.executeSession(sql, 1).get(0);
					toppingPrices.add(toppingPrice);
					
					float toppingTotal = Integer.parseInt(toppingQuantity)*Integer.parseInt(toppingPrice);
					System.out.printf("%2s%20s%10s%10s%15.2f\n", "", toppingName, toppingQuantity, toppingPrice, toppingTotal);
					totals += toppingTotal;
				}
				orderTotals += totals;
				System.out.printf("%2s%20s%10s%10s%15.2f\n", "", "", "", "", totals);
				System.out.println(fillers);

			}			
			System.out.printf("%2s%20s%10s%10s%15.2f\n", "", "", "", "TOTAL:", orderTotals);
		}
		else{
			System.out.println("View Cart is empty");
		}
		
		
		
		commandLine.endSession();
		
		
		}
}
