package es.ciudadescolar;

import java.time.LocalDate;
import java.util.Objects;

public class Pelicula {

	private Integer cod_peli;
	private String titulo;
	private LocalDate fecha;
	private Director director;
	
	public Pelicula() {
		
	}

	public Integer getCod_peli() {
		return cod_peli;
	}

	public void setCod_peli(Integer cod_peli) {
		this.cod_peli = cod_peli;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod_peli);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return Objects.equals(cod_peli, other.cod_peli);
	}

	@Override
	public String toString() {
		return "Pelicula [cod_peli=" + cod_peli + ", titulo=" + titulo + ", fecha=" + fecha + ", director=" + director
				+ "]";
	}
	
	
	
}
