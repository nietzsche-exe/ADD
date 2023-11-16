package es.ciudadescolar.ficheroAccesoSecuencial2;

import java.io.Serializable;

public class Alumno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String expediente;
	private String nombre;
	private transient String password;
	
	
	public Alumno(String expediente, String nombre, String password) {
		this.expediente = expediente;
		this.nombre = nombre;
		this.password = password;
	}
	
	public Alumno() {
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "Alumno [expediente=" + expediente + ", nombre=" + nombre + ", password=" + password + "]";
	}
	
	
	
}
