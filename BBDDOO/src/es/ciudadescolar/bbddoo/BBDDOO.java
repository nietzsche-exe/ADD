package es.ciudadescolar.bbddoo;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


/**
 * Clase BBDDOO que se encarga de gestionar la BD orientada a objetos db4o
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023/2024
 */
public class BBDDOO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BBDDOO.class);
	private ObjectContainer db;
	private File ficheroBD;
	
	
	/**
	 * Constructor sobrecargado para admitir como parametro el nombre de la BD
	 * Se creara si no exite para su posible uso
	 * @param nombreBaseDatos
	 */
	public BBDDOO(String nombreBaseDatos) {
		
		ficheroBD = new File(nombreBaseDatos);
		
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),nombreBaseDatos);
		LOGGER.debug("Se ha abierto o creado la BD " + nombreBaseDatos);
		
	}
	
	/**
	 * Metodo que cierra la base de datos
	 */
	public void cerrarBD() {
		
		db.close();
		LOGGER.debug("Se ha cerrado la BD");
		
	}
	
	/**
	 * Metodo para guardar Objetos Alumno en la BD pasados por parametros
	 * @param a
	 */
	public void almacenarAlumno(Alumno a) {
		
		db.store(a);
		LOGGER.debug("Se ha guardado el Alumno " + a.getNombre());
		
	}
	
	/**
	 * Metodo que devuelve todos los alumnos de la BD
	 * @return
	 */
	public List<Alumno> getAlumnos(){
		
		Alumno queryAlumno = new Alumno(null, null, 0);
		
		ObjectSet<Alumno> res = db.queryByExample(queryAlumno);
		
		LOGGER.debug("Se han recuperado todos los alumnos");
		
		return res;
	}
	
	/**
	 * Metodo que devuelve alumnos de la BD segun el expediente pasado por parametros
	 * @param exp
	 * @return
	 */
	public List<Alumno> getAlumnosExpediente(String exp){
		
		Alumno queryAlumno = new Alumno(exp, null, 0);
		
		ObjectSet<Alumno> res = db.queryByExample(queryAlumno);
		
		LOGGER.debug("Se han recuperado todos los alumnos con expediente " + exp);
		
		return res;
	}
	
	
}
