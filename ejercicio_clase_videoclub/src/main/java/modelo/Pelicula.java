package modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "peliculas", schema = "peliculas_orm_2324")
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_peli")
    private Integer codPeli;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "fecha_grabacion")
    private Date fechaGrabacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_dire")
    private Director director;

    public Pelicula() {
    }

    // Getters y setters
    public Integer getCodPeli() {
        return codPeli;
    }

    public void setCodPeli(Integer codPeli) {
        this.codPeli = codPeli;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaGrabacion() {
        return fechaGrabacion;
    }

    public void setFechaGrabacion(Date fechaGrabacion) {
        this.fechaGrabacion = fechaGrabacion;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    // hashCode, equals y toString
    @Override
    public int hashCode() {
        return Objects.hash(codPeli);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pelicula other = (Pelicula) obj;
        return Objects.equals(codPeli, other.codPeli);
    }

    @Override
    public String toString() {
        return "Pelicula{" +
               "codPeli=" + codPeli +
               ", titulo='" + titulo + '\'' +
               ", fechaGrabacion=" + fechaGrabacion +
               '}';
    }
}