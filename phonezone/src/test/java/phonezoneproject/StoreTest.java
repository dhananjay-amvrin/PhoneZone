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

	@Test
	public void AddStoreTest() throws InterruptedException {
		String storeName = "TestStore" + randNo;
		String address = "12 West Street";
		String city = "West Church";
		String state = "Queensland";
		String country = "New Zealand";
		String pinCode = "23423";
		String status = "Active";
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
				store.addStore(storeName, address, city, state, country, pinCode, status));
		storeId = storeName;
		Reporter.log("Store with name: " + storeId + " added successfully.");
	}

	@Test
	public void EditStoreTest() throws InterruptedException {
		String storeName = "";
		String address = "121 West Street";
		String city = "";
		String state = "";
		String country = "";
		String pinCode = "";
		String status = "";

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

		if (wd.findElement(By.xpath("//div[@id='default_load']//div[2]//ul[1]//li[3]//a[1]")).isDisplayed()) {
			wd.findElement(By.xpath("//div[@id='default_load']//div[2]//ul[1]//li[3]//a[1]")).click();
		}
		Thread.sleep(2000);

		WebElement webtable = wd.findElement(By.cssSelector("table.table-bordered.table-striped.table-condensed.cf"));
		List<WebElement> rows = webtable.findElements(By.xpath("//tr"));
		String rowContent;
		for (int i = 1; i <= rows.size() - 1; i++) {
			rowContent = rows.get(i).getText();
			Thread.sleep(2000);
			if (rowContent.contains(storeId)) {
				String elementId = rows.get(i).getAttribute("id");
				wd.findElement(By.xpath("//tr[@id='" + elementId + "']//i[@class='fa fa-pencil']")).click();
				Thread.sleep(2000);
				break;
			}
		}

		Store store = new Store(wd);
		assertEquals("Store Listing Update Store", store.verifyBreadcrumb());
		assertEquals("Update Store", store.verifyPageTitle());
		assertEquals("Store updated successfully.",
				store.editStore(storeName, address, city, state, country, pinCode, status));
		Reporter.log("Store with name: " + storeId + " updated successfully.");
	}

}
