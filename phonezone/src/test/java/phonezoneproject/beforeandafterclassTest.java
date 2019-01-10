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
	
	private static  Properties prop =new Properties();
	private static FileInputStream fis;
	public static void load_properties()
	{
			
	        try {
	        	fis=new FileInputStream("D:\\Maven_workspace\\phonezone\\properties\\locators.properties");
	        	
	        } catch (FileNotFoundException e) {
	        	
	            e.printStackTrace();
	        } 
	        
	        try {
				prop.load(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       
	    }
	
	
	@BeforeMethod
	public void opendriver() throws IOException
	{
		Properties properties=new Properties();
		FileInputStream fis=new FileInputStream("D:\\Maven_workspace\\phonezone\\properties\\config.properties");
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
