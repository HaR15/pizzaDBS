import java.util.ArrayList;
import java.util.Scanner;


public class sideOrder {
	public static String sideOrderr(String username){
		Scanner input = new Scanner(System.in);
		String sql = "select name from product where category = 'side' or category ='drink'";
		commandLine.startSession();
		ArrayList<String> data = commandLine.executeSession(sql, 1);
		commandLine.endSession();
		String choose = "";
		String tid = null;
		int total = 0;
		while (!choose.equals(Integer.toString(data.size()))){
			for (int a= 0;a< data.size();a++){
				System.out.println(a +")"+data.get(a));
			}
			System.out.println("type the number of the product you want or press '"+data.size()+"' to quit");
			choose = input.nextLine();
			if (choose.equals(Integer.toString(data.size()))){
				break;
			}
			sql = "select pid from product where name = '"+data.get(Integer.parseInt(choose))+"';";
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
			}
			else{
				sql = "insert into `order` (tid,pid,quantity,price) VALUES ('"+tid+"','"+pid+"',1,'"+price+"');";
				commandLine.executeSession(sql, 2);
			}
			commandLine.endSession();
		}
		
		return tid;
	}
}
