package facility;

import java.util.Hashtable;
import java.util.Set;

import exception.InvalidDataException;
import exception.NoSuchItemException;


public interface Facility {
	
	int updateInventory(String itemName, int quantity) throws InvalidDataException, NoSuchItemException;
	int getArrivalDate(int startDay, int numberOfItems);
    int setSchedule(int startday, int quantity);
	void getSchedule(int dayStart, int dayEnd) throws InvalidDataException;
	void showInventory();
	int getQuantity(String name);
	Hashtable<String,Double> getNeighbor();
	double getDistance(String name);
	String getName();
	boolean hasItem(String name);
	double getProcessDay(int numberOfItems);
	
}
