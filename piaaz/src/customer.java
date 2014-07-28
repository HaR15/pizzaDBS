import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class customer {

	private String username;
	private String password; 
	private Timestamp time;
	private Timestamp DeactiveTime;
	private int lock = 0;
	
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
			String sql = "select date from transaction inner join customers where customers.username = '"+this.username+"' order by date desc limit 1;";
			commandLine.startSession();
			ArrayList<String> check = commandLine.executeSession(sql, 1);
			if (check.size() != 0){
				CTC();
			}
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
		this.time = Timestamp.valueOf(time);
		System.out.println();
		Calendar calendar = Calendar.getInstance();
	    java.sql.Timestamp currentTime = new java.sql.Timestamp(calendar.getTime().getTime());
		long diff = currentTime.getTime() - this.time.getTime();
		long diffMinutes = diff / (60 * 1000) % 60;
		if (Integer.parseInt(balance) < 0){
			System.out.println("your account has an outstanding balance");
			System.out.println("Within 20 minutes your account will be deactivated and only an admin can reactivate your account");
			System.out.println("add balance in the setting menu right now");
		    if (lock == 0){
		    	this.DeactiveTime = new java.sql.Timestamp(calendar.getTime().getTime());
			    lock = 1;
		    }
			diff = currentTime.getTime() - this.DeactiveTime.getTime();
			diffMinutes = diff / (60 * 1000) % 60;
			if (diffMinutes > 20){
        		sql = "UPDATE customers set Active = 0 where Username = '"+this.username+"';";
        		commandLine.startSession();
        		commandLine.executeSession(sql, 2);
        		commandLine.endSession();
			}
		}

	}
}
