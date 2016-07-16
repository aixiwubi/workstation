package order;

import java.util.Set;

public interface Order {
	String getDestination();
	int getOrderDate();
	int getItemQuantity(String itemName);
	Set<String> getAllOrderItems();
	String getOrderId();
}
