package facility;

import item.ItemService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import Inventory.Inventory;
import Schedule.Schedule;
import exception.InvalidDataException;
import exception.NoSuchItemException;


public class FacilityImpl implements Facility {
	private String facilityName;
	private int processRate;
	private double cost;
	private Hashtable<String,Double> nearBy = new Hashtable<String,Double>();
	private Schedule schedule;
	private Inventory inventory;
	
	public FacilityImpl(String inName,int inProcessRate, Hashtable<String,Double> inNearBy, double inCost,
			Schedule inSchedule, Inventory inInventory) throws InvalidDataException{
		if(inProcessRate <0 ){
			throw new InvalidDataException( inName + "inProcessRate can not have a negative value");
		}
		if(inCost <0 ){
			throw new InvalidDataException( inName + "inCost can not have a negative value");
		}
		if(inName == null){
			throw new InvalidDataException("Need a facility name");
		}
		if(inNearBy == null){
			throw new InvalidDataException("need an empty hashtable if there is no neighbors");
		}
		if(inInventory == null){throw new InvalidDataException(inName + "need an inventory to keep items!!!");
		}
		if(inSchedule == null){throw new InvalidDataException(inName + "need a schedule to process orders!!!");}
		
		this.facilityName = inName;
		this.processRate = inProcessRate;
		this.cost = inCost;
		this.nearBy = inNearBy;
		this.schedule = inSchedule;
		this.inventory = inInventory;
		
	}
	
	public void showStatus(){
		System.out.println("Facility name: "+ facilityName + ". ");
		System.out.println("ProcessRate: "+ processRate + ". ");
		System.out.println("Cost: " + cost + ". ");
		System.out.println("Near By Facilities: "+ nearBy + ". ");
		
	}
	@Override
	public int getQuantity(String itemName){
		return this.inventory.getItemQuantity(itemName);
	}
	public int getArrivalDate(int startDay, int numberOfItems){
		return this.schedule.getArrivalDate(startDay, numberOfItems);
	}
	public double getProcessDay(int numberOfItems){
		return this.schedule.getProcessDay(numberOfItems);
	}
	@Override
	public int updateInventory(String itemName, int quantity) throws InvalidDataException, NoSuchItemException {
		ItemService itemsvc = ItemService.getInstance();
		if(!itemsvc.getItemNames().contains(itemName)){
			throw new NoSuchItemException(itemName + " does not exist in the system");
		}
		
		
		return this.inventory.updateInventory(itemName, quantity);
		
	}

	
	public int setSchedule(int startDay, int quantity) {
		return this.schedule.setSchedule(startDay,quantity);
	}
	public void getSchedule(int dayStart, int dayEnd) throws InvalidDataException {
		this.schedule.showSchedule(dayStart, dayEnd);
		
	}
	@Override
	public void showInventory() {
		this.inventory.showInventory();
		
	}

	public boolean hasItem(String name) {
		// TODO Auto-generated method stub
		return this.inventory.hasItem(name);
		
	}
	@Override
	public Hashtable<String, Double> getNeighbor() {
		
		return this.nearBy;
	}
	@Override
	public double getDistance(String name) {
		// TODO Auto-generated method stub
		
		return this.nearBy.get(name);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.facilityName;
	}
	
	

	
}
