package AAD_EX02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class BBDD implements RequisitosExamen {

	private static final Logger LOGGER = LoggerFactory.getLogger(BBDD.class);
	private Connection conexion;
	private String dataBaseName;
	PreparedStatement insertCiudad, insertPais, insertIdioma;
	
	
	public BBDD() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		
		Properties fichProperties = new Properties();
		fichProperties.load(new FileInputStream("BD_JoseJulian_Saavedra.properties"));
		dataBaseName = fichProperties.getProperty("url");
		Class.forName(fichProperties.getProperty("driver"));
		LOGGER.debug("Se han cargado correctamente los Drivers");
		
		conexion = DriverManager.getConnection(fichProperties.getProperty("url"), fichProperties.getProperty("user"), fichProperties.getProperty("password"));
		LOGGER.debug("Se ha conectado correctamente con la base de datos");
		
		insertPais = conexion.prepareStatement("INSERT INTO country (Code, name, Continent, Population, headofstate, Capital) values(?,?,?,?,?,?)");
		LOGGER.debug("Se ha creado correctamente el PreparedStament para insertar paises");
		
		insertCiudad = conexion.prepareStatement("INSERT INTO city (name, CountryCode, district, Population) values(?,?,?,?)");
		LOGGER.debug("Se ha creado correctamente el PreparedStament para insertar ciudades");
		
		insertIdioma = conexion.prepareStatement("INSERT INTO countrylanguage (CountryCode, language, IsOfficial, Percentage) values(?,?,?,?)");
		LOGGER.debug("Se ha creado correctamente el PreparedStament para insertar idiomas");
	
	}
	
	public void cierreBD() {
		
		try {
			
			insertIdioma.close();
			LOGGER.debug("Se ha cerrado correctamente el PreparedStament para insertar idiomas");
			
			insertCiudad.close();
			LOGGER.debug("Se ha cerrado correctamente el PreparedStament para insertar ciudades");
			
			insertPais.close();
			LOGGER.debug("Se ha cerrado correctamente el PreparedStament para insertar paises");
			
			conexion.close();
			LOGGER.debug("Se ha cerrado correctamente la base de datos: " + dataBaseName);
			
		} catch (SQLException e) {
			LOGGER.error("Error durante el cierre de la base de datos: " + dataBaseName);
		}
		
	}
	
	@Override
	public String getCapitalPais(String nombrePais) {
		
		CallableStatement cs = null;
		String capital = null;
		
		try {
			
			cs = conexion.prepareCall("{ ? = call ObetenerCapitalPais(?)}");
			cs.registerOutParameter(1, Types.CHAR);
			cs.setString(2, nombrePais);
			cs.execute();
			
			capital = cs.getString(1);
			
			
		} catch (SQLException e) {
			LOGGER.debug("Error durante la llamada al CallableStatement");
		}
		finally {
			
			try {
				cs.close();
				LOGGER.debug("Se ha cerrado correctamente el CallableStatement");
			} catch (SQLException e) {
				LOGGER.error("Error al cerrar el CallableStatement");
			}
			
		}
		
		
		return capital;
	}

	@Override
	public Boolean insertarCiudadPaisIdioma(Ciudad ciudad, Pais pais, IdiomaPais idioma) {
		
		if(ciudad == null || pais == null || idioma == null) {
			LOGGER.warn("Faltan datos");
		}
		else {
			
			try {
				
				String codigoPais = new String(pais.getCodigo());
				
				conexion.setAutoCommit(false);
				
				insertPais.clearParameters();
				insertPais.setString(1, codigoPais);
				insertPais.setString(2, pais.getNombre());
				insertPais.setString(3, pais.getContinente());
				insertPais.setInt(4, pais.getPoblacion().intValue());
				insertPais.setString(5, pais.getJefeDeEstado());
				insertPais.setInt(6, pais.getCapital().intValue());
				
				if(insertPais.executeUpdate() == 1) {
					LOGGER.debug("Se ha dado de alta correctamente el pais [" + pais.getNombre() +"]");
				}
				
				insertCiudad.clearParameters();
				insertCiudad.setString(1, ciudad.getNombre());
				insertCiudad.setString(2, codigoPais);
				insertCiudad.setString(3, ciudad.getDistrito());
				insertCiudad.setInt(4, ciudad.getPoblacion().intValue());
				
				if(insertCiudad.executeUpdate() == 1) {
					LOGGER.debug("Se ha dado de alta correctamente la ciudad [" + ciudad.getNombre() + "]");
				}
				
				insertIdioma.clearParameters();
				insertIdioma.setString(1, codigoPais);
				insertIdioma.setString(2, idioma.getIdioma());
				insertIdioma.setBoolean(3, false);
				insertIdioma.setFloat(4, idioma.getPorcentajeUso());
				
				if(insertIdioma.executeUpdate() == 1) {
					LOGGER.debug("Se ha dado de alta correctamente el idioma [" + idioma.getIdioma() + "]");
				}
				
				
				
			} catch (SQLException e) {
				try {
					conexion.rollback();
					LOGGER.error("Ha habido un error SQL y se ha hecho rollback");
				} catch (SQLException e1) {
					LOGGER.error("No se ha podido hacer rollback");
				}
			}
			
			finally {
				
				try {
					conexion.setAutoCommit(true);
				} catch (SQLException e) {
					LOGGER.error("No se ha podido restaurar el autoCommit a true");
				}
				
			}
			
		}
		
		
		
		return null;
	}

	@Override
	public void borrarCiudad(String nomCiudad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CambiarJefeEstadoDePais(String nomPais, String nomJefeDeEstado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pais> getPaisPorIdioma(String idioma) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
