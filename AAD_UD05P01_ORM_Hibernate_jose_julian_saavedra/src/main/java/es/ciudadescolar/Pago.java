package es.ciudadescolar;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "payments")
public class Pago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "payment_id")
	private Integer cod_pagos;
	
	@Column (name = "customer_id")
	private Integer cod_cliente;
	
	@Column (name = "amount")
	private Double cantidad;
	
	@Column (name = "payment_date")
	private LocalDate fecha;
	
	@OneToMany (fetch = FetchType.EAGER)
	@JoinColumn (name = "customer_id")
	private Cliente cliente;
	
	public Pago() {
		
	}

	public Integer getCod_pagos() {
		return cod_pagos;
	}

	public void setCod_pagos(Integer cod_pagos) {
		this.cod_pagos = cod_pagos;
	}

	public Integer getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(Integer cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod_pagos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pago other = (Pago) obj;
		return Objects.equals(cod_pagos, other.cod_pagos);
	}

	@Override
	public String toString() {
		return "Pago [cod_pagos=" + cod_pagos + ", cod_cliente=" + cod_cliente + ", cantidad=" + cantidad + ", fecha="
				+ fecha + "]";
	}
	
	
	
}
