import java.util.Scanner;


public class product {
	public product(){
		Scanner input = new Scanner(System.in);
		String choose = "";
		while (! choose.equals("4")){
			System.out.println("1, Get info about a product");
			System.out.println("2, On sale items");
			System.out.println("3, View Cart");
			System.out.println("4, Back");
			choose = input.nextLine();
		}
		
	}
}
