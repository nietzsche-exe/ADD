package es.ciudadescolar.ficheroAccesoSecuencial3;
import java.io.*;

public class FlujoTexto {

	private File fichero;
	private FileReader ficheroLegible;
	private FileWriter ficheroEscribible;
	
	public FlujoTexto(String fichero) {
		this.fichero = new File(fichero);
	}
	
	public void escribirFichero() throws IOException {
		
		ficheroEscribible = new FileWriter(fichero);
		PrintWriter pt = new PrintWriter(ficheroEscribible);
		
		for(int i = 0; i <= 100; i++) {
			pt.println("Linea[" + i + "]");
		}
		
		pt.flush();
		pt.close();
		
	}
	
	public void leerFichero() throws IOException {
		
		ficheroLegible = new FileReader(fichero);
		BufferedReader br = new BufferedReader(ficheroLegible);
		String linea;
		linea = br.readLine();
		
		while(linea != null) {
			
			System.out.println(linea);
			linea = br.readLine();
		}
		
		br.close();
		
	}
}
