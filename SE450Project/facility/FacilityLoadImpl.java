package facility;

import item.ItemService;

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

import Inventory.Inventory;
import Inventory.InventoryFactory;
import Schedule.Schedule;
import Schedule.ScheduleFactory;
import exception.InvalidDataException;
import exception.NoSuchItemException;


public class FacilityLoadImpl implements FacilityLoader{
	
	private Hashtable<String, Facility> facilities = new Hashtable();
	
	public Hashtable<String, Facility> loadFacility() {
		try {
			
            String fileName = "facilitycontent.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList facilityEntries = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < facilityEntries.getLength(); i++) {
                if (facilityEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                
                String entryName = facilityEntries.item(i).getNodeName();
                if (!entryName.equals("Facility")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return null;
                }
                
                // Get a node attribute
                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                String facilityName = aMap.getNamedItem("Name").getNodeValue();

                // Get a named nodes
                Element elem = (Element) facilityEntries.item(i);
                String processRate = elem.getElementsByTagName("Rate").item(0).getTextContent();
                String cost = elem.getElementsByTagName("Cost").item(0).getTextContent();
                Hashtable<String, Double> nearBys = new Hashtable<String,Double>();
          
                NodeList nearByList = elem.getElementsByTagName("NearBy");
                NodeList inventoryList = elem.getElementsByTagName("Inventory");
            
                for (int j = 0; j < nearByList.getLength(); j++) {
                    if (nearByList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = nearByList.item(j).getNodeName();
                    if (!entryName.equals("NearBy")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return null;
                    }

                    // Get some named nodes
                    elem = (Element) nearByList.item(j);
                    String nearByName = elem.getElementsByTagName("Name").item(0).getTextContent();
                    String distance = elem.getElementsByTagName("Distance").item(0).getTextContent();
                   
                    // Create a string summary of the book
                   
                    nearBys.put(nearByName, Double.parseDouble(distance));
                   
                    
                }
            
         
                
                FacilityFactory facilityFactory = new FacilityFactory();
                InventoryFactory inventoryFactory = new InventoryFactory();
                ScheduleFactory scheduleFactory = new ScheduleFactory();
                
                
                Hashtable<String,Integer> inventoryData = new Hashtable();
                
                for (int k = 0; k < inventoryList.getLength(); k++) {
                    if (inventoryList.item(k).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = inventoryList.item(k).getNodeName();
                    if (!entryName.equals("Inventory")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return null;
                    }
                   
                    // Get some named nodes
                    elem = (Element) inventoryList.item(k);
                    String itemName = elem.getElementsByTagName("Item").item(0).getTextContent();
                    String quantity = elem.getElementsByTagName("Quantity").item(0).getTextContent();
                    
                    inventoryData.put(itemName, Integer.parseInt(quantity));
                 
                }
                Schedule schedule = scheduleFactory.createSchedule(Integer.parseInt(processRate));
                Inventory inventory = inventoryFactory.createInventory(inventoryData);
                Facility facility =  facilityFactory.createFacility(facilityName,Integer.parseInt(processRate), 
                		nearBys,Double.parseDouble(cost),schedule,inventory);
               
                facilities.put(facilityName, facility);
                
               
             
                // Here I would create a Store object using the data I just loaded from the XML
                
              
                
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException | InvalidDataException e) {
            e.printStackTrace();
        }
		return facilities ;
		
	}

}
