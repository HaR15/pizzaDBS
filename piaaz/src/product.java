import java.util.ArrayList;
import java.util.Scanner;


public class product {
	public product(){
		Scanner input = new Scanner(System.in);
		String choose = "";
		while (! choose.equals("4")){
			System.out.println("1, Get info about a product");
			System.out.println("2, On sale items");
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
			String choose = "from product where ";
			String[] data = {"name","category","price","supplier","manufacturer","description"};
			for (int a=0;a< data.length;a++){
				System.out.println("Enter the "+data[a]+" of the product");
				String in = input.nextLine();
				if (!in.isEmpty()){
						choose = choose + data[a]+" = '"+ in + "'";
					}
				}	
			if (choose.endsWith(",")){
				choose = choose.substring(0,choose.length()-1) + ";";
			}
			else{
				choose = choose + ";";
			}
			String sql = "select name "+choose;
			String sql1 = "select price "+choose;
			String sql2 = "select description "+choose;
			commandLine.startSession();
			ArrayList<String> info = commandLine.executeSession(sql, 1);
			ArrayList<String> info1 = commandLine.executeSession(sql1, 1);
			ArrayList<String> info2 = commandLine.executeSession(sql2, 1);
			for (int a= 0;a< info.size();a++){
				System.out.println();
			}
			commandLine.endSession();
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
