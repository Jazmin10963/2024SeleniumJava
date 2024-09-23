package Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestDemoBlaze {
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeMethod
	public void Precondicion() {
		// DRIVER CHROME
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@AfterMethod
	public void PostCondicion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

	@Test(description = "Validar que al presionar la categoría Laptops se filtren los productos de esa categoría")
	public void ValidarCategoriaLaptops() throws Exception {
		// Abrimos el demoblaze
		driver.get("https://www.demoblaze.com/index.html");
		MetodosUtiles.Utiles.step("Abrimos la pagina web www.demoblaze.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Samsung galaxy s6']")));
		WebElement catLaptop = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[normalize-space()='Laptops'])[1]")));
		MetodosUtiles.Utiles.step("buscamos el botón correspondiente a la categoría Laptops y chequeamos que esté visible");
		Assert.assertTrue(catLaptop.isDisplayed());
		List<WebElement> allProductsBefore = driver.findElements(By.xpath("(//div[@class='col-lg-4 col-md-6 mb-4'])"));
        int totalProductsBefore = allProductsBefore.size();
                MetodosUtiles.Utiles.step("Chequeamos la cantidad de productos antes de filtrar por categoría, son "+totalProductsBefore);
        MetodosUtiles.Utiles.step("Hacemos click en la categoría Laptops");
		catLaptop.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Sony vaio i5']")));
		List<WebElement> allProductsAfter = driver.findElements(By.xpath("(//div[@class='col-lg-4 col-md-6 mb-4'])"));
		int totalProductsAfter = allProductsAfter.size();
        MetodosUtiles.Utiles.step("Chequeamos la cantidad de productos después de filtrar por categoría, son "+totalProductsAfter);
		
	}

	private By ByXPath(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
