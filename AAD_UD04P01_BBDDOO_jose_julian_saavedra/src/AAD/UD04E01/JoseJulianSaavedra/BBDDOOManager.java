package AAD.UD04E01.JoseJulianSaavedra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class BBDDOOManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(BBDDOOManager.class);
	private ObjectContainer db;
	private String nombreBaseDatos = "JoseJulian_Saavedra.db4o";
	
	public BBDDOOManager() {
		
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), nombreBaseDatos);
		LOGGER.debug("Se ha abierto o creado la base de datos: " + nombreBaseDatos);
		
	}
	
	public void almacenarEnDB(List<Distrito> distritos) {
       
		try {
            for (Distrito distrito : distritos) {
                
                	db.store(distrito);
            }
    
		}
		catch(Exception e) {
			db.rollback();
			LOGGER.error("Ha habido un error en la subida datos a la base de datos: " + nombreBaseDatos + " Por lo tanto se ha llevado acabo un rollback");
		}
		finally {
			 
			 db.commit();
			 db.close();
			 LOGGER.debug("Distritos guardados exitosamente en la base de datos: " + nombreBaseDatos);
			 LOGGER.debug("Base de datos " + nombreBaseDatos + " cerrada");
		}
		 
	}	
	
	public void consultarDistrito() {
       
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), nombreBaseDatos);
        
        try {
          
        	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingrese el código de distrito para consultar: ");
            int codigoDistrito = Integer.parseInt(reader.readLine());
        	
            Query query = db.query();
            query.constrain(Distrito.class);
            query.descend("codigoDistrito").constrain(codigoDistrito);
            ObjectSet<Distrito> result = query.execute();
       
            if (result.hasNext()) {
                Distrito distrito = result.next();
                System.out.println("Código: " + distrito.getCodigoDistrito());
                System.out.println("Descripción: " + distrito.getDescripcionDistrito());
                System.out.println("Número de mujeres: " + distrito.getNumMujeres());
                System.out.println("Número de hombres: " + distrito.getNumHombres());
            } else {
            	LOGGER.warn("Distrito no encontrado");
            }
            
        } 
        catch (NumberFormatException e) {
        	LOGGER.error("Error de formato numerico");
        } catch (IOException e) {
			LOGGER.error("Error de E/S");
		}
        finally {
            db.close();
            LOGGER.debug("Base de datos " + nombreBaseDatos + " cerrada");
        }
        
    }
	
	 public void borrarDatos() {
	        
		 	db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), nombreBaseDatos);
		 
	        ObjectSet<Distrito> result = db.queryByExample(Distrito.class);
	        while (result.hasNext()) {
	            db.delete(result.next());
	        }
	        
	        db.close();
	        LOGGER.debug("Datos eliminados");
	    }

	
}
