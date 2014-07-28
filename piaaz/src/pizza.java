import java.util.ArrayList;
import java.util.Scanner;


public class pizza {
	public static String pizzaa(String username){
		String sql = "select name from product where category = 'pizza'";
		commandLine.startSession();
		ArrayList<String> pizza = commandLine.executeSession(sql, 1);
		commandLine.endSession();
		Scanner input = new Scanner(System.in);
		String choose = "";
		int total =0 ;
		String tid = null;
		while (! choose.equals(Integer.toString((pizza.size())))){
			for (int i=0; i < pizza.size();i++){
				System.out.println(i + ")"+pizza.get(i)) ;
			}
			System.out.println("type the number of the pizza you want or type '"+(pizza.size())+"' to quit");
			choose = input.nextLine();
			if (choose.equals(Integer.toString(pizza.size()))){
				break;
			}
			sql = "select pid from product where name = '"+pizza.get(Integer.parseInt(choose))+"';";
			commandLine.startSession();
			String pid = commandLine.executeSession(sql, 1).get(0);
			sql = "select tid from transaction where username = '"+username+"';";
			ArrayList<String> gtid = commandLine.executeSession(sql, 1);
			tid = gtid.get(gtid.size()-1);
			sql = "select exists(select * from `order` where tid = '"+tid+"' and pid = '"+pid+"');";
			String check = commandLine.executeSession(sql, 1).get(0);
			sql = "select price from product where pid = '"+pid+"';";
			String price = commandLine.executeSession(sql, 1).get(0);
			total = Integer.parseInt(price);
			if (check.equals("1")){
				sql = "update `order` set quantity = quantity +1, price = quantity*price where tid = '"+tid+"' and pid = '"+pid+"';";
				commandLine.executeSession(sql, 2);
				sql = "select oid from `order` where tid='"+tid+"' and pid='"+pid+"';";
				ArrayList<String> userOids = commandLine.executeSession(sql, 1);
				String oid = userOids.get(userOids.size()-1);
				sql = "insert into `pizzaorder` (tid,pid,oid) VALUES ('"+tid+"','"+pid+"','"+oid+"');";
				commandLine.executeSession(sql, 2);
			}
			else{
				sql = "insert into `order` (tid,pid,quantity,price) VALUES ('"+tid+"','"+pid+"',1,'"+price+"');";
				commandLine.executeSession(sql, 2);
				sql = "select oid from `order` where tid='"+tid+"' and pid='"+pid+"';";
				ArrayList<String> userOids = commandLine.executeSession(sql, 1);
				String oid = userOids.get(userOids.size()-1);
				sql = "insert into `pizzaorder` (tid,pid,oid) VALUES ('"+tid+"','"+pid+"','"+oid+"');";
				commandLine.executeSession(sql, 2);
			}
			topping(total, username, pizza.get(Integer.parseInt(choose)));
			commandLine.endSession();
		}

		return tid;
	}
	public static void topping(int total, String username, String pizza){
		String sql = "select name from product where category = 'topping'";
		commandLine.startSession();
		ArrayList<String> data = commandLine.executeSession(sql, 1);
		commandLine.endSession();

		Scanner input = new Scanner(System.in);
		String choose = "";
		while (!choose.equals(Integer.toString(data.size()))){
			for (int a= 0;a< data.size();a++){
				System.out.println(a +")"+data.get(a));
			}
			System.out.println("type the number of the topping you want on your '"+pizza+"' or press '"+data.size()+"' to quit");
			choose = input.nextLine();
			if (choose.equals(Integer.toString(data.size()))){
				break;
			}
			sql = "select pid from product where name = '"+data.get(Integer.parseInt(choose))+"';";
			commandLine.startSession();
			String pid = commandLine.executeSession(sql, 1).get(0);
			sql = "select tid from transaction where username = '"+username+"';";
			ArrayList<String> gtid = commandLine.executeSession(sql, 1);
			String tid = gtid.get(gtid.size()-1);
			sql = "select exists(select * from `order` where tid = '"+tid+"' and pid = '"+pid+"');";
			String check = commandLine.executeSession(sql, 1).get(0);
			sql = "select price from product where pid = '"+pid+"';";
			String price = commandLine.executeSession(sql, 1).get(0);
			total = Integer.parseInt(price);
			if (check.equals("1")){
				sql = "update `order` set quantity = quantity +1, price = quantity*price where tid = '"+tid+"' and pid = '"+pid+"';";
				commandLine.executeSession(sql, 2);
				sql = "select oid from `order` where tid='"+tid+"' and pid='"+pid+"';";
				ArrayList<String> userOids = commandLine.executeSession(sql, 1);
				String oid = userOids.get(userOids.size()-1);
				sql = "select pizzaoid from `pizzaorder` order by pizzaoid desc limit 1;";
				String pizzaoid = commandLine.executeSession(sql, 1).get(0);
				sql = "insert into `toppingorder` (tid,pid,oid,pizzaoid) VALUES ('"+tid+"','"+pid+"','"+oid+"','"+pizzaoid+"');";
				commandLine.executeSession(sql, 2);
			}
			else{
				sql = "insert into `order` (tid,pid,quantity,price) VALUES ('"+tid+"','"+pid+"',1,'"+price+"');";
				commandLine.executeSession(sql, 2);
				sql = "select oid from `order` where tid='"+tid+"' and pid='"+pid+"';";
				ArrayList<String> userOids = commandLine.executeSession(sql, 1);
				String oid = userOids.get(userOids.size()-1);
				sql = "select pizzaoid from `pizzaorder` order by pizzaoid desc limit 1;";
				String pizzaoid = commandLine.executeSession(sql, 1).get(0);
				sql = "insert into `toppingorder` (tid,pid,oid,pizzaoid) VALUES ('"+tid+"','"+pid+"','"+oid+"','"+pizzaoid+"');";
				commandLine.executeSession(sql, 2);
			}
			commandLine.endSession();
		}
	}
}