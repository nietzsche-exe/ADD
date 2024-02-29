package modelo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="direcciones", schema="peliculas_orm_2324")
public class Direccion implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	@Column (name="cod_dir")
	private Integer codigo_direccion;
	
	@Column (name="calle")
	private String calle_direccion;
	
	@Column (name="numero")
	private Integer numero_direccion;

	public Direccion()
	{
		
	}

	public Integer getCodigo_direccion() {
		return codigo_direccion;
	}

	public void setCodigo_direccion(Integer codigo_direccion) {
		this.codigo_direccion = codigo_direccion;
	}

	public String getCalle_direccion() {
		return calle_direccion;
	}

	public void setCalle_direccion(String calle_direccion) {
		this.calle_direccion = calle_direccion;
	}

	public Integer getNumero_direccion() {
		return numero_direccion;
	}

	public void setNumero_direccion(Integer numero_direccion) {
		this.numero_direccion = numero_direccion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo_direccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion other = (Direccion) obj;
		return Objects.equals(codigo_direccion, other.codigo_direccion);
	}

	@Override
	public String toString() {
		return "Direccion [codigo_direccion=" + codigo_direccion + ", calle_direccion=" + calle_direccion
				+ ", numero_direccion=" + numero_direccion + "]";
	}
	
	
	
	
}
