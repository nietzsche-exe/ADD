package AAD.UD02E03.JoseJulianSaavedra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner input = null;
		RandomAccessFile fich = null;
		File fichero = null;

		if (args.length != 1) {
			System.out.println("Error: Se espera un parametro con la ruta del fichero");
		} else {
			fichero = new File(args[0]);
			if (fichero.exists()) {
				System.out.println("Error: El fichero ya existe y se ha borrado, ejecute nuevamente");
				fichero.delete();
			} else {
				try {
					fich = new RandomAccessFile(fichero, "rw");
					
						fich.writeChars("abcdefghijklmnñopqrstuvwxyz");
						
					fich = new RandomAccessFile(fichero, "rw");

					System.out.println(
							"El fichero [" + fichero.getAbsolutePath() + "] ocupa " + fichero.length() + " bytes");
					System.out.println("Antes de posicionar el puntero, este se encuentra en: " + fich.getFilePointer());

					int posicion = -1;
					do {
						try {

							input = new Scanner(System.in);
							System.out.println("Indique el numero de la letra del abecedarioa mostrar [0 - 27]" );
							posicion = input.nextInt() * 2;
							input.close();

						} catch (InputMismatchException e) {
							System.out.println("Error: opcion incorrecta");
						}

					} while (posicion < 0 && posicion > fich.length());

					fich.seek(posicion - 2); // posiciono el puntero en la posicion a leer

					System.out.println("El puntero se encuentra ahora en la posicion: [" + fich.getFilePointer() + "]");
					System.out.println(fich.readChar());
					
					
				} catch (FileNotFoundException e) {
					System.out.println("Error: El fichero no existe");
				} catch (IOException e) {
					System.out.println("Error de escritura");
				} finally {
					try {	
						fich.close();
					
					} catch (IOException e) {
						System.out.println("error al cerrar fichero");
					}
				}
			}

		}
	
	}
	
}
