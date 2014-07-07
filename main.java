import java.util.*;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String fillers = "================================";
		System.out.println("Welcome to Rich and Ris' Pizzas!\n");
		System.out.println(fillers);
		System.out.println("Choose the menu (a number):");
		System.out.println("(1) Login\n(2) Register");
		System.out.println(fillers);
		String loginOrRegister = input.nextLine();
		while(!loginOrRegister.equals("1") && !loginOrRegister.equals("2")){
			System.out.println("Incorrect input. Try again!");
			loginOrRegister = input.nextLine();
		}
		if(loginOrRegister.equals("1")){
			new login();
		}
		if(loginOrRegister.equals("2")){
			new register();
		}
	}

}
