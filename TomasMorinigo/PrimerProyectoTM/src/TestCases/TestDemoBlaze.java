package TestCases;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import MetodosUtiles.Utils;

public class TestDemoBlaze {

	private final String USERNAME = "admin";
	private final String PASSWORD = "admin";
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeMethod
	public void Precondicion() {
		// DRIVER CHROME
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();

		// DRIVER FIREFOX
		// System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
		// driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Utils.escribir("Abrimos el navegador en www.google.com");
	}

	@AfterMethod
	public void PostCondicion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

	@Test(description = "Validar que el sitio logee correctamente")
	public void ValidarLogin() throws Exception {
		// Abrimos la pagina web
		Utils.escribir("Abrimos la pagina web www.demoblaze.com");
		driver.get("https://www.demoblaze.com/index.html");

		// Tomamos el boton para el login
		Utils.escribir("Tomamos el boton para el login y chequeamos que esté visible");
		WebElement loginButton = driver.findElement(By.xpath("//a[@id='login2']"));
		Assert.assertTrue(loginButton.isDisplayed());
		Utils.escribir("Hacemos click en el boton Login");
		loginButton.click();

		// Tomamos los inputs
		Utils.escribir("Tomamos los inputs para insertar username y password");
		WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
		WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword")));

		// Rellenamos con datos validos
		Utils.escribir("Rellenamos los campos con datos validos");
		usernameInput.sendKeys(USERNAME);
		passwordInput.sendKeys(PASSWORD);

		// Enviamos el formulario
		Utils.escribir("Hacemos click en el boton Submit");
		WebElement submitLogin = driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));
		submitLogin.click();

		// Comprobamos que se haya iniciado con nuestro usuario ingresado
		Utils.escribir("Obtenemos el nombre del usuario iniciado");
		WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

		String welcomeText = userElement.getText();

		// Divide el texto para obtener solo el nombre de usuario
		String actualUsername = welcomeText.replace("Welcome ", "");

		// Compara el nombre de usuario actual con el esperado
		Utils.escribir("Comparamos el username ingresado por el esperado");
		Assert.assertEquals(actualUsername, USERNAME);
	}

	@Test(description = "Validar que se pueda eliminar correctamente un producto del carrito")
	public void ValidarEliminacionProductoCarrito() {
		// Abrimos la pagina web
		Utils.escribir("Abrimos la pagina web www.demoblaze.com");
		driver.get("https://www.demoblaze.com/index.html");

		// Tomamos el primer producto de nuestro Home
		Utils.escribir("Tomamos el primer producto de nuestro Home");
		WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html[1]/body[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/h4[1]/a[1]")));
		Assert.assertTrue(firstProduct.isDisplayed());
		Utils.escribir("Hacemos click en el producto");
		firstProduct.click();

		// Tomamos y hacemos click en el boton "Add to Cart"
		Utils.escribir("Tomamos y hacemos click en el boton \"Add to Cart\"");
		WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btn btn-success btn-lg']")));
		Assert.assertTrue(addToCart.isDisplayed());
		addToCart.click();

		// Tomamos el boton del header "Cart" y hacemos click
		Utils.escribir("Tomamos el boton del header \"Cart\" y hacemos click");
		WebElement cartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Cart']")));
		Assert.assertTrue(cartButton.isDisplayed());
		cartButton.click();

		// Tomamos el boton "Delete" y hacemos click
		Utils.escribir("Tomamos el boton \"Delete\" y hacemos click");
		WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Delete']")));
		Assert.assertTrue(deleteButton.isDisplayed());
		deleteButton.click();

		// Comprobamos de que se haya eliminado correctamente
		Utils.escribir("Comprobamos de que se haya eliminado correctamente");
		boolean productoEliminado = wait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[normalize-space()='Delete']")));
		Assert.assertTrue(productoEliminado, "El producto no ha sido eliminado despues del click.");
	}
}
