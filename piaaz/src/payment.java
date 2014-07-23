import java.util.Scanner;


public class payment {
	public payment(){
		Scanner input = new Scanner(System.in);
		System.out.println("Choose your method of payment");
		System.out.println("1, Balance \n 2,Credit \n 3,Cash");
		System.out.println("Your total is $$");
		String choose = input.nextLine();
	}
}
