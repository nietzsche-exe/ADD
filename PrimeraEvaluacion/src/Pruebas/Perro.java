package Pruebas;

public class Perro extends Animal implements Mascota{

	private Integer codigo;
	private String nombre;
	
	public Perro(String tipo, String sexo, Integer codigo, String nombre) {
		super(tipo, sexo);
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void hazRuido() {
		System.out.println("Guau Guau!");
		
	}

	@Override
	public void come() {
		System.out.println("dame de comer o te arranco las cortinas!");
		
	}
	
	public String toString() {
		return "CODIGO: " + this.codigo + ", NOMBRE: " + this.nombre + ", TIPO: " + this.getTipo() + ", SEXO: " + this.getSexo(); 
	}
	
}
