package BBDD_relacional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BBDD bd = null;
		List<Alumno> alumnos = null;
		
		try {
			
			bd = new BBDD();
			alumnos = bd.getTodosAlumnos();
			
			LOGGER.info("Los alumnos recueprados son: ");
			for (Alumno alumno : alumnos) {
				LOGGER.info(alumno.toString());
			}
			
			
			Scanner input = new Scanner(System.in);
			
			/* 
			System.out.println("Escriba el expediente del alumno");
			String expediente = input.nextLine();
			
			System.out.println("Escriba el nombre del alumno");
			String nombre = input.nextLine();
			
			bd.insertarAlumno(expediente, nombre);
			*/
			
			/*
			System.out.println("Escriba el expediente del alumno a consultar");
			String expediente = input.nextLine();
			Alumno alumno = bd.getAlumnoSP(expediente);
			if(alumno != null) {
				LOGGER.info("Se ha consultado el alumno [" + expediente + "]:" + alumno);
			}
			else {
				LOGGER.warn("Se ha consultado el alumno [" + expediente + "] pero no se ha encontrado en la BD");
			}
			*/
			
			/*
			System.out.println("Escriba el nombre del alumno a consultar:");
			String nombre = input.nextLine();
			Alumno alumno = bd.getAlumnoFUN(nombre);
			if(alumno != null) {
				LOGGER.info("Se ha consultado el alumno [" + nombre + "]:" + alumno);
			}
			else {
				LOGGER.warn("Se ha consultado el alumno [" + nombre + "] pero no se ha encontrado en la BD");
			}
			*/
			
			
			//bd.insertTransaccionAlumnos();
			
			
		}
		catch(ClassNotFoundException e) {
			LOGGER.error("Ha habido un error durante el regsitro del driver mysql");
		}
		catch(SQLException e) {
			LOGGER.error("Ha habido un error durante la interaccion con la BD");
		}
		catch(FileNotFoundException e) {
			LOGGER.error("Fichero de propiedades no encontrado");
		} catch (IOException e) {
			LOGGER.error("Error de E/S");
		}
		finally {
			
			if(bd != null) 
			bd.cerrarBD();
			
		}
		
		
		
	}

}
