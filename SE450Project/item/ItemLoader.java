package item;

import java.util.Hashtable;

public interface ItemLoader {
	Hashtable<String,Item> loadItem();
}
