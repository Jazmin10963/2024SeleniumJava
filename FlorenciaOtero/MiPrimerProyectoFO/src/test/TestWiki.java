package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 //ctrl shift f
//agregar un assert por test
//cada clase tiene que tener 2 test
//firefox
public class TestWiki {
	WebDriver driver;

	@BeforeMethod
	public void Precondition() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\admin\\Desktop\\Testing\\MiPrimerProyecto\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://wikipedia.org");
	}

	@AfterMethod
	public void PostCondition() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

	@Test(description = "Validar que las busquedas en Wikipedia funcionan")
	public void ValidarBusquedaWikipedia() throws Exception {
		WebElement searchInput = driver.findElement(By.id("searchInput"));
		Assert.assertTrue(searchInput.isDisplayed());
		searchInput.sendKeys("Selenium");
		searchInput.submit();
		WebElement tituloResultado = driver.findElement(By.id("firstHeading"));
		System.out.println("Texto encontrado " + tituloResultado.getText());
		Assert.assertTrue(tituloResultado.isDisplayed());
	}

	@Test(description = "Validar que las busquedas en Wikipedia funcionan")
	public void ValidarBusquedaWikipedia2() throws Exception {
		WebElement searchInput = driver.findElement(By.id("searchInput"));
		Assert.assertTrue(searchInput.isDisplayed());
		searchInput.sendKeys("Selenium");
		searchInput.submit();
		WebElement tituloResultado = driver.findElement(By.id("firstHeading"));
		System.out.println("Texto encontrado " + tituloResultado.getText());
		Assert.assertTrue(tituloResultado.isDisplayed());
	}
}