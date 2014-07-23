import java.util.ArrayList;


public class sideOrder {
	public sideOrder(){
		String sql = "select name from product where category = 'side' and category ='drink'";
		commandLine.startSession();
		ArrayList<String> data = commandLine.executeSession(sql, 1);
		commandLine.endSession();
		for (int a= 0;a< data.size();a++){
			System.out.println(a +"="+data.get(a));
		}
	}
}
