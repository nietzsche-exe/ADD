package AAD_UD03P01_JDBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase BBDD. En ella se gestiona la conexion con la base de datos coleccion_peliculas y los diferentes metodos para insertar y consultar Registros
 *  
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023-2024
 *
 */
public class BBDD {

	private static final Logger LOGGER = LoggerFactory.getLogger(BBDD.class);
	
	private Connection conexion;
	
	/**
	 * Constructor de la clase BBDD que gestiona la conexion con la base de datos a partir de un archivo properties
	 * 
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public BBDD() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		
		Properties propiedades = new Properties();
		propiedades.load(new FileInputStream(new File("BD_jose_julian_saavedra.properties")));
		
		Class.forName(propiedades.getProperty("driver"));
		LOGGER.debug("Se ha registrado correctamente el driver mysql");
		
		conexion = DriverManager.getConnection(propiedades.getProperty("url"), propiedades.getProperty("user"), propiedades.getProperty("password"));
		LOGGER.debug("Se ha establaecido correctamente la conexion con la BD coleccion_peliculas");
		
	}
	
	/**
	 * Metodo cerrarBD gestiona el cierre de la base de datos
	 * 
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 */
	public void cerrarBD() {
		try {
			conexion.close();
			LOGGER.debug("Se ha cerrado la BD");
		} catch (SQLException e) {
			LOGGER.error("Ha habido un error durante el cierre de la BD");
		}
	}
	
	/**
	 * Metodo insertarActor. Este metodo inserta registros de actores en la base de datos
	 * 
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 * @param nombre Recibe el nombre del actor
	 */
	public void insertarActor(String nombre) {
		
		if(nombre == null || nombre.length() == 0) {
			LOGGER.error("Los parametros para dar de alta a un actor son incorrectos");
		}
		else {
			PreparedStatement psInsertar = null;
			PreparedStatement psBuscar = null;
			try {
				
				psBuscar = conexion.prepareStatement(SQL.BUSCAR_ACTOR);
			    psBuscar.setString(1, nombre);
			    ResultSet rsActor = psBuscar.executeQuery();
				
			    if(rsActor.next()) {
			    	 LOGGER.warn("El actor [" + nombre + "] ya existe en la base de datos");
			    }
			    else {
			    	psInsertar = conexion.prepareStatement(SQL.INSERTAR_ACTOR);
			    	psInsertar.setString(1, nombre);
			    	if(psInsertar.executeUpdate() == 1) {
			    		LOGGER.debug("Se ha dado de alta el actor [" + nombre + "]");
			    	}
			    }
				
			} catch (SQLException e) {
				LOGGER.error("Error durante la insercion del actor [" + nombre + "]");
			
			}
			finally {
				if (psInsertar != null) {
					try {
						psInsertar.close();
					} catch (SQLException e) {
						LOGGER.error("Error durante el cierrre del PreparedStatement");
					}
				}
				if (psBuscar != null) {
					try {
						psBuscar.close();
					} catch (SQLException e) {
						LOGGER.error("Error durante el cierre del PreparedStatement");
					}
				}
			}
		}
		
	}
	
	/**
	 * Metodo insertarPelicula. Este metodo inserta registros de peliculas en la base de datos
	 * 
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 * @param titulo Recibe el titulo de la pelicula 
	 * @param año Recibe el año de la pelicula
	 */
	public void insertarPelicula(String titulo, int año) {

		if (titulo == null || titulo.length() == 0) {
			LOGGER.error("Los parametros para dar de alta a una pelicula son incorrectos");
		} else {
			PreparedStatement psInsetar = null;
			PreparedStatement psBuscar = null;
			try {

				psBuscar = conexion.prepareStatement(SQL.BUSCAR_PELICULA);
				psBuscar.setString(1, titulo);
				ResultSet rsActor = psBuscar.executeQuery();

				if (rsActor.next()) {
					LOGGER.warn("La pelicula [" + titulo + "] ya existe en la base de datos");
				} else {
					psInsetar = conexion.prepareStatement(SQL.INSERTAR_PELICULA);
					psInsetar.setString(1, titulo);
					psInsetar.setInt(2, año);
					if (psInsetar.executeUpdate() == 1) {
						LOGGER.debug("Se ha dado de alta la pelicula [" + titulo + "]");
					}
				}

			} catch (SQLException e) {
				LOGGER.error("Error durante la insercion de la pelicula [" + titulo + "]");

			} finally {
				if (psInsetar != null) {
					try {
						psInsetar.close();
					} catch (SQLException e) {
						LOGGER.error("Error durante el cierrre del PreparedStatement");
					}
				}
				if (psBuscar != null) {
					try {
						psBuscar.close();
					} catch (SQLException e) {
						LOGGER.error("Error durante el cierre del PreparedStatement");
					}
				}
			}
		}

	}
	
