package VinitCom.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import VinitCom.TestComponents.BaseTest;
import VinitCom.pagebjects.CheckoutPage;
import VinitCom.pagebjects.ConfirmationPage;
import VinitCom.pagebjects.LandingPage;
import VinitCom.pagebjects.MyCartPAge;
import VinitCom.pagebjects.OrdersPage;
import VinitCom.pagebjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
{
		
		
		ProductCatalog productcat = landingpage.loginApplication(input.get("email"), input.get("password"));

		Thread.sleep(6000);

		List<WebElement> products = productcat.getProductList();
		productcat.addProductToCart(input.get("product"));
		MyCartPAge mycartpage = productcat.clickonCart();

		Boolean match = mycartpage.selectedProductpresent(input.get("product"));
		Assert.assertTrue(match);

		CheckoutPage checkoutpage = mycartpage.goToCheckout();

		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.clickPlaceOrder();
		String successMsg = confirmationpage.getSuccesstext();
		Assert.assertTrue(successMsg.equalsIgnoreCase("Thankyou for the order."));
		System.out.println(successMsg);

	
		
}
	@Test(dependsOnMethods = "SubmitOrder")
	public void verifyOrderHistory()
	{
		ProductCatalog productcat = landingpage.loginApplication("vinit123@gmail.com", "Vinit@123");
		OrdersPage ordersPage = productcat.goToOrdersPage();
		ordersPage.verifyOrderspresent(productName);
		
	}
	
	

	
/*	@DataProvider
	public Object[][] getData()
	{
		return new Object[][ ] {{"vinit123@gmail.com", "Vinit@123","ADIDAS ORIGINAL"},{"Sujal123@gmail.com", "Sujal@14325","IPHONE 13 PRO"}};
	}*/
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
	/*	HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "vinit123@gmail.com");
		map.put("password", "Vinit@123");
		map.put("product", "ADIDAS ORIGINAL");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "Sujal123@gmail.com");
		map1.put("password", "Sujal@14325");
		map1.put("product", "IPHONE 13 PRO");
	*/	
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\VinitCom\\data\\PurchaseOrder.json");
		return new Object[][ ] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	
	
	}


