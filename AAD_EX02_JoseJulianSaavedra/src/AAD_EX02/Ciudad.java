package AAD_EX02;

public class Ciudad 
{
	private Integer id;
	private String nombre;
	private char[]  codigoPais;
	private String distrito;
	private Integer poblacion;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public char[] getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(char[] codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public Integer getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
	}
	@Override
	public String toString() {
		return "Ciudad [id=" + id + ", nombre=" + nombre + ", distrito=" + distrito + "]";
	}

}
