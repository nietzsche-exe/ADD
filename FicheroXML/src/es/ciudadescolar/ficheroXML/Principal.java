package es.ciudadescolar.ficheroXML;


import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class Principal {

	public static void main(String[] args) {
		
		List<Alumno> alumnos = null;
		File ficheroXml = new File("alumnos.xml");
		
		try {
			alumnos = analizarDOM(ficheroXml);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Alumno a1:alumnos) {
			System.out.println(a1);
		}
		
		// INVOCACIÓN GENERAR XML DOM
		try {
			generarxmldom(alumnos);
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (TransformerException e) {
			
			e.printStackTrace();
		}
    }
	// MÉTODO PARA ANALIZAR
	private static List<Alumno> analizarDOM (File file) throws ParserConfigurationException, SAXException, IOException {
		
		List<Alumno> alumnos = new ArrayList<Alumno>();
		
		Alumno a1 = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		//dbf.setValidating(true); // se valida con el dtd
		//dbf.setIgnoringElementContentWhitespace(true); // eliminar espacios en blanco
		
		dbf.setValidating(false);
		
		File ficheroXsd = new File("alumnos.xsd");
		
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(ficheroXsd);
		
		dbf.setSchema(schema);
		dbf.setNamespaceAware(true);
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		
	
		
		
		Document document = db.parse(file); // sera una estructura en memoria que representa el documento xml
		
		Element raiz = document.getDocumentElement(); // recuperar el elemento raiz que es alumnos		
		NodeList nodosalumnos = raiz.getChildNodes(); // lista de nodos "Alumno" bajo el nodo raíz
		
		for(int i=0; i<nodosalumnos.getLength(); i++) {
			Element elementalumno = (Element) nodosalumnos.item(i);
			
			 a1 = new Alumno();
			a1.setExpediente(elementalumno.getFirstChild().getNodeValue());
			a1.setNombre(elementalumno.getFirstChild().getNextSibling().getTextContent());
			a1.setEdad(Integer.parseInt(elementalumno.getLastChild().getTextContent()));
			
			alumnos.add(a1);
			
			/*
			// EXPEDIENTE
			// OPCIÓN 1
			System.out.println("expediente: " + elementalumno.getFirstChild().getNodeValue());
			
			// OPCIÓN 2
			// System.out.println("expediente: " + elementalumno.getFirstChild().getTextContent());
			
			// NOMBRE
			// OPCIÓN 1
			System.out.println("nombre: " + elementalumno.getFirstChild().getNextSibling().getNodeValue());
			
			// OPCION 2
			System.out.println("nombre: " + elementalumno.getFirstChild().getNextSibling().getTextContent());
			
			// EDAD
			// OPCIÓN 1
			System.out.println("edad: " + elementalumno.getLastChild().getFirstChild().getNodeValue());
			
			// OPCION 2
			System.out.println("edad: " + elementalumno.getLastChild().getTextContent());
			
			// OPCION 3
			System.out.println("edad: " + elementalumno.getLastChild().getPreviousSibling().getFirstChild().getNodeValue());
			*/
		}
		
		return alumnos;
	}
	
	// GENERAR XML DOM
	public static void generarxmldom(List<Alumno> alumnos) throws ParserConfigurationException, IOException, TransformerException {
		
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        
        Element raiz = document.createElement("estudiantes");
        document.appendChild(raiz);
        
        //Opcion 2 para validar con xsd
        raiz.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        
        for(Alumno a1:alumnos) {
        	Element alumno = document.createElement("alumno");
        	raiz.appendChild(alumno);
        	
        	/*
        	// OTRA OPCIÓN PARA AÑADIR ELEMENTOS (OPCIÓN 1)
        	// EXPEDIENTE
        	Attr atributoExpediente = document.createAttribute("exp");
        	atributoExpediente.setValue(a1.getExpediente());
        	alumno.setAttributeNode(atributoExpediente);
        	
        	// NOMBRE
        	Attr atributoNombre = document.createAttribute("nom");
        	atributoNombre.setValue(a1.getNombre());
        	alumno.setAttributeNode(atributoNombre);
        	
        	// EDAD
        	Attr atributoEdad = document.createAttribute("edad");
        	atributoEdad.setValue(String.valueOf(a1.getEdad()));
        	alumno.setAttributeNode(atributoEdad);*/
        	
        	// OPCION 2 PARA AÑADIR ATRIBUTOS (OPCIÓN CORTA)
        	alumno.setAttribute("exp", a1.getExpediente());
        	alumno.setAttribute("nom", a1.getNombre());
        	alumno.setAttribute("edad", String.valueOf(a1.getEdad()));
        	
        	
        	Element edadALumno = document.createElement("edad");
        	alumno.appendChild(edadALumno);
        	Text textoEdad = document.createTextNode(String.valueOf(a1.getEdad()));
        	edadALumno.appendChild(textoEdad);
        }
        
        // HASTA AQUÍ HEMOS CREQADO LA ESTRUCTURA ASBORESCENTE (DOM) EN MEMORIA PPAL
        
        // AHORA TOCA VOLCARLA AL FICHERO
        
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t =  tf.newTransformer();
        DOMSource source = new DOMSource (document);
		
        
        StreamResult streamResult = null;
        
        // OPCION 1: FLUJO BINARIO
        // streamResult = new StreamResult(new FileOutputStream("alumnos.xml"));
        
        // OPCIÓN 2: FLUJO DE TEXTO
        streamResult = new StreamResult(new FileWriter(new File("alumnos.xml")));
        
        // OPCIÓN 3: FLUJO DE SALIDA ESTANDAR
        //streamResult = new StreamResult(System.out);
        
        //Opcion para validar con dtd
        /*
        DOMImplementation domImp = document.getImplementation();
        DocumentType doctype = domImp.createDocumentType("doctype", null, "alumnos2.dtd");
        t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
        */
        
        
        
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.setOutputProperty(OutputKeys.VERSION, "1.0");
        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        
        t.transform(source, streamResult);
	}

}
