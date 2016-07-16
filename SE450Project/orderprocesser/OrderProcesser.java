package orderprocesser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import item.ItemService;
import exception.InvalidDataException;
import exception.NoSuchFacilityException;
import exception.NoSuchItemException;
import exception.NoSuchOrderException;
import facility.FacilityService;
import order.Order;
import order.OrderLoadImpl;
import order.OrderService;

public class OrderProcesser {
		public void processOrder(String orderId){
		
		OrderService ordersvc = OrderService.getInstance();
		
		FacilityService facilitysvc = FacilityService.getInstance();
		ItemService itemsvc = ItemService.getInstance();
		double allCost =0;
		ArrayList<Integer> deliverDays = new ArrayList();
		
		System.out.println("Order ID: " + orderId);
		
		System.out.println("Destination: "+ ordersvc.getOrderDestination(orderId));
		
		for(String item: ordersvc.getAllOrderItems(orderId)){
			System.out.println(item+ ": " +ordersvc.getOrderItemQuantity(orderId, item));
		}
		System.out.println("\nDetailed Solution: ");
		for(String item: ordersvc.getAllOrderItems(orderId)){
			double itemTotalCost=0;
			ArrayList<OrderRecord> result = new ArrayList();	//System.out.println(item+": ");
			Hashtable<String,Integer> tranvelTime = new Hashtable();
			Hashtable<Double, String > deliveryDate = new Hashtable<Double, String>();
			List<Double> sort = new ArrayList();
			for(String facility: facilitysvc.showAllFacility()){
			
					
						try {
							if(facilitysvc.hasItem(facility, item)&&!facility.equals(ordersvc.getOrderDestination(orderId))){					//System.out.println(facility+ item);
								int departure = facilitysvc.getArrivalDate(facility, ordersvc.getOrderDate(orderId), 
										ordersvc.getOrderItemQuantity(orderId, item));
								//System.out.println(departure);						
								double shippingTime = facilitysvc.findShortestPath(facility, ordersvc.getOrderDestination(orderId));
							//System.out.println(shippingTime);
								double arrival = departure + shippingTime;
								deliveryDate.put(arrival, facility);
								sort.add(arrival);
							}
						} catch (NoSuchFacilityException | InvalidDataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
			}
			Collections.sort(sort);
			
			int orderItemLeft = ordersvc.getOrderItemQuantity(orderId, item);
			//System.out.println("Initial Item: "+orderItemLeft);
			OrderRecordFactory orderRecordFactory = new OrderRecordFactory();
			
			for(int i =0; i<sort.size();i++){
				int facilityItem;
				try{
					facilityItem = facilitysvc.getItemQuantity(deliveryDate.get(sort.get(i)), item);
				
				//System.out.println(deliveryDate.get(sort.get(i)));
				if(orderItemLeft ==0){
					break;
				}
				
				else{
					//facilitysvc.getSchedule(deliveryDate.get(sort.get(i)), 1, 20);
					//System.out.println("\n");
					if(facilityItem>orderItemLeft){
						
							facilitysvc.updateSchedule(deliveryDate.get(sort.get(i)), ordersvc.getOrderDate(orderId),
									orderItemLeft);
						
					}
					else{
						
							facilitysvc.updateSchedule(deliveryDate.get(sort.get(i)), ordersvc.getOrderDate(orderId),
									facilityItem);
						
					}
				}
					//facilitysvc.getSchedule(deliveryDate.get(sort.get(i)), 1, 20);
					int orderPre = orderItemLeft;
					//System.out.println("Item left to order: "+orderItemLeft);
					//System.out.println("travel time: "+ sort.get(i));
					
						orderItemLeft =facilitysvc.updateInventory(deliveryDate.get(sort.get(i)), item, orderItemLeft);
					
					int travelTime;
					
						travelTime = (int) facilitysvc.findShortestPath(deliveryDate.get(sort.get(i)), ordersvc.getOrderDestination(orderId))+1;
					
					double itemCost;
					double processCost;
					double transportCost;
				
						itemCost = itemsvc.getPrice(item)*(orderPre-orderItemLeft);
						processCost = facilitysvc.getProcessDay(deliveryDate.get(sort.get(i)),orderPre-orderItemLeft );
						transportCost = travelTime * 500;
						double totalCost = itemCost+processCost+transportCost;
						OrderRecord orderRecord = orderRecordFactory.createOrderRecord(orderId,deliveryDate.get(sort.get(i)), sort.get(i).intValue()+1,item,
								orderPre-orderItemLeft,totalCost);
						deliverDays.add(orderRecord.getArrivalDate());
						result.add(orderRecord);					
					
							
				}
				catch(NoSuchFacilityException | InvalidDataException | NoSuchItemException e){
					e.printStackTrace();
				}
				double totalCost=0;
				
				for(int x = 0;x<result.size();x++){
					totalCost+=result.get(x).getTotalCost();
					
				}
				itemTotalCost = itemTotalCost +totalCost;
				allCost = allCost+totalCost;
			
			}
			//System.out.println("\nDetailed Solution");
			System.out.println("\nItem: " + item);
			int processed = 0;
			for(int x=0;x<result.size();x++){
				processed = processed + result.get(x).getItemQuantity();
			}
			
			System.out.println("Quanitity: "+ processed);
			System.out.println(String.format("Cost: $ %.2f", itemTotalCost));
			System.out.println("Number Of Sources: " + result.size());
			System.out.println("The Solution Facilities are: ");
			for(int a=0; a<result.size();a++){
				System.out.println(result.get(a).getFacility());
			}
			System.out.println("First Day Delivery: " + result.get(0).getArrivalDate());
			System.out.println("Last Day Delivery: "+ result.get(result.size()-1).getArrivalDate());
			
		}
		Collections.sort(deliverDays);
		
		System.out.println("\nSummary:\nFirst Day Deliver: "+ deliverDays.get(0));
		System.out.println("Last Day Deliver: "+ deliverDays.get(deliverDays.size()-1));
		System.out.println(String.format("Total Cost: $ %.2f", allCost));
		System.out.println();System.out.println();
		
		
		}
			
		
	
	
	public static void main(String args[]){
		OrderProcesser process = new OrderProcesser();
		FacilityService facilitysvc = FacilityService.getInstance();
		OrderService ordersvc = OrderService.getInstance();
		ArrayList<String> orders = new ArrayList();
		orders.addAll(ordersvc.getAllOrders());
		Collections.sort(orders);
		

		try {
			facilitysvc.showFacilitiesStatus();
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String order: orders){
			System.out.println("Order Detail");
			ordersvc.getOrderStatus(order);
			System.out.println();
			System.out.println("Order Solution");
			process.processOrder(order);
		}
	
		try {
			facilitysvc.showFacilitiesStatus();
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
