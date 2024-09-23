package Test;

import org.testng.Reporter;
import org.testng.annotations.Test;

import MetodosUtiles.Utiles;

public class TestWiki2 {

	@Test (description = "Validar y verificar")
	
	public void validarConceptosTestingJava() throws Exception {
		Utiles.probando();
		Reporter.log("Caso de prueba");
	}
	
}
