package es.ciudadescolar.xml_dom_sax;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml {
	
	private static Document documentoXmlDom;

	public static boolean parseXmlDomWithSchema(File xml, File xsd) {
		DocumentBuilderFactory dbf;
		DocumentBuilder db;
		SchemaFactory sf;
		Schema schema;

		dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true); // eliminar todos los espacios en blanco

		// opción validar con schema
		dbf.setValidating(false); // NO se valida contra dtd
		sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		try {
			schema = sf.newSchema(xsd);
			dbf.setSchema(schema); // debe validar el xml contra el esquema proporcionado
			dbf.setNamespaceAware(true);
			db = dbf.newDocumentBuilder();
			documentoXmlDom = db.parse(xml);

		} catch (SAXException | ParserConfigurationException | IOException e) {
			System.err.println("Error durante el parseo DOM del fichero xml");
			return false;
		}
		return true;
	}
	
	public static boolean parseXmlDomWithDtd(File xml) {
		DocumentBuilderFactory dbf;
		DocumentBuilder db;

		dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true); // eliminar todos los espacios en blanco

		// opción validar con DTD
		dbf.setValidating(true); // se valida contra dtd

		dbf.setNamespaceAware(true);
		try {
			db = dbf.newDocumentBuilder();
			documentoXmlDom = db.parse(xml);
		} catch (SAXException | ParserConfigurationException | IOException e) {
			System.err.println("Error durante el parseo DOM del fichero xml");
			return false;
		}
		return true;

	}

	public static Document getDocumentoXmlDom() {
		return documentoXmlDom;
	}
	
	
	
}
