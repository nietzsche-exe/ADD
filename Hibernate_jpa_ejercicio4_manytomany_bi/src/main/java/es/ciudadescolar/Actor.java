package es.ciudadescolar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable (name = "interpretes2", joinColumns = @JoinColumn (name = "cod_actor"), inverseJoinColumns = @JoinColumn(name = "cod_pelicula"))
	private List<Pelicula> peliculasActor;
	
	public Actor() {
		this.peliculasActor = new ArrayList<Pelicula>();
	}
	
	public Actor(String nombre, Direccion dir) {
		this.nombre = nombre;
		this.direccion_actor = dir;
		this.peliculasActor = new ArrayList<Pelicula>();
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

	

	public List<Pelicula> getPeliculasActor() {
		return peliculasActor;
	}

	public void setPeliculasActor(List<Pelicula> peliculasActor) {
		this.peliculasActor = peliculasActor;
	}


	@Override
	public String toString() {
		return "Actor [cod_act=" + cod_act + ", nombre=" + nombre + ", direccion_actor=" + direccion_actor + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod_act, direccion_actor, nombre);
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
		return Objects.equals(cod_act, other.cod_act) && Objects.equals(direccion_actor, other.direccion_actor)
				&& Objects.equals(nombre, other.nombre);
	}
	
	public void addPelicula(Pelicula peli) {
		this.peliculasActor.add(peli);
		peli.addActor(this);
	}
	
	public void removePelicula(Pelicula peli) {
		this.peliculasActor.remove(peli);
		peli.removeActor(this);
	}
	
}
