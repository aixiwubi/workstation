package Inventory;

import java.util.Hashtable;

import exception.InvalidDataException;

public class InventoryImpl implements Inventory {
	private Hashtable<String,Integer> inventory = new Hashtable<String,Integer>();
	public InventoryImpl(Hashtable<String,Integer> inInventory) throws InvalidDataException{
		if(inInventory == null){
			throw new InvalidDataException("Need a hashtable to keep inventory!!");
		}
		this.inventory = inInventory;
	}
	public int getItemQuantity(String itemName){
		if(!inventory.containsKey(itemName)){
			return 0;
		}
		else{
		return inventory.get(itemName);
		}
	}
	public int updateInventory(String itemName, int diff) throws InvalidDataException{
		if(!inventory.containsKey(itemName)){
			System.out.println(itemName+ " Inventory does not have this item.");
			return diff;
		}
		int quantity =inventory.get(itemName);
		if(diff<0){
			throw new InvalidDataException("cannot have negative item number");
		}
		
		else{
			if(diff>=quantity){
				
				inventory.remove(itemName);
				diff = diff - quantity;
				//System.out.println(diff);
			}
			else{
				inventory.replace(itemName, quantity-diff);
				diff = 0;
			}
		}
		return diff;
	}
	public void showInventory(){
		System.out.println(this.inventory);
	}
	public boolean hasItem(String name){
		if(!this.inventory.containsKey(name)){
			return false;
		}
		else{
			return this.inventory.get(name)>0;
		}
		
		
	}
	
	
	

}
