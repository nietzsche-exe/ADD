package es.ciudadescolar.ficheroAccesoSecuencial3;
import java.io.*;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String nombreFichero = "C:\\Users\\alumno\\eclipse-workspace\\ficheroFlujoTexto.txt";
		
		FlujoTexto ft = new FlujoTexto(nombreFichero);
		
		try {
			
			ft.escribirFichero();
			ft.leerFichero();
		}
		catch(IOException e) {
			System.err.println("Error en la manipulacion del fichero");
			
		}
		
	}

}
