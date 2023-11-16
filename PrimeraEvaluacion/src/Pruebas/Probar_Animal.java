package Pruebas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Probar_Animal {

	static Gato listaGatos [] = new Gato[0];
	static ArrayList<Perro> listaPerro = new ArrayList<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean error = false;
		String tipo = null;
		String sexo = null;
		Integer codigo = null;
		
		do {
		try {
			
			InputStreamReader scan = new InputStreamReader(System.in);
			BufferedReader input = new BufferedReader(scan);
			
			System.out.println("Introduzca 1 gato con el siguiente formato: (Tipo, Sexo, Codigo)");
		
			listaGatos = Arrays.copyOf(listaGatos, listaGatos.length+1);
			tipo = input.readLine();
			sexo = input.readLine();
			codigo = Integer.parseInt(input.readLine());
			listaGatos[listaGatos.length-1] = new Gato(tipo, sexo, codigo);
			
		
		
		} catch(IOException e) {
			System.err.println("error de entrada");
			error = true;
		} catch(NumberFormatException e) {
			System.out.println("error de formato");
		}
		
		}while(listaGatos.length < 3);
		
		
		
		System.out.println("GATOS:");
		for (int i = 0; i < listaGatos.length; i++) {
			System.out.println(listaGatos[i]);
		}
		
		
		listaPerro.add(new Perro("hasky", "macho", 0, "Hachiko"));
		listaPerro.add(new Perro("rotwailer", "hembra", 1, "Princesa"));
		listaPerro.add(new Perro("meztizo", "hembra", 2, "Layka"));
		
		System.out.println("PERROS:");
		for (int i = 0; i < listaPerro.size(); i++) {
			System.out.println(listaPerro.get(i));
		}
		
		listaGatos[0].hazRuido();
		listaGatos[1].come();
		
		listaPerro.get(0).come();
		listaPerro.get(1).hazRuido();
		
		
	}

}
