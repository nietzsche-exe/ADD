package AAD_EX02;

import java.util.List;

/**
 * Esta interfaz expone la firma de los m�todos que los alumnos y alumnas deben implementar para cubrir los requisitos del examen
 * @author Jos� Sala
 * @since 18-12-2023
 */

public interface RequisitosExamen 
{
	/**
	 * Este m�todo debe invocar la funci�n de BD 'ObtenerCapitalPais' para obtener la capital del pais pasado como par�metro
	 * @param nombrePais
	 * @return String
	 */
	public String getCapitalPais(String nombrePais);
	
	/**
	 * Este m�todo debe permitir insertar en la BD de forma transaccional una ciudad, su pais y el idioma de ese pais.
	 * si durante la inserci�n, algo fallara, por ejemplo que el pa�s o idioma ya existiera, no deber�a completarse el alta de la ciudad
	 * @param ciudad
	 * @param pais
	 * @param idioma
	 * @return Boolean
	 */
	public Boolean insertarCiudadPaisIdioma(Ciudad ciudad, Pais pais, IdiomaPais idioma);

	/**
	 * Este m�todo debe permitir el borrado de una ciudad de la bd que coincida con el nombre pasado como par�metro
	 * @param nomCiudad
	 */
	public void borrarCiudad (String nomCiudad);
	
	/**
	 * Este m�todo debe permitir el cambio de jefe de estado de un determinado pa�s en la BD de acuerdo los par�metros pasados
	 * @param nomPais
	 * @param nomJefeDeEstado
	 */
	public void CambiarJefeEstadoDePais (String nomPais, String nomJefeDeEstado);
	
	/**
	 * Este m�todo debe permitir recuperar los paises en los que se haba un determinado idioma
	 * @param idioma
	 * @return List
	 */
	public List<Pais> getPaisPorIdioma(String idioma);
}
