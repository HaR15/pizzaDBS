import java.text.SimpleDateFormat;
import java.util.*;
import java.math.*;
public class dispatchTicket {

	private String tid;
	private String username = "";
	private String delivererName = "";
	private String delivererDid = "";
	private int costPerMile = 0;
	private double estimateCost = 0;
	private float distance = 0;
	private String dispatchDate = "";
	
	public dispatchTicket(String tid){
		this.tid = tid;		
		Scanner input = new Scanner(System.in);
		String sql = "";
		String fillers = "======================================================================";

		commandLine.startSession();
		sql = "select username from transaction where tid = '"+tid+"';";
		this.username = commandLine.executeSession(sql, 1).get(0);
		commandLine.endSession();
		
		if(!nextDeliveryPerson()){
			System.out.println("Have a nice day!");
			return;
		}
		
		commandLine.startSession();
		
		sql = "SELECT costPerMiles FROM deliverypeople WHERE did = '" + this.delivererDid + "';";
		this.costPerMile = Integer.parseInt(commandLine.executeSession(sql, 1).get(0));
		
		sql = "Update deliverypeople SET availableStatus = 0 WHERE did = '" + this.delivererDid +"';";
		commandLine.executeSession(sql, 2);
	
		commandLine.endSession();
		
		Date now = new Date( );
		
		this.dispatchDate = now.toString();
		String deliveryTime = addMinutesToDate(10, now).toString();
		
		this.estimateCost = (double)Math.round(this.costPerMile * this.distance * 10) / 10 ;
		
		System.out.println(fillers);
		System.out.println("BILL");
		System.out.println(fillers);
		new viewCart(this.tid);
		System.out.println(fillers);
		
		System.out.println("");
		System.out.println("");
		System.out.println(fillers);
		System.out.println("DISPATCH TICKET");
		System.out.println(fillers);
		System.out.println("Customer Username:\t " + this.username);
		System.out.println("\nDispatch Time: " + this.dispatchDate);
		System.out.println("Estimated delivery time:\t " + deliveryTime);
		System.out.println("Delivery Person ID:\t " + this.delivererDid);
		System.out.println("Delivery Person:\t " + this.delivererName);
		System.out.println("Cost/mile:\t " + this.costPerMile);
		System.out.println("Delivery Distance:\t " + this.distance);
		System.out.println("\nEstimated Cost:\t " + this.estimateCost);
		System.out.println(fillers);

		
		
	}
	
	public Boolean nextDeliveryPerson(){
		commandLine.startSession();
		String sql = "SELECT did FROM deliverypeople WHERE availableStatus = '" + 1 + "';"; 
		ArrayList<String> deliveryPeople = commandLine.executeSession(sql, 1);
		
		if(deliveryPeople.size() == 0){
			System.out.println("Sorry, no deliverers available at the moment!");
			return false;
		}
		
		float minimumDistance = 100000;
		commandLine.endSession();
		for(int i = 0; i < deliveryPeople.size(); i++){
			commandLine.startSession();
			sql = "SELECT currentLocation FROM deliverypeople WHERE did = '"+deliveryPeople.get(i)+"';";
			String currentLocation = commandLine.executeSession(sql, 1).get(0);
			commandLine.endSession();
			if(calculateDistance(currentLocation) < minimumDistance){
				commandLine.startSession();
				sql = "SELECT name FROM deliverypeople WHERE did = '"+deliveryPeople.get(i)+"';";
				this.delivererName = commandLine.executeSession(sql, 1).get(0);
				this.delivererDid = deliveryPeople.get(i);
				minimumDistance = calculateDistance(currentLocation);
				this.distance = minimumDistance;
				commandLine.endSession();
			}
			
		}	
		commandLine.endSession();
		return true;
	}

	public float calculateDistance(String currentLocation) {
		String sql = "";
		commandLine.startSession();
		
		sql = "SELECT addr FROM Customers WHERE Username = '" + this.username + "';";
		String customerAddr = commandLine.executeSession(sql, 1).get(0);
		String[] coordinates = customerAddr.split(",");
		int customerX = Integer.parseInt(coordinates[0].substring(1)); 
		int customerY = Integer.parseInt(coordinates[1].substring(0, coordinates[1].length()-1));
		
		String [] delivererCoordinates = currentLocation.split(",");
		int delivererX = Integer.parseInt(delivererCoordinates[0].substring(1));
		int delivererY = Integer.parseInt(delivererCoordinates[1].substring(0, delivererCoordinates[1].length()-1));
		
		int distanceX = delivererX - customerX;
		int distanceY = delivererY - customerY;
		float distance = (float) Math.sqrt((distanceX * distanceX)  + (distanceY * distanceY));
		
		commandLine.endSession();
		return distance; 
	}
	
	private Date addMinutesToDate(int minutes, Date beforeTime){
	    final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

	    long curTimeInMs = beforeTime.getTime();
	    Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
	    return afterAddingMins;
	}
}
