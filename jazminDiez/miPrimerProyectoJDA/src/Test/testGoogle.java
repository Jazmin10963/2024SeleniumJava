package Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class testGoogle {
	@Test(description = "Validar que las busquedas en Google funcionan")
	public void ValidarBusquedaGoogle() throws Exception {
		//configurar web driver de chrome
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//Abrir Google
		driver.get("https://www.google.com.ar/");
		
		//Buscar "Selenium"
		WebElement searchInput = driver.findElement(By.id("APjFqb"));
		Assert.assertTrue(searchInput.isDisplayed());
		String busqueda="Java"; 
		searchInput.sendKeys(busqueda);
		searchInput.submit();
		
		Thread.sleep(3000);
		
		List<WebElement> Resultados = driver.findElements(By.xpath("//h3[contains(text(),'"+busqueda+"')]"));
		
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