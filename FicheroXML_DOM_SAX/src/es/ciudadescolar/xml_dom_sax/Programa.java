package es.ciudadescolar.xml_dom_sax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Programa {
	
	private static File ficheroXml;
	private static File ficheroXsd;
	private static File ficheroTxt;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (args.length != 3) {
			System.err.println(
					"Invocación incorrecta. Se esperaban tres parámetros: [fichero xml] [fichero xsd] [fichero txt]");
			System.exit(0); // finaliza JVM con estado "normal"
		}

		ficheroXml = new File(args[0]);
		ficheroXsd = new File(args[1]);
		ficheroTxt = new File(args[2]);

		if (!validarFicheros(ficheroXml, ficheroXsd, ficheroTxt)) {
			System.err.println("Error durante la validación de ficheros Xml Xsd y Txt");
			System.exit(0);
		}

		if (!Xml.parseXmlDomWithSchema(ficheroXml, ficheroXsd)) {
			System.err.println("Error durante el parseo de fichero Xml validado contra Xsd");
			System.exit(0);
		}

		List<Item> items = getAllItems(Xml.getDocumentoXmlDom());

		if (!writeItemsToFile(items, ficheroTxt)) {
			System.err.println(
					"Error detectado durante el volcado de items al fichero [" + ficheroTxt.getAbsolutePath() + "]");
			System.exit(0);
		}
		

	}
	
	private static boolean writeItemsToFile (List<Item> listaItems, File ficheroSalida)
	{
		FileWriter ficheroEscribible = null;
		PrintWriter pt = null;
	    try 
		{
				ficheroEscribible = new FileWriter(ficheroSalida);
				pt = new PrintWriter(ficheroEscribible);
				
			    for (Item itemCve:listaItems)
			    	pt.println(itemCve);
		} 
		catch (IOException e) 	{	return false; 	}
		finally
		{
		    if (pt != null)
		    {	
		    	pt.flush();
		    	pt.close();
		    }
		}
		return true;
	}
	
	private static List<Item> getAllItems(Document doc)
	{
		List<Item> items = new ArrayList<Item>();
		
		Element cve = doc.getDocumentElement();
		NodeList nodosItem = cve.getChildNodes();  // lista de nodos "Item" bajo el nodo raiz cve
	    
	    //System.out.println("el numero de Items es :"+nodosItem.getLength());
	   
	    Item item = null;
	    	    
	    for (int i=0; i<nodosItem.getLength();i++)
	    {
	    	Element elementItem = (Element) nodosItem.item(i);
	    	
	    	item = new Item();
	    	item.setName(elementItem.getAttribute("name"));
	    	item.setSeq(elementItem.getAttribute("seq"));
	    	item.setType(elementItem.getAttribute("type"));
	    	item.setStatus(elementItem.getFirstChild().getTextContent());
	    	item.setDesc(elementItem.getElementsByTagName("desc").item(0).getTextContent()); // otra opción para recuperar info. Solo hay un nodo. 
	    	
	    	items.add(item);
	    }
	    return items;
	}
	
	private static boolean validarFicheros(File xml, File xsd, File txt) {
		if (!ficheroXml.exists() || !ficheroXml.isFile()) {
			System.err.println("El fichero XML no se ha localizado [" + ficheroXml.getAbsolutePath() + "]");
			return false;
		}

		if (!ficheroXsd.exists() || !ficheroXsd.isFile()) {
			System.err.println("El fichero Schema no se ha localizado [" + ficheroXsd.getAbsolutePath() + "]");
			return false;
		}

		// opcion 1 chequeo extensión de ficheros

		String extensionXml = String.valueOf(ficheroXml.getName().toCharArray(), ficheroXml.getName().lastIndexOf("."),
				ficheroXml.getName().length() - ficheroXml.getName().lastIndexOf("."));
		String extensionXsd = String.valueOf(ficheroXsd.getName().toCharArray(), ficheroXsd.getName().lastIndexOf("."),
				ficheroXsd.getName().length() - ficheroXsd.getName().lastIndexOf("."));
		String extensionTxt = String.valueOf(ficheroTxt.getName().toCharArray(), ficheroTxt.getName().lastIndexOf("."),
				ficheroTxt.getName().length() - ficheroTxt.getName().lastIndexOf("."));

		if (!extensionXml.toLowerCase().equals(".xml")) {
			System.out.println("El fichero xml NO tiene extensión xml [" + extensionXml + "]");
			return false;
		}
		if (!extensionXsd.toLowerCase().equals(".xsd")) {
			System.out.println("El fichero xsd NO tiene extensión xsd [" + extensionXsd + "]");
			return false;
		}

		if (!extensionTxt.toLowerCase().equals(".txt")) {
			System.out.println("El fichero txt NO tiene extensión txt [" + extensionTxt + "]");
			return false;
		}

		// opción 2 chequeo extensión de ficheros

		if (!ficheroXml.getName().toLowerCase().endsWith(".xml")) {
			System.out.println("El fichero xml NO tiene extensión [.xml]");
			return false;
		}
		if (!ficheroXsd.getName().toLowerCase().endsWith(".xsd")) {
			System.out.println("El fichero xsd NO tiene extensión [.xsd]");
			return false;
		}
		if (!ficheroTxt.getName().toLowerCase().endsWith(".txt")) {
			System.out.println("El fichero txt NO tiene extensión [.txt]");
			return false;
		}

		if (ficheroTxt.exists() && ficheroTxt.isFile()) {
			System.out.println("El fichero TXT se sobrescribirá [" + ficheroTxt.getAbsolutePath() + "]");
		}
		return true;
	}
	
}
