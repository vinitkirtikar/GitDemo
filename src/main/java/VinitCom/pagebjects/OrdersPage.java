package VinitCom.pagebjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VinitCom.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents{

	WebDriver driver;
	
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//tr/td[2]")    //pagefactory is only for findElement
	List<WebElement> productsName;
	

	
	public Boolean verifyOrderspresent(String productName)
	{
		
		Boolean Match = productsName.stream()
				.anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return Match;
		
	}
}
