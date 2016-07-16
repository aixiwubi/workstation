package Inventory;

import java.util.Hashtable;

import exception.InvalidDataException;


public class InventoryFactory {
	public Inventory createInventory(Hashtable<String,Integer> inInventory) throws InvalidDataException{
		return new InventoryImpl(inInventory);
	}
}
