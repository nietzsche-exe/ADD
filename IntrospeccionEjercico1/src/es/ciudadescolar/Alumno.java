package es.ciudadescolar;

public class Alumno {

	
	private String expediente;
	private String nombre;
	private Integer edad;
	
	public Alumno() {
		
		
	}

	private void cambiarNombre(String nombre) {
		
		this.nombre = nombre;
	}
	
	public Alumno(String expediente, String nombre, Integer edad) {
		this.expediente = expediente;
		this.edad = edad;
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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Alumno [expediente=" + expediente + ", nombre=" + nombre + "]";
	}
	
}
