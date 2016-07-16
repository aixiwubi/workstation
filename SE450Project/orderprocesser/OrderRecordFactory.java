package orderprocesser;



public class OrderRecordFactory {
	public OrderRecord createOrderRecord(String inOrderId,String inFacility,int inArrivalDate, String inItem,int inItemQuantity,
			double inTotalCost){
		return new OrderRecord(inOrderId,inFacility, inArrivalDate,inItem,inItemQuantity,inTotalCost);
		
	}
}
