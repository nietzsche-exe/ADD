package AAD_EX02;

public class Pais 
{
	private char [] codigo;
	private String nombre;
	private String continente;
	private Integer poblacion;
	private String jefeDeEstado;
	private Integer capital;
	
	public  char [] getCodigo() {
		return codigo;
	}
	public void setCodigo( char [] codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContinente() {
		return continente;
	}
	public void setContinente(String continente) {
		this.continente = continente;
	}
	
	public Integer getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
	}
	
	public String getJefeDeEstado() {
		return jefeDeEstado;
	}
	public void setJefeDeEstado(String jefeDeEstado) {
		this.jefeDeEstado = jefeDeEstado;
	}
	public Integer getCapital() {
		return capital;
	}
	public void setCapital(Integer capital) {
		this.capital = capital;
	}
	@Override
	public String toString() {
		return "Pais [codigo=" + String.copyValueOf(codigo) + ", nombre=" + nombre + ", continente=" + continente + "]";
	}

}
