package es.ciudadescolar.ficheroAccesoSecuencial2;

import java.io.*;

public class FlujoBinario {

	private File ficheroAlumno = null;
	
	public FlujoBinario(File fichero) {
		this.ficheroAlumno = fichero;
	}
	
	public void escribirAlumnos(Alumno[] alumnos) throws IOException, FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(ficheroAlumno);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		for(Alumno al:alumnos) {
			oos.writeObject(al);
		}
		
		oos.flush();
		oos.close();
	}
	
	public void leerAlumnos() throws ClassNotFoundException, IOException{
		FileInputStream fis = new FileInputStream(ficheroAlumno);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);
		
		
		
		try {
		while(true) {
			Alumno alumno = (Alumno) ois.readObject();	
			System.out.println(alumno);
			}
		}
		catch(EOFException e) {
			
		}
		finally {
			ois.close();
		}
		
		
	}
	
}
