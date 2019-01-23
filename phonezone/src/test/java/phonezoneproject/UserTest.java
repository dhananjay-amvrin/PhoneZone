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
	private String userEmail="User8@datagenius.co.nz";
	Random rand = new Random();
	int randNo = rand.nextInt(1000) - 100;

	@Test
	public void AddUserTest() throws InterruptedException {
		String firstName = "Test";
		String lastName = "User";
		String email = "User" + randNo + "@datagenius.co.nz";
		String password = "qwerty123";
		String phoneNumber = "2032244000";
		String userRole = "Store Staff";
		String wholeseller = "";
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

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'Add User')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();

		User user = new User(wd);
		assertEquals("Users Listing Add User", user.verifyBreadcrumb());
		assertEquals("Add User", user.verifyPageTitle());
		assertEquals("User added successfully.",
				user.addUser(firstName, lastName, email, password, phoneNumber, userRole, wholeseller, status));
		userEmail = email;
		Reporter.log("User with email id: " + userEmail + " added successfully.");
	}

	@Test
	public void EditUserTest() throws InterruptedException {
		String firstName = "";
		String lastName = "";
		String email = "";
		String phone = "2032245000";
		String userRole = "";
		String wholeseller = "";
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

		WebElement subMenu = wd.findElement(By.xpath("//a[contains(text(),'View User')]"));
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
			if (rowContent.contains(userEmail)) {
				String elementId = rows.get(i).getAttribute("id");
				wd.findElement(By.xpath("//tr[@id='" + elementId + "']//a[@title='Edit']")).click();
				Thread.sleep(2000);
				break;
			}
		}

		User user = new User(wd);
		assertEquals("Users Listing Update User", user.verifyBreadcrumb());
		assertEquals("Update User", user.verifyPageTitle());
		assertEquals("User updated successfully.",
				user.editUser(firstName, lastName, email, phone, userRole, wholeseller, status));
		Reporter.log("User with email id: " + userEmail + " edited successfully.");
	}

	@Test
	public void changeUserPasswordTest() throws InterruptedException {
		String password = "Qwerty123";

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
			if (rowContent.contains(userEmail)) {
				String elementId = rows.get(i).getAttribute("id");
				wd.findElement(By.xpath("//tr[@id='" + elementId + "']//i[@class='fa fa-unlock-alt']")).click();
				Thread.sleep(2000);
				break;
			}
		}

		ChangePassword changePwd = new ChangePassword(wd);
		assertEquals("Dashboard Change Password", changePwd.getBreadcrumb());
		assertEquals("Change Password", changePwd.getPageHeader());
		assertEquals("Password updated successfully.", changePwd.changePassword(password));
		Reporter.log("Password for User with email id: " + userEmail + " is now changed to: " + password);
	}
}
