package phonezoneproject;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class WholesellerTest extends beforeandafterclassTest {
	private String wholesellerId;
	Random rand = new Random();
	int randNo = rand.nextInt(900) + 100;
	public enum Status {Active, Inactive};

	@Test
	public void AddWholesellerTest() throws InterruptedException {
		String wholesellerName = "TestWholeseller" + randNo;
		String address = "12 West End";
		String city = "Auckland";
		String state = "North Island";
		String country = "New Zealand";
		String pinCode = "23456";

		wd.findElement(By.xpath("//a[@class='btn btn-primary btn-lg btn_style btn_style-1']")).click();
		wd.findElement(By.cssSelector("#email")).sendKeys("dhananjay.singh@datagenius.co.nz");
		wd.findElement(By.cssSelector("#password")).sendKeys("phonezone@18#$");
		wd.findElement(By.xpath("//button[@type='submit']")).click();

		Actions actions = new Actions(wd);
		WebElement menu = wd.findElement(By.xpath("//i[@class='fa fa-user nav_icon']"));
		actions.moveToElement(menu);
		actions.click().build().perform();
		Thread.sleep(2000);

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'Add Wholeseller')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);

		Wholeseller wholeseller = new Wholeseller(wd);
		assertEquals("Wholeseller Listing Add Wholeseller", wholeseller.verifyBreadcrumb());
		assertEquals("Add Wholeseller", wholeseller.verifyPageTitle());
		assertEquals("Wholeseller added successfully.",
				wholeseller.addWholeseller(wholesellerName, address, city, state, country, pinCode, Status.Active.toString()));
		wholesellerId = wholesellerName;
		Reporter.log("Wholeseller with name: " + wholesellerId + " added successfully.");
	}

	@Test (dependsOnMethods={"AddWholesellerTest"})
	public void editWholesellerTest() throws InterruptedException {
		String wholesellerName = "";
		String address = "121 West End";
		String city = "";
		String state = "";
		String country = "";
		String pinCode = "";
		boolean found = false;
		
		wd.findElement(By.xpath("//a[@class='btn btn-primary btn-lg btn_style btn_style-1']")).click();
		wd.findElement(By.cssSelector("#email")).sendKeys("dhananjay.singh@datagenius.co.nz");
		wd.findElement(By.cssSelector("#password")).sendKeys("phonezone@18#$");
		wd.findElement(By.xpath("//button[@type='submit']")).click();

		Actions actions = new Actions(wd);
		WebElement menu = wd.findElement(By.xpath("//i[@class='fa fa-user nav_icon']"));
		actions.moveToElement(menu);
		actions.click().build().perform();
		Thread.sleep(2000);

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'View Wholeseller')]"));
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
				if (rowContent.contains(wholesellerId)) {
					String elementId = rows.get(i).getAttribute("id");
					wd.findElement(By.xpath("//tr[@id='" + elementId + "']//i[@class='fa fa-pencil']")).click();
					Thread.sleep(2000);
					found = true;
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

		Wholeseller wholeseller = new Wholeseller(wd);
		assertEquals("Wholeseller Listing Update Wholeseller", wholeseller.verifyBreadcrumb());
		assertEquals("Update Wholeseller", wholeseller.verifyPageTitle());
		assertEquals("Wholeseller updated successfully.",
				wholeseller.editWholeseller(wholesellerName, address, city, state, country, pinCode, Status.Active.toString()));
		Reporter.log("Wholeseller with name: " + wholesellerId + " updated successfully.");
	}
	
	
	@Test
	public void AddWholesellerRequiredFieldTest() throws InterruptedException {
		String wholesellerName = "";
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

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'Add Wholeseller')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();

		Wholeseller wholeseller = new Wholeseller(wd);
		assertEquals("Wholeseller Listing Add Wholeseller", wholeseller.verifyBreadcrumb());
		assertEquals("Add Wholeseller", wholeseller.verifyPageTitle());
		errorMessages = wholeseller.addWholeseller(wholesellerName, address, city, state, country, pinCode, Status.Active.toString());
		Reporter.log("Wholeseller required fields tested successfully. Fields in error are : \n" + errorMessages);
	}
}
