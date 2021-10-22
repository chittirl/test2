package basePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {	
	WebDriver driver;
	Properties prop= new Properties();
	FileInputStream fis;
	String myBrowser;
	
	public TestBase() throws IOException {
		String path= "C:\\Users\\nkumar\\eclipse-workspace\\retail\\src\\main\\resources\\config.properties";
		fis= new FileInputStream(path);
		prop.load(fis);
		System.out.println("constructor message");
	}
	
	@Test
	public void setup() {
		myBrowser=prop.getProperty("browser");
		System.out.println(myBrowser);
		
		if(myBrowser== "chrome"){
		WebDriverManager.chromedriver().setup();	
		driver= new ChromeDriver();
	}
	else if(myBrowser== "ff"){
		WebDriverManager.firefoxdriver().setup();;	
		driver= new FirefoxDriver();
	}
	else {
		WebDriverManager.edgedriver().setup();
		driver= new EdgeDriver();
	}	
		
				
		driver.get(prop.getProperty("url"));	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}	
}

//switch(myBrowser) {
//case("chrome"):
//	WebDriverManager.chromedriver().setup();	
//driver= new ChromeDriver();
//case("ff"):
//	WebDriverManager.firefoxdriver().setup();;	
//	driver= new FirefoxDriver();
//case("edge"):
//	WebDriverManager.edgedriver().setup();
//driver= new EdgeDriver();
//}
	