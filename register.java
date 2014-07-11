import java.util.*;

public class register {

	private String username;
	private String password;
	private String name;
	private String sex; 
	
	public register(){
		// Setup scanner for inputs
		Scanner input = new Scanner(System.in);
		String fillers = "================================";
		
		System.out.println("Please enter a username: ");
		this.username = input.nextLine();
		this.username = isEmpty(this.username, input);

		System.out.println("Please enter a password: ");
		this.password = input.nextLine();
		this.password = isEmpty(this.password, input);
		
		System.out.println("Please enter you name");
		this.name = input.nextLine();
		this.name = isEmpty(this.password, input);
		
		System.out.println("Enter your sex (M/F)");
		this.sex = input.nextLine().toUpperCase();
		this.sex = isEmpty(this.sex, input);
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
		main.main(null);
	}
	
	/*
	 * Keeps prompting user for re-entry to get a value 
	 * of user's sex. Returns the correct input. 
	 */
	public String isEmpty(String userInput, Scanner input){
		while(userInput.isEmpty()){
			System.out.println("Try again.");
			userInput = input.nextLine();
		}
		return userInput;
	}

}
