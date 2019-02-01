package phonezoneproject;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class StoreTest extends beforeandafterclassTest {
	private String storeId;
	Random rand = new Random();
	int randNo = rand.nextInt(900) + 100;
	public enum Status {Active, Inactive};

	@Test
	public void AddStoreTest() throws InterruptedException {
		String storeName = "TestStore" + randNo;
		String address = "12 West Street";
		String city = "West Church";
		String state = "Queensland";
		String country = "New Zealand";
		String pinCode = "23423";
		
		wd.findElement(By.xpath("//a[@class='btn btn-primary btn-lg btn_style btn_style-1']")).click();
		wd.findElement(By.cssSelector("#email")).sendKeys("dhananjay.singh@datagenius.co.nz");
		wd.findElement(By.cssSelector("#password")).sendKeys("phonezone@18#$");
		wd.findElement(By.xpath("//button[@type='submit']")).click();

		Actions actions = new Actions(wd);
		WebElement menu = wd.findElement(By.xpath("//i[@class='fa fa-user nav_icon']"));
		actions.moveToElement(menu);
		actions.click().build().perform();
		Thread.sleep(2000);

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'Add Store')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);

		Store store = new Store(wd);
		assertEquals("Store Listing Add Store", store.verifyBreadcrumb());
		assertEquals("Add Store", store.verifyPageTitle());
		assertEquals("Store added successfully.",
				store.addStore(storeName, address, city, state, country, pinCode, Status.Active.toString()));
		storeId = storeName;
		Reporter.log("Store with name: " + storeId + " added successfully.");
	}

	@Test (dependsOnMethods={"AddStoreTest"})
	public void EditStoreTest() throws InterruptedException {
		String storeName = "";
		String address = "121 West Street";
		String city = "";
		String state = "";
		String country = "";
		String pinCode = "";
		boolean found = false;
		String storeId = "TestStore865";

		wd.findElement(By.xpath("//a[@class='btn btn-primary btn-lg btn_style btn_style-1']")).click();
		wd.findElement(By.cssSelector("#email")).sendKeys("dhananjay.singh@datagenius.co.nz");
		wd.findElement(By.cssSelector("#password")).sendKeys("phonezone@18#$");
		wd.findElement(By.xpath("//button[@type='submit']")).click();

		Actions actions = new Actions(wd);
		WebElement menu = wd.findElement(By.xpath("//i[@class='fa fa-user nav_icon']"));
		actions.moveToElement(menu);
		actions.click().build().perform();
		Thread.sleep(2000);

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'View Store')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);

		List<WebElement> pages = wd.findElements(By.xpath("//div[@id='default_load']//li/a"));
		for (int k = 0; k <= (pages.size() / 2) - 1; k++) {
			WebElement webtable = wd.findElement(By.cssSelector("table.table-bordered.table-striped.table-condensed.cf"));
			List<WebElement> rows = webtable.findElements(By.xpath("//tr"));
			String rowContent=null;
			for (int i = 1; i <= rows.size() - 1; i++) {
				rowContent = rows.get(i).getText();
				Thread.sleep(2000);
				if (rowContent.contains(storeId)) {
					String elementId = rows.get(i).getAttribute("id");
					wd.findElement(By.xpath("//tr[@id='" + elementId + "']//i[@class='fa fa-pencil']")).click();
					found = true;
					Thread.sleep(2000);
					break;
				}
			}
			if (found != false) {
				break;
			}
			List<WebElement> pagelink = wd.findElements(By.xpath("//div[@id='default_load']//li/a"));
			if (pages.size()<pagelink.size()) {
				pagelink.get(k+2).click();
				Thread.sleep(2000);
			} else {
				pagelink.get(k+1).click();
				Thread.sleep(2000);
			}
		}
		
		Store store = new Store(wd);
		assertEquals("Store Listing Update Store", store.verifyBreadcrumb());
		assertEquals("Update Store", store.verifyPageTitle());
		assertEquals("Store updated successfully.",
				store.editStore(storeName, address, city, state, country, pinCode, Status.Active.toString()));
		Reporter.log("Store with name: " + storeId + " updated successfully.");
	}
	
	
	@Test
	public void AddStoreRequiredFieldTest() throws InterruptedException {
		String storeName = "";
		String address = "";
		String city = "";
		String state = "";
		String country = "";
		String pinCode = "";
		String errorMessages;
		
		wd.findElement(By.xpath("//a[@class='btn btn-primary btn-lg btn_style btn_style-1']")).click();
		wd.findElement(By.cssSelector("#email")).sendKeys("dhananjay.singh@datagenius.co.nz");
		wd.findElement(By.cssSelector("#password")).sendKeys("phonezone@18#$");
		wd.findElement(By.xpath("//button[@type='submit']")).click();

		Actions actions = new Actions(wd);
		WebElement menu = wd.findElement(By.xpath("//i[@class='fa fa-user nav_icon']"));
		actions.moveToElement(menu);
		actions.click().build().perform();
		Thread.sleep(2000);

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'Add Store')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);

		Store store = new Store(wd);
		assertEquals("Store Listing Add Store", store.verifyBreadcrumb());
		assertEquals("Add Store", store.verifyPageTitle());
		errorMessages = store.addStore(storeName, address, city, state, country, pinCode, Status.Active.toString());
		Reporter.log("Store required fields tested successfully. Fields in error are : \n" + errorMessages);
	}

}
