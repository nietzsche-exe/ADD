package es.ciudadescolar.bbddoo;

public class Alumno {

	
	private String expediente;
	private String nombre;
	private int edad;
	
	public Alumno() {
		
	}
	
	public Alumno(String expediente, String nombre, int edad) {
		super();
		this.expediente = expediente;
		this.nombre = nombre;
		this.edad = edad;
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
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Alumno [expediente=" + expediente + ", nombre=" + nombre + ", edad=" + edad + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Alumno alumno = (Alumno) obj;
		
		return this.expediente.equals(alumno.expediente);
		
	}
	
	
	
}
