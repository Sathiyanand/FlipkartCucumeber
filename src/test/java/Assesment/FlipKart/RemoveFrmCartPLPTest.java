package Assesment.FlipKart;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Assesment.FlipKartPages.HomePage;
import Assesment.FlipKartPages.MyCartPage;
import Assesment.FlipKartPages.ProductDescPage;
import Assesment.FlipKartPages.ProductListPage;



public class RemoveFrmCartPLPTest extends AutomationBase {
	public WebDriver driver;

	public static Logger log=LogManager.getLogger(RemoveFrmCartPLPTest.class.getName());
	
	@BeforeTest
	public void Initialize() throws IOException {
		driver = IntializeDriver();
		driver.get(prop.getProperty("url"));
		log.info("Url Launched Successfully");
	}
	
	@Test 
	public void DeleteCart() throws IOException {
		
		HomePage homePage = new HomePage(driver);
		homePage.CloseLogin().click();
		homePage.SearchProduct().click();
		homePage.SearchProduct().sendKeys(prop.getProperty("searchtext"));
		homePage.SearchBtn().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ProductListPage plPage = new ProductListPage(driver);
		int prdCount = plPage.searchList().size();
		for (int i = 0; i < prdCount; i++) {
			String prodName = plPage.searchList().get(i).getText();
			while (prodName.equalsIgnoreCase(prop.getProperty("firstproduct"))) {

				plPage.searchList().get(i).click();
				break;
			}

		}
log.info("First Product Selected");
		Set<String> getwindows = driver.getWindowHandles();
		Iterator<String> windowid = getwindows.iterator();
		String ProdListId = windowid.next();
		String prodDesId = windowid.next();
		driver.switchTo().window(prodDesId);
		ProductDescPage pDesc = new ProductDescPage(driver);
		pDesc.AddToCart().click();
		MyCartPage myCart = new MyCartPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(myCart.MyCartText()));
		driver.close();
		driver.switchTo().window(ProdListId);
		driver.navigate().refresh();
		String firstcartCnt = plPage.CartCount().getText();
		log.info("Cart Count After Adding First Product"+firstcartCnt);
		//ProductListPage plPage = new ProductListPage(driver);
		int prd2Count = plPage.searchList().size();
		for (int i = 0; i < prd2Count; i++) {
			String prod2Name = plPage.searchList().get(i).getText();
			while (prod2Name.equalsIgnoreCase(prop.getProperty("secondproduct"))) {

				plPage.searchList().get(i).click();
				break;
			}

		}
		log.info("Second Product Selected");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Move to Product Description window and add second product to cart
		Set<String> newwindows = driver.getWindowHandles();
		Iterator<String> newwindowid = newwindows.iterator();
		String pListId = newwindowid.next();
		String pDesId = newwindowid.next();
		driver.switchTo().window(pDesId);
		//ProductDescPage pDesc = new ProductDescPage(driver);
		// WebDriverWait waitForSellerName=new WebDriverWait(driver, 10);
		// waitForSellerName.until(ExpectedConditions.visibilityOf(pDesc.SellerName()));
		System.out.println("Moved to new Screen");
		pDesc.AddToCart().click();
//Wait till cart update and go back to product list page and refresh to get cart count
		MyCartPage mynewCart = new MyCartPage(driver);
		WebDriverWait waitforcart = new WebDriverWait(driver, 10);
		waitforcart.until(ExpectedConditions.visibilityOf(myCart.MyCartText()));
		driver.switchTo().window(pListId);
		driver.navigate().refresh();
		String cartCnt = plPage.CartCount().getText();
		log.info("Cart Count After Adding Second Product"+cartCnt);

		// Go to My cart page to remove product from cart and refresh the page and get count
		driver.switchTo().window(pDesId);
		WebDriverWait cartRfrshh= new WebDriverWait(driver, 10);
		cartRfrshh.until(ExpectedConditions.visibilityOf(myCart.MyCartText()));
		
		int cartCount = myCart.CartProducts().size();
		for (int j = 0; j < cartCount; j++) {
			String cartProdName = myCart.CartProducts().get(j).getText();
			while (cartProdName.equalsIgnoreCase(prop.getProperty("secondproduct"))) {

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
		
		//Go to PL page and Validate cart count decreased successfully
		driver.switchTo().window(pListId);
		driver.navigate().refresh();
		String plCartcount = plPage.CartCount().getText();
		log.info("Cart Count After Removing Second Product"+plCartcount);
		//Assert cart count with pl page count
		
		Assert.assertEquals(plCartcount, firstcartCnt);
		
		log.info("Cart Count Decreased Successfully");
   
	}

	@AfterTest
	public void TearDown() {
		driver.quit();
		log.info("Browser Closed Successfully");
	}
}

