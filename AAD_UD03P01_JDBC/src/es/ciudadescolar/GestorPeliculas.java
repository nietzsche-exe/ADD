package es.ciudadescolar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

public class GestorPeliculas 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(GestorPeliculas.class);
	
	public static List<Pelicula> leerFichero(String fichero) 
	{
		FileReader fr =null;
		BufferedReader br =null;
		StringTokenizer stk,stk2,stk3 =null;
		List<Pelicula> peliculas = null;
		Pelicula peli=null;
		try 
		{
			fr = new FileReader(fichero);
			br = new BufferedReader(fr);
			String linea;
			
			linea = br.readLine();
			if (linea != null)
			{
				peliculas =new ArrayList<Pelicula>();
			}
			while (linea != null)
			{
				stk = new StringTokenizer(linea,"|");
				String tit = stk.nextToken();
				Integer anyo = Integer.valueOf(stk.nextToken());
				
				String actores = stk.nextToken();
				String protas = stk.nextToken();
				stk2 = new StringTokenizer(actores,",");
				stk3 = new StringTokenizer(protas,",");
				
				peli = new Pelicula();
				peli.setAnio(anyo);
				peli.setTitulo(tit);
				while (stk2.hasMoreTokens() && stk3.hasMoreTokens())
				{
					peli.addActorProta(stk2.nextToken().trim(), Boolean.valueOf(stk3.nextToken().equals("s") ? true : false));
					
					
				}
				peliculas.add(peli);
				linea = br.readLine();
			}
		} 
		catch (FileNotFoundException e) 
		{
			LOGGER.error("Error. Fichero de entrada de películas no encontrado ["+fichero+"]");
			
		}
		catch (IOException e)
		{
			LOGGER.error("Error durante la lectura del fichero de entrada de películas ["+fichero+"]");
		}
		finally
		{
			if (br != null)
				try 
				{
					br.close();
					//fr.close();
				} 
				catch (IOException e) 
				{
					LOGGER.error("Error cerrando el fichero de entrada de películas ["+fichero+"]");
				}

		}
		return peliculas;

	}

}
