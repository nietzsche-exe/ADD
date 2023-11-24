package ADD_EX01E01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Clase JuegoDefaultHandler es la clase que maneja la gestion del xml
 * En ella se hace uso de la Clase extendida DefaultHandler
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023-2024
 */
public class JuegoDefaultHandler extends DefaultHandler {
	
	private static List<Juego> juegos;
    private Juego juego;
    private StringBuilder data;

    public JuegoDefaultHandler() {
        juegos = new ArrayList<>();
        data = new StringBuilder();
    }

    public List<Juego> getJuegos() {
        return juegos;
    }

	private static final Logger LOGGER = LoggerFactory.getLogger(JuegoDefaultHandler.class);

	@Override
	public void startDocument() throws SAXException {
		LOGGER.debug("Inicio Parseo XML");
	}

	@Override
	public void endDocument() throws SAXException {
		LOGGER.debug("Final Parseo XML");
	}
	
	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
		if (qName.equalsIgnoreCase("game")) {
            juegos.add(juego);
        } else if (qName.equalsIgnoreCase("year")) {
            juego.setAnyo(data.toString().trim());
        } else if (qName.equalsIgnoreCase("dev")) {
            juego.setDesarrollador(data.toString().trim());
        }
       
        data.setLength(0);
    }

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if (qName.equalsIgnoreCase("game")) {
            juego = new Juego();
            juego.setTitulo(attributes.getValue("name"));
        }
		
	}
	
	@Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }


	@Override
	public void error(SAXParseException e) throws SAXException {
		LOGGER.error("Error parseo SAX");
	}

	
	/**
	 * Metodo escribirJuegosFichero recibe como parametros el fichero de texto plano en el cual se volcaran los datos recogidos del xml
	 * @param ficheroTxt
	 * @throws IOException
	 */
	public static void escribirJuegosFichero(File ficherotxt) throws IOException {
		
		LOGGER.debug("Inicio escritura .txt");
		
		FileWriter fichero = new FileWriter(ficherotxt);
		PrintWriter pt = new PrintWriter(fichero);
		
		for (int i = 0; i <= juegos.size() - 1 ; i++) {
			pt.write(juegos.get(i).toString() + "\n");
		}
		pt.flush();
		pt.close();
		
		LOGGER.debug("Final escritura .txt");
	}
	
	
}
