package modelo;

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
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="actores", schema="peliculas_orm_2324")
public class Actor implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	@Column (name="cod_act")
	private Integer codigo_actor;
	
	@Column (name="nombre")
	private String nombre_actor;
	
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)    // desencadena la acciones de la clase padre a la hija Actor - Direcci�n 
	@JoinColumn (name="cod_direccion")       // esta clase Actor es el owner de la relaci�n
	private Direccion direccion_actor;

	public Actor()
	{
		
	}
	

	public Integer getCodigo_actor() {
		return codigo_actor;
	}



	public void setCodigo_actor(Integer codigo_actor) {
		this.codigo_actor = codigo_actor;
	}



	public String getNombre_actor() {
		return nombre_actor;
	}

	public void setNombre_actor(String nombre_actor) {
		this.nombre_actor = nombre_actor;
	}

	public Direccion getDireccion_actor() {
		return direccion_actor;
	}

	public void setDireccion_actor(Direccion direccion_actor) {
		this.direccion_actor = direccion_actor;
	}



	@Override
	public int hashCode() {
		return Objects.hash(codigo_actor);
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
		return Objects.equals(codigo_actor, other.codigo_actor);
	}



	@Override
	public String toString() {
		return "Actor [codigo_actor=" + codigo_actor + ", nombre_actor=" + nombre_actor + ", direccion_actor="
				+ direccion_actor + "]";
	}
	
}
