import java.util.ArrayList;
import java.util.Scanner;


public class pizza {
	public pizza(){
		String sql = "select name from product where category = 'pizza'";
		commandLine.startSession();
		ArrayList<String> pizza = commandLine.executeSession(sql, 1);
		commandLine.endSession();
		int order[] = {0,0,0};
		Scanner input = new Scanner(System.in);
		String choose = "";
		while (! choose.equals("7")){
			for (int i=0; i < pizza.size();i++){
				System.out.println(i+1 + ")"+pizza.get(i) + "  Amount = " +order[i]);
			}
			choose = input.nextLine();
			if (Integer.parseInt(choose)-1 < pizza.size()){
				order[Integer.parseInt(choose)-1] = order[Integer.parseInt(choose)-1] +1;
			}
			else{
				System.out.println("invalid");
			}
			System.out.println("");
			}
		}
}
