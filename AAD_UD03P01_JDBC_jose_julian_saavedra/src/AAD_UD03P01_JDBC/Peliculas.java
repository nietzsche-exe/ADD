package AAD_UD03P01_JDBC;

import java.util.Arrays;
import java.util.List;

/**
 * Clase Peliculas. En ella se gestionan la creacion de instancias de Peliculas para guardar la informacion extraida del fichero de texto.
 * 
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023-2024
 */
public class Peliculas {

	private String titulo;
	private int año;
	private String[] actores;
	private List<String> protagonista;
	
	public Peliculas(String titulo, int año, String[] actores, List<String> protagonista) {
		super();
		this.titulo = titulo;
		this.año = año;
		this.actores = actores;
		this.protagonista = protagonista;
	}
	
	public Peliculas() {
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String[] getActores() {
		return actores;
	}

	public void setActores(String[] prota) {
		this.actores = prota;
	}
	

	public List<String> getProtagonista() {
		return protagonista;
	}

	public void setProtagonista(List<String> protagonista) {
		this.protagonista = protagonista;
	}

	@Override
	public String toString() {
		return "Peliculas [titulo=" + titulo + ", año=" + año + ", actores=" + Arrays.toString(actores)
				+ ", protagonista=" + protagonista + "]";
	}

	
	
	
	
	
	
}
