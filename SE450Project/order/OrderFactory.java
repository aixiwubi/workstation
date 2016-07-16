package order;

import java.util.Hashtable;

import exception.InvalidDataException;

public class OrderFactory {
	public Order createOrder(String inOrderId,String destination,int inOrderDate, Hashtable<String,Integer> inOrderItems) throws InvalidDataException{
		return new OrderImpl(inOrderId, destination,inOrderDate, inOrderItems);
		
	}

}
