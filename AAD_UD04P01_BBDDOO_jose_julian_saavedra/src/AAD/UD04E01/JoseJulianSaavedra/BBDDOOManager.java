package AAD.UD04E01.JoseJulianSaavedra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class BBDDOOManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(BBDDOOManager.class);
	private ObjectContainer db;
	
	
	public BBDDOOManager(String nombreBaseDatos) {
		
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), nombreBaseDatos);
		LOGGER.debug("Se ha abierto o creado la base de datos: " + nombreBaseDatos);
		
	}
	
	public void cerrarBD() {
		
		db.close();
		LOGGER.debug("Se ha cerrado la base de datos");
		
	}
	
}
