package Pruebas;

public class Gato extends Animal implements Mascota{

	private Integer codigo;
	
	public Gato(String tipo, String sexo, Integer codigo) {
		super(tipo, sexo);
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Override
	public void hazRuido() {
		System.out.println("miauuu!");
		
	}

	@Override
	public void come() {
		System.out.println("dame de comer o te araño la cara");
	}
	
	public String toString() {
		return "CODIGO: " + this.codigo + ", TIPO: " + this.getTipo() + ", SEXO: " + this.getSexo();
		
	}

	
}
