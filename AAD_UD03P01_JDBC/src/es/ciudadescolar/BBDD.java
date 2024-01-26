package es.ciudadescolar;

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
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BBDD 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(BBDD.class);
	private Connection conexion;
	private String databaseName;
	PreparedStatement pstPeli, pstActor,pstInterpretes;
	PreparedStatement pstCodPeli, pstCodActor;
	
	/**
	 * Este constructor establece conexión con la BD ciudadescolar en un SGBD MySQL
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public BBDD() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException
	{
		Properties fichProperties = new Properties();
		
		fichProperties.load(new FileInputStream(new File("BD_jose_sala.properties")));
		
		Class.forName(fichProperties.getProperty("driver"));
		//Class.forName("com.mysql.cj.jdbc.Driver");
		LOGGER.debug("Se ha registrado correctamente el driver MySQL");
		
		conexion=DriverManager.getConnection(fichProperties.getProperty("url"),fichProperties.getProperty("user"),fichProperties.getProperty("password"));
		//conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/ciudadescolar","root","root");
		databaseName = fichProperties.getProperty("url");
		LOGGER.debug("Se ha establecido correctamente la conexión contra la BD "+databaseName );
		
		pstPeli = conexion.prepareStatement(SQL.insertarPelicula);
		LOGGER.debug(" Sentencia SQL insercion peliculas creada");
		
		pstActor = conexion.prepareStatement(SQL.insertarActor);
		LOGGER.debug(" Sentencia SQL insercion actores creada");
		
		pstInterpretes = conexion.prepareStatement(SQL.insertarInterprete);
		LOGGER.debug(" Sentencia SQL insercion interpretes creada");
		
		pstCodPeli = conexion.prepareStatement(SQL.consultaCodigoPelicula);
		LOGGER.debug(" Sentencia SQL consulta codigo de peli creada");
		
		pstCodActor = conexion.prepareStatement(SQL.consultaCodigoActor);
		LOGGER.debug(" Sentencia SQL consulta codigo de actor creada");
		
	}
	
	public void cierre()
	{
		try 
		{
			LOGGER.debug(" Cerrando definitivamente el PreparedStatement alta de peliculas ");
			this.pstPeli.close();
			LOGGER.debug(" Cerrando definitivamente el PreparedStatement alta de actores ");
			this.pstActor.close();
			LOGGER.debug(" Cerrando definitivamente el PreparedStatement alta de interpretes ");
			this.pstInterpretes.close();
			
			LOGGER.debug(" Cerrando definitivamente el PreparedStatement consulta de codigo pelicula");
			this.pstCodPeli.close();
			
			LOGGER.debug(" Cerrando definitivamente el PreparedStatement consulta de codigo actor");
			this.pstCodActor.close();
			
			conexion.close();
			LOGGER.debug("Se ha cerrado correctamente la conexión contra la BD "+ databaseName);
		} 
		catch (SQLException e) 
		{
			LOGGER.error("Ha habido un error durante el cierre de statements y conexión");
		}
	}

	public void insertarPelicula(Pelicula peli)
	{
		if (peli == null )
		{
			LOGGER.error("La pelicula a dar de alta está sin inicializar");
		}
		else
		{
			try 
			{
				int codPeli=-1;
				int codActor=-1;
				conexion.setAutoCommit(false);
			
				pstPeli.clearParameters();
				pstPeli.setString(1, peli.getTitulo());
				pstPeli.setInt(2, peli.getAnio().intValue());
				if(pstPeli.executeUpdate() == 1)
				{
					LOGGER.trace("Se ha dado de alta correctamente la película ["+ peli.getTitulo()+"]");
				}
				pstCodPeli.clearParameters();
				pstCodPeli.setString(1, peli.getTitulo());
				ResultSet rstCodigosPeliculas = pstCodPeli.executeQuery();
				
				if (rstCodigosPeliculas.next())
					codPeli = rstCodigosPeliculas.getInt(1);
				
				for (String actorName:peli.getActores().keySet())
				{
					pstActor.clearParameters();
					pstActor.setString(1, actorName);
					if(pstActor.executeUpdate() == 1)
					{
						LOGGER.trace("Se ha dado de alta correctamente el actor ["+ actorName +"] en la pelicula ["+ peli.getTitulo()+"]");
					}
					
					pstCodActor.clearParameters();
					pstCodActor.setString(1, actorName);
					ResultSet rstCodigosActores = pstCodActor.executeQuery();
					if (rstCodigosActores.next())
						codActor = rstCodigosActores.getInt(1);
					
					pstInterpretes.clearParameters();
					
						pstInterpretes.setInt(1,codPeli); // recupero el código de peli
						pstInterpretes.setInt(2,codActor); // recupero el código de  actor
						 
						pstInterpretes.setBoolean(3, peli.getActores().get(actorName));
						if(pstInterpretes.executeUpdate() == 1)
						{
							LOGGER.trace("Se ha dado de alta correctamente un interprete en la pelicula ["+ peli.getTitulo()+"]");
						}
					rstCodigosActores.close();
				}
				
				rstCodigosPeliculas.close();
								
				conexion.commit();
				
				
			} catch (SQLException e) 
			{
				LOGGER.error("Error durante la inserccion transaccional de la pelicula ["+ peli.getTitulo()+"]");
				try 
				{
					conexion.rollback();
					LOGGER.warn("Haciendo rollback de la transaccion para la pelicula ["+ peli.getTitulo()+"]");
					
				} catch (SQLException e1) 
				{
					LOGGER.error("Error durante el rollback de la transacción de la pelicula  ["+ peli.getTitulo()+"]");
						
				}
			}
			finally
			{
				try 
				{
					conexion.setAutoCommit(true);
				} 
				catch (SQLException e) 
				{
					LOGGER.error("Error restaurando el autocommit en la BD");
				}
			}
		}
	}

	//buscar pelicula por actor
	public List<Pelicula> buscarPeliculasPorActor(String nomActor) 
	{
		List<Pelicula> pelis = null;
		PreparedStatement pstPelisActor = null;
		ResultSet rstPeliculas =null;
		try 
		{
			pstPelisActor = conexion.prepareStatement(SQL.consultaPeliculasPorActor);
			pstPelisActor.setString(1, nomActor);
			rstPeliculas = pstPelisActor.executeQuery();
			if (rstPeliculas.next())
			{
				pelis = new ArrayList<Pelicula>();
				do
				{
					Pelicula peli = new Pelicula();
					peli.setTitulo(rstPeliculas.getString(1));
					peli.setAnio(rstPeliculas.getInt(2));
					pelis.add(peli);
				}
				while(rstPeliculas.next());
			}
		} 
		catch (SQLException e) 
		{
			LOGGER.error("Error durante la búsqueda de peliculas para el actor ["+ nomActor +"]");
		}
		finally
		{
			if(rstPeliculas != null)
			{
				try 
			
				{
					rstPeliculas.close();
					pstPelisActor.close();
				} 
				catch (SQLException e) 
				{
					LOGGER.error("Error durante el cierre de resultSet o PreparedStatement durante la consulta de peliculas para el actor ["+ nomActor +"]");
				}
			}
		}
		
		return pelis;
	}
	
	//Funcion que devuelve numero de actores
	public int getNumActoresPorPeliculaFUN (String tituloPelicula)
	{
		CallableStatement cs = null;
		int totalActores = 0;	
		try 
		{
				cs = conexion.prepareCall(SQL.invocacionGetNumActoresFUN);
				cs.registerOutParameter(1,Types.INTEGER);  // la función devuelve un VARCHAR ---- expediente
				cs.setString(2, tituloPelicula);
				cs.execute();
				
				totalActores =cs.getInt(1);
		} 
		catch (SQLException e) 
		{
			LOGGER.error("Error durante la invocación de la Funcion getNumActoresPorPeliculaFUN : ["+ tituloPelicula +"]");
		}
		finally
		{
			if (cs != null)
			{
				try 
				{
					cs.close();
				} 
				catch (SQLException e) 
				{
					LOGGER.error("Error durante el cierre del CS durante la invocación de la funcion getNumActoresPorPeliculaFUN ["+tituloPelicula+"]");
				}
			}
		}
		
		return totalActores;
	}

}
