package mariadb;

public class sqlcc {
	private static sqlc sqlMngr = null;
	public static boolean startSession() {
		boolean success = true;

		if (sqlMngr == null) {
			sqlMngr = new sqlc();
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
	public static void executeSession(String sql,int option){
		if (option == 1){
			sqlMngr.selectOp(sql);
		}

}
}
