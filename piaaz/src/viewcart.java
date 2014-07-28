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

		sql = "SELECT pizzaoid FROM `pizzaOrder` WHERE tid = '"+tid+"';";
		commandLine.startSession();
		// All pizza of this transaction
		ArrayList<String> transactionPizzas = commandLine.executeSession(sql, 1);

		sql = "SELECT oid FROM `pizzaOrder` WHERE tid = '"+tid+"';";
		commandLine.startSession();
		// All pizza of this transaction
		ArrayList<String> transactionOid = commandLine.executeSession(sql, 1);
		
		sql = "SELECT oid FROM `Order` WHERE tid = '"+tid+"';";
		commandLine.startSession();
		// All pizza of this transaction
		ArrayList<String> total = commandLine.executeSession(sql, 1);
		System.out.println(transactionOid);
		String product = "Product";
		String quantity = "Quantity";
		String price = "Price";
		String totalPrice = "Subtotal";
		int count = 0;
		System.out.printf("%2s%20s%10s%10s%15s\n", "#", product, quantity, price, totalPrice);
		float totals = 0;
		float orderTotals = 0;
		System.out.println(fillers);
		if(total.size() != 0){
			
			for(int i = 0; i < transactionPizzas.size(); i++){				

				sql = "SELECT pid FROM pizzaOrder WHERE pizzaoid = '" + transactionPizzas.get(i) + "';";
				String pizzaPID = commandLine.executeSession(sql, 1).get(0);

				sql = "SELECT name FROM product WHERE pid = '"+ pizzaPID+"';";
				String pizzaName = commandLine.executeSession(sql, 1).get(0);

				sql = "SELECT quantity FROM `Order` WHERE oid = '" + transactionOid.get(i) + "';";
				String pizzaQuantity = commandLine.executeSession(sql, 1).get(0);

				sql = "SELECT price FROM product WHERE pid = '" + pizzaPID + "';";
				String pizzaPrice = commandLine.executeSession(sql, 1).get(0);

				sql = "SELECT pid FROM toppingOrder WHERE pizzaOID = '" + transactionPizzas.get(i) + "';";
				ArrayList<String> thisPizzaToppings = commandLine.executeSession(sql, 1);

				float pizzaTotal = Integer.parseInt(pizzaQuantity)*Integer.parseInt(pizzaPrice);
				System.out.printf("%2d%20s%10s%10s%15.2f\n", i, pizzaName.toUpperCase(), 1, Integer.parseInt(pizzaPrice)/Integer.parseInt(pizzaQuantity), pizzaTotal);
				totals += pizzaTotal;

				ArrayList<String> toppingNames = new ArrayList<String>();
				ArrayList<String> toppingQuantities = new ArrayList<String>();
				ArrayList<String> toppingPrices = new ArrayList<String>();
				for(int j = 0; j < thisPizzaToppings.size(); j++){

					sql = "SELECT name FROM product WHERE pid = '"+ thisPizzaToppings.get(j) +"';";
					String toppingName = commandLine.executeSession(sql, 1).get(0);
					toppingNames.add(toppingName);

					sql = "SELECT quantity FROM `Order` WHERE pID = '" + thisPizzaToppings.get(j) + "';";
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
			sql = "select product.name from `order` inner join product where `order`.tid = '"+tid+"' and `order`.pid = product.pid and product.category = 'side';";
			String sql1 = "select product.price from `order` inner join product where `order`.tid = '"+tid+"' and `order`.pid = product.pid and product.category = 'side';";
			String sql2 = "select `order`.quantity from `order` inner join product where `order`.tid = '"+tid+"' and `order`.pid = product.pid and product.category = 'side';";
			commandLine.startSession();
			ArrayList<String> dataa = commandLine.executeSession(sql1, 1);
			ArrayList<String> datab = commandLine.executeSession(sql, 1);
			ArrayList<String> datac = commandLine.executeSession(sql2, 1);
			System.out.println("Asdsad"+total);
			for (int a =0;a< dataa.size();a++){
				orderTotals += Integer.parseInt(dataa.get(a));
				System.out.printf("%2s%20s%10s%10s%15.2f\n", transactionPizzas.size()+a, datab.get(a), datac.get(a), dataa.get(a), orderTotals);
				System.out.printf("%2s%20s%10s%10s%15.2f\n", "", "", "", "", totals);
				System.out.println(fillers);
			}
			count = transactionPizzas.size()+dataa.size();
			sql = "select product.name from `order` inner join product where `order`.tid = '"+tid+"' and `order`.pid = product.pid and product.category = 'drink';";
			sql1 = "select product.price from `order` inner join product where `order`.tid = '"+tid+"' and `order`.pid = product.pid and product.category = 'drink';";
			sql2 = "select `order`.quantity from `order` inner join product where `order`.tid = '"+tid+"' and `order`.pid = product.pid and product.category = 'drink';";
			commandLine.startSession();
			dataa = commandLine.executeSession(sql1, 1);
			datab = commandLine.executeSession(sql, 1);
			datac = commandLine.executeSession(sql2, 1);
			for (int a =0;a< dataa.size();a++){
				orderTotals += Integer.parseInt(dataa.get(a));
				System.out.printf("%2s%20s%10s%10s%15.2f\n", count +a, datab.get(a), datac.get(a), dataa.get(a), orderTotals);
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