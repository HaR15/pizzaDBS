import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;


public class customer {

	private String username;
	private String password; 
	private String name;
	
	public customer(String username, String password) throws ClassNotFoundException, SQLException{
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
			CTC();
			choose = input.nextLine();
			
	        switch (choose) {
	            case "1":  new placeOrder(this.username,this.password);
	                     break;
	            case "2":  new product();
	                     break;
	            case "3":  new setting(username);
	                     break;
	            case "4":  choose = "4";
	                     break;
	        }
		}
		System.out.println("Have a nice day");
		main.main(null);
	}
	public void CTC(){
		String sql = "select date from transaction inner join customers where customers.username = '"+this.username+"' order by date desc limit 1;";
		String sql1 = "select balance from transaction inner join customers where customers.username = '"+this.username+"' order by date desc limit 1;";
		commandLine.startSession();
		String time = commandLine.executeSession(sql, 1).get(0);
		String balance = commandLine.executeSession(sql1, 1).get(0);
		commandLine.endSession();
		System.out.println(time);
		System.out.println(balance);
		
	}
}
