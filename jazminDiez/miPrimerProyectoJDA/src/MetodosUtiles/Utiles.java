package MetodosUtiles;

import org.testng.Reporter;

public class Utiles{
	public static void step(String linea) {
		System.out.println("Paso: " + linea);
		Reporter.log("Paso: " + linea);
	}
}
