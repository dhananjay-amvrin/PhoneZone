package phonezoneproject;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class UserTest extends beforeandafterclassTest {
	private String userEmail;
	Random rand = new Random();
	int randNo = rand.nextInt(900) + 100;

	public enum Role {
		STORESTAFF, WHOLESELLER, NONE
	};

	public String UserRole(Role value) {
		String role = null;
		switch (value) {
		case STORESTAFF:
			role = "Store Staff";
			break;
		case WHOLESELLER:
			role = "Wholeseller";
			break;
		case NONE:
			role = "";
			break;
		}
		return role;
	}

	public enum Status {
		Active, Inactive
	};

	@Test
	public void AddUserTest() throws InterruptedException {
		String firstName = "Test";
		String lastName = "User";
		String email = "User" + randNo + "@datagenius.co.nz";
		String password = "qwerty123";
		String phoneNumber = "2032244000";
		String wholeseller = "";
		wd.findElement(By.xpath("//a[@class='btn btn-primary btn-lg btn_style btn_style-1']")).click();
		wd.findElement(By.cssSelector("#email")).sendKeys("dhananjay.singh@datagenius.co.nz");
		wd.findElement(By.cssSelector("#password")).sendKeys("phonezone@18#$");
		wd.findElement(By.xpath("//button[@type='submit']")).click();

		Actions actions = new Actions(wd);
		WebElement menu = wd.findElement(By.xpath("//i[@class='fa fa-user nav_icon']"));
		actions.moveToElement(menu);
		actions.click().build().perform();
		Thread.sleep(2000);

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'Add User')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);

		User user = new User(wd);
		assertEquals("Users Listing Add User", user.verifyBreadcrumb());
		assertEquals("Add User", user.verifyPageTitle());
		assertEquals("User added successfully.", user.addUser(firstName, lastName, email, password, phoneNumber,
				UserRole(Role.STORESTAFF), wholeseller, Status.Active.toString()));
		userEmail = email;
		Reporter.log("User with email id: " + userEmail + " added successfully.");
	}

	@Test(dependsOnMethods = { "AddUserTest" })
	public void EditUserTest() throws InterruptedException {
		String firstName = "";
		String lastName = "";
		String email = "";
		String phone = "2032245000";
		String wholeseller = "";
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

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'View User')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);

		List<WebElement> pages = wd.findElements(By.xpath("//div[@id='default_load']//li/a"));
		for (int k = 0; k <= (pages.size() / 2) - 1; k++) {
			WebElement webtable = wd
					.findElement(By.cssSelector("table.table-bordered.table-striped.table-condensed.cf"));
			List<WebElement> rows = webtable.findElements(By.xpath("//tr"));
			String rowContent=null;
			for (int i = 1; i <= rows.size() - 1; i++) {
				rowContent = rows.get(i).getText();
				if (rowContent.contains(userEmail)) {
					String elementId = rows.get(i).getAttribute("id");
					wd.findElement(By.xpath("//tr[@id='" + elementId + "']//a[@title='Edit']")).click();
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

		User user = new User(wd);
		assertEquals("Users Listing Update User", user.verifyBreadcrumb());
		assertEquals("Update User", user.verifyPageTitle());
		assertEquals("User updated successfully.", user.editUser(firstName, lastName, email, phone, UserRole(Role.NONE),
				wholeseller, Status.Active.toString()));
		Reporter.log("User with email id: " + userEmail + " edited successfully.");
	}

	@Test(dependsOnMethods = { "AddUserTest" })
	public void changeUserPasswordTest() throws InterruptedException {
		String password = "Qwerty123";
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

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'View User')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);
		
		/*
		 * if (wd.findElement(By.xpath(
		 * "//div[@id='default_load']//div[2]//ul[1]//li[3]//a[1]")).isDisplayed()) {
		 * wd.findElement(By.xpath(
		 * "//div[@id='default_load']//div[2]//ul[1]//li[3]//a[1]")).click(); }
		 */	
		
		List<WebElement> pages = wd.findElements(By.xpath("//div[@id='default_load']//li/a"));
		for (int k = 0; k <= (pages.size() / 2) - 1; k++) {
			Thread.sleep(2000);
			WebElement webtable = wd.findElement(By.cssSelector("table.table-bordered.table-striped.table-condensed.cf"));
			List<WebElement> rows = webtable.findElements(By.xpath("//tr"));
			String rowContent=null;
			for (int i = 1; i <= rows.size() - 1; i++) {
				rowContent = rows.get(i).getText();
				if (rowContent.contains(userEmail)) {
					String elementId = rows.get(i).getAttribute("id");
					wd.findElement(By.xpath("//tr[@id='" + elementId + "']//i[@class='fa fa-unlock-alt']")).click();
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

		ChangePassword changePwd = new ChangePassword(wd);
		assertEquals("Dashboard Change Password", changePwd.getBreadcrumb());
		assertEquals("Change Password", changePwd.getPageHeader());
		assertEquals("Password updated successfully.", changePwd.changePassword(password));
		Reporter.log("Password for User with email id: " + userEmail + " is now changed to: " + password);
	}

	@Test
	public void AddUserRequiredFieldTest() throws InterruptedException {
		String firstName = "";
		String lastName = "";
		String email = "";
		String password = "";
		String phoneNumber = "";
		String wholeseller = "";
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

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'Add User')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);

		User user = new User(wd);
		assertEquals("Users Listing Add User", user.verifyBreadcrumb());
		assertEquals("Add User", user.verifyPageTitle());
		errorMessages = user.addUser(firstName, lastName, email, password, phoneNumber, UserRole(Role.NONE),
				wholeseller, Status.Active.toString());
		Reporter.log("User required fields tested successfully. Fields in error are : \n" + errorMessages);
	}

}
