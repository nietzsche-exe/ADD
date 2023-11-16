package AAD.UD04E01.JoseJulianSaavedra;

import java.io.IOException;
import java.io.File;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;


public class Principal {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);

	public static void main(String[] args) {
		
		
		try {
			
			XMLManager.descargarXML();
			File xml = new File("padron_madrid_202310.xml");
			
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLManager defaultHandler = new XMLManager();
			sp.parse(xml, defaultHandler);
		
		} catch (IOException e) {
			 LOGGER.error("error de E/S durante la recuperación del XML de internet");
		} catch (ParserConfigurationException e) {
			LOGGER.error("Error configuracion parser");
		} catch (SAXException e) {
			LOGGER.error("Error SAXE");
		}
		

	}

}
