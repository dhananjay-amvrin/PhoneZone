package phonezoneproject;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class browserlistTest {
	
	static WebDriver wd;
	
		
	public static void InvokeDriver(String enterdriver)
	{
		
		if(enterdriver.equals("chrome"))
		{
			wd=new ChromeDriver();
			wd.manage().deleteAllCookies();
		}
		else if(enterdriver.equals("ff") || enterdriver.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D:\\Maven_workspace\\phonezone\\geckodriver32.exe");
			wd=new FirefoxDriver();
			wd.manage().deleteAllCookies();
		}
		else if(enterdriver.equals("ie") || enterdriver.equals("Internet Explorer"))
		{
			System.setProperty("webdriver.ie.driver", "D:\\Maven_workspace\\phonezone\\IEDriverServer32.exe");
			wd=new InternetExplorerDriver();
			wd.manage().deleteAllCookies();
		}
		
		else System.out.println("Enter the Valid Browser Name");
	}
	

}
