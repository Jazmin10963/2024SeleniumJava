package Test;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import MetodosUtiles.Utiles;

public class TestDemoBlaze {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void precondicion() {
	
		System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://demoblaze.com/index.html");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@AfterMethod
	public void postcondicion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

	@Test(description = "Verificar que funcione el boton eliminar producto del carrito")
	public void validarBotonDelete() throws Exception {

		Utiles.escribir("Ingreso a DemoBlaze");

		Utiles.escribir("Selecciono un producto del Home");

		Utiles.escribir("Obtener titulo del quinto elemento");
		WebElement producto = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html[1]/body[1]/div[5]/div[1]/div[2]/div[1]/div[5]/div[1]/div[1]/h4[1]/a[1]")));
		producto.click();

		Utiles.escribir("Presiono boton Add to Cart");
		WebElement botonAddToCart = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btn btn-success btn-lg']")));
		botonAddToCart.click();

		Thread.sleep(2000);

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Utiles.escribir("Cierro la ventana emergente");
		alert.accept();

		Utiles.escribir("Presiono boton Cart");
		WebElement botonCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='cartur']")));
		botonCart.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[4]/a[1]")));

		Utiles.escribir("Presiono boton delete");
		WebElement botonDelete = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[4]/a[1]")));
		botonDelete.click();

		boolean productoEliminado = wait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[4]/a[1]")));

		Assert.assertTrue(productoEliminado, "El producto no fue eliminado correctamente del carrito");

		Utiles.escribir("Producto eliminado correctamente y cierro la página");

		Utiles.escribir("Cierro la página");
	}	
}
