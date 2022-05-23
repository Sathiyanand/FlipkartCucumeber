package stepDefinitions;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Assesment.FlipKart.AutomationBase;
import Assesment.FlipKart.ViewAllBtnTest;
import Assesment.FlipKartPages.HomePage;
import Assesment.FlipKartPages.MyCartPage;
import Assesment.FlipKartPages.ProductDescPage;
import Assesment.FlipKartPages.ProductListPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends AutomationBase {

	public static Logger log=LogManager.getLogger(ViewAllBtnTest.class.getName());
	String ProdListId;
	String prodDesId;
	String firstcartCnt;
	
	@Given("Initalize browser with chrome")
	public void initalize_browser_with_chrome() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		driver = IntializeDriver();
	}
	@Given("Navigate to {string} HomePage")
	public void navigate_to_home_page(String url) {
	    // Write code here that turns the phrase above into concrete actions
		driver.get(url);
		log.info("Url Launched Successfully");
		HomePage homePage = new HomePage(driver);
		homePage.CloseLogin().click();
	}
	@SuppressWarnings("deprecation")
	@When("User click on ViewAll button")
	public void user_click_on_view_all_button() {
	    // Write code here that turns the phrase above into concrete actions
		log.info("ViewAll Button Test Start");
		HomePage homePage = new HomePage(driver);
		homePage.ViewAllbtn().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Then("User Navigates to Product Listing Page")
	public void user_navigates_to_product_listing_page() {
		ProductListPage plPage = new ProductListPage(driver);
		Boolean products = plPage.ProductsList().isDisplayed();
        Assert.assertTrue(products);
        log.info("User Navigated to Products listing Page");
	}
	
	@Then("Close browser")
	public void close_browser() {
		driver.quit();
		log.info("Browser Closed Successfully");
	}
	
	@When("User enter {string}")
	public void user_enter(String product) {
		HomePage homePage = new HomePage(driver);
		homePage.SearchProduct().click();
		homePage.SearchProduct().sendKeys(product);
	}
	@When("User click on search button")
	public void user_click_on_search_button() {
	    // Write code here that turns the phrase above into concrete actions
		HomePage homePage = new HomePage(driver);
		homePage.SearchBtn().click();
	}
	@Then("User Navigates to Product Listing Page with products as searched")
	public void user_navigates_to_product_listing_page_with_products_as_searched() {
		ProductListPage plPage = new ProductListPage(driver);
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(plPage.FilterText()));
		String filterText = plPage.FilterText().getText();
		Assert.assertEquals(filterText, "Filters");
		log.info("Search Successful. User Navigated to Product Listing Page");
	}

	@Given("User selects the {string}")
	public void user_selects_the(String productOne) {
		ProductListPage plPage = new ProductListPage(driver);
		int prdCount = plPage.searchList().size();
		for (int i = 0; i < prdCount; i++) {
			String prodName = plPage.searchList().get(i).getText();
			while (prodName.equalsIgnoreCase(productOne)) {

				plPage.searchList().get(i).click();
				break;
			}

		}
        log.info("Product Selected Successfully");

	}
	@Given("User moves to product Description page")
	public void user_moves_to_product_description_page() {
		Set<String> getwindows = driver.getWindowHandles();
		Iterator<String> windowid = getwindows.iterator();
		ProdListId = windowid.next();
		prodDesId = windowid.next();
		driver.switchTo().window(prodDesId);
	}
	@When("User clicks on seller name")
	public void user_clicks_on_seller_name() {
		ProductDescPage pDesc = new ProductDescPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(pDesc.SellerName()));
		pDesc.SellerName().click();
	}
	@Then("User gets SellerRating from Popup")
	public void user_gets_seller_rating_from_popup() {
		ProductDescPage pDesc = new ProductDescPage(driver);
		String productrtg = pDesc.ProductRating().getText();
		log.info("Seller Rating is"+productrtg);
		pDesc.CloseSellerPopup().click();
		//Assert.assertEquals(productrtg, "4.6");
		
	}
	
	@Given("User add item to cart")
	public void user_add_item_to_cart() {
		ProductDescPage pDesc = new ProductDescPage(driver);
		pDesc.AddToCart().click();
		log.info("Product Added to Cart");
		MyCartPage myCart = new MyCartPage(driver);
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(myCart.MyCartText()));
	}
	@Given("User moves to product listing page")
	public void user_moves_to_product_listing_page() {
		driver.switchTo().window(ProdListId);
		
		
	}
	@When("User refresh product listing page")
	public void user_refresh_product_listing_page() {
		driver.navigate().refresh();
		
	}
	@Then("Cart Count in product listing page gets increased")
	public void cart_count_in_product_listing_page_gets_increased() {
		int plCartCntBefore = 0;
		ProductListPage plPage = new ProductListPage(driver);
		int plCartCntAfter = Integer.parseInt(plPage.CartCount().getText());
		
		if (plCartCntAfter>plCartCntBefore) {
		Assert.assertTrue(true);
		log.info("Cart Count Increased to"+plCartCntAfter);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	
	@Given("User gets Intial Cart count")
	public void user_gets_intial_cart_count() {
		ProductListPage plPage = new ProductListPage(driver);
		 firstcartCnt = plPage.CartCount().getText();
		log.info("Cart Count After Adding First Product"+firstcartCnt);
	}
	@Given("User gets final Cart count")
	public void user_gets_final_cart_count() {
		ProductListPage plPage = new ProductListPage(driver);
		String cartCnt = plPage.CartCount().getText();
		log.info("Cart Count After Adding Second Product"+cartCnt);
	}
	@Given("User moves to My Cart page")
	public void user_moves_to_my_cart_page() {
		driver.switchTo().window(prodDesId);
		MyCartPage myCart = new MyCartPage(driver);
		WebDriverWait cartRfrshh= new WebDriverWait(driver, 10);
		cartRfrshh.until(ExpectedConditions.visibilityOf(myCart.MyCartText()));
	}
	@When("User removes {string} from Cart")
	public void user_removes_from_cart(String productTwo) {
		MyCartPage myCart = new MyCartPage(driver);
		int cartCount = myCart.CartProducts().size();
		for (int j = 0; j < cartCount; j++) {
			String cartProdName = myCart.CartProducts().get(j).getText();
			while (cartProdName.equalsIgnoreCase(productTwo)) {

				myCart.RemoveLink().get(j).click();
				break;
			}

		}
		
         myCart.RemoveConfrm().click();
         log.info("Second Product Removed from Cart");
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
		WebDriverWait cartRefresh= new WebDriverWait(driver, 10);
		cartRefresh.until(ExpectedConditions.visibilityOf(myCart.MyCartText()));
		// validate product count in my cart page
		int UpdtcrtCount = myCart.CartProducts().size();
		log.info("Updated Cart count After Removing product in My cart Page"+UpdtcrtCount);
	}
	@Then("Cart Count Product listing page gets decresed")
	public void cart_count_product_listing_page_gets_decresed() {
		ProductListPage plPage = new ProductListPage(driver);
		String plCartcount = plPage.CartCount().getText();
		log.info("Cart Count After Removing Second Product in Product listing page"+plCartcount);
		//Assert cart count with pl page count
		
		Assert.assertEquals(plCartcount, firstcartCnt);
		
		log.info("Cart Count Decreased Successfully");
	}
	@Given("Close Current browser window")
	public void close_current_browser_window() {
	    driver.close();
	}
}
