import java.util.*;
import java.sql.*;

import mariadb.sqlcc;
public class register {
	private String username;
	private String password;
	private String name;
	private String sex; 
	private String addr;
	private int age;
	private int balance;
	
	public register() throws ClassNotFoundException, SQLException{
		// Setup scanner for inputs
		Scanner input = new Scanner(System.in);
		String fillers = "================================";
		
		System.out.println("Please enter a username: ");
		this.username = input.nextLine();
		while (!userNameCheck()){
			System.out.println("Username already taken, choose another one:");
			this.username = input.nextLine();
			this.username = isEmpty(this.username, input);
		}
		this.username = isEmpty(this.username, input);

		System.out.println("Please enter a password: ");
		this.password = input.nextLine();
		this.password = isEmpty(this.password, input);
		
		System.out.println("Please enter you name");
		this.name = input.nextLine();
		this.name = isEmpty(this.name, input);
		
		System.out.println("Enter your sex (M/F)");
		this.sex = input.nextLine().toUpperCase();
		while (!this.sex.equals("M") && !this.sex.equals("F")){
			System.out.println("Please enter either m(male) or f(female):");
			this.sex = input.nextLine().toUpperCase();
			this.sex = isEmpty(this.sex, input);
		}
		this.sex = isEmpty(this.sex, input);
		
		String age = "";
		System.out.println("Please enter you age");
		age = input.nextLine();
		age = isEmpty(age, input);
		while (true){
			try{
				this.age = Integer.parseInt(age);
				break;
			}
			catch (Exception e){
				System.out.println("Please enter a correct age");
				age = input.nextLine();
				age = isEmpty(age, input);
			}
			
		}
				
		System.out.println("Enter your address");
		this.addr = input.nextLine().toUpperCase();
		this.addr = isEmpty(this.addr, input);
		
		String balance = "";
		System.out.println("Enter your balance");
		balance = input.nextLine().toUpperCase();
		balance = isEmpty(balance, input);
		while (true){
			try{
				this.balance = Integer.parseInt(balance);
				break;
			}
			catch (Exception e){
				System.out.println("Please enter a correct balance");
				balance = input.nextLine();
				balance= isEmpty(balance, input);
			}
			
		}
		
		// Keeps prompting user for re-entry to get correct value 
		// of their sex
		while(!(this.sex.equals("M")) && !(this.sex.equals("F"))){
			System.out.println("Incorrect input. Try again!");
			this.sex = input.nextLine().toUpperCase();
		}	
		System.out.println("\nThank you for Registering");
		System.out.println("Taking you back now to the home menu");
		System.out.println(fillers);
		//Takes back to Home menu (in main() of main.java)
			commandLine.startSession();
			String sql = "INSERT INTO customers (Active, name, addr, sex, age, balance, Username, Password) " +
					"VALUES (1 , '"+this.name+"', '"+this.addr+"', '"+this.sex+"','"+this.age+"', '"+this.balance+"','"+this.username+"','"+this.password+"' );";
			commandLine.executeSession(sql,2);
			commandLine.endSession();
		main.main(null);
	}
	
	private boolean userNameCheck() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
			commandLine.startSession();
			String sql = "SELECT EXISTS(SELECT * FROM customers WHERE Username = '"+this.username+"' ) ";
			ArrayList<String> rs = commandLine.executeSession(sql,1);
			commandLine.endSession();
			if (rs.get(0).equals("1")){
				return false;
			}
			//STEP 5: Extract data from result set

		return true;
	}

	/*
	 * Keeps prompting user for re-entry to get a value 
	 * of user's sex. Returns the correct input. 
	 */
	public String isEmpty(String userInput, Scanner input){
		while(userInput.isEmpty()){
			System.out.println("Can not be empty, please try again:");
			userInput = input.nextLine();
		}
		return userInput;
	}

}
