package Assesment.FlipKart;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Assesment.FlipKartPages.HomePage;
import Assesment.FlipKartPages.ProductListPage;

public class SearchProductTest extends AutomationBase {
	public WebDriver driver;
public static Logger log=LogManager.getLogger(SearchProductTest.class.getName());
	
	@BeforeTest
	public void Initialize() throws IOException {
		driver = IntializeDriver();
		driver.get(prop.getProperty("url"));
		log.info("Url Launched Successfully");
	}
	@Test
	public void SearchPrdts() throws IOException {
		log.info("Search Product Test Start");
		HomePage homePage = new HomePage(driver);
		homePage.CloseLogin().click();
		homePage.SearchProduct().click();
		homePage.SearchProduct().sendKeys(prop.getProperty("searchtext"));
		homePage.SearchBtn().click();

		ProductListPage plPage = new ProductListPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(plPage.FilterText()));
		String filterText = plPage.FilterText().getText();
		Assert.assertEquals(filterText, "Filters");
		log.info("Search Successful. User Navigated to Product Listing Page");
	}
	
	@AfterTest
	public void TearDown() {
		driver.close();
		log.info("Browser Closed Successfully");
	}
}
