package Assesment.FlipKartPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductListPage {
WebDriver driver;
	
	public  ProductListPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//*[@class='EJP5Be _6t1WkM _3HqJxg']")
	WebElement productsList;
	
	@FindBy(xpath="//*[@class='_3V8rao']")
	WebElement filtertxt;
	
	@FindBy(xpath="//*[@class='_4rR01T']")
	List<WebElement> srchList;
	
	@FindBy(xpath="//*[@class='KK-o3G']")
	WebElement cartCount;
	
	
	
	//Availability of Product list
	public WebElement ProductsList() {
		return productsList;
	     }
	
	//Availability of filtertext
		public WebElement FilterText() {
			return filtertxt;
		}
		
		public List<WebElement> searchList() {
			return srchList;
		}
		
		public WebElement CartCount() {
			return cartCount;
		}
}
