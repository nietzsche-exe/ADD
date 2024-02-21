package es.ciudadescolar;

public class Alumno {

	
	private String expediente;
	private String nombre;
	
	public Alumno() {	
		
	}

	private void cambiarNombre(String nombre) {
		
		this.nombre = nombre;
	}
	
	public Alumno(String expediente, String nombre) {
		this.expediente = expediente;
		//this.nombre= nombre;
		
		cambiarNombre(nombre);
	}

	public String getExpediente() {
		return expediente;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	@Override
	public String toString() {
		return "Alumno [expediente=" + expediente + ", nombre=" + nombre + "]";
	}
	
}
