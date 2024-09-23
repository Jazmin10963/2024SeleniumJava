package Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestWiki {
	
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
	@Test(description = "Validar que las busquedas en Wikipedia funcionan")
	public void ValidarBusquedaWikipedia() throws Exception {
		driver.get("http://wikipedia.org");
		MetodosUtiles.Utiles.step("Abrimos la pagina http://wikipedia.org");
		WebElement searchInput = driver.findElement(By.id("searchInput"));
		
		Assert.assertTrue(searchInput.isDisplayed());
		MetodosUtiles.Utiles.step("Buscamos el campo de busqueda y chequeamos que esté visible");
		
		searchInput.sendKeys("Selenium");
		searchInput.submit();
		MetodosUtiles.Utiles.step("Buscamos 'Selenium'");
		
		WebElement tituloResultado = driver.findElement(By.id("firstHeading"));
		System.out.println("Texto encontrado " + tituloResultado.getText());
		Assert.assertTrue(tituloResultado.isDisplayed(),"No se encontró el título de la página");
		driver.close();
	}
}