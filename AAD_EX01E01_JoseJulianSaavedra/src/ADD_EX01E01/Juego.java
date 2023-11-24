package ADD_EX01E01;

/**
 * Clase Juego. 
 * En esta clase se definen los atributos del Objeto Juego
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023-2024
 */
public class Juego {

	private String titulo;
	private String desarrollador;
	private String anyo;
	
	public Juego() {
		
	}
	
	public Juego(String titulo, String desarrollador, String anyo) {
		super();
		this.titulo = titulo;
		this.desarrollador = desarrollador;
		this.anyo = anyo;
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDesarrollador() {
		return desarrollador;
	}
	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
	}
	public String getAnyo() {
		return anyo;
	}
	public void setAnyo(String anyo) {
		this.anyo = anyo;
	}

	@Override
	public String toString() {
		return titulo + "|" + desarrollador + "|" + anyo ;
	}
	
	
}


