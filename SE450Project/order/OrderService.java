package order;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;



public class OrderService {
	private static OrderService orderSrv = null;
	private OrderLoadImpl loader = new OrderLoadImpl();
	private OrderService(){}
	public static OrderService getInstance(){
		if(orderSrv==null){
			orderSrv = new OrderService();
		}
		return orderSrv;
	}
	private Hashtable<String, Order> orders = loader.loadOrder();
	
	public int getOrderDate(String orderId){
		
		return orders.get(orderId).getOrderDate();
		
	}
	public String getOrderDestination(String orderId){
		return orders.get(orderId).getDestination();
	}
	public int getOrderItemQuantity(String orderId, String itemName){
		return orders.get(orderId).getItemQuantity(itemName);
		
	}
	public Set<String> getAllOrderItems(String orderId){
		return orders.get(orderId).getAllOrderItems();
	}
	public void getAllOrderStatus(){
		Set<String> allorders = orders.keySet();
		
		for(String order: allorders){
			System.out.println("Order: " + order+ ". ");
			System.out.println("Destination : " +orders.get(order).getDestination()+". ");
			System.out.println("Order Date: "+ orders.get(order).getOrderDate()+ ". ");
			System.out.println("Order Items: "+ orders.get(order).getAllOrderItems());
			System.out.println("");
		}
	}
	public void getOrderStatus(String order){
		System.out.println("Order: " + order+ ". ");
		System.out.println("Destination : " +orders.get(order).getDestination()+". ");
		System.out.println("Order Date: "+ orders.get(order).getOrderDate()+ ". ");
		System.out.println("Order Items: "+ orders.get(order).getAllOrderItems());
		System.out.println("");
		
	}
	public Set<String> getAllOrders(){
		return orders.keySet();
	}
	public static void main(String[] args){
		OrderService ordersvc = OrderService.getInstance();
		
		
		ordersvc.getAllOrderStatus();
		
	}
}
