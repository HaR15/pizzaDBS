import java.util.Scanner;


public class customer {

	private String username;
	private String password; 
	private String name;
	
	public customer(String username, String password){
		this.username = username;
		this.password = password;
		Scanner input = new Scanner(System.in);
		String choose = "0";
		while (! choose.equals("4")){
			System.out.println("Welcome username: " + username);
			System.out.println("1, Place Order");
			System.out.println("2, Products");
			System.out.println("3, Option");
			System.out.println("4, Log Out");
			choose = input.nextLine();
	        switch (choose) {
	            case "1":  new placeOrder(this.username,this.password);
	                     break;
	            case "2":  new product();
	                     break;
	            case "3":  new setting();
	                     break;
	            case "4":  choose = "4";
	                     break;
	        }
		}
		System.out.println("Have a nice day");
		main.main(null);
	}

}
