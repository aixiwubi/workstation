package order;

import java.util.Hashtable;
import java.util.Set;

import exception.InvalidDataException;

public class OrderImpl implements Order {
	
	private String orderID;
	private String destination;
	private int orderDate;
	private Hashtable<String,Integer> orderItems = new Hashtable(); 
	public OrderImpl(String inOrderId,String inDestination, int inOrderDate,Hashtable<String,Integer> inOrderItems)
			throws InvalidDataException{
		if(inOrderId == null){
			throw new InvalidDataException("Need an order ID!!");
		}
		if(inDestination == null){
			throw new InvalidDataException("Need a Destination !!!!");
		}
		if(inOrderDate < 0){
			throw new InvalidDataException(inOrderId+"cannot have a negative order date!!!");
		}
		if(inOrderItems == null){
			throw new InvalidDataException(inOrderId + "cannot have an empty order!!!");
		}
		this.orderID = inOrderId;
		this.orderItems = inOrderItems;
		this.orderDate = inOrderDate;
		this.destination = inDestination;
	}
	public String getOrderId(){
		return this.orderID;
	}
	public String getDestination() {
		// TODO Auto-generated method stub
		return this.destination;
	}

	
	public int getItemQuantity(String itemName) {
		// TODO Auto-generated method stub
		return this.orderItems.get(itemName);
		
	}

	@Override
	public int getOrderDate() {
		// TODO Auto-generated method stub
		return this.orderDate;
	}

	@Override
	public Set<String> getAllOrderItems() {
		// TODO Auto-generated method stub
		return this.orderItems.keySet();
	}
	

}
