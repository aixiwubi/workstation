package order;

import item.Item;
import item.ItemFactory;
import item.ItemImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import exception.InvalidDataException;

public class OrderLoadImpl implements OrderLoader{
private Hashtable<String,Order >orders = new Hashtable();
	
	public Hashtable<String, Order> loadOrder() {
		try {
			
            String fileName = "ordercontent.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList orderEntries = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < orderEntries.getLength(); i++) {
                if (orderEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                
                String entryName = orderEntries.item(i).getNodeName();
                if (!entryName.equals("Order")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return null;
                }
                
                // Get a node attribute
                NamedNodeMap aMap = orderEntries.item(i).getAttributes();
                String orderId = aMap.getNamedItem("Name").getNodeValue();
                
                

                // Get a named nodes
                Element elem = (Element) orderEntries.item(i);
                String destination = elem.getElementsByTagName("Destination").item(0).getTextContent();
                String orderDate = elem.getElementsByTagName("Ordertime").item(0).getTextContent();
                
                NodeList itemList = elem.getElementsByTagName("OrderItems");
                Hashtable<String,Integer> orderItems = new Hashtable();
                
                for (int j = 0; j < itemList.getLength(); j++) {
                    if (itemList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = itemList.item(j).getNodeName();
                    if (!entryName.equals("OrderItems")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return null;
                    }

                    // Get some named nodes
                    elem = (Element) itemList.item(j);
                    String itemName = elem.getElementsByTagName("Name").item(0).getTextContent();
                    String quantity = elem.getElementsByTagName("Quantity").item(0).getTextContent();
                   
                    // Create a string summary of the book
                   
                    orderItems.put(itemName, Integer.parseInt(quantity));
                   
                    
                }
                
                OrderFactory orderfactory = new OrderFactory();
                Order order = orderfactory.createOrder(orderId, destination, Integer.parseInt(orderDate), orderItems);
                orders.put(orderId, order);
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException | InvalidDataException  e) {
            e.printStackTrace();
        }
		return orders ;
		
	
	}

}
