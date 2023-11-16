package es.ciudadescolar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File ficheroXsd = new File(args[0]);
		File ficheroXml = new File(args[1]);
		LOGGER.debug("El fichero a parsear es " + ficheroXml);
		LOGGER.debug("El fichero xsd contra el que validar el xml es " + ficheroXsd);
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		LOGGER.debug("Nueva instancia de SAXParserFactory");
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema;
		
		try {
			
			schema = schemaFactory.newSchema(ficheroXsd);
			saxParserFactory.setSchema(schema);
			LOGGER.debug("Añadido schema al SAXParserFactory");
			saxParserFactory.setNamespaceAware(true);
			
			SAXParser saxParser = saxParserFactory.newSAXParser();
			LOGGER.debug("Nueva instancia del SAXParser");
			ItemDefaultHandler defaultHandler = new ItemDefaultHandler();
			LOGGER.debug("Nueva instancia del DefaultHandler");
			
			saxParser.parse(ficheroXml, defaultHandler);
			LOGGER.debug("Fichero xml parseado");
			
			List<Item> items = defaultHandler.getItems();
			LOGGER.debug("Se han recuperado [" + items.size() + "] items del xml");
			
			if(!writeItemsToFile(items, new File("cve_list.txt"))) {
				
				LOGGER.debug("Error al escribir en el fichero de texto");
				
			}
			
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static boolean writeItemsToFile (List<Item> listaItems, File ficheroSalida)
	{
		FileWriter ficheroEscribible = null;
		PrintWriter pt = null;
	    try 
		{
				ficheroEscribible = new FileWriter(ficheroSalida);
				pt = new PrintWriter(ficheroEscribible);
				
			    for (Item itemCve:listaItems)
			    	pt.println(itemCve);
		} 
		catch (IOException e) 	{	return false; 	}
		finally
		{
		    if (pt != null)
		    {	
		    	pt.flush();
		    	pt.close();
		    }
		}
		return true;
	}

}
