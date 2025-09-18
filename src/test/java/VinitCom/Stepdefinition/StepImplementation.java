package VinitCom.Stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import VinitCom.TestComponents.BaseTest;
import VinitCom.pagebjects.CheckoutPage;
import VinitCom.pagebjects.ConfirmationPage;
import VinitCom.pagebjects.LandingPage;
import VinitCom.pagebjects.MyCartPAge;
import VinitCom.pagebjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepImplementation extends BaseTest {

	public LandingPage landingpage;
	public ProductCatalog productcat;
	public ConfirmationPage confirmationpage;

	@Given("I Landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		landingpage = launchApplication();
	}

	@Given("Logged in with {string} and {string}")
	public void logged_in_with_and(String email, String password) {
		// Write code here that turns the phrase above into concrete actions
		productcat = landingpage.loginApplication(email, password);
	}

	@When("User add product {string} to cart")
	public void user_add_product_to_cart(String productname) {
		// Write code here that turns the phrase above into concrete actions
		List<WebElement> products = productcat.getProductList();
		productcat.addProductToCart(productname);
	}

	@When("Checkout and submit order {string}")
	public void checkout_and_submit_order(String productname) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(3000);
		MyCartPAge mycartpage = productcat.clickonCart();

		Boolean match = mycartpage.selectedProductpresent(productname);
		Assert.assertTrue(match);

		CheckoutPage checkoutpage = mycartpage.goToCheckout();

		checkoutpage.selectCountry("india");
		confirmationpage = checkoutpage.clickPlaceOrder();
	}

	@Then("{string} msg is display on confirmation Page")
	public void msg_is_display_on_confirmation_page(String string) {
		// Write code here that turns the phrase above into concrete actions
		String successMsg = confirmationpage.getSuccesstext();
		Assert.assertTrue(successMsg.equalsIgnoreCase(string));
		driver.close();
	}
}
