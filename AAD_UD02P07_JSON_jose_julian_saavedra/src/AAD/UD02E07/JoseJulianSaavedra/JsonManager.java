package AAD.UD02E07.JoseJulianSaavedra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase Json Manager gestiona todo lo relacionado con el archivo json pasado como parametro por el main.
 * Contiene los metodos para leer un fichero json, crear nuevos objetos json y escribir un nuevo json.
 * @author Jose Julian Saavedra  
 * @version 1.0
 * @since curso 2023-2024
 */
public class JsonManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonManager.class);
	
	private static JSONObject objeto = null;
	private static JSONObject equipoJSON = new JSONObject();
	
	private static JSONArray victorias = new JSONArray();
	private static JSONArray derrotas = new JSONArray();
	private static JSONArray empates = new JSONArray();

	
	/**
	* El método estático leerFicheroJson permite la lectura de un fichero json pasado por parametros al main 
	* @author Jose Julian Saavedra
	* @version 1.0
	* @since curso 2023-2024
	* @param File file Espera un parametro correspondiente al fichero json
	* @return Boolean true Devuelve Verdadero si la lectura finaliza correctamente y Falso si surjen problemas durante su ejeccucion
	*/
	public static boolean leerFicheroJson(File file) throws IOException {
		 
		LOGGER.debug("Inicio lectura JSON");
		
		String linea = null;
 
		StringBuilder sb = new StringBuilder();
 
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
 
			while ((linea = br.readLine()) != null) {
 
				sb.append(linea);
			}
			br.close();
			
		objeto = new JSONObject(sb.toString());
	
		LOGGER.debug("Finalizacion lectura JSON");
		
		return true;
	}
	
	/**
	* El método estático crearEquipoJson permite la creacion de un nuevo objeto JSON a partir del nombre de Equipo que pase el usuario por el main  
	* @author Jose Julian Saavedra
	* @version 1.0
	* @since curso 2023-2024
	* @param String equipo_nombre Espera un parametro correspondiente al nombre del Equipo especificado en el main por el usuario 
	*/
	public static void crearEquipoJson(String equipo_nombre) throws JSONException, IOException {
		
		LOGGER.debug("Inicio de creacion de objetos JSON");
		
		if(leerFicheroJson(Principal.getJson())) {
			
			
			String division = null;
			Integer goles_visitantes = 0;
			Integer goles_locales = 0;
			Integer goles_visitantes_contra = 0;
			Integer goles_locales_contra = 0;
			Integer tarjetas_amarillas_visitantes = 0;
			Integer tarjetas_amarillas_locales = 0;
			Integer tarjetas_rojas_visitantes = 0;
			Integer tarjetas_rojas_locales = 0;
			
			JSONArray resultados = objeto.getJSONArray("resultados");
			
			
			for (int i = 0; i < resultados.length(); i++) {
				
				JSONObject resultadoJSON = resultados.getJSONObject(i);
				JSONObject partidos = new JSONObject();
				
				division = resultadoJSON.getString(XmlManager.getEtiquetas().getDivision());
				
				if(resultadoJSON.getString(XmlManager.getEtiquetas().getEquipo_local()).equals(equipo_nombre)) {
					
					if(resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local()) > resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())) {
						partidos.put("Adversario", resultadoJSON.getString(XmlManager.getEtiquetas().getEquipo_visitante()));
						partidos.put("Fecha", resultadoJSON.getString(XmlManager.getEtiquetas().getFecha()));
						partidos.put("Local", "Si");
						partidos.put("Resultado", (resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local())+"-"+resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())));
						victorias.put(partidos);
					}
					if(resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local()) < resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())) {
						partidos.put("Adversario", resultadoJSON.getString(XmlManager.getEtiquetas().getEquipo_visitante()));
						partidos.put("Fecha", resultadoJSON.getString(XmlManager.getEtiquetas().getFecha()));
						partidos.put("Local", "Si");
						partidos.put("Resultado", (resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local())+"-"+resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())));
						derrotas.put(partidos);
					}
					if(resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local()) == resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())) {
						partidos.put("Adversario", resultadoJSON.getString(XmlManager.getEtiquetas().getEquipo_visitante()));
						partidos.put("Fecha", resultadoJSON.getString(XmlManager.getEtiquetas().getFecha()));
						partidos.put("Local", "Si");
						partidos.put("Resultado", (resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local())+"-"+resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())));
						empates.put(partidos);
					}
					
					goles_locales += resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local());
					goles_visitantes_contra += resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante()); 
					tarjetas_amarillas_locales += resultadoJSON.getInt(XmlManager.getEtiquetas().getTarjetas_amarillas_local());
					tarjetas_rojas_locales += resultadoJSON.getInt(XmlManager.getEtiquetas().getTarjetas_rojas_local());
				}
				
				if(resultadoJSON.getString(XmlManager.getEtiquetas().getEquipo_visitante()).equals(equipo_nombre)) {
					
					if(resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local()) < resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())) {
						partidos.put("Adversario", resultadoJSON.getString(XmlManager.getEtiquetas().getEquipo_local()));
						partidos.put("Fecha", resultadoJSON.getString(XmlManager.getEtiquetas().getFecha()));
						partidos.put("Local", "No");
						partidos.put("Resultado", (resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())+"-"+resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local())));
						victorias.put(partidos);
					}
					if(resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local()) > resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())) {
						partidos.put("Adversario", resultadoJSON.getString(XmlManager.getEtiquetas().getEquipo_local()));
						partidos.put("Fecha", resultadoJSON.getString(XmlManager.getEtiquetas().getFecha()));
						partidos.put("Local", "No");
						partidos.put("Resultado", (resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())+"-"+resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local())));
						derrotas.put(partidos);
					}
					if(resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local()) == resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())) {
						partidos.put("Adversario", resultadoJSON.getString(XmlManager.getEtiquetas().getEquipo_local()));
						partidos.put("Fecha", resultadoJSON.getString(XmlManager.getEtiquetas().getFecha()));
						partidos.put("Local", "No");
						partidos.put("Resultado", (resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante())+"-"+resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local())));
						empates.put(partidos);
					}
					
					goles_visitantes += resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_visitante());
					goles_locales_contra += resultadoJSON.getInt(XmlManager.getEtiquetas().getGoles_local());
					tarjetas_amarillas_visitantes += resultadoJSON.getInt(XmlManager.getEtiquetas().getTarjetas_amarillas_visitante());
					tarjetas_rojas_visitantes += resultadoJSON.getInt(XmlManager.getEtiquetas().getTarjetas_rojas_visitante());
				}	
				
			}
			
			equipoJSON.put("Equipo", equipo_nombre);
			equipoJSON.put("Division", division);
			equipoJSON.put("goles a favor", (goles_locales+goles_visitantes));
			equipoJSON.put("goles en contra", (goles_visitantes_contra+goles_locales_contra));
			equipoJSON.put("Tarjetas amarillas", (tarjetas_amarillas_locales+tarjetas_amarillas_visitantes));
			equipoJSON.put("Tarjetas rojas", (tarjetas_rojas_locales+tarjetas_rojas_visitantes));
			
			equipoJSON.put("Victorias", victorias);
			equipoJSON.put("Derrotas", derrotas);
			equipoJSON.put("Empates", empates);
			
			LOGGER.debug("Finalizacion de creacion de objetos JSON");
			
		}
	}
	
	/**
	* El método estático escribirJSON permite la escritura del nuevo JSON a partir del objeto JSON nuevo creado en el metodo crearEquipoJson  
	* @author Jose Julian Saavedra
	* @version 1.0
	* @since curso 2023-2024
	*/
	public static void escribirJSON() throws FileNotFoundException {
		
		LOGGER.debug("Inicio escritura JSON");
		
		PrintWriter writer = new PrintWriter("AAD_UD02P07E01_JSON_"+Principal.getEquipo()+"_jose_julian_saavedra.json");
		writer.print(equipoJSON.toString(2));
		writer.flush();
		writer.close();
		
		LOGGER.debug("Finalizacion escritura JSON");
		
	}

	
}
