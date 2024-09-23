package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//HOLA
public class TestGoogle {
	WebDriver driver;

	@BeforeMethod
	public void Precondition() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\admin\\Desktop\\Testing\\MiPrimerProyecto\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://google.com");
	}

	@AfterMethod
	public void PostCondition() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

	@Test(description = "Validar que las busquedas en Google funcionan")
	public void ValidarBusquedaGoogle() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\admin\\Desktop\\Testing\\MiPrimerProyecto\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Reporter.log("Abrimos el navegador en www.google.com");
		driver.get("http://google.com");
		WebElement searchInput = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
		Reporter.log("Verificamos que la caja de búsqueda esté desplegada");
		Assert.assertTrue(searchInput.isDisplayed());
		Reporter.log("Ingresamos el valor The Beatles");
		searchInput.sendKeys("The Beatles");
		Reporter.log("Presionamos Enter");
		searchInput.submit();
		Reporter.log("Verificamos que el título se ha desplegado");
		WebElement tituloResultado = driver.findElement(By.xpath(
				"//body/div[@id='main']/div[@id='cnt']/div[@id='rcnt']/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"));
		Reporter.log("Verificamos que el título se ha desplegado");
		System.out.println("Texto encontrado " + tituloResultado.getText());
		Assert.assertTrue(tituloResultado.isDisplayed(), "No se mostró el título");
		Reporter.log("Verificamos que contenga la palabra The Beatles");
		Assert.assertEquals(tituloResultado.getText(), "The Beatles");
		driver.close();
	}

	@Test(description = "Verificar aspectos de los resultados de búsqueda")
	public void ValidarAspectosResultados() throws Exception {
		WebElement searchInput = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
		Reporter.log("Ingresamos el valor The Beatles");
		searchInput.sendKeys("The Beatles");
		Reporter.log("Presionamos Enter");
		searchInput.submit();

		// Verificar que el texto "The Beatles" tiene 11 carácteres
		String textoBusqueda = "The Beatles";
		Reporter.log("Verificamos que el texto 'The Beatles' tiene 11 caracteres");
		Assert.assertEquals(textoBusqueda.length(), 11, "El texto 'The Beatles' no tiene 11 caracteres");

		// Verificar que la búsqueda no es null
		WebElement searchBox = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
		Reporter.log("Verificamos que la búsqueda no sea null");
		Assert.assertNotNull(searchBox.getAttribute("value"), "La búsqueda es null");
	}
}
