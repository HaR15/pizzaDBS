import java.sql.ResultSet;
import java.util.ArrayList;


public class commandLine {
	private static sqlController sqlMngr = null;
	public static boolean startSession() {
		boolean success = true;

		if (sqlMngr == null) {
			sqlMngr = new sqlController();
		}
		String [] cred = {"root","123","customers"};
		try {
			success = sqlMngr.connect(cred);
		} catch (ClassNotFoundException e) {
			success = false;
			System.err.println("Establishing connection triggered an exception!");
			e.printStackTrace();

			sqlMngr = null;
		}
		return success;
	}
	
    /* Function that acts as destructor of an instance of this class.
     * Performs some housekeeping setting instance's private field
     * to null
     */
	public static void endSession() {
		if (sqlMngr != null)
			sqlMngr.disconnect();

		sqlMngr = null;

	}
	public static ArrayList<String> executeSession(String sql,int option){
		if (option == 1){
			ArrayList<String> rs = sqlMngr.selectOp(sql);
			return rs;
		}
		else{
			sqlMngr.insertOp(sql);
		}
		
		return null;
}
}
