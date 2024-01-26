package es.ciudadescolar;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name = "peliculas" , schema = "peliculas_orm_2324")
public class Pelicula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "cod_peli")
	private Integer cod_pelicula;
	
	@Column (name = "titulo")
	private String titulo;
	
	@Column (name = "fecha_grabacion")
	private LocalDate fecha;
	
	public Pelicula() {
		
	}

	public Integer getCod_pelicula() {
		return cod_pelicula;
	}

	public void setCod_pelicula(Integer cod_pelicula) {
		this.cod_pelicula = cod_pelicula;
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

	@Override
	public int hashCode() {
		return Objects.hash(cod_pelicula);
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
		return Objects.equals(cod_pelicula, other.cod_pelicula);
	}

	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + "]";
	}
	
	

	
	
	
	
	
}
