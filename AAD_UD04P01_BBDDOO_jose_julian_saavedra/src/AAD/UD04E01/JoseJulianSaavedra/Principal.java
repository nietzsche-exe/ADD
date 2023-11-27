package AAD.UD04E01.JoseJulianSaavedra;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;


/**
 * Clase Principal del programa que ejecuta la logica de la app
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023-2024
 */
public class Principal {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);

	/**
	 * Metodo main
	 * Aqui se lleva acabo la ejecuccion de los metodos principales
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 */
	public static void main(String[] args) {
		
		LOGGER.debug("Inicio programa");
		
		File xml = new File("padron_madrid_202310.xml");
        if (!xml.exists()) {
            LOGGER.debug("Descargando XML...");
            XMLManagerDefaultHandler.descargarXML();
        } else {
            LOGGER.debug("El XML ya existe. No es necesario descargarlo.");
        }
        
        BBDDOOManager BD = new BBDDOOManager();
        
	try {
       
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setValidating(true);
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XMLManagerDefaultHandler defaultHandler = new XMLManagerDefaultHandler();
        saxParser.parse(xml, defaultHandler);
        
        
		BD.almacenarEnDB(XMLManagerDefaultHandler.getDistritos());
		
		BD.consultarDistrito();
        
	}
	catch (SAXException e) {
		LOGGER.error("Error de parseo SAX");
	} 
	catch (IOException e) {
		LOGGER.error("Error de E/S");
	} 
	catch (ParserConfigurationException e) {
		LOGGER.error("Error de configuracion SAX");
	}
	finally {
		BD.borrarDatos();
		LOGGER.debug("Fin programa");
	}
	
		
		
	}

}
