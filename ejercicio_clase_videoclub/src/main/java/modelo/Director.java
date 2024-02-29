package modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "directores", schema = "peliculas_orm_2324")
public class Director implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_director")
    private Integer codigoDirector;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Pelicula> peliculas = new HashSet<>();

    public Director() {
    }

    // Getters y setters
    public Integer getCodigoDirector() {
        return codigoDirector;
    }

    public void setCodigoDirector(Integer codigoDirector) {
        this.codigoDirector = codigoDirector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    // hashCode, equals y toString
    @Override
    public int hashCode() {
        return Objects.hash(codigoDirector);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Director other = (Director) obj;
        return Objects.equals(codigoDirector, other.codigoDirector);
    }

    @Override
    public String toString() {
        return "Director{" +
               "codigoDirector=" + codigoDirector +
               ", nombre='" + nombre + '\'' +
               '}';
    }
}
