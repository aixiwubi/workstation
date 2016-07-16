package item;

import exception.InvalidDataException;

public class ItemImpl implements Item {
	private String itemID;
	private double price;
	
	
	
	public ItemImpl(String inId, double inPrice) throws InvalidDataException{
		if(inPrice<0){
			throw new InvalidDataException(inPrice + "Price cannot be Negative");
		}
		if(inId == null){
			throw new InvalidDataException("Need an Item Name");
		}
		this.itemID = inId;
		this.price = inPrice;
		
	}

	
	public double getPrice() {
		return this.price;
	}


	

}
