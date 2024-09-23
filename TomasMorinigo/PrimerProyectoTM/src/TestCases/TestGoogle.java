package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import MetodosUtiles.Utils;

public class TestGoogle {

	private WebDriver driver;

	@BeforeMethod
	public void Precondicion() {
		// DRIVER CHROME
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();
		
		// DRIVER FIREFOX
		//System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
		//driver = new FirefoxDriver();
		Utils.escribir("Abrimos el navegador en www.google.com");
	}
	
	@AfterMethod
	public void PostCondicion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

	@Test(description = "Validar que las busquedas en Google funcionan")
	public void ValidarBusquedaGoogle() throws Exception {

		driver.get("http://google.com");
		WebElement searchInput = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));

		Utils.escribir("Verificamos que la caja de busqueda este desplegada");
		Assert.assertTrue(searchInput.isDisplayed());

		Utils.escribir("Ingresamos el valor Messi");
		searchInput.sendKeys("Messi");

		Utils.escribir("Presionamos Enter");
		searchInput.submit();

		WebElement tituloResultado = driver
				.findElement(By.xpath("//div[@role='heading'][normalize-space()='Lionel Messi']"));
		
		System.out.println("Texto encontrado " + tituloResultado.getText());

		Utils.escribir("Verificamos que el titulo se ha desplegado");
		Assert.assertTrue(tituloResultado.isDisplayed(), "No se mostró el titulo");

		Utils.escribir("Verificamos que contenga la palabra Messi");
		Assert.assertEquals(tituloResultado.getText(), "Lionel Messi", "El valor Messi no fue mostrado");

		Utils.escribir("Cerramos el navegador");
	}
}
