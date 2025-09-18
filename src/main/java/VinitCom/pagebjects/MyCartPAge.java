package VinitCom.pagebjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import VinitCom.AbstractComponents.AbstractComponents;

public class MyCartPAge extends AbstractComponents{

	WebDriver driver;
	
	public MyCartPAge(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")    //pagefactory is only for findElement
	List<WebElement> cartProds;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutBtn;
	
	public Boolean selectedProductpresent(String productName)
	{
		
		Boolean Match = cartProds.stream()
				.anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return Match;
		
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutBtn.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}
	
}
