package ADD_EX01E02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class JSONManager {

		private static final Logger LOGGER = LoggerFactory.getLogger(JSONManager.class);
		
		private static JSONObject objeto = null;
		private Genero genero = new Genero(); 
		
		
		public static boolean leerJson(File file) throws IOException {
			 
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
			
		
			LOGGER.debug("Fin lectura JSON");
			
			return true;
		}
		
		
	
}
