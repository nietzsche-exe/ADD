package AAD.UD02E01.JoseJulianSaavedra;

import java.io.File;

public class FicherosClase {

	public static void borrarDirectorio(String ruta) {

		File directorio = new File(ruta);

		while(!directorio.delete()) {
		
		if (!directorio.exists() || !directorio.isDirectory()) {
			System.err.println("La ruta no se encuentra o no es un directorio");
		} else if (directorio.listFiles().length == 0) {
			System.err.println("El directorio " + directorio.getName() + " esta vacio");
			directorio.delete();
		}

		else {

			//borrar directorios

			for (int file = 0; file < directorio.listFiles().length; file++) {

				if (directorio.listFiles()[file].isDirectory()) {
					FicherosClase.borrarDirectorio(directorio.listFiles()[file].getAbsolutePath());
				}

				if (directorio.listFiles()[file].isFile()) {

					directorio.listFiles()[file].delete();
					
				}

			}

		}

		}
	}
}
