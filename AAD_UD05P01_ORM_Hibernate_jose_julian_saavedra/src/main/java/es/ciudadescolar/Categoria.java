package es.ciudadescolar;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "categories", schema = "empresa_orm_2324")
public class Categoria {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "category_id")
	private Integer cod_categoria;
	
	@Column (name = "category_name")
	private String nombre;
	
	@ManyToMany (mappedBy = "categorias", fetch = FetchType.EAGER)
	private List<Producto> productos;
	
	public Categoria() {
		
	}

	public Integer getCod_categoria() {
		return cod_categoria;
	}

	public void setCod_categoria(Integer cod_categoria) {
		this.cod_categoria = cod_categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public void addProducto(Producto producto) {
		this.productos.add(producto);
		producto.addCategoria(this);
	}
	
	public void removeProducto(Producto producto) {
		this.productos.remove(producto);
		producto.removeCategoria(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod_categoria, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(cod_categoria, other.cod_categoria) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Categoria [cod_categoria=" + cod_categoria + ", nombre=" + nombre + "]";
	}
	
	
}
