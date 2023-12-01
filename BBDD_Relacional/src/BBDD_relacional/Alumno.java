package BBDD_relacional;


public class Alumno {

	
	private String nombre;
	private String expediente;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getExpediente() {
		return expediente;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	
	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", expediente=" + expediente + "]";
	}
	
	
	
	
	
}
