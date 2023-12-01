package BBDD_relacional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BBDD {

	private static final Logger LOGGER = LoggerFactory.getLogger(BBDD.class);
	
	private Connection conexion;
	
	private static final String CONSULTA_ALUMNOS = "SELECT A.expediente, A.nombre FROM alumnos A";
	
	public BBDD() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		
		Properties propiedades = new Properties();
		propiedades.load(new FileInputStream(new File("bbdd_ciudadescolar.properties")));
		
		Class.forName(propiedades.getProperty("driver"));
		LOGGER.debug("Se ha registrado correctamente el driver mysql");
		
		conexion = DriverManager.getConnection(propiedades.getProperty("url"), propiedades.getProperty("user"), propiedades.getProperty("password"));
		LOGGER.debug("Se ha establaecido correctamente la conexion con la BD ciudadescolar");
		
	}
	
	public void cerrarBD() {
		try {
			conexion.close();
			LOGGER.debug("Se ha cerrado la BD");
		} catch (SQLException e) {
			LOGGER.error("Ha habido un error durante el cierre de la BD");
		}
	}
	
	public List<Alumno> getTodosAlumnos() throws SQLException{
		
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		Statement st = conexion.createStatement();
		LOGGER.debug("Se ha creado correctamente el statement");
		
		ResultSet resAlumnos = st.executeQuery(CONSULTA_ALUMNOS);
		LOGGER.debug("Se ha ejecutado correctamente el statement: " + CONSULTA_ALUMNOS);
		
		if(resAlumnos.next()) {
			do {
				
				Alumno alumno = new Alumno();
				
				//Opcion 1: eficiente y rapida
				alumno.setExpediente(resAlumnos.getString(1));
				alumno.setNombre(resAlumnos.getString(2));
				
				//Opcion 2: intuitiva
				//alumno.setExpediente(resAlumnos.getString("expediente"));
				//alumno.setNombre(resAlumnos.getString("nombre"));
				
				alumnos.add(alumno);
				
			}while(resAlumnos.next());
		}
		else {
			LOGGER.warn("El statement no ha recuperado ningun registro de: " + CONSULTA_ALUMNOS);
		}
		
		resAlumnos.close();
		LOGGER.debug("Se ha cerrado el resultset");
		st.close();
		LOGGER.debug("Se ha cerrado el statement");
		
		return alumnos;
	}
	
}
