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

public class AddToCartPLPTest extends AutomationBase {
	public WebDriver driver;
	
	public static Logger log=LogManager.getLogger(AddToCartPLPTest.class.getName());

	@BeforeTest
	public void Initialize() throws IOException {
		driver = IntializeDriver();
		driver.get(prop.getProperty("url"));
		log.info("Url Launched Successfully");
	}
	@Test
	public void VerifyCart() throws IOException {
		log.info("Verify Cart Count Test Start");
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
		log.info("Product Selected Successfully");
		Set<String> getwindows = driver.getWindowHandles();
		Iterator<String> windowid = getwindows.iterator();
		String ProdListId = windowid.next();
		String prodDesId = windowid.next();
		driver.switchTo().window(prodDesId);
		ProductDescPage pDesc = new ProductDescPage(driver);
		pDesc.AddToCart().click();
		log.info("Product Added to Cart");
		MyCartPage myCart = new MyCartPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(myCart.MyCartText()));
		//String myCartText= myCart.MyCartText().getText();
		//My Cart (2)
		//String cartCount=myCartText.split("\\(")[1].split("\\)")[0];
		
		//driver.close();
		driver.switchTo().window(ProdListId);
		int plCartCntBefore = 0;
		driver.navigate().refresh();
		//ProductListPage plPage = new ProductListPage(driver);
		//String plCartCnt = plPage.CartCount().getText();
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

	@AfterTest
	public void TearDown() {
		driver.quit();
		log.info("Browser Closed Successfully");
	}
}
