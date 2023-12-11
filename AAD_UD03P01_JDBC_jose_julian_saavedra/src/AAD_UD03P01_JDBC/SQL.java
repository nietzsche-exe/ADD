package AAD_UD03P01_JDBC;

/**
 * Clase SQL. En ella se enceuntran las distintas consultas que luego se usaran en la clase BBDD
 * 
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023-2024
 */
public class SQL {

	protected static final String INSERTAR_ACTOR = "INSERT INTO actores(nombre) values(?)";
	protected static final String INSERTAR_PELICULA = "INSERT INTO peliculas(titulo, anio) values(?,?)";
	protected static final String INSERTAR_INTERPRETE = "INSERT INTO interpretes (cod_actor, cod_pelicula, es_protagonista) VALUES (?, ?, ?)";
	protected static final String BUSCAR_ACTOR = "SELECT * FROM actores WHERE nombre = ?";
	protected static final String BUSCAR_PELICULA = "SELECT * FROM peliculas WHERE titulo = ?";
	protected static final String NUMERO_ACTORES_POR_PELICULAS= "{? = call fun_GetNumActores(?)}";
	protected static final String BUSCAR_PELICULAS_POR_ACTOR = "SELECT peliculas.titulo "
															+ "FROM peliculas "
															+ "JOIN interpretes ON peliculas.cod_peli = interpretes.cod_pelicula "
															+ "JOIN actores ON interpretes.cod_actor = actores.cod_act "
															+ "WHERE actores.nombre = ?;";
}
