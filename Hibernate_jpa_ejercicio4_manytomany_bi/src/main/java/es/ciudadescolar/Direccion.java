package es.ciudadescolar;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	
	@OneToOne (mappedBy = "direccion_actor", fetch = FetchType.LAZY)
	private Actor actor;
	
	public Direccion() {
		
	}
	
	public Direccion(String calle, Integer numero) {
		this.calle = calle;
		this.numero = numero;
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

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
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
		return "Direccion [cod_dir=" + cod_dir + ", calle=" + calle + ", numero=" + numero + ", actor=" + actor.getCod_act() + "]";
	}

}