	/**
	 * Metodo insertarInterprete.
	 * 
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 * @param nombreActor Recibe nombre del actor
	 * @param tituloPelicula Recibe nombre de una pelicula
	 * @param esProtagonista Recibe un booleano 
	 */
	public void insertarInterprete(String nombreActor, String tituloPelicula, boolean esProtagonista) {
	    PreparedStatement psInsetar = null;
	    PreparedStatement psBuscarPelicula = null;
	    PreparedStatement psBuscarActor = null;

	    try {
	        conexion.setAutoCommit(false);
	    	
	        psBuscarActor = conexion.prepareStatement(SQL.BUSCAR_ACTOR);
	        psBuscarActor.setString(1, nombreActor);
	        ResultSet rsActor = psBuscarActor.executeQuery();

	        int codActor = -1;
	        if (rsActor.next()) {
	            codActor = rsActor.getInt("cod_act");
	        } else {
	            
	            LOGGER.error("No se encontró el actor: " + nombreActor);
	            return;
	        }

	       
	        psBuscarPelicula = conexion.prepareStatement(SQL.BUSCAR_PELICULA);
	        psBuscarPelicula.setString(1, tituloPelicula);
	        ResultSet rsPelicula = psBuscarPelicula.executeQuery();

	        int codPelicula = -1;
	        if (rsPelicula.next()) {
	            codPelicula = rsPelicula.getInt("cod_peli");
	        } else {
	           
	            LOGGER.error("No se encontró la película: " + tituloPelicula);
	            return;
	        }

	        
	        psInsetar = conexion.prepareStatement(SQL.INSERTAR_INTERPRETE);
	        psInsetar.setInt(1, codActor);
	        psInsetar.setInt(2, codPelicula);
	        psInsetar.setInt(3, esProtagonista ? 1 : 0);

	        if (psInsetar.executeUpdate() == 1) {
	        	conexion.commit();
	        	LOGGER.debug("Se ha dado de alta al intérprete [codActor=" + codActor + ", codPelicula=" + codPelicula + "]");
	        }


	    } catch (SQLException e) {
	        LOGGER.warn("Error durante la inserción del intérprete [nombreActor=" + nombreActor + "] Ya exite en la base de datos"  );
	        try {
				conexion.rollback();
			} catch (SQLException e1) {
				LOGGER.error("Error en el rollback");
			}
	    } finally {
	        try {
				conexion.setAutoCommit(true);
			} catch (SQLException e) {
				LOGGER.error("Error al asignar autoCommit a true");
			}
	    	
	    	if (psInsetar != null) {
	            try {
	                psInsetar.close();
	            } catch (SQLException e) {
	                LOGGER.error("Error durante el cierre del PreparedStatement");
	            }
	        }
	        if (psBuscarActor != null) {
	            try {
	                psBuscarActor.close();
	            } catch (SQLException e) {
	                LOGGER.error("Error durante el cierre del PreparedStatement");
	            }
	        }
	        if (psBuscarPelicula != null) {
	            try {
	                psBuscarPelicula.close();
	            } catch (SQLException e) {
	                LOGGER.error("Error durante el cierre del PreparedStatement");
	            }
	        }
	    }
	}
	
	/**
	 * Metodo buscarPeliculaPorActor. Este metodo se encarga de buscar todas las pelicuas en las que trabaje un actor en concreto
	 * 
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 * @param nombreActor Recibe el nombre de un actor
	 * @return List<String> devuelve una lista de peliculas
	 * @throws SQLException
	 */
	public List<String> buscarPeliculasPorActor(String nombreActor) throws SQLException {
        List<String> peliculasActor = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(SQL.BUSCAR_PELICULAS_POR_ACTOR)) {
            ps.setString(1, nombreActor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String tituloPelicula = rs.getString("titulo");
                    peliculasActor.add(tituloPelicula);
                }
            }
        }

        return peliculasActor;
    }
	
	/**
	 * Metodo regsitrarNumeroActoresPorPelicula. Este metodo hace llamada a una funcion propia de la base de datos.
	 * 
	 * @param tituloPelicula Recibe el titulo de una pelicula
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 * @throws SQLException
	 */
	public void registrarNumeroActoresPorPelicula(String tituloPelicula) throws SQLException {
		CallableStatement cs = null;
		
		try {
        	cs = conexion.prepareCall(SQL.NUMERO_ACTORES_POR_PELICULAS);
            cs.registerOutParameter(1, Types.TINYINT);
        	cs.setString(2, tituloPelicula);
            
            cs.execute();

            int numActores = cs.getInt(1);

            LOGGER.info("Número de actores en la película '" + tituloPelicula + "': " + numActores);  
           
        }
		catch(SQLException e){
			LOGGER.error("Error durante la invocacion de la FUN [" + SQL.BUSCAR_PELICULAS_POR_ACTOR + "]");
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
	
}
