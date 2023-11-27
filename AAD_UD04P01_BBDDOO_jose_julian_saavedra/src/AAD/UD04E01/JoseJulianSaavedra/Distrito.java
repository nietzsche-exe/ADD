package AAD.UD04E01.JoseJulianSaavedra;

/**
 * Clase Distrito 
 * Gestiona las instancias de los Distritos recogidos desde el XML
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023-2024
 */
public class Distrito {

	 private int codigoDistrito;
	 private String descripcionDistrito;
	 private int numMujeres;
	 private int numHombres;
	
	 
	 
	 public Distrito() {
		 
	 }

	public Distrito(int codigoDistrito, String descripcionDistrito, int numMujeres, int numHombres) {
		super();
		this.codigoDistrito = codigoDistrito;
		this.descripcionDistrito = descripcionDistrito;
		this.numMujeres = numMujeres;
		this.numHombres = numHombres;
	}



	public int getCodigoDistrito() {
		return codigoDistrito;
	}

	public void setCodigoDistrito(int codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
	}

	public String getDescripcionDistrito() {
		return descripcionDistrito;
	}

	public void setDescripcionDistrito(String descripcionDistrito) {
		this.descripcionDistrito = descripcionDistrito;
	}

	public int getNumMujeres() {
		return numMujeres;
	}

	public void setNumMujeres(int numMujeres) {
		this.numMujeres = numMujeres;
	}

	public int getNumHombres() {
		return numHombres;
	}

	public void setNumHombres(int numHombres) {
		this.numHombres = numHombres;
	}

	@Override
	public String toString() {
		return "Distrito [codigoDistrito=" + codigoDistrito + ", descripcionDistrito=" + descripcionDistrito
				+ ", numMujeres=" + numMujeres + ", numHombres=" + numHombres + "]";
	}
	 
	 
	
}
