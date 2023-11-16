package es.ciudadescolar.ficheroAccesoSecuencial;

import java.io.*;

public class FlujoBinario {

	private String nombreFichero;

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	
	public void crearFichero() {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null; 
		DataOutputStream dos = null;
		
		try {
			
			fos = new FileOutputStream(nombreFichero);
			bos = new BufferedOutputStream(fos);
			dos = new DataOutputStream(bos);
			
			for (int i = 1; i <= 100; i++) {
					
				dos.writeInt(i);
					
			}
			
			dos.flush();
			dos.flush();
			
		} catch (FileNotFoundException e) {
			
			System.err.println("error FileNotFoundException");
		}
		 catch (IOException e) {
				
				System.err.println("error IOException");
			}
	}
	
	public void leerFichero() {
		
	}
	
}
