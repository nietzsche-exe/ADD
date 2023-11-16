package Pruebas;

public class Vehiculo {

	private String marca;
	private String modelo;
	private Integer potencia;
	
	public Vehiculo(String marca, String modelo, Integer potencia) {
		this.marca = marca;
		this.modelo = modelo;
		this.potencia = potencia;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void setPotencia(Integer potencia) {
		this.potencia = potencia;
	}
	
	public String getMarca() {
		return this.marca;
	}
	
	public String getModelo() {
		return this.modelo;
	}
	
	public Integer getPotencia() {
		return this.potencia;
	}
	
	public String toString() {
		return "Marca: " + this.marca + ", Modelo: " + this.modelo + ", Potencia: " + this.potencia;
	}
}
