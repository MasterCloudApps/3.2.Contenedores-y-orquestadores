package es.codeurjc.test.tablonanuncios;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AnuncioTest {

	private static String sutURL;

	WebDriver driver;
	
	@BeforeClass
	public static void setupClass() {
		sutURL = "http://localhost:8080/";
		System.out.println("App url: " + sutURL);
		
		WebDriverManager.chromedriver().setup();
	}

	@Before
	public void setupTest() throws MalformedURLException {
			driver = new ChromeDriver();
	}
	
	@After
	public void teardown() {
		if(driver != null) {
			driver.quit();
		}
	}
	
	@Test
	public void createTest() throws InterruptedException {
		
		driver.get(sutURL);
		
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Nuevo anuncio")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.name("nombre")).sendKeys("Anuncio nuevo con Selenium");
		driver.findElement(By.name("asunto")).sendKeys("Vendo moto");
		driver.findElement(By.name("comentario")).sendKeys("Un comentario muy largo...");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		driver.findElement(By.linkText("Volver al tablón")).click();
		
		assertNotNull(driver.findElement(By.partialLinkText("Selenium")));
	}

}
