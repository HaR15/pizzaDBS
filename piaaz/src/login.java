import java.util.*;
import java.sql.*;
public class login {

	private String username;
	private String password;
	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost:3306/Customers";
	public login() throws ClassNotFoundException{
		// Setup scanner
		Scanner input = new Scanner(System.in);
		String fillers = "================================";
		
		promptUsernamePassword(input);
		//Check if username and password exists in db
		while(!userExists()){
			System.out.println("Incorrect username or password, try again.");
			promptUsernamePassword(input);
		}
		// User exists
		System.out.println(fillers);
		userActive();
		new customer(this.username, this.password);
	}
	
	/* Prompts user for username, password and sets the
	 * inputed Strings to this.username and this.password
	 */
	public void promptUsernamePassword(Scanner input){
		System.out.println("Input your username: ");
		this.username = input.nextLine(); 
		this.username = isEmpty(this.username, input);
		System.out.println("Input your password: ");
		this.password = input.nextLine();
		this.password = isEmpty(this.password, input);
	}
	
	/* Checks if input fields are empty, and requests
	 * re-input if they are. Returns input when given.  
	 */ 
	public String isEmpty(String userInput, Scanner input){
		while(userInput.isEmpty()){
			System.out.println("Try again.");
			userInput = input.nextLine();
		}
		return userInput;
	}
	
	/*
	 * Returns true if the user with the inputed username 
	 * and password matches any tuple in the customers table.
	 */
	public Boolean userExists() throws ClassNotFoundException{
		/* * GOTTA DO THIS * */
		// query database to see if username = this.username and password = this.password 
		Class.forName(dbClassName);
		//Database credentials
		final String USER = "root";
		final String PASS = "123";
		try {
			//Establish connection
			Connection conn = DriverManager.getConnection(CONNECTION,USER,PASS);			
			//Execute a query
			Statement stmt = conn.createStatement();
			String sql = "SELECT EXISTS(SELECT * FROM customers WHERE Username = '"+this.username+"' and Password = '"+this.password+"' ) ";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			if (! rs.getString(1).equals("1")){
				return false;
			}
			
			//STEP 5: Extract data from result set
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Connection error occured!" + e);
		}
		return true;
	}
		public void userActive() throws ClassNotFoundException{
			/* * GOTTA DO THIS * */
			// query database to see if username = this.username and password = this.password 
			Class.forName(dbClassName);
			//Database credentials
			final String USER = "root";
			final String PASS = "123";
			try {
				//Establish connection
				Connection conn = DriverManager.getConnection(CONNECTION,USER,PASS);			
				//Execute a query
				Statement stmt = conn.createStatement();
				String sql = "update customers set Active = 1 where Username = '"+this.username+"'";
				stmt.executeUpdate(sql);

				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Connection error occured!" + e);
			}
	
		}
}
