package orderprocesser;

public class OrderRecord {
	private String orderId;
	private String facility;
	private int arrivalDate;
	private String item;
	private int itemQuantity;
	private double totalCost;
	
	public OrderRecord(String inOrderId, String inFacility, int inArrivalDate,String inItem, int inItemQuantity,double inTotalCost){
		this.orderId = inOrderId;
		this.item = inItem;
		this.facility = inFacility;
		this.arrivalDate = inArrivalDate;
		this.itemQuantity = inItemQuantity;
		this.totalCost = inTotalCost;
		
	}
	public double getTotalCost(){
		return this.totalCost;
	}
	public String getOrderId(){
		return this.orderId;
	}
	public String getItemName(){
		return this.item;
	}
	public String getFacility(){
		return this.facility;
	}
	public int getArrivalDate(){
		return this.arrivalDate;
	}
	public int getItemQuantity(){
		return this.itemQuantity;
	}
	
}
