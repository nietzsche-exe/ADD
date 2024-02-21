package modelo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "direcciones", schema = "peliculas_orm_2324")
public class Direccion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "cod_dir")
	private Integer cod_dir;
	
	@Column (name = "calle")
	private String calle;
	
	@Column (name = "numero")
	private Integer numero;
	
	public Direccion() {
		
	}

	public Integer getCod_dir() {
		return cod_dir;
	}

	public void setCod_dir(Integer cod_dir) {
		this.cod_dir = cod_dir;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod_dir);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion other = (Direccion) obj;
		return Objects.equals(cod_dir, other.cod_dir);
	}

	@Override
	public String toString() {
		return "Direccion [cod_dir=" + cod_dir + ", calle=" + calle + ", numero=" + numero + "]";
	}
	
	
}
