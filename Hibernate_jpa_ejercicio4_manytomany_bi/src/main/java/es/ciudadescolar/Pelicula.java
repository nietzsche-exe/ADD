package es.ciudadescolar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "peliculas", schema = "peliculas_orm_2324")
public class Pelicula {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "cod_peli")
	private Integer cod_peli;
	
	@Column (name = "titulo")
	private String titulo;
	
	@Column (name = "fecha_grabacion")
	private LocalDate fecha;
	
	@ManyToMany (mappedBy = "peliculasActor", cascade = CascadeType.ALL)
	private List<Actor> actoresPeliculas;
	
	public Pelicula() {
		this.actoresPeliculas = new ArrayList<Actor>();
	}
	
	public Pelicula(String titulo, LocalDate fecha) {
		this.titulo = titulo;
		this.fecha = fecha;
		this.actoresPeliculas = new ArrayList<Actor>();
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

	public List<Actor> getActoresPeliculas() {
		return actoresPeliculas;
	}

	public void setActoresPeliculas(List<Actor> actoresPeliculas) {
		this.actoresPeliculas = actoresPeliculas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod_peli, fecha, titulo);
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
		return Objects.equals(cod_peli, other.cod_peli) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Pelicula [cod_peli=" + cod_peli + ", titulo=" + titulo + ", fecha=" + fecha + "]";
	}
	
	public void addActor(Actor actor) {
		this.actoresPeliculas.add(actor);
		
	}
	
	public void removeActor(Actor actor) {
		this.actoresPeliculas.remove(actor);
	}
	
	
}
