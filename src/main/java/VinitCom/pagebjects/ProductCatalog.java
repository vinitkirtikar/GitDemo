package VinitCom.pagebjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VinitCom.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		//intialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> products = driver.findElements(By.cssSelector("div[class*='mb-3']"));
	//PageFactory
	
	@FindBy(css="div[class*='mb-3']")    //pagefactory is only for findElement
	List<WebElement> products;
	
	@FindBy(css="button[routerlink*='cart']")    
	WebElement cartBtn;
	
	@FindBy(css="[routerlink*='myorders']")    
	WebElement ordersBtn;
	
	By productList = By.cssSelector("div[class*='mb-3']");//findElements use this type
	By addToCart = By.xpath(".//div[@class='card-body']/button[text()=' Add To Cart']");
	By popUp = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToVisible(productList);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement DesiredProd = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return DesiredProd;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement DesiredProd = getProductByName(productName);
		DesiredProd.findElement(addToCart).click();
		waitForElementToVisible(popUp);
	}
	
	public MyCartPAge clickonCart()
	{
		cartBtn.click();
		MyCartPAge mycartpage = new MyCartPAge(driver);
		return mycartpage;
	}
	
	public OrdersPage goToOrdersPage()
	{
		ordersBtn.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
	
	
}
