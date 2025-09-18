package VinitCom.pagebjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VinitCom.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		//intialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@Placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//a[contains(text(),'Place Order')]")
	WebElement PlaceOrderBtn;
	
	@FindBy(css=".ta-item span")   
	List<WebElement> countrys;
	
	By results = By.cssSelector(".ta-item span");
	
	public List<WebElement> getCountryList()
	{
		return countrys;
	}
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();

	 getCountryList().stream()
			.filter(country -> country.getText().equalsIgnoreCase(countryName))
				.forEach(country -> country.click());

	}
	
	public ConfirmationPage clickPlaceOrder()
	{
		PlaceOrderBtn.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
