package es.ciudadescolar;

import java.util.List;
import java.util.Objects;

public class Director {

	private Integer cod_director;
	private String nombre;
	private List<Pelicula> peliculas;

	public Director(){
		
	}

	public Integer getCod_director() {
		return cod_director;
	}

	public void setCod_director(Integer cod_director) {
		this.cod_director = cod_director;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod_director);
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
		return Objects.equals(cod_director, other.cod_director);
	}

	@Override
	public String toString() {
		return "Director [cod_director=" + cod_director + ", nombre=" + nombre + "]";
	}
	
	

}
