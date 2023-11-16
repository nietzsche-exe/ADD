package Pruebas;

import java.util.ArrayList;
import java.util.Scanner;

public class Probar_Vehiculo {

	static ArrayList <Vehiculo> listaVehiculos  = new ArrayList<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner input = new Scanner(System.in);
		Integer opcion = 0;
		
		do {
			
			System.out.println( "Opcion 1: dar de alta un coche (marca, modelo, potencia) "
								+ "\n Opcion 2: mostrar todos los coches  "
								+ "\n  Opcion 3: salir ");
								
			opcion = input.nextInt();
			
			switch (opcion) {
			
			case 1:  
				darAlta();
				break;
			case 2:
				mostrarCoches();
				break;
			case 3:
				System.out.println("Fin del programa");
				break;
			default:
				System.err.println("Numero no valido");
				break;
			}
			
		}while(opcion!=3);
		
		input.close();
		
	}
	
	public static void darAlta() {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Introduzca marca, modelo y potencia: ");
		
		String marca = input.next();
		String modelo = input.next();
		Integer potencia = input.nextInt();
		
		listaVehiculos.add(new Vehiculo(marca, modelo, potencia));
		
	}
	
	public static String mostrarCoches() {
		return listaVehiculos.toString();
				
	}

}
