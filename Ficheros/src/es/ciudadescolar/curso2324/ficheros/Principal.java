package es.ciudadescolar.curso2324.ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InputStreamReader scan = new InputStreamReader(System.in); 
		BufferedReader input = new BufferedReader(scan);
		
		Integer opcion;
		File dirActual = new File(System.getProperty(args[0]));
		File fichero = null;
		File directorio = null;
		boolean salir = false;
		
		while(salir == false) {
			
			System.out.println("1. Modificar directorio de trabajo");
			System.out.println("2. Crear nuevo directorio");
			System.out.println("3. Crear nuevo fichero");
			System.out.println("4. Renombrar fichero");
			System.out.println("5. Borrar archivo");
			System.out.println("6. Borrar directorio");
			System.out.println("7. Listar directorio");
			System.out.println("8. Propiedades de un fichero");
			System.out.println("9. Salir");
			
			
			try {
				System.out.println("Indique la opcion Deseada: ");
				opcion = Integer.parseInt(input.readLine());
				
				if (opcion > 9) {
					System.err.println("Por favor marque una opcion disponible:");
				}
				
				switch(opcion) {
				
				case 1:
					System.out.println("El directorio de trabajo actual es " + System.getProperty("user.dir"));
					System.out.println("Introduzca nuevo directorio");
					String ruta = input.readLine();
					if(!new File(ruta).exists()) {
						System.err.println("La ruta indicada no existe: " + ruta);
					}
					else {
						dirActual = new File(ruta);
					}
					break;
					
				case 2:
					System.out.println("Introduzca el nombre del nuevo directorio: ");
					String nameDir = input.readLine();
					directorio = new File(dirActual, nameDir);
					directorio.mkdir();
					System.out.println("Se ha creado un nuevo directorio: " + directorio);
					break;
					
				case 3: 
					System.out.println("Introduzca el nombre del nuevo fichero: ");
					String nameFichero = input.readLine();
					fichero = new File(dirActual, nameFichero);
					fichero.createNewFile();
					System.out.println("Se ha creado un nuevo fichero: " + fichero);
					break;
					
				case 4:
					System.out.println("Que fichero quiere renombrar?: ");
					String oldFich = input.readLine();
					fichero = new File(dirActual, oldFich);
					
					if(fichero.exists() && fichero.isFile()) {
						System.out.println("Introduzca el nuevo nombre del fichero: ");
						String newFich = input.readLine();
						File ficheroRenombrado = new File(dirActual, newFich);
						fichero.renameTo(ficheroRenombrado);
						System.out.println("Se ha renombrado el fichero: " + ficheroRenombrado.getAbsolutePath());
					}
					else {
						System.err.println("El fichero original no existe");
					}
				
					break;
					
				case 5:
					System.out.println("Que fichero quiere borrar?: ");
					String deleteFich = input.readLine();
					fichero = new File(dirActual, deleteFich);
					
					if(fichero.exists() && fichero.isFile()) {
						fichero.delete();
						System.out.println("Se ha borrado el fichero: " + fichero.getAbsolutePath());
					}
					else {
						System.err.println("No exite el fichero");
					}
					break;
					
				case 6:
					System.out.println("Que directorio VACIO quiere borrar?:");
					String deleteDir = input.readLine();
					directorio = new File(dirActual, deleteDir);
					
					if(directorio.exists() && directorio.isDirectory() && directorio.list().length == 0) {
						directorio.delete();
						System.out.println("Se ha borrado el directorio: " + directorio.getAbsolutePath());
					}
					else {
						System.err.println("El directorio no existe o esta vacio");
					}
					break;
					
				case 7:
					System.out.println("Que directorio quiere listar?:");
					String listDir = input.readLine();
					directorio = new File(dirActual, listDir);
					
					if(!directorio.exists()) {
						System.err.println("El directorio no existe");
					}
					else {
						if(!directorio.isDirectory()) {
							System.err.println("No es un directorio");
						}
						else {
							File[] ficheros = directorio.listFiles();
							
							if(ficheros.length == 0) {
								System.err.println("El fichero esta vacio");
							}
							else {
								for(File fich:ficheros) {
									System.out.println(fich.getName());
								}
							}
						}
					}
					
					break;
					
				case 8:
					System.out.println("Que fichero quiere examinar?: ");
					String examFich = input.readLine();
					fichero = new File(dirActual, examFich);
					
					if(fichero.exists() && fichero.isFile()) {
						
						String propiedades = new String();
						if (fichero.canRead()) {
							propiedades = propiedades + "|lectura";
						}
						else {
							propiedades = propiedades + "|";
						}
						if (fichero.canWrite()) {
							propiedades = propiedades + "|escritura";
						}
						else {
							propiedades = propiedades + "|";
						}
						if (fichero.isHidden()) {
							propiedades = propiedades + "|oculto";
						}
						else {
							propiedades = propiedades + "|";
						}
						
						System.out.println(fichero.getName() + propiedades);
						
					}
					else {
						System.err.println("No existe el fichero");
					}
					break;
					
				case 9:
					salir = true;
					break;
				
				}
			} 
			
			catch(InputMismatchException e) {
				System.out.println("Debe introducir un numero");
				
			} catch (NumberFormatException e) {
				System.err.println("Error de formato");
			} catch (IOException e) {
				System.err.println("Error de E/S");
			}
			
			
			
		}
		
		
		
		
		
		
		
		
	}

}
