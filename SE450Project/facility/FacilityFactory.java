package facility;

import java.util.ArrayList;
import java.util.Hashtable;

import Inventory.Inventory;
import Schedule.Schedule;
import exception.InvalidDataException;


public class FacilityFactory {
	public Facility createFacility(String inName, int inProcessRate, Hashtable<String,Double> inNearBy,double inCost,
			Schedule inSchedule,Inventory inInventory) throws InvalidDataException{
		return new FacilityImpl(inName,inProcessRate,inNearBy, inCost, inSchedule,inInventory);
	}
	
}
