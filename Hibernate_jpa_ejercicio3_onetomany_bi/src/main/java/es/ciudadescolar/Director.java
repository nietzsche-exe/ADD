package es.ciudadescolar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "directores", schema = "peliculas_orm_2324")
public class Director implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name = "cod_director")
	private Integer codigo_director;
	
	@Column (name = "nombre")
	private String nombre_director;

	//Vamos a indicar que director es owner (1) y peliculas es (N)
	@OneToMany(mappedBy = "director", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Pelicula> peliculas_dirigidas;
	
	public Director () {
		
	}
	
	
	public Integer getCodigo_director() {
		return codigo_director;
	}
	public void setCodigo_director(Integer codigo_director) {
		this.codigo_director = codigo_director;
	}
	public String getNombre_director() {
		return nombre_director;
	}
	public void setNombre_director(String nombre_director) {
		this.nombre_director = nombre_director;
	}
	public List<Pelicula> getPeliculas_dirigidas() {
		return peliculas_dirigidas;
	}
	public void setPeliculas_dirigidas(List<Pelicula> peliculas_dirigidas) {
		this.peliculas_dirigidas = peliculas_dirigidas;
	}
	
	
	public void addPeliculaDirigida (Pelicula peli) {
		if (this.peliculas_dirigidas == null) {
			this.peliculas_dirigidas = new ArrayList<Pelicula>();
		}
		this.peliculas_dirigidas.add(peli);
		peli.setDirector(this);
	}

	
	public void removePeliculaDirigida (Pelicula peli) {
		
		if (this.peliculas_dirigidas != null) {
			this.peliculas_dirigidas.remove(peli);
			peli.setDirector(null);
		}
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(codigo_director, nombre_director);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Director other = (Director) obj;
		return Objects.equals(codigo_director, other.codigo_director)
				&& Objects.equals(nombre_director, other.nombre_director);
	}


	@Override
	public String toString() {
		return "Director [codigo_director=" + codigo_director + ", nombre_director=" + nombre_director
				+ ", peliculas_dirigidas=" + peliculas_dirigidas + "]";
	}
	
	
	
}
