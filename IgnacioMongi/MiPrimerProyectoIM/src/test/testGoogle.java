package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class testGoogle {

	@Test(description = "Validar que las busquedas en Google funcionan")
	public void ValidarBusquedaGoogle() throws Exception {
		
		//busco el driver en el archivo MiPrimerProyecto/drivers/chromedriver.exe 
	System.setProperty("webdriver.chrome.driver", 
			"./drivers/chromedriver.exe");
	
	//1) creo un objeto driver
	WebDriver driver = new ChromeDriver();
	
	
	Reporter.log("2) Abrimos el navegador en www.google.com");
	driver.get("http://google.com");
	
	WebElement searchInput = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
	Reporter.log("3) Verificamos que la caja de busqueda este desplegada");
	Assert.assertTrue(searchInput.isDisplayed());	
	Reporter.log("Ingresamos el valor Messi");
	searchInput.sendKeys("Lionel Messi");
	Reporter.log("Presionamos Enter");
	searchInput.submit();
	Reporter.log("Verificamos que el titulo se ha desplegado"); 
	WebElement tituloResultado = driver.findElement(By.xpath("//div[@role='heading'] [normalize-space()='Lionel Messi']"));
	Reporter.log("Verificamos que el titulo se ha desplegado"); System.out.println("Texto encontrado "+ tituloResultado.getText());
	Assert.assertTrue(tituloResultado.isDisplayed(), "No se mostró el titulo");
	Reporter.log("Verificamos que contenga el nombre 'Lionel Messi' ");
	Assert.assertEquals(tituloResultado.getText(), "Lionel Messi", "El valor Messi no fue mostrado");
	driver.close();
	}
}
