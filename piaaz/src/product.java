import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class product {
	public product(){
		Scanner input = new Scanner(System.in);
		String choose = "";
		while (! choose.equals("4")){
			System.out.println("1, Get info about a product");
			System.out.println("2, On sale items");
			System.out.println("3, best/worst sales");
			System.out.println("4, Back");
			choose = input.nextLine();
			if (choose.equals("1")){
				info();
			}
			if (choose.equals("2")){
				onSale();
			}
			if (choose.equals("3")){
				bestWorst();
			}

		}
		
	}
	private void bestWorst() {
		// TODO Auto-generated method stub
		String sql = "select pid from `order` order by pid desc;";
		String sql1 = "select quantity from `order` order by pid desc";
		commandLine.startSession();
		ArrayList<String> item = commandLine.executeSession(sql, 1);
		ArrayList<String> quantity = commandLine.executeSession(sql1, 1);
		Map<String, Integer> map = new HashMap<String, Integer>();
		ArrayList<String> dupItem = item;
		for (int a=0;a< item.size();a++){
			if (map.containsKey(item.get(a))){
				int temp = map.get(item.get(a));
				map.put(item.get(a), temp+Integer.parseInt(quantity.get(a)));
			}
			else{
				map.put(item.get(a), Integer.parseInt(quantity.get(a)));
			}
		}
		int largest = 0;
		String largestItem="";
		for (int a=0;a< item.size();a++){
			if (map.get(item.get(a)) > largest){
				largest = map.get(item.get(a));
				largestItem = item.get(a);
			}
		}
		String smallestItem="";
		int smallest = largest;
		for (int a=0;a<dupItem.size();a++){
			if (map.get(dupItem.get(a)) < smallest){
				smallest = map.get(dupItem.get(a));
				smallestItem = item.get(a);
			}
		}
		sql = "select name from product where pid = '"+largestItem+"';";
		commandLine.startSession();
		String name = commandLine.executeSession(sql, 1).get(0);
		sql = "select name from product where pid ='"+smallestItem+"';";
		String name1 = commandLine.executeSession(sql, 1).get(0);
		commandLine.endSession();
		System.out.println("One of the best selling  item is "+name);
		System.out.println("One of the least selling item is "+name1);
	}
	public void info(){
			System.out.println("Enter the search critia, you can leave it blank");
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
			String fillers = "======================================================================";
			String sql;
			String sql1;
			String sql2; 
			System.out.println(choose.length());
			if (choose.length() <= 20){
				sql = "select name from product";
				sql1 = "select price from product";
				sql2 = "select description from product";
			}
			else{
				sql = "select name "+choose;
				sql1 = "select price "+choose;
				sql2 = "select description "+choose;
	
			}
			commandLine.startSession();
			ArrayList<String> info = commandLine.executeSession(sql, 1);
			ArrayList<String> info1 = commandLine.executeSession(sql1, 1);
			ArrayList<String> info2 = commandLine.executeSession(sql2, 1);
			System.out.printf("%20s%10s%35s\n", "Name","Price","Desription");
			System.out.println(fillers);
			for (int a= 0;a< info.size();a++){
				
				System.out.printf("%20s%10s%35s\n", info.get(a),info1.get(a),info2.get(a));
			}
			System.out.println(fillers);
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
