package es.ciudadescolar;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "peliculas", schema = "peliculas_orm_2324")
public class Pelicula implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "cod_peli")
	private Integer codigo_pelicula;
	
	@Column (name = "titulo")
	private String titulo;
	
	@Column (name = "fecha_grabacion")
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name = "cod_dire")
	private Director director;
	
	
	public Pelicula () {	
		
	}


	public Integer getCodigo_pelicula() {
		return codigo_pelicula;
	}
	public void setCodigo_pelicula(Integer codigo_pelicula) {
		this.codigo_pelicula = codigo_pelicula;
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
		return Objects.hash(codigo_pelicula);
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
		return Objects.equals(codigo_pelicula, other.codigo_pelicula);
	}


	@Override
	public String toString() {
		if (this.director == null) {
			return "Pelicula [codigo_pelicula=" + codigo_pelicula + ", titulo=" + titulo + ", fecha=" + fecha + "]";
		}
		else {
			return "Pelicula [codigo_pelicula=" + codigo_pelicula + ", titulo=" + titulo + ", fecha=" + fecha
				+ ", director=" + this.getDirector().getCodigo_director() + "]";
		}
		
	}
	
	
	
	
}
