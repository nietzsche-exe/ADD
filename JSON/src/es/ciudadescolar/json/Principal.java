package es.ciudadescolar.json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;



public class Principal {

		private static JSONObject objeto;
		 
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			if (leerFicheroAlumnosJSON()) {
	 
				System.out.println("el centro " + objeto.getString("centro"));
				System.out.println("el curso " + objeto.getString("curso"));
	 
				Number numero = objeto.getNumber("codigo");
	 
				System.out.println("el codigo " + numero.intValue());
				JSONArray alumnos = objeto.getJSONArray("alumnos");
	 
				List<Alumno> lista_alumnos = new ArrayList<Alumno>();
	 
				for (int i = 0; i < alumnos.length(); i++) {
	 
					JSONObject alumnoJSON = alumnos.getJSONObject(i);
					String nomAlumno = alumnoJSON.getString("nombre");
					int edad = alumnoJSON.getInt("edad");
					int expediente = alumnoJSON.getInt("expediente");
	 
					Alumno al = new Alumno(nomAlumno, edad, expediente);
					lista_alumnos.add(al);
				}
				for (Alumno alumno : lista_alumnos) {
					System.out.println(alumno);
				}
				System.out.println("volcando a fichero el objeto JSON original");
				
				if(!escribirFicherosAlumnosJSON()) {
					System.err.println("error durante el volcado de fichero");
				}
			}
		}
	 
		private static boolean leerFicheroAlumnosJSON() {
	 
			String linea = null;
	 
			StringBuilder sb = new StringBuilder();
	 
			try {
				FileReader fr = new FileReader("alumnos.json");
				BufferedReader br = new BufferedReader(fr);
	 
				while ((linea = br.readLine()) != null) {
	 
					sb.append(linea);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				return false;
			} catch (IOException e) {
				return false;
			}
	 
			objeto = new JSONObject(sb.toString());
	 
			return true;
		}
	 
		private static boolean escribirFicherosAlumnosJSON() {
	 
			if (objeto != null) {
	 
				try {
	 
					PrintWriter pw = new PrintWriter("alumno_bis.json");
					pw.write(objeto.toString(2));
					pw.flush();
					pw.close();
	 
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 
			}
			return true;
		}

}
