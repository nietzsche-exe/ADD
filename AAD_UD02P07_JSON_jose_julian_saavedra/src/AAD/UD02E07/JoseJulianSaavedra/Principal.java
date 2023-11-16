package AAD.UD02E07.JoseJulianSaavedra;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * Clase principal del programa que maneja la logica de la aplicacion. Tiene un metodo main donde se ejecutan los metodos principales.
 * Permite leer un fichero json para generar uno nuevo con la informacion especifica de un Equipo durante la temporada 18-19
 * @author Jose Julian Saavedra  
 * @version 1.0
 * @since curso 2023-2024
 */
public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);
	
	private static String equipo;
	private static File json;
	
	/**
	 * El método estatico getEquipo permite recuperar la variable equipo para usarse en otras clases 
	 * @return String equipo Nombre del equipo pedido al usuario en el main
	 */
	public static String getEquipo() {
			return equipo;
	}

	/**
	 * El método estatico getJson permite recuperar la variable json para usarse en otras clases 
	 * @return File json Fichero json pasado por parametos args
	 */
	public static File getJson() {
		return json;
	}
	
	/**
	* El método estático main permite la ejecución de la aplicación 
	* @author Jose Julian Saavedra
	* @version 1.0
	* @since curso 2023-2024
	* @param String [] args Espera 2 parámetros correspondientes al fichero json y xml
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LOGGER.debug("Inicio main");
		
		try {
			
			Scanner input = new Scanner(System.in);
			File xml = new File(args[0]);
			json = new File(args[1]);
			
			System.out.println("Escribe el nombre del equipo que quieres analizar: ");
			equipo = input.nextLine();
			input.close();
			
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XmlManager defaultHandler = new XmlManager();
			sp.parse(xml, defaultHandler);
			
			
			LOGGER.debug("Ejeccucion de metodos principales");
			
			JsonManager.leerFicheroJson(json);
			JsonManager.crearEquipoJson(equipo);
			JsonManager.escribirJSON();
			
		}
		catch(JSONException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			System.err.println("error parser");
		} catch (SAXException e) {
			System.err.println("error saxe");
		} catch (IOException e) {
			System.err.println("Parametros introducidos incorrectos");
		} catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Numero de parametros encontrados " + "[" + args.length +"]" + ", se esperan [2]");
		} 
		
		LOGGER.debug("Fin main");
		
	}
	
}
