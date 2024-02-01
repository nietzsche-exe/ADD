package es.ciudadescolar;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "customer_details", schema = "empresa_orm_2324")
public class ClienteDetalles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column (name = "customer_id")
	private Integer cod_cliente;
	
	@Column (name = "address")
	private String direccion;
	
	@Column (name = "phone_number")
	private String telefono;
	
	@OneToOne (mappedBy = "cliente_detalles", fetch = FetchType.EAGER)
	private Cliente cliente;
	
	public ClienteDetalles() {
		
	}

	public Integer getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(Integer cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	@Override
	public int hashCode() {
		return Objects.hash(cod_cliente, direccion, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteDetalles other = (ClienteDetalles) obj;
		return Objects.equals(cod_cliente, other.cod_cliente) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		return "ClienteDetalles [cod_cliente=" + cod_cliente + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}
	
	
	
}


