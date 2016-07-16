package item;

import exception.InvalidDataException;

public class ItemFactory {
	public Item createItem(String inId,double inPrice) throws InvalidDataException{
		return new ItemImpl(inId, inPrice);
		
	}

}
