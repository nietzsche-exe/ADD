package es.ciudadescolar.ficheroAccesoSecuencial2;

import java.io.*;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File fich = new File("C:\\Users\\alumno\\eclipse-workspace\\alumno.dat");
		
		Alumno[] alumnos = {
				
				new Alumno("1001", "Manolo", "1234"),
				new Alumno("1002", "Jacobo", "1233"),
				new Alumno("1003", "Fermin", "2343"),
				new Alumno("1004", "David", "5424")
				
		};
		
		FlujoBinario fb = new FlujoBinario(fich);
		
		try {
			fb.escribirAlumnos(alumnos);
			fb.leerAlumnos();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
		
		
}
	


