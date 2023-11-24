package ADD_EX01E01;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * Clase Programa es la clase principal del Programa. 
 * En ella se lleva la logica de ejecuccion 
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023-2024
 */
public class Programa {
	
	static JuegoDefaultHandler defaultHandler = new JuegoDefaultHandler(); 

	private static final Logger LOGGER = LoggerFactory.getLogger(Programa.class);
	
	/**
	 * Metodo main donde se ejecutan los metodos principales
	 * Este espera como parametro el fichero xml a parsera
	 * @param args 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LOGGER.debug("Inicio Programa");
		
		
	try {
		
		File xml = new File(args[0]);
		File fichero = new File("juegos_JoseJulianSaavedra.txt");
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		saxParserFactory.setNamespaceAware(true);
		JuegoDefaultHandler defaultHandler = new JuegoDefaultHandler();
		SAXParser saxParser = saxParserFactory.newSAXParser();
		saxParser.parse(xml, defaultHandler);
		
		JuegoDefaultHandler.escribirJuegosFichero(fichero);
		
	} 
	catch(ParserConfigurationException e) {
		LOGGER.error("Error de configuracion Parser");
	}
	catch(SAXException e) {
		LOGGER.error("Error de SAX");
	}
	catch(IOException e) {
		LOGGER.error("Error de E/S ");
	}
	catch(ArrayIndexOutOfBoundsException e) {
		LOGGER.error("Se espera un parametro (fichero xml)");
	}
		
		LOGGER.debug("Fin Programa");
		
	}
	
	


}
