package es.ciudadescolar;

public class SQL 
{
	protected static final String insertarPelicula = "INSERT INTO peliculas(titulo,anio) values(?,?)";
	protected static final String consultaCodigoPelicula = "SELECT A.cod_peli FROM peliculas A WHERE A.titulo = ?";
	
	protected static final String insertarActor = "INSERT INTO actores(nombre) values(?)";
	protected static final String consultaCodigoActor = "SELECT A.cod_act FROM actores A WHERE A.nombre = ?";
	
	protected static final String insertarInterprete = "INSERT INTO interpretes(cod_pelicula,cod_actor,es_protagonista) values(?,?,?)";
	
	protected static final String invocacionGetNumActoresFUN= "{ ? = call fun_GetNumActores(?)}";
	
	protected static final String consultaPeliculasPorActor = "SELECT P.titulo, P.anio FROM peliculas P, actores A, interpretes I WHERE I.cod_actor = A.cod_act and I.cod_pelicula = P.cod_peli AND A.nombre = ?";
	
}
