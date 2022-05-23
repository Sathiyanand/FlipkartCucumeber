package Assesment.FlipKartPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDescPage {
WebDriver driver;
	
	public  ProductDescPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//*[@id='sellerName']")
	WebElement sellerName;
	
	@FindBy(xpath="//*[@class='_366KMO']/div[1]/div[1]/div[2]")
	WebElement prdctRating;
	
	
	@FindBy(xpath="//button[@class='_2KpZ6l _1KAjNd']")
	WebElement closeSellerPopup;
	
	@FindBy(xpath="//*[@class='_2KpZ6l _2U9uOA _3v1-ww']")
	WebElement addToCart;
		
	
	
	
	public WebElement SellerName() {
		return sellerName;
	}
	
	//Product rate
	public WebElement ProductRating() {
		return prdctRating;
	}
	
	public WebElement CloseSellerPopup() {
		return closeSellerPopup;
	}
	
	public WebElement AddToCart() {
		return addToCart;
	}
	
	
		
		
}
