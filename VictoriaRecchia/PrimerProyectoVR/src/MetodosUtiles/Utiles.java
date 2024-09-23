package MetodosUtiles;

import org.testng.Reporter;

public class Utiles {

	 public static void probando() {
		 String cadena = "hola Mundo";
		 System.out.print(cadena);
		 System.out.println();
		 int nroentero = 10000;
		 System.out.print(nroentero);
		 System.out.println();

	 }
	 
	 public static void escribir(String linea) {
		 System.out.println(linea);
		 Reporter.log(linea);
	 }
}
