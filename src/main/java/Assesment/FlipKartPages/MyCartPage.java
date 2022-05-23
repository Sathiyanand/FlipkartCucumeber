package Assesment.FlipKartPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyCartPage {

	
WebDriver driver;
	
	public  MyCartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//*[@class='_3g_HeN']")
	WebElement myCart;
	
	@FindBy(xpath="//div[@class='_1YokD2 _3Mn1Gg col-12-12'] //div[@class='_1AtVbE col-12-12']/div/div[1]/div[1]/div[1]/a")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//div[@class='_1YokD2 _3Mn1Gg col-12-12'] //div[@class='_1AtVbE col-12-12']/div //div[@class='nZz3kj _1hNI6F']/div[2]/div[2]")
	List<WebElement> removefrmCart;
	
	@FindBy(xpath="//*[@class='_3dsJAO _24d-qY FhkMJZ']")
	WebElement rmvConfirm;
	
	public WebElement MyCartText() {
		return myCart;
	}
	public List<WebElement> CartProducts() {
		return cartProducts;
	}
	public List<WebElement> RemoveLink() {
		return removefrmCart;
	}
	public WebElement RemoveConfrm() {
		return rmvConfirm;
	}
}
