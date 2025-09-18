package VinitCom.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;



import VinitCom.TestComponents.BaseTest;
import VinitCom.TestComponents.Retry;
import VinitCom.pagebjects.CheckoutPage;
import VinitCom.pagebjects.ConfirmationPage;
import VinitCom.pagebjects.LandingPage;
import VinitCom.pagebjects.MyCartPAge;
import VinitCom.pagebjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidation extends BaseTest {

	@Test
	public void loginErrorValidation() throws IOException, InterruptedException
{
	
		
		ProductCatalog productcat = landingpage.loginApplication("vinit123@gmail.com", "Vinit@13");
		Assert.assertEquals("Incorrect email or password.", landingpage.errorPopup());
		
}
	
	@Test(groups = {"Error"},retryAnalyzer=Retry.class)
	public void productErrorValidation() throws IOException, InterruptedException
{
		String productName = "ADIDAaS ORIGINAL";
		
		ProductCatalog productcat = landingpage.loginApplication("vinit123@gmail.com", "Vinit@123");

		Thread.sleep(6000);

		List<WebElement> products = productcat.getProductList();
		productcat.addProductToCart(productName);
		MyCartPAge mycartpage = productcat.clickonCart();

		Boolean match = mycartpage.selectedProductpresent(productName);
		Assert.assertFalse(match);

	

	
		
}
	}


