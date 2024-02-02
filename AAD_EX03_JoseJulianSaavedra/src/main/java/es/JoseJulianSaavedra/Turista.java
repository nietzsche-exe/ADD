package es.JoseJulianSaavedra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "turista", schema = "agencia_2324")
public class Turista
{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_turista")
	private Integer codigo_turista;
	
	@Column (name = "nom_turista")
	private String nombre_turista;
	
	@Column (name = "ape_turista")
	private String apellido_turista;
	
	@Column (name = "fechaNac_turista")
	private LocalDate fecha_turista;
	
	@OneToMany (mappedBy = "turista", cascade = CascadeType.ALL)
	private List<Reserva> reservas = new ArrayList<Reserva>();
	
	public Turista() {
		
	}

	public Integer getCodigo_turista() {
		return codigo_turista;
	}

	public void setCodigo_turista(Integer codigo_turista) {
		this.codigo_turista = codigo_turista;
	}

	public String getNombre_turista() {
		return nombre_turista;
	}

	public void setNombre_turista(String nombre_turista) {
		this.nombre_turista = nombre_turista;
	}

	public String getApellido_turista() {
		return apellido_turista;
	}

	public void setApellido_turista(String apellido_turista) {
		this.apellido_turista = apellido_turista;
	}

	public LocalDate getFecha_turista() {
		return fecha_turista;
	}

	public void setFecha_turista(LocalDate fecha_turista) {
		this.fecha_turista = fecha_turista;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	public void addReserva(Reserva reserva) {
		if(this.reservas == null) {
			this.reservas = new ArrayList<Reserva>();
		}
		this.reservas.add(reserva);
		reserva.setTurista(this);
	}
	
	public void removeReserva(Reserva reserva) {
		if(this.reservas != null) {
			this.reservas.remove(reserva);
		}
		reserva.setTurista(null);
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido_turista, codigo_turista, fecha_turista, nombre_turista);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turista other = (Turista) obj;
		return Objects.equals(apellido_turista, other.apellido_turista)
				&& Objects.equals(codigo_turista, other.codigo_turista)
				&& Objects.equals(fecha_turista, other.fecha_turista)
				&& Objects.equals(nombre_turista, other.nombre_turista);
	}

	@Override
	public String toString() {
		return "Turista [codigo_turista=" + codigo_turista + ", nombre_turista=" + nombre_turista
				+ ", apellido_turista=" + apellido_turista + ", fecha_turista=" + fecha_turista + ", reservas="
				+ reservas + "]";
	}
	
	
}
