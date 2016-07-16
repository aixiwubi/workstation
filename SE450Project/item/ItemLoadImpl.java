package item;



import java.io.File;
import java.io.IOException;
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

public class ItemLoadImpl implements ItemLoader{
    private Hashtable<String,Item> items = new Hashtable();
	
	public Hashtable<String, Item> loadItem() {
		try {
			
            String fileName = "itemcontent.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList itemEntries = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < itemEntries.getLength(); i++) {
                if (itemEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                
                String entryName = itemEntries.item(i).getNodeName();
                if (!entryName.equals("Item")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return null;
                }
                
                // Get a node attribute
                NamedNodeMap aMap = itemEntries.item(i).getAttributes();
                String itemName = aMap.getNamedItem("Name").getNodeValue();

                // Get a named nodes
                Element elem = (Element) itemEntries.item(i);
                String price = elem.getElementsByTagName("Price").item(0).getTextContent();
                
                ItemFactory itemfactory = new ItemFactory();
                Item item = itemfactory.createItem(itemName, Double.parseDouble(price));
                
                items.put(itemName, (ItemImpl) item);
              
                
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException | InvalidDataException  e) {
            e.printStackTrace();
        }
		return items ;
		
	
	}

}
