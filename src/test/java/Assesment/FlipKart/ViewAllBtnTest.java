package Assesment.FlipKart;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Assesment.FlipKartPages.HomePage;
import Assesment.FlipKartPages.MyCartPage;
import Assesment.FlipKartPages.ProductDescPage;
import Assesment.FlipKartPages.ProductListPage;

public class ViewAllBtnTest extends AutomationBase {
	public WebDriver driver;
	public static Logger log=LogManager.getLogger(ViewAllBtnTest.class.getName());

	@BeforeTest
	public void Initialize() throws IOException {
		driver = IntializeDriver();
		driver.get(prop.getProperty("url"));
		log.info("Url Launched Successfully");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void ViewAllBtn() throws IOException, InterruptedException {
		log.info("ViewAll Button Test Start");
		HomePage homePage = new HomePage(driver);
		homePage.CloseLogin().click();
		homePage.ViewAllbtn().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ProductListPage plPage = new ProductListPage(driver);
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.visibilityOf(plPage.ProductsList()));
		Boolean products = plPage.ProductsList().isDisplayed();
        Assert.assertTrue(products);
        log.info("User Navigated to Products listing Page");
		

	}

	@AfterTest
	public void TearDown() {
		driver.close();
		log.info("Browser Closed Successfully");
	}
	
}
