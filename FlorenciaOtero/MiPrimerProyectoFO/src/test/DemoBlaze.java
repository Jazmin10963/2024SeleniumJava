package test;

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

import metodosUtiles.Utiles;

public class DemoBlaze {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void Precondition() {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\admin\\Desktop\\Testing\\geckodriver-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10); // Establecer un tiempo de espera de 10 segundos
		driver.get("http://demoblaze.com");
	}

	@AfterMethod
	public void PostCondition() {
		driver.manage().deleteAllCookies();
		driver.quit(); // quit() para cerrar completamente el navegador
	}

	@Test(description = "Validar que las adiciones al carrito en DemoBlaze funcionan")
	public void ValidarAdicionCarrito() throws Exception {
		// Abrimos la pagina web
		Utiles.escribir("Abrimos la pagina web www.demoblaze.com");
		driver.get("https://www.demoblaze.com/index.html");

		// Entramos a la categoría Phones
		Utiles.escribir("Entramos a la categoría Phones");

		WebElement categoriaPhones = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='contcont']//a[2]")));

		Assert.assertTrue(categoriaPhones.isDisplayed(), "La categoría Phones no está visible.");
		Utiles.escribir("Hacemos click en la categoría Phones");
		categoriaPhones.click();

		// Ver si los productos son visibles después de seleccionar la categoría
		WebElement allProduct = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tbodyid']")));

		Assert.assertTrue(allProduct.isDisplayed(), "Los productos no están visible.");

		// Hacer click en el producto Samsung galaxy s6
		WebElement firstProduct = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Samsung galaxy s6']")));
		Utiles.escribir("Hacemos click en el producto samsung galaxy s6");
		firstProduct.click();

		// Agregamos al carrito
		WebElement samsungProduct = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Add to cart']")));
		Utiles.escribir("Agregamos el celular al carrito");
		Assert.assertTrue(samsungProduct.isDisplayed(), "El producto samsung no se pudo agregar al carro.");
		samsungProduct.click();

		// Manejamos el alert que aparece después de agregar al carrito
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();

		// Comprobamos que el carrito contenga el producto seleccionado
		WebElement carrito = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Cart']")));
		Utiles.escribir("Comprobamos el carrito");
		Assert.assertTrue(carrito.isDisplayed(), "El carrito está vacío.");
		carrito.click();

		// Pausa para ver el carrito por 5 segundos
		Thread.sleep(5000);
	}

	@Test(description = "Validar la visualización del video en ABOUT US")
	public void ValidarVisualizacionVideo() throws Exception {
		// Abrimos la pagina web
				Utiles.escribir("Abrimos la pagina web www.demoblaze.com");
				driver.get("https://www.demoblaze.com/index.html");

		// Entramos a about us
				Utiles.escribir("Seleccionamos about us");

				WebElement aboutUs = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='About us']")));

				Assert.assertTrue(aboutUs.isDisplayed(), "No se puede seleccionar about us.");
				Utiles.escribir("Hacemos click en about us");
				aboutUs.click();
				
				// Hacemos click a play
				Utiles.escribir("Seleccionamos play");

				WebElement play = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Play Video']")));

				Assert.assertTrue(aboutUs.isDisplayed(), "No se puede seleccionar play.");
				Utiles.escribir("Hacemos click en play");
				play.click();
				
				Thread.sleep(5000);
	}
}