package AAD.UD02E01.JoseJulianSaavedra;

import java.io.File;

public class FicherosClase {

	public static void recorrerDirectorio(String ruta) {

		File directorio = new File(ruta);

		if (!directorio.exists() || !directorio.isDirectory()) {
			System.err.println("La ruta no se encuentra o no es un directorio");
		} else if (directorio.listFiles().length == 0) {
			System.err.println("El directorio " + directorio.getName() + " esta vacio");
		}

		else {

			String propiedades_directorio = new String();

			if (directorio.canRead()) {
				propiedades_directorio += "|R";
			} else {
				propiedades_directorio += "|";
			}
			if (directorio.canWrite()) {
				propiedades_directorio += "|W";
			} else {
				propiedades_directorio += "|";
			}
			if (directorio.isHidden()) {
				propiedades_directorio += "|H";
			} else {
				propiedades_directorio += "|";
			}

			System.out.println("D|" + directorio.getAbsolutePath() + propiedades_directorio);

			for (int file = 0; file < directorio.listFiles().length; file++) {

				if (directorio.listFiles()[file].isDirectory()) {
					FicherosClase.recorrerDirectorio(directorio.listFiles()[file].getAbsolutePath());
				}

				if (directorio.listFiles()[file].isFile()) {

					String propiedades_ficheros = new String();

					if (directorio.listFiles()[file].canRead()) {
						propiedades_ficheros += "|R";
					} else {
						propiedades_ficheros += "|";
					}
					if (directorio.listFiles()[file].canWrite()) {
						propiedades_ficheros += "|W";
					} else {
						propiedades_ficheros += "|";
					}
					if (directorio.listFiles()[file].isHidden()) {
						propiedades_ficheros += "|H";
					} else {
						propiedades_ficheros += "|";
					}

					System.out.println("F|" + directorio.getAbsolutePath() + propiedades_ficheros);
				}

			}

		}

	}
}
