package es.ciudadescolar.json;

public class Alumno {

	private String nombre;
	private int edad;
	private int expediente;
	
	
	public Alumno(String nombre, int edad, int expediente) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.expediente = expediente;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getExpediente() {
		return expediente;
	}
	public void setExpediente(int expediente) {
		this.expediente = expediente;
	}
	@Override
	public String toString() {
		return "Alumnos [nombre=" + nombre + ", edad=" + edad + ", expediente=" + expediente + "]";
	}
	
	
}
