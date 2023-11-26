package AAD.UD04E01.JoseJulianSaavedra;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Principal {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);

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
		BD.almacenarEnDB(Distrito.getDistritos());
		
		BD.consultarDistrito();
	
		
		BD.borrarDatos();
		LOGGER.debug("Fin programa");
		
	}

}
