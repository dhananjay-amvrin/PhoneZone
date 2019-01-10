package phonezoneproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class beforeandafterclassTest extends browserlistTest {
	

	
	
	@BeforeMethod
	public void opendriver() throws IOException
	{
		Properties properties=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir") + "/properties/config.properties");
		properties.load(fis);
	InvokeDriver(properties.getProperty("browser"));
		wd.get(properties.getProperty("baseURL"));
	}
	
	
	
	@AfterMethod
	public void quitedriver()
	{
		wd.quit();
	}

}
