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
import Assesment.FlipKartPages.ProductDescPage;
import Assesment.FlipKartPages.ProductListPage;

public class GetSellerRatingTest extends AutomationBase{
	public WebDriver driver;
	
	public static Logger log=LogManager.getLogger(GetSellerRatingTest.class.getName());
	@BeforeTest
	public void Initialize() throws IOException {
		driver = IntializeDriver();
		driver.get(prop.getProperty("url"));
		log.info("Url Launched Successfully");
	}
	
	@Test
	public void SellerRating() throws IOException {
		log.info("Get Seller Rating Test Start");
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
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(pDesc.SellerName()));
		pDesc.SellerName().click();
		String productrtg = pDesc.ProductRating().getText();
		log.info(productrtg);
		pDesc.CloseSellerPopup().click();
		Assert.assertEquals(productrtg, "4.6");
		log.info("Seller Rating Validated");

	}
	@AfterTest
	public void TearDown() {
		driver.quit();
		log.info("Browser Closed Successfully");
	}
}
