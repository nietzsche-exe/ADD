package AAD.UD04E01.JoseJulianSaavedra;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


public class XMLManager extends DefaultHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XMLManager.class);

	private static final String FILE_URL = "https://datos.madrid.es/egob/catalogo/200076-4-padron.xml";
	private static final String FILE_NAME = "padron_madrid_202310.xml";
	
	
	public static void descargarXML() throws IOException {
		
		URL xmlUrl = new URL(FILE_URL);
		 try (InputStream in = xmlUrl.openStream()) {
	            Path destino = Path.of(FILE_NAME);
	            Files.copy(in, destino, StandardCopyOption.REPLACE_EXISTING);
	        }
		 LOGGER.debug("Descarga exitosa");
			
	}


	@Override
	public void startDocument() throws SAXException {
		LOGGER.debug("Inicio parseo XML");
	}


	@Override
	public void endDocument() throws SAXException {
		LOGGER.debug("Fin parseo XML");
	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		
		
	}


	@Override
	public void error(SAXParseException e) throws SAXException {
		LOGGER.error("Error en el Parseo XML");
	}
	
	
	
	
	
}
