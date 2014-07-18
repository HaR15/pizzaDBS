import java.util.ArrayList;
import java.util.Scanner;


public class pizza {
	public pizza(){
		ArrayList<String> stringList = new ArrayList<String>();
		String pizzas[] ={"a","b","c"};
		int order[] = {0,0,0};
		Scanner input = new Scanner(System.in);
		String choose = "";
		while (! choose.equals("7")){
			for (int i=0; i < pizzas.length;i++){
				System.out.println(i+1 + ")"+pizzas[i] + "  Amount = " +order[i]);
			}
			choose = input.nextLine();
			if (Integer.parseInt(choose)-1 < pizzas.length){
				order[Integer.parseInt(choose)-1] = order[Integer.parseInt(choose)-1] +1;
			}
			else{
				System.out.println("invalid");
			}
			System.out.println("");
			}
		}
}
