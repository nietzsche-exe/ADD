package AAD.UD02E04.JoseJulianSaavedra;

import java.io.IOException;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try {
			
			Modulo.leerFichero(args[0]);
			Modulo.escribirFichero(args[1]);
			
		} catch (IOException e) {
			System.err.println("Error en la manipulacion de los ficheros");
		}
		
	}
	
	
	
	
	
}
