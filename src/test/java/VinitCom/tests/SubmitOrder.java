package VinitCom.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import VinitCom.pagebjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		LandingPage landingpage = new LandingPage(driver);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

		driver.findElement(By.id("userEmail")).sendKeys("vinit123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Vinit@123");
		driver.findElement(By.cssSelector("[name=login]")).click();

		Thread.sleep(6000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='mb-3']")));

		List<WebElement> products = driver.findElements(By.cssSelector("div[class*='mb-3']"));

		WebElement DesiredProd = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst()
				.orElse(null);
		System.out.println(DesiredProd);
		DesiredProd.findElement(By.xpath(".//div[@class='card-body']/button[text()=' Add To Cart']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		List<WebElement> cartProds = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean Match = cartProds.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(Match);

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@Placeholder='Select Country']")), "india").build().perform();
		
		
		List<WebElement> countrys = driver.findElements(By.cssSelector(".ta-item span"));
		countrys.stream()
		.filter(country -> country.getText().equalsIgnoreCase("India"))
		.forEach(country -> country.click());
		
		driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
		
		String successMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(successMsg.equalsIgnoreCase("Thankyou for the order."));
		
		System.out.println(successMsg);
	}

}
