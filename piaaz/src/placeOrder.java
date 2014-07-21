import java.util.Scanner;


public class placeOrder {
	public placeOrder(String username, String password) throws ClassNotFoundException{
		// Setup scanner for inputs
		Scanner input = new Scanner(System.in);
		String choose = "";
		while (! choose.equals("6")){
			String fillers = "================================";
			System.out.println("1, Add Pizza");
			System.out.println("2, Add Side dishes");
			System.out.println("3, Add drinks");
			System.out.println("4, View Cart");
			System.out.println("5, Proceed to checkout");
			System.out.println("6, Cancel");
			choose = input.nextLine();
	        switch (choose) {
	            case "1":  new pizza();
	                     break;
	            case "2":  new product();
	                     break;
	            case "3":  choose = "March";
	                     break;
	            case "4":  choose = "4";
	                     break;
	            case "5":  choose = "March";
	                     break;
	            case "6":  new customer(username, password);
	                     break;
	        }
		}
		
		
	}
}
