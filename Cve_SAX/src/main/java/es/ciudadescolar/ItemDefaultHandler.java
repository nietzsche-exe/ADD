package es.ciudadescolar;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ItemDefaultHandler extends DefaultHandler {

	private List<Item> items;
	private Item item;
	private StringBuilder informacionSB;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemDefaultHandler.class);
	
	public ItemDefaultHandler() {
		
		items = new ArrayList<Item>();
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		informacionSB.append(String.valueOf(ch, start, length));
		
	}

	@Override
	public void endDocument() throws SAXException {
		
		LOGGER.trace("Fin de parseo XML");
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if(qName.equals("item")) {
			items.add(item);
		}
		else if (qName.equals("status")) {
			item.setStatus(informacionSB.toString());
		}
		else if (qName.equals("desc")) {
			item.setDesc(informacionSB.toString());
		}
		
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		
		LOGGER.error(e.getMessage());;
		
	}

	@Override
	public void startDocument() throws SAXException {
		
		LOGGER.trace("Comienzo de parseo XML");
		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if(qName.equals("item")) {
			item = new Item();
			for(int i = 0; i<attributes.getLength(); i++) {
			
				String nombreAtributo = attributes.getLocalName(i);
				if(nombreAtributo.equals("name")) {
					item.setName(attributes.getValue(i));
				}
				else if (nombreAtributo.equals("seq")) {
					item.setSeq(attributes.getValue(i));
				}
				else if (nombreAtributo.equals("type")) {
					item.setType(attributes.getValue(i));
				}
				
				
			}
		}
		else if (qName.equals("status")||qName.equals("desc")) {
			
			//inicializar y resetear el StringBuilder
			informacionSB = new StringBuilder();
			
		}
		
	}
	
	public List<Item> getItems(){
		return items;
	}

}
