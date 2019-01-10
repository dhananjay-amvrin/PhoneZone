package phonezoneproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WholesalerTest extends beforeandafterclassTest {
	
	private static  Properties prop =new Properties();
	private static FileInputStream fis;
	
	static {
        try {
        	fis=new FileInputStream(System.getProperty("user.dir")+"/properties/locators.properties");
        	
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
	
	
	
	@Test
	public void wholesaler_button_test() throws InterruptedException, IOException
	{ 
		wd.findElement(By.xpath(prop.getProperty("wholeseller_button"))).click();
		System.out.println(wd.getTitle());
		String text= wd.findElement(By.xpath(prop.getProperty("common_header_login"))).getText();
		
		Assert.assertEquals(text,"Staff/Wholeseller Login");
		Thread.sleep(5000);
	}
}
