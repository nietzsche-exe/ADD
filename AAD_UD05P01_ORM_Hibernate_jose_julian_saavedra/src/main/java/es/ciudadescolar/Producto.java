package es.ciudadescolar;

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
import jakarta.persistence.Table;

/*
 * Clase Producto que maneja la relacion con la base de datos
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since 2023 - 2024
 */
@Entity
@Table (name = "products", schema = "empresa_orm_2324")
public class Producto {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "product_id")
	private Integer cod_producto;
	
	@Column (name = "product_name")
	private String nombre;
	
	@Column (name = "price")
	private Double price;
	
	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	
	@JoinTable (name = "product_categories", joinColumns = 
	@JoinColumn (name = "product_id"), inverseJoinColumns = 
	@JoinColumn (name = "category_id"))
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	public Producto() {
		
	}

	public Integer getCod_producto() {
		return cod_producto;
	}

	public void setCod_producto(Integer cod_producto) {
		this.cod_producto = cod_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public void addCategoria(Categoria categoria) {
		this.categorias.add(categoria);
		categoria.addProducto(this);
	}
	
	public void removeCategoria(Categoria categoria) {
		this.categorias.remove(categoria);
		categoria.removeProducto(this);
	}


	@Override
	public int hashCode() {
		return Objects.hash(cod_producto, nombre, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(cod_producto, other.cod_producto) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(price, other.price);
	}

	@Override
	public String toString() {
		return "Producto [cod_producto=" + cod_producto + ", nombre=" + nombre + ", price=" + price + ", categorias="
				+ categorias + "]";
	}
	
	
}
