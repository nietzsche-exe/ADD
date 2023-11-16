package AAD.UD02E06.JoseJulianSaavedra;

import java.io.Serializable;
import java.util.ArrayList;

public class Coche implements Serializable {

	/**
	 * Clase Coche con sus atributos corresponientes en privado y sus respectivos getters y setters
	 * ademas de el constructor por defecto y el metodo toString para escribir correctamente los objetos
	 */
	private static final long serialVersionUID = 5165113957339885847L;

	private Integer id;
	private String marca;
	private String modelo;
	private Float precio;
	static ArrayList<Coche> listaCoches = new ArrayList<>();

	public Coche() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return id + "#" + marca + "#" + modelo + "#" + precio;
	}

}
