package AAD.UD02E04.JoseJulianSaavedra;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Modulo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 193696883210793116L;
	
	private String modulo;
	private int horas_semanales;
	private String profesor;
	private int curso;
	transient String contraseña;
	static ArrayList<Modulo> listaModulos = new ArrayList<Modulo>();
	
	public Modulo(String modulo, int horas_semanales, String profesor, int curso, String contraseña) {
		super();
		this.modulo = modulo;
		this.horas_semanales = horas_semanales;
		this.profesor = profesor;
		this.curso = curso;
		this.contraseña = contraseña;
	}
	
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public int getHoras_semanales() {
		return horas_semanales;
	}
	public void setHoras_semanales(int horas_semanales) {
		this.horas_semanales = horas_semanales;
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	public int getCurso() {
		return curso;
	}
	public void setCurso(int curso) {
		this.curso = curso;
	}
	
	public static void leerFichero(String ficheroLegible) throws IOException  {
		
		FileReader fichero = new FileReader(ficheroLegible);
		BufferedReader br = new BufferedReader(fichero);
		
		String cadena = br.readLine();
		
		while (cadena != null) {
			
			StringTokenizer stk = new StringTokenizer(cadena, "|");
		
			String modulo = stk.nextToken();
			int horas_semanales = Integer.parseInt(stk.nextToken());
			String profesor = stk.nextToken();
			int curso = Integer.parseInt(stk.nextToken());
			String contraseña = stk.nextToken();
			
			listaModulos.add(new Modulo(modulo, horas_semanales, profesor, curso, contraseña));
			
			cadena = br.readLine();
			br.close();
		
		}
	
	}

	public static void escribirFichero(String ficheroEscribible) throws IOException {

		FileOutputStream fos = new FileOutputStream(ficheroEscribible);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(bos);

		for (Modulo mod : listaModulos) {
			oos.writeObject(mod);
		}

		oos.flush();
		oos.close();

	}

}
