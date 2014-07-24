import java.util.Scanner;
import java.sql.*;

public class admin {
	public admin(){
		Scanner input = new Scanner(System.in);
		System.out.println("Type the admin password");
		String pass = input.nextLine();
		String choose = "a";
		if (pass.equals("adminR") || pass.equals("adminH")){
			while (!choose.equals("5")){
				System.out.println("welcome admin, perform action by typing the indicated number");
				System.out.println("1, add a product");
				System.out.println("2, remove a product");
				System.out.println("3, add a special");
				System.out.println("4, remove a special");
				System.out.println("5, leave");
				choose = input.nextLine();
				if (choose.equals("1")){
					addnew();	
				}
				if (choose.equals("2")){
					deleteold();
				}
				if (choose.equals("3")){
					addsp();
				}
				if (choose.equals("4")){
					removesp();
				}
			}
		}
		return;
	}
	public void addnew(){
		Scanner input = new Scanner(System.in);
		String choose = "INSERT INTO product (pid,name,category,price,supplier,manufacturer,description,special) " +
				"SELECT MAX(pid)+1,";
		String[] data = {"name","category","price","supplier","manufacturer","description"};
		for (int a=0;a< data.length;a++){
			System.out.println("Enter the "+data[a]+" of the product");
			if (a+1 == data.length){
				choose = choose +"'"+ input.nextLine() + "',0 from product;";
			}
			else{
				choose = choose +"'"+ input.nextLine() + "',";
			}	
		}
		
		System.out.println(choose);
		commandLine.startSession();
		commandLine.executeSession(choose, 2);
		commandLine.endSession();
	}
	public void deleteold(){
		Scanner input = new Scanner(System.in);
		String[] data = {"name","category","price","supplier","manufacturer","description"};
		String choose = "DELETE from prodcut where ";
		for (int a=0;a< data.length;a++){
			System.out.println("Enter the "+data[a]+" of the product");
			if (a+1 == data.length){
				choose = choose + data[a] + "= "+ input.nextLine()+"';";
			}
			choose = choose + data[a] + "= "+ input.nextLine()+"',";
		}
		System.out.println(choose);
		commandLine.startSession();
		commandLine.executeSession(choose, 2);
		commandLine.endSession();
	}
	public void addsp(){
		Scanner input= new Scanner(System.in);
		System.out.println("What's the name of the product?");
		String choose = input.nextLine();
		commandLine.startSession();
		String sql ="UPDATE product set special = 1 where name = '"+choose+"';"; 
		commandLine.executeSession(sql, 2);
		commandLine.endSession();
	}
	
	public void removesp(){
		Scanner input= new Scanner(System.in);
		System.out.println("What's the name of the product?");
		String choose = input.nextLine();
		commandLine.startSession();
		String sql ="UPDATE product set special = 0 where name = '"+choose+"';"; 
		commandLine.executeSession(sql, 2);
		commandLine.endSession();
	}
}

