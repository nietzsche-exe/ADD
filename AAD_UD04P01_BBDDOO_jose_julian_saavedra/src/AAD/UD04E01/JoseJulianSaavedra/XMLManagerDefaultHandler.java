package AAD.UD04E01.JoseJulianSaavedra;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Clase XMLManager 
 * Esta clase se encarga de gestionar el fichero xml pasado por parametro al main 
 * La clase usa la api SAX heredada de la clase DefaultHandler
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023-2024
 */
public class XMLManagerDefaultHandler extends DefaultHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XMLManagerDefaultHandler.class);
	
	private static List<Distrito> distritos = new ArrayList<>();
	
	private boolean esCodigoDistrito = false;
	private boolean esDescripcionDistrito = false;
	private boolean esNumHombres = false;
	private boolean esNumMujeres = false;
	
	private int codigoDistrito;
    private String descripcionDistrito;
    private int numHombres;
    private int numMujeres;
	
	private static final String FILE_URL = "https://datos.madrid.es/egob/catalogo/200076-4-padron.xml";
	private static final String FILE_NAME = "padron_madrid_202310.xml";
	
	/**
	 * Metodo descargarXML
	 * Este metodo recibe una url a partir de la cual se procede a la descarga del documento XML 
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 */
	public static void descargarXML() {

		try {
			URL xmlUrl = new URL(FILE_URL);
			InputStream in = xmlUrl.openStream();
			Path destino = Path.of(FILE_NAME);
			Files.copy(in, destino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			LOGGER.error("Error en la descarga del XML");
		}

		LOGGER.debug("Descarga exitosa");

	}

	
	
	@Override
	public void startDocument() throws SAXException {
		LOGGER.debug("Inicio parseo XML");
	}



	@Override
	public void endDocument() throws SAXException {
		LOGGER.debug("Final parseo XML");
	}



	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if (qName.equalsIgnoreCase("COD_DISTRITO")) {
            esCodigoDistrito = true;
        } else if (qName.equalsIgnoreCase("DESC_DISTRITO")) {
            esDescripcionDistrito = true;
        } else if (qName.equalsIgnoreCase("ESPANOLESHOMBRES") || qName.equalsIgnoreCase("EXTRANJEROSHOMBRES")) {
            esNumHombres = true;
        } else if (qName.equalsIgnoreCase("ESPANOLESMUJERES") || qName.equalsIgnoreCase("EXTRANJEROSMUJERES")) {
            esNumMujeres = true;
        }
		
	}


	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		if (esCodigoDistrito) {
	            codigoDistrito = Integer.parseInt(new String(ch, start, length).trim());
	            esCodigoDistrito = false;
	        } else if (esDescripcionDistrito) {
	            descripcionDistrito = new String(ch, start, length).trim();
	            esDescripcionDistrito = false;
	        } else if (esNumHombres) {
	            numHombres += Integer.parseInt(new String(ch, start, length).trim());
	            esNumHombres = false;
	        } else if (esNumMujeres) {
	            numMujeres += Integer.parseInt(new String(ch, start, length).trim());
	            esNumMujeres = false;
	        }
		
	}
	
	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       
		if (qName.equalsIgnoreCase("record")) {
           
            Distrito distrito = new Distrito(codigoDistrito, descripcionDistrito, numMujeres, numHombres);
            distritos.add(distrito);

           
            codigoDistrito = 0;
            descripcionDistrito = "";
            numHombres = 0;
            numMujeres = 0;
        }
    }


	@Override
	public void error(SAXParseException e) throws SAXException {
		super.error(e);
	}
	
	
	public static List<Distrito> getDistritos() {
        return distritos;
    }

}
