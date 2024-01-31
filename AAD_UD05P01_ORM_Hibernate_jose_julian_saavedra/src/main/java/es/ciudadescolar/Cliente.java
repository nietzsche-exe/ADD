package es.ciudadescolar;

import java.io.Serializable;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "customers", schema = "empresa_orm_2324")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "customer_id")
	private Integer cod_cliente;
	
	@Column (name = "first_name")
	private String nombre;
	
	@Column (name = "last_name")
	private String appellido;
	
	@Column (name = "email")
	private String email;
	
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "customer_id")
	private ClienteDetalles cliente_detalles;
	
	@OneToMany (mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Pago> pagos = new ArrayList<Pago>();
	
	public Cliente() {
		
	}

	public Integer getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(Integer cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAppellido() {
		return appellido;
	}

	public void setAppellido(String appellido) {
		this.appellido = appellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ClienteDetalles getCliente_detalles() {
		return cliente_detalles;
	}

	public void setCliente_detalles(ClienteDetalles cliente_detalles) {
		this.cliente_detalles = cliente_detalles;
		this.cliente_detalles.setCliente(this);
	}
	

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod_cliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cod_cliente, other.cod_cliente);
	}

	@Override
	public String toString() {
		return "Cliente [cod_cliente=" + cod_cliente + ", nombre=" + nombre + ", appellido=" + appellido + ", email="
				+ email + ", cliente_detalles=" + cliente_detalles + ", pagos=" + pagos + "]";
	}

	

	
}
