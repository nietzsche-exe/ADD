package es.ciudadescolar.bbddoo;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Clase Principal donde se ejecuta la logica del programa
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023/2024
 */
public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LOGGER.debug("Inicio main");
		
		Scanner input = new Scanner(System.in);
		
		if(args.length != 1) {
			
			LOGGER.debug("Error: El programa espera un parametro (base de datos)");
			System.exit(0);
			
		}
		
		LOGGER.debug("Se ha creado o abierto la base de datos " + args[0]);
		
		BBDDOO gestorBD = new BBDDOO(args[0]);
		
		Alumno a1 = new Alumno("A11", "Paco", 21);
		Alumno a2 = new Alumno("A11", "Manolo", 19);
		Alumno a3 = new Alumno("A13", "Frank", 20);
		Alumno a4 = new Alumno("A14", "Juan", 25);
		
		gestorBD.almacenarAlumno(a1);
		gestorBD.almacenarAlumno(a2);
		gestorBD.almacenarAlumno(a3);
		gestorBD.almacenarAlumno(a4);
		
		List<Alumno> alumnos = gestorBD.getAlumnos();
		
		for (Alumno a : alumnos) {
			System.out.println(a);
		}
		
		
		System.out.println("Introduzca el expediente del alumno a recuperar");
		
		List<Alumno> alumnos2 = gestorBD.getAlumnosEdad(input.nextInt());
		input.close();
		
		if (alumnos2.size() == 0) {
			LOGGER.warn("No hay alumnos con la edad facilitada");
		}
		else {
			for (Alumno a : alumnos2) {
			System.out.println(a);
			}
		}
		
		
		gestorBD.cerrarBD();
		
		LOGGER.debug("Fin main");
	}

}
