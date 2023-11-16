package AAD.UD02E06.JoseJulianSaavedra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml {

	
	// Metodo que recoge los datos del xml
	public static void analizarDOM(String xml) throws ParserConfigurationException, SAXException, IOException {

		// Variables principales
		File fichero = new File(xml);
		Coche coche = null;

		// Inicializacion del documento mediante DOM
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(true);
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(fichero);

		// Se recoge el elemento raiz
		Element concesionario = document.getDocumentElement();
		
		// Se recoge la lista de nodos para reiterar en ella con la finalidad de sacar el resto de datos de los hijos
		NodeList nodoscoches = concesionario.getChildNodes();
		for (int i = 0; i < nodoscoches.getLength(); i++) {
			Element elementoCoche = (Element) nodoscoches.item(i);

			coche = new Coche();
			coche.setId(Integer.parseInt(elementoCoche.getAttribute("id")));
			coche.setMarca(elementoCoche.getFirstChild().getTextContent());
			coche.setModelo(elementoCoche.getFirstChild().getNextSibling().getTextContent());
			coche.setPrecio(Float.parseFloat(elementoCoche.getFirstChild().getNextSibling().getNextSibling().getTextContent()));

			//Una vez creados los objetos Coche estos son almacenados en la lista
			Coche.listaCoches.add(coche);
		}

	}
	
	//Metodo para escribir los objetos de la lista en un fichero de texto
	public static void escribirCochesFichero(File ficheroTxt) throws IOException {

		//Variables Principales
		FileWriter fichero = new FileWriter(ficheroTxt);
		PrintWriter pt = new PrintWriter(fichero);

		//Escritura de los objetos
		for (int i = 0; i <= Coche.listaCoches.size() - 1; i++) {
			pt.write(Coche.listaCoches.get(i).toString() + "\n");
		}
		
		//Vaciado y cierre
		pt.flush();
		pt.close();
		
		System.out.println("Fichero .txt creado y guardado con exito en " + ficheroTxt);

	}

	//Metodo para crear un documento DOM y volcar los datos de la lista en un fichero Xml nuevo
	public static void escribirXml() throws ParserConfigurationException, IOException, TransformerException {

		// Inicializacion del documento mediante DOM
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		//Se crea el elemento raiz
		Element coches = document.createElement("coches");
		document.appendChild(coches);

		//Se reitera en la lista para asi sacar los datos de todos los objetos
		for (Coche coche : Coche.listaCoches) {

			//Se crean los elementos y atributos correspondientes siguiendo la logica del documento DTD proporcionado
			Element cocheElement = document.createElement("coche");
			cocheElement.setAttribute("marca", coche.getMarca());
			cocheElement.setAttribute("modelo", coche.getModelo());
			cocheElement.setAttribute("precio", String.valueOf(coche.getPrecio()));

			Element id = document.createElement("id");
			id.appendChild(document.createTextNode(String.valueOf(coche.getId())));

			cocheElement.appendChild(id);
			coches.appendChild(cocheElement);

		}

		//Se Inicializa la creacion del documento Xml donde se volcaran los datos
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new FileWriter("coches.xml"));
		transformer.transform(source, result);
		
		//Mensaje de correcta finalizacion
		System.out.println("Documento .xml creado y guardado en coches.xml");

	}

}
