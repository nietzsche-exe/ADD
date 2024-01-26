package es.ciudadescolar;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "actores", schema = "peliculas_orm_2324")
public class Actor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "cod_act")
	private Integer cod_act;
	
	@Column (name = "nombre")
	private String nombre;
	
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn (name = "cod_direccion")
	private Direccion direccion_actor;
	
	public Actor() {
		
	}

	public Integer getCod_act() {
		return cod_act;
	}

	public void setCod_act(Integer cod_act) {
		this.cod_act = cod_act;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Direccion getDireccion_actor() {
		return direccion_actor;
	}

	public void setDireccion_actor(Direccion direccion_actor) {
		this.direccion_actor = direccion_actor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod_act);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return Objects.equals(cod_act, other.cod_act);
	}

	@Override
	public String toString() {
		return "Actor [cod_act=" + cod_act + ", nombre=" + nombre + ", direccion_actor=" + direccion_actor + "]";
	}

	
	
	
}
