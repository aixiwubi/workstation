package facility;

import item.ItemService;
import shortestpath.DistanceToDay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import exception.InvalidDataException;
import exception.NoSuchFacilityException;
import exception.NoSuchItemException;
import shortestpath.Network;

public class FacilityService{
	private static FacilityService facilitySrv = null;
	private FacilityLoadImpl loader = new FacilityLoadImpl();
	private FacilityService(){}
	public static FacilityService getInstance(){
		if(facilitySrv==null){
			facilitySrv = new FacilityService();
		}
		return facilitySrv;
	}
	private Hashtable<String, Facility> facilities = loader.loadFacility();
	public Set<String> showAllFacility() {
		Set<String> facilityNames = new HashSet();
		return facilityNames =  facilities.keySet();
	}
	public void verifyFacility(String facilityName) throws NoSuchFacilityException{
		if(!facilities.containsKey(facilityName)){
			throw new NoSuchFacilityException(facilityName + "does not exist!!!");
		}
	}
	
	public double findShortestPath(String start,String destination) throws NoSuchFacilityException, InvalidDataException{
		double distance;
		if(start.equals(destination)){
			throw new InvalidDataException("start and destination cannot be the same! ");
		}
		verifyFacility(start);
		verifyFacility(destination);
		Network network = new Network(facilities);
		network.createNetwork();
		distance= network.findPath(start,destination);
		return distance;
	}
	
	public void getSchedule(String facilityName,int dayStart,int dayEnd) throws NoSuchFacilityException, InvalidDataException {
		verifyFacility(facilityName);
		facilities.get(facilityName).getSchedule(dayStart, dayEnd);
	}

	
	
	public void  showInventory(String facilityName) throws NoSuchFacilityException {
		verifyFacility(facilityName);
		System.out.println("Available inventory: ");
		facilities.get(facilityName).showInventory();
		
	}
	public boolean hasItem(String facilityName, String itemName) throws NoSuchFacilityException{
		verifyFacility(facilityName);
		return facilities.get(facilityName).hasItem(itemName);
	}
	public int updateInventory(String facilityName, String itemName, int quantity) throws NoSuchFacilityException, InvalidDataException, NoSuchItemException{
		verifyFacility(facilityName);
		return facilities.get(facilityName).updateInventory(itemName,quantity);
	}
	public int updateSchedule(String facilityName, int startDay, int numberOfItems) throws NoSuchFacilityException{
		verifyFacility(facilityName);
		return facilities.get(facilityName).setSchedule(startDay, numberOfItems);
	}
	public void getNeighbor(String facilityName) throws NoSuchFacilityException{
		verifyFacility(facilityName);
		DistanceToDay delegate = new DistanceToDay();
		System.out.println(facilityName + "'s "+"Direct Links are: ");
		for(String facility: facilities.get(facilityName).getNeighbor().keySet()){
		System.out.println("Facility Name: ("+ facility +  
				String.format(") %1$.2f days. ", delegate.toDay(facilities.get(facilityName).getDistance(facility))));
		}
	}
	public int getItemQuantity(String facilityName, String itemName) throws NoSuchFacilityException{
		verifyFacility(facilityName);
		return facilities.get(facilityName).getQuantity(itemName);
	}
	public void getDistance(String facilityName, String destination) throws NoSuchFacilityException{
		verifyFacility(facilityName);
		verifyFacility(destination);
		facilities.get(facilityName).getDistance(destination);
	}
	public int getArrivalDate(String facilityName, int startDay, int numberOfItems) throws NoSuchFacilityException{
		verifyFacility(facilityName);
		return facilities.get(facilityName).getArrivalDate(startDay, numberOfItems);
	}
	public double getProcessDay(String facilityName, int numberOfItems) throws NoSuchFacilityException{
		verifyFacility(facilityName);
		return facilities.get(facilityName).getProcessDay(numberOfItems);
	}
	public void showFacilitiesStatus() throws InvalidDataException{
		
	
		for(String facility : facilities.keySet()){
			System.out.println("Facility: " + facility);
			System.out.println("Direct Links: ");
			System.out.println(facilities.get(facility).getNeighbor());
			System.out.println("Available Invetory: ");
			facilities.get(facility).showInventory();
			System.out.println("Current Schedule is : ");
			facilities.get(facility).getSchedule(1, 20);
			System.out.print("\n");
			System.out.println("Depleted Item: ");
			ItemService itemsvc = ItemService.getInstance();
			
			for(String item: itemsvc.getItemNames()){
				if(!facilities.get(facility).hasItem(item)){
					System.out.print(item + ", ");
				}
				
			}
			System.out.println("\n");
		}
		
	}
		


	
}
