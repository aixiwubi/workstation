package Inventory;

import exception.InvalidDataException;

public interface Inventory {
	int getItemQuantity(String itemName);
	int updateInventory(String itemName, int diff) throws InvalidDataException;
	void showInventory();
	boolean hasItem(String itemName);
}
