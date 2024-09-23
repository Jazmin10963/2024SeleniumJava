package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestGoogle {
	@Test(description = "Validar que busquedas en Google")
	
	public void validarBusquedaGoogle() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		Reporter.log("Ingreso a google");
		driver.get("http://google.com");
		WebElement searchInput = driver.findElement(By.id("APjFqb"));
		Reporter.log("Verificamos que la caja de busqueda esté desplegada");
		Assert.assertTrue(searchInput.isDisplayed());
		searchInput.sendKeys("The Smiths");
		Reporter.log("Presionamos enter");
		searchInput.submit();
		
		Reporter.log("Verificamos que el titulo se ha desplegado");
		WebElement tituloResultado = driver.findElement(By.xpath("//div[@class='PZPZlf ssJ7i B5dxMb']"));
		
		System.out.println("Texto encontrado " + tituloResultado.getText());
		
		Assert.assertTrue(tituloResultado.isDisplayed(), "No se mostró el titulo");
		Reporter.log("Comparo el resultado con el texto ingresado");	
		Assert.assertEquals(tituloResultado.getText(), "The Smiths");
		
		Reporter.log("Clickeo el tercer elemento");
		WebElement tercerElemento = driver.findElement(By.xpath("//h3[contains(text(),'Morrissey aceptó reunir a The Smiths en 2025 pero ')]"));
		tercerElemento.click();
		
		Reporter.log("Valido un elemento de la pagina resultante");
		WebElement elementoNuevaPag = driver.findElement(By.className("storyTitle"));
		System.out.println("Titulo de la página: " + elementoNuevaPag.getText());
		Reporter.log("Cierro la página");
		driver.close();
	}
}
