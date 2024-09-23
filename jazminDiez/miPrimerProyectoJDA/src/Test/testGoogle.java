package Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class testGoogle {
	private WebDriver driver;
	
	@BeforeMethod
	public void Precondicion() {
		// DRIVER CHROME
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void PostCondicion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
	@Test(description = "Validar que las busquedas en Google funcionan")
	public void ValidarBusquedaGoogle() throws Exception {
		
		//Abrir Google
		driver.get("https://www.google.com.ar/");
		MetodosUtiles.Utiles.step("Abrir Google");
		//Buscar "Selenium"
		WebElement searchInput = driver.findElement(By.id("APjFqb"));
		MetodosUtiles.Utiles.step("Encontrando el campo de busqueda");
		Assert.assertTrue(searchInput.isDisplayed());
		String busqueda="Java"; 
		searchInput.sendKeys(busqueda);
		searchInput.submit();
		MetodosUtiles.Utiles.step("Buscando: "+busqueda);
		
		Thread.sleep(3000);
		
		List<WebElement> Resultados = driver.findElements(By.xpath("//h3[contains(text(),'"+busqueda+"')]"));
		MetodosUtiles.Utiles.step("Guardando resultados de búsqueda");
		
		WebElement primerResultado = Resultados.get(0);
		WebElement tercerResultado = Resultados.get(2);
		System.out.println("Texto encontrado " + primerResultado.getText());
		Assert.assertTrue(primerResultado.getText().contains(busqueda),"El valor '" +busqueda +"' no fue encontrado");
		System.out.println(tercerResultado.getText());
		tercerResultado.click();
		
		Thread.sleep(3000);
		
		List<WebElement> elementosTercerResultado = driver.findElements(By.xpath("//h1[contains(text(),'"+busqueda+"')]"));
		Assert.assertTrue(elementosTercerResultado.size()>0, "No hay un título que contenga el texto "+busqueda);
		driver.close();
	}
}