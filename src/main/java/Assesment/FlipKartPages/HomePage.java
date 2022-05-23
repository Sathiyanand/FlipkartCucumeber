package Assesment.FlipKartPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	public  HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath="//*[@class='_2KpZ6l _2doB4z']")
	WebElement skipLogin;
	
	
	@FindBy(xpath="//*[@class='_6t1WkM _3HqJxg']/div[4]/div/div[1]/div/div")
	WebElement viewAllBtn;
	
	@FindBy(xpath="//*[@class='_3704LK']")
	WebElement searchPrdct;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement searchbtn;
	
	
	//SkipLogin by Close
	public WebElement CloseLogin() {
		return skipLogin;
	}
	
	public WebElement ViewAllbtn() {
		return viewAllBtn;
	}
	
	public WebElement SearchProduct() {
		return searchPrdct;
	}
	
	public WebElement SearchBtn() {
		return searchbtn;
	}
	
}
