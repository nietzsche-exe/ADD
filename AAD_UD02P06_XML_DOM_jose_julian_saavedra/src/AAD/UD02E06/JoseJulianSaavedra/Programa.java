package AAD.UD02E06.JoseJulianSaavedra;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;

import org.xml.sax.SAXException;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
		
		// Variables para el manejo de ficheros
		File ficheroTxt = new File("listaCoches.txt");
		System.out.println("Escriba la direccion del fichero .xml:");
		String ficheroXml = input.nextLine();

		input.close();
		
		//Llamadas a los metodos de la clase Xml
		try {
			
			Xml.analizarDOM(ficheroXml);
			Xml.escribirCochesFichero(ficheroTxt);
			Xml.escribirXml();
			

		//Manejo de Excepciones lanzadas desde la clase Xml 	
		} catch (ParserConfigurationException e) {
			System.err.println("Error parser");
		} catch (SAXException e) {
			System.err.println("Error Saxe");
		} catch (IOException e) {
			System.err.println("Error: no se encuentra el documento xml coreespondiente");
		} catch (TransformerException e) {
			System.err.println("Error de Transforamcion");
		}

		mostrarInventario();
		
	}
	
	/*
	 * Metodo para mostrar el inventario al finalizar el programa
	 * Lo he definido en el main ya que pienso que es el lugar mas apropiado, 
	 * puesto que la clase Xml es para gestionar los archivos y la clase Coche gestiona los objetos
	 *   
	 */
	public static void mostrarInventario() {

		int numeroCoches = Coche.listaCoches.size();
		int valorTotal = 0;

		for (int i = 0; i <= numeroCoches - 1; i++) {

			valorTotal += Coche.listaCoches.get(i).getPrecio();

		}

		System.out.println(
				"El concesionario dispone de " + numeroCoches + " coches con un valor total de " + valorTotal + " €");

	}

}
