package AAD.UD04E01.JoseJulianSaavedra;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class XMLManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XMLManager.class);

	private static final String FILE_URL = "https://datos.madrid.es/egob/catalogo/200076-4-padron.xml";
	private static final String FILE_NAME = "padron_madrid_202310.xml";
	
	
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

	public static void parsearXML(File fichero) {

		LOGGER.debug("Inicio parseo XML");
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document documento = builder.parse(fichero);

			NodeList records = documento.getElementsByTagName("record");

			for (int i = 0; i < records.getLength(); i++) {
				Element recordElement = (Element) records.item(i);

				Distrito distrito = new Distrito();

				distrito.setCodigoDistrito(Integer.parseInt(getElementValue(recordElement, "COD_DISTRITO")));
				distrito.setDescripcionDistrito(getElementValue(recordElement, "DESC_DISTRITO"));
				
				int numeroHombresEspañoles = Integer.parseInt(getElementValue(recordElement, "ESPANOLESHOMBRES"));
				int numeroMujeresEspañolas = Integer.parseInt(getElementValue(recordElement, "ESPANOLESMUJERES"));
				int numeroHombresExtranjeros = Integer.parseInt(getElementValue(recordElement, "EXTRANJEROSHOMBRES"));
				int numeroMujeresExtranjeras = Integer.parseInt(getElementValue(recordElement, "EXTRANJEROSMUJERES"));
				
				distrito.setNumMujeres(numeroMujeresEspañolas + numeroMujeresExtranjeras);
				distrito.setNumHombres(numeroHombresEspañoles + numeroHombresExtranjeros);

				Distrito.getDistritos().add(distrito);
			}

		} catch (Exception e) {
			LOGGER.error("Error en el parseo del XML");
		}
		LOGGER.debug("Fin parseo XML");
	}

	private static String getElementValue(Element parentElement, String tagName) {

		NodeList nodeList = parentElement.getElementsByTagName(tagName);
		Element element = (Element) nodeList.item(0);
		return element.getTextContent().trim();
		
	}

}
