package Assesment.FlipKart;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AutomationBase {
	public WebDriver driver;
	public Properties prop;

	
	@SuppressWarnings("deprecation")
	public  WebDriver IntializeDriver() throws IOException {
		
		FileInputStream fis= new FileInputStream("C:\\Users\\sdharmy3\\OneDrive - WBA\\Desktop\\Learning\\Workspace\\FlipKart\\src\\main\\java\\Assesment\\FlipKart\\inputdata.properties");
	    prop=new Properties();
		
		
		prop.load(fis);
		String browserName= prop.getProperty("browser");
		//String url=prop.getProperty("URL");
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			 driver= new ChromeDriver();
			 
		}
		else if (browserName.equals("firefox")) {
			//firefox
			System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
			 driver= new FirefoxDriver();
		}
		else if (browserName.equals("internetexplorer")) {
			System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");
			 driver= new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	
	}
	
	public String getScreenShot(String Testcasename,WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File Source= ts.getScreenshotAs(OutputType.FILE);
		String destinationFile=System.getProperty("user.dir")+"\\reports\\"+Testcasename+".png";
		FileUtils.copyFile(Source, new File(destinationFile));
		return destinationFile;
	}
	
}
