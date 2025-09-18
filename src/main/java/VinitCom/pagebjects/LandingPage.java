package VinitCom.pagebjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VinitCom.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//intialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement Email = driver.findElement(By.id("userEmail"))''
	//PageFactory
	
	@FindBy(id="userEmail")
	private WebElement userEmail;
	
	@FindBy(id="userPassword")
	private WebElement userPassword;
	
	@FindBy(css="[name=login]")
	private WebElement loginBtn;
	//ng-tns-c4-35 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error ng-animating
	@FindBy(css="[class*='flyInOut'")
	private WebElement errorPop;
	
	public ProductCatalog loginApplication(String email,String password)
	{

		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		ProductCatalog productcat = new ProductCatalog(driver);
		return productcat;
	}
	
	public WebElement errorPopup()
	{
		waitForWebElementToVisible(errorPop);
		errorPop.getText();
		return errorPop;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	
	}


	
	
	
	
	
	
	
	
	
	
	
}
