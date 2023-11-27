package AAD.UD04E01.JoseJulianSaavedra;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase Principal del programa que ejecuta la logica de la app
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023-2024
 */
public class Principal {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);

	/**
	 * Metodo main
	 * Aqui se lleva acabo la ejecuccion de los metodos principales
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 */
	public static void main(String[] args) {
		
		LOGGER.debug("Inicio programa");
		
		File xml = new File("padron_madrid_202310.xml");
        if (!xml.exists()) {
            LOGGER.debug("Descargando XML...");
            XMLManager.descargarXML();
        } else {
            LOGGER.debug("El XML ya existe. No es necesario descargarlo.");
        }
        
		XMLManager.parsearXML(xml);
		
		BBDDOOManager BD = new BBDDOOManager();
		BD.almacenarEnDB(XMLManager.distritos);
		
		BD.consultarDistrito();
	
		
		BD.borrarDatos();
		LOGGER.debug("Fin programa");
		
	}

}
