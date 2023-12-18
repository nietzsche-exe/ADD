package BBDD_relacional;

public class SQL {

	protected static final String CONSULTA_ALUMNOS = "SELECT A.expediente, A.nombre FROM alumnos A";
	protected static final String INSERTAR_ALUMNO = "INSERT INTO alumnos(expediente, nombre) values(?,?)";
	protected static final String GET_ALUMNO_SP = "{call sp_GetAlumno(?)}";
	protected static final String GET_ALUMNO_FUN = "{? = call fun_GetAlumno(?)}";
	
}
