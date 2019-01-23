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

	@Test
	public void AddWholesellerTest() throws InterruptedException {
		String wholesellerName = "TestWholeseller" + randNo;
		String address = "12 West End";
		String city = "Auckland";
		String state = "North Island";
		String country = "New Zealand";
		String pinCode = "23456";
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

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'Add Wholeseller')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();

		Wholeseller wholeseller = new Wholeseller(wd);
		assertEquals("Wholeseller Listing Add Wholeseller", wholeseller.verifyBreadcrumb());
		assertEquals("Add Wholeseller", wholeseller.verifyPageTitle());
		assertEquals("Wholeseller added successfully.",
				wholeseller.addWholeseller(wholesellerName, address, city, state, country, pinCode, status));
		wholesellerId = wholesellerName;
		Reporter.log("Wholeseller with name: " + wholesellerId + " added successfully.");
	}

	@Test
	public void editWholesellerTest() throws InterruptedException {
		String wholesellerName = "";
		String address = "121 West End";
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

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'View Wholeseller')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);

		/*
		 * if (wd.findElement(By.xpath(
		 * "//div[@id='default_load']//div[2]//ul[1]//li[3]//a[1]")).isDisplayed()) {
		 * wd.findElement(By.xpath(
		 * "//div[@id='default_load']//div[2]//ul[1]//li[3]//a[1]")).click(); }
		 */

		WebElement webtable = wd.findElement(By.cssSelector("table.table-bordered.table-striped.table-condensed.cf"));
		List<WebElement> rows = webtable.findElements(By.xpath("//tr"));
		String rowContent;
		for (int i = 1; i <= rows.size() - 1; i++) {
			rowContent = rows.get(i).getText();
			Thread.sleep(2000);
			if (rowContent.contains(wholesellerId)) {
				String elementId = rows.get(i).getAttribute("id");
				wd.findElement(By.xpath("//tr[@id='" + elementId + "']//i[@class='fa fa-pencil']")).click();
				Thread.sleep(2000);
				break;
			}
		}

		Wholeseller wholeseller = new Wholeseller(wd);
		assertEquals("Wholeseller Listing Update Wholeseller", wholeseller.verifyBreadcrumb());
		assertEquals("Update Wholeseller", wholeseller.verifyPageTitle());
		assertEquals("Wholeseller updated successfully.",
				wholeseller.editWholeseller(wholesellerName, address, city, state, country, pinCode, status));
		Reporter.log("Wholeseller with name: " + wholesellerId + " updated successfully.");
	}
}
