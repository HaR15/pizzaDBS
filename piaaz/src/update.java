import java.util.Scanner;

public class update {
	public update(String username) throws ClassNotFoundException{
		Scanner input = new Scanner(System.in);
		String choose = "";
		String password = "",name = "",address = "",sex = "";
		int age = 0;
		while (! choose.equals("6")){
			System.out.println("1, Password");
			System.out.println("2, name");
			System.out.println("3, age");
			System.out.println("4, address");
			System.out.println("5, sex");
			System.out.println("6, done");
			System.out.println("7, back");
			choose = input.nextLine();
			switch (choose) {
            case "1":  {
            	System.out.println("Type the changes here:");
            	choose = input.nextLine();
            	password = choose;
            }
                     break;
            case "2": {
            	System.out.println("Type the changes here:");
            	choose = input.nextLine();
            	name = choose;
            }
                     break;
            case "3": {
            	System.out.println("Type the changes here:");
            	choose = input.nextLine();
            	age= Integer.parseInt(choose);
            }
                     break;
            case "4":  {
            	System.out.println("Type the changes here:");
            	choose = input.nextLine();
            	address = choose;
            }
                     break;
            case "5":  {
            	System.out.println("Type the changes here:");
            	choose = input.nextLine();
            	sex = choose;
            }
                     break;

            case "6":  {
            	commandLine.startSession();
            	if (!password.equals("")){
            		String sql = "UPDATE customers set password= '"+password+"' where Username = '"+username+"';";
            		commandLine.executeSession(sql, 2);
            	}
            	if (!name.equals("")){
            		String sql = "UPDATE customers set name = '"+name+"' where Username = '"+username+"';";
            		commandLine.executeSession(sql, 2);
            	}
            	if (!address.equals("")){
            		String sql = "UPDATE customers set address = '"+address+"' where Username = '"+username+"';";
            		commandLine.executeSession(sql, 2);
            	}
            	if (!sex.equals("")){
            		String sql = "UPDATE customers set sex = '"+sex+"' where Username = '"+username+"';";
            		commandLine.executeSession(sql, 2);
            	}
            	if (age != 0){
            		String sql = "UPDATE customers set age = '"+age+"' where Username = '"+username+"';";
            		commandLine.executeSession(sql, 2);
            	}
            	commandLine.endSession();
            	return;
            }
			case "7":  return;
            
            
        }
		}
	}
}
