package AAD.UD04E01.JoseJulianSaavedra;

import java.util.ArrayList;
import java.util.List;

public class Distrito {

	 private int codigoDistrito;
	 private String descripcionDistrito;
	 private int numMujeres;
	 private int numHombres;
	
	 private static List<Distrito> distritos = new ArrayList<>();
	 
	 public Distrito() {
		 
	 }

	public Distrito(int codigoDistrito, String descripcionDistrito, int numMujeres, int numHombres) {
		super();
		this.codigoDistrito = codigoDistrito;
		this.descripcionDistrito = descripcionDistrito;
		this.numMujeres = numMujeres;
		this.numHombres = numHombres;
	}

	
	
	public static List<Distrito> getDistritos() {
		return distritos;
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
