package AAD.UD02E01.JoseJulianSaavedra;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			FicherosClase.recorrerDirectorio(args[0]);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Numero de parametros encontrados " + "[" + args.length +"]" + ", se espera [1]");
		}
		
		
		
		
		
	}

}
