import java.util.ArrayList;
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
			if (choose.equals("1")){
				info();
			}
			if (choose.equals("2")){
				onSale();
			}
		}
		
	}
	public void info(){
		Scanner input = new Scanner(System.in);
		System.out.println("Type the name of your product:");
		String choose = input.nextLine();
		String sql = "Select description from product where name = '"+choose+"';";
		commandLine.startSession();
		ArrayList<String> data = commandLine.executeSession(sql, 1);
		commandLine.endSession();
		if (data.size() == 0){
			System.out.println("The product does not exist");
		}
		else{
			System.out.println(data.get(0));
		}
	}
	public void onSale(){
		String sql = "Select name from product where special = 1";
		String sql1 = "Select description from product where special = 1";
		commandLine.startSession();
		ArrayList<String> data = commandLine.executeSession(sql, 1);
		ArrayList<String> data2 = commandLine.executeSession(sql1, 1);
		System.out.println("Special item:");
		for (int a = 0;a< data.size();a++){
			System.out.println(data.get(a) +" :" + data2.get(a));
		}
	}
}
