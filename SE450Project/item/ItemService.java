package item;


import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import exception.NoSuchItemException;

public class ItemService {
	private static ItemService itemSrv = null;
	
	private ItemLoadImpl loader = new ItemLoadImpl();
	private ItemService(){}
	public static ItemService getInstance(){
		if(itemSrv==null){
			itemSrv = new ItemService();
		}
		return itemSrv;
	}
	private Hashtable<String, Item> items = loader.loadItem();
	
	public void verifyItem(String itemName) throws NoSuchItemException{
		if(!items.containsKey(itemName)){
			throw new NoSuchItemException(itemName + "does not exist in Items");
		}
	} 


	public double getPrice(String itemName) throws NoSuchItemException {
		verifyItem(itemName);
		return items.get(itemName).getPrice();
	}
	public Set<String> getItemNames(){
		Set<String> itemNames = new HashSet();
		return itemNames =  items.keySet();
	
		
	}
	public static void main(String args[]){
		ItemService itemsvc = ItemService.getInstance();
	}
	

	

}
