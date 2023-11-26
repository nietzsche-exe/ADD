package ADD_EX01E02;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;



public class BBDDOO {

	private static final Logger LOGGER = LoggerFactory.getLogger(BBDDOO.class);
	private ObjectContainer db;
	private File ficheroBD;
	
	public BBDDOO(String nombreBaseDatos) {
		
		ficheroBD = new File(nombreBaseDatos);
		
		if(ficheroBD.exists()) {
			db.delete(nombreBaseDatos);
			LOGGER.debug("Se ha borrado la base de datos");
		}
		
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),nombreBaseDatos);
		LOGGER.debug("Se ha creado o abierto la BD");
		
	}
	
	public static void guardarGenero(Genero g) {
		
		
		
	}
	
	public void comitDB(){
		
		db.commit();
		LOGGER.debug("commit de la BD");
		
	}
	public void rollbackBD(){
		
		db.rollback();
		LOGGER.debug("rollback de la BD");
		
	}
	
	public void cerrarBD() {
		
		db.close();
		LOGGER.debug("cerrada la BD");
		
	}
	
}
