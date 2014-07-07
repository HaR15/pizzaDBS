import java.util.*;

public class login {

	private String username;
	private String password;
	
	public login(){
		Scanner input = new Scanner(System.in);
		System.out.println("Input your username: ");
		this.username = input.nextLine();
		System.out.println("Input your password: ");
		this.password = input.nextLine();
		//Check if username and password exists in db

	}

}
