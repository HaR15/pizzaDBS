import java.util.Scanner;


public class setting {
	public setting(){
		Scanner input = new Scanner(System.in);
		String choose = "";
		while (! choose.equals("6")){
			System.out.println("1, Update");
			System.out.println("2, Account Balance");
			System.out.println("3, Orders");
			System.out.println("4, Tranaction History");
			System.out.println("5, Deactivate");
			System.out.println("6,Back");
			choose = input.nextLine();
		}
	}
}
