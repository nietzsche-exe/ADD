package es.JoseJulianSaavedra;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "reserva", schema = "agencia_2324")
public class Reserva 
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_reserva")
	private Integer codigo_reserva;

	@Column (name = "nom_hotel")
	private String hotel_reserva;
	
	private LocalDate fecha_entrada;
	
	private LocalDate fecha_salida;
	
	@ManyToOne
	@JoinColumn (name = "turista")
	private Turista turista;
	
	
	public Reserva() {
		
	}


	public Integer getCodigo_reserva() {
		return codigo_reserva;
	}


	public void setCodigo_reserva(Integer codigo_reserva) {
		this.codigo_reserva = codigo_reserva;
	}


	public String getHotel_reserva() {
		return hotel_reserva;
	}


	public void setHotel_reserva(String hotel_reserva) {
		this.hotel_reserva = hotel_reserva;
	}


	public LocalDate getFecha_entrada() {
		return fecha_entrada;
	}


	public void setFecha_entrada(LocalDate fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}


	public LocalDate getFecha_salida() {
		return fecha_salida;
	}


	public void setFecha_salida(LocalDate fecha_salida) {
		this.fecha_salida = fecha_salida;
	}


	public Turista getTurista() {
		return turista;
	}


	public void setTurista(Turista turista) {
		this.turista = turista;
	}


	@Override
	public int hashCode() {
		return Objects.hash(codigo_reserva, fecha_entrada, fecha_salida, hotel_reserva);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(codigo_reserva, other.codigo_reserva)
				&& Objects.equals(fecha_entrada, other.fecha_entrada)
				&& Objects.equals(fecha_salida, other.fecha_salida)
				&& Objects.equals(hotel_reserva, other.hotel_reserva);
	}


	@Override
	public String toString() {
		return "Reserva [codigo_reserva=" + codigo_reserva + ", hotel_reserva=" + hotel_reserva + ", fecha_entrada="
				+ fecha_entrada + ", fecha_salida=" + fecha_salida + "]";
	}
	
	
}
