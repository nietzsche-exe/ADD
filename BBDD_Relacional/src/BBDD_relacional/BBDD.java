package BBDD_relacional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class BBDD {

	private static final Logger LOGGER = LoggerFactory.getLogger(BBDD.class);
	
	private Connection conexion;
	
	
	
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
		
		ResultSet resAlumnos = st.executeQuery(SQL.CONSULTA_ALUMNOS);
		LOGGER.debug("Se ha ejecutado correctamente el statement: " + SQL.CONSULTA_ALUMNOS);
		
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
			LOGGER.warn("El statement no ha recuperado ningun registro de: " + SQL.CONSULTA_ALUMNOS);
		}
		
		resAlumnos.close();
		LOGGER.debug("Se ha cerrado el resultset");
		st.close();
		LOGGER.debug("Se ha cerrado el statement");
		
		return alumnos;
	}
	
	public void insertarAlumno(String exp, String nombre) {
		
		if(exp == null || nombre == null || exp.length() == 0 || nombre.length() == 0) {
			LOGGER.error("Los parametros para dar de alta a un alumno son incorrectos");
		}
		else {
			PreparedStatement ps = null;
			try {
				ps = conexion.prepareStatement(SQL.INSERTAR_ALUMNO);
				ps.setString(1, exp);
				ps.setString(2, nombre);
				if(ps.executeUpdate() == 1) {
					LOGGER.debug("Se ha dado de alta el alumno [" + exp + "|" + nombre +"]");
				}
				
			} catch (SQLException e) {
				LOGGER.error("Error durante la insercion del alumno [" + exp + "|" + nombre +"]");
				//throw e;
			}
			finally {
				if(ps != null) 
					try {
					ps.close();
					}
					catch(SQLException e){
						LOGGER.error("Error durante el cierrre del PreparedStatement");
					}
			}
		}
		
	}
	
	public Alumno getAlumnoSP(String exp) {
		
		Alumno alumno = null;
		
		if(exp == null || exp.length() == 0) {
			LOGGER.error("El parametro para consultar es incorrecto");
		}
		else {
			CallableStatement cs = null;
			ResultSet rs = null;
			try {
				cs = conexion.prepareCall(SQL.GET_ALUMNO_SP);
				cs.setString(1, exp);
				cs.execute();
				rs = cs.getResultSet();
				if(rs.next()) {
					alumno = new Alumno();
					alumno.setExpediente(rs.getString(1));
					alumno.setNombre(rs.getString(2));
				}
				
				
			} catch (SQLException e) {
				LOGGER.error("Error durante la invocacion del SP [" + SQL.GET_ALUMNO_SP + "]");
			}
			finally {
				if(rs != null) {
					try {
						rs.close();
					}
					catch(SQLException e) {
						LOGGER.error("Error en el cierre del ResultSet");
					}
				}
				if(cs != null) {
					try {
						cs.close();
					}
					catch(SQLException e) {
						LOGGER.error("Error en el cierre del CallableSatement");
					}
				}
			}
			
		}
		
		return alumno;
	}
	
	public Alumno getAlumnoFUN(String nombre) {
		Alumno alumno = null;
		
		if(nombre == null || nombre.length() == 0) {
			LOGGER.error("");
		}
		else {
			CallableStatement cs = null;
			
			try {
				cs = conexion.prepareCall(SQL.GET_ALUMNO_FUN);
				cs.registerOutParameter(1, Types.VARCHAR);
				cs.setString(2, nombre);
				cs.execute();
				
				String exp = cs.getString(1);
				if(!((exp == null || exp.length() == 0))) {
					alumno = new Alumno();
					alumno.setExpediente(exp);
					alumno.setNombre(nombre);
				}
				
			}
			catch(SQLException e){
				LOGGER.error("Error durante la invocacion de la FUN [" + SQL.GET_ALUMNO_FUN + "]");
			}
			finally {
				if(cs != null) {
					try {
						cs.close();
					}
					catch(SQLException e) {
						LOGGER.error("Error en el cierre del CallableSatement");
					}
				}
				
			}
		}
		
		return alumno; 
	}
	
	public void insertTransaccionAlumnos() {
		
		try {
			conexion.setAutoCommit(false);
			
			insertarAlumno("7", "Paco");
			insertarAlumno("8", "David");
			insertarAlumno("9", "Luisa");
			
			conexion.commit();
			
		} catch (SQLException e) {
			
			LOGGER.error("Error durante la insercion de Alumnos");
			
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				LOGGER.error("Error haciendo rollback durante el guardado de alumnos");
			}
			
		}
		finally {
			try {
				conexion.setAutoCommit(true);
			} catch (SQLException e) {
				LOGGER.error("Error restaurando autocommit a true ");
			}
		}
	}
	
}
