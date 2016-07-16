import item.ItemService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import order.OrderService;
import orderprocesser.OrderProcesser;
import exception.InvalidDataException;
import exception.NoSuchFacilityException;
import exception.NoSuchItemException;
import facility.*;

public class Main {
	public static void main(String args[]) {
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
