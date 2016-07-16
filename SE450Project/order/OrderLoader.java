package order;

import java.util.Hashtable;

public interface OrderLoader {
	Hashtable<String,Order> loadOrder();

}
