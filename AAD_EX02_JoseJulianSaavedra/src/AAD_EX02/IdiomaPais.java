package AAD_EX02;

public class IdiomaPais 
{
	private char[] codigoPais;
	private String idioma; 
	private Boolean esOficial;
	private Float porcentajeUso;
	
	public char[] getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(char[] codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public Boolean getEsOficial() {
		return esOficial;
	}
	public void setEsOficial(Boolean esOficial) {
		this.esOficial = esOficial;
	}
	public Float getPorcentajeUso() {
		return porcentajeUso;
	}
	public void setPorcentajeUso(Float porcentajeUso) {
		this.porcentajeUso = porcentajeUso;
	}
	@Override
	public String toString() {
		return "IdiomaPais [codigoPais=" + String.valueOf(codigoPais) + ", idioma=" + idioma + ", esOficial="
				+ esOficial + ", porcentajeUso=" + porcentajeUso + "]";
	}

}
