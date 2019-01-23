package phonezoneproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class User {
	private WebDriver driver;

	@FindBy(xpath = "//ol[@class='breadcrumb']")

	private WebElement breadcrumbText;

	@FindBy(xpath = "//h3[@class='title1']")

	private WebElement pageHeaderText;

	@FindBy(id = "first_name")

	private WebElement firstNameTextBox;

	@FindBy(id = "last_name")

	private WebElement lastNameTextBox;

	@FindBy(id = "email")

	private WebElement emailTextBox;

	@FindBy(id = "password")

	private WebElement passwordTextBox;

	@FindBy(id = "phone")

	private WebElement phoneTextBox;

	@FindBy(id = "users_role")

	private WebElement userRoleDropdown;

	@FindBy(id = "distributor_id")

	private WebElement wholesellerDropdown;

	@FindBy(id = "status")

	private WebElement statusDropdown;

	@FindBy(id = "add_btn")

	private WebElement submitButton;

	@FindBy(id = "first_name-error")

	private WebElement firstNameErrorText;

	@FindBy(id = "last_name-error")

	private WebElement lastNameErrorText;

	@FindBy(id = "email-error")

	private WebElement emailErrorText;

	@FindBy(id = "password-error")

	private WebElement passwordErrorText;

	@FindBy(id = "phone-error")

	private WebElement phoneErrorText;

	@FindBy(id = "users_role-error")

	private WebElement userRoleErrorText;

	@FindBy(xpath = "//div[@class='alert alert-success fade in alert-dismissable alert-cust']")

	private WebElement successMessage;

	public User(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private String getBreadcrumb() {
		return breadcrumbText.getText();
	}

	private String getPageHeader() {
		return pageHeaderText.getText();
	}

	private void setFirstName(String firstName) {
		if (firstName != null && !firstName.isEmpty()) {
			firstNameTextBox.clear();
			firstNameTextBox.sendKeys(firstName);
		}
	}

	private void setLastName(String lastName) {
		if (lastName != null && !lastName.isEmpty()) {
			lastNameTextBox.clear();
			lastNameTextBox.sendKeys(lastName);
		}
	}

	private void setEmail(String email) {
		if (email != null && !email.isEmpty()) {
			emailTextBox.clear();
			emailTextBox.sendKeys(email);
		}
	}

	private void setPassword(String password) {
		if (password != null && !password.isEmpty()) {
			passwordTextBox.clear();
			passwordTextBox.sendKeys(password);
		}
	}

	private void setPhone(String phone) {
		if (phone != null && !phone.isEmpty()) {
			phoneTextBox.clear();
			phoneTextBox.sendKeys(phone);
		}
	}

	private void selectUserRole(String role) {
		Select listbox = new Select(userRoleDropdown);
		if (role != null && !role.isEmpty())
			listbox.selectByVisibleText(role);
	}

	private void selectWholeseller(String wholeseller) {
		Select listbox = new Select(wholesellerDropdown);
		if (wholeseller != null && !wholeseller.isEmpty())
			listbox.selectByVisibleText(wholeseller);
	}

	private void selectStatus(String status) {
		Select listbox = new Select(statusDropdown);
		if (status != null && !status.isEmpty()) {
			listbox.selectByVisibleText(status);
		}
	}

	private void submit() {
		submitButton.click();
	}

	private String getFirstNameError() {
		return firstNameErrorText.getText();
	}

	private String getLastNameError() {
		return lastNameErrorText.getText();
	}

	private String getEmailError() {
		return emailErrorText.getText();
	}

	private String getPasswordError() {
		return passwordErrorText.getText();
	}

	private String getPhoneError() {
		return phoneErrorText.getText();
	}

	private String getUserRoleError() {
		return userRoleErrorText.getText();
	}

	private String getsuccessMessage() {
		return successMessage.getText();
	}

	public String verifyBreadcrumb() {
		return this.getBreadcrumb();
	}

	public String verifyPageTitle() {
		return this.getPageHeader();
	}

	public void waitForPageToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
	}

	public String addUser(String firstName, String lastName, String email, String password, String phoneNumber,
			String userRole, String wholeseller, String status) {
		StringBuffer errorMessages = new StringBuffer();
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
		this.setPhone(phoneNumber);
		this.selectUserRole(userRole);
		this.selectWholeseller(wholeseller);
		this.selectStatus(status);
		this.submit();

		if (successMessage.isDisplayed())
			return getsuccessMessage();
		else
			errorMessages.append(this.getFirstNameError());
		errorMessages.append(this.getLastNameError());
		errorMessages.append(this.getEmailError());
		errorMessages.append(this.getPasswordError());
		errorMessages.append(this.getPhoneError());
		errorMessages.append(this.getUserRoleError());
		return errorMessages.toString();
	}

	public String editUser(String firstName, String lastName, String email, String phone, String userRole,
			String wholeseller, String status) {
		StringBuffer errorMessages = new StringBuffer();
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.selectUserRole(userRole);
		this.selectWholeseller(wholeseller);
		this.selectStatus(status);
		this.submit();

		if (successMessage.isDisplayed())
			return getsuccessMessage();
		else
			errorMessages.append(this.getFirstNameError());
		errorMessages.append(this.getLastNameError());
		errorMessages.append(this.getEmailError());
		errorMessages.append(this.getPhoneError());
		errorMessages.append(this.getUserRoleError());
		return errorMessages.toString();
	}

	/*
	 * public boolean isTextPresent(String text){ try { boolean flag =
	 * driver.getPageSource().contains(text); return flag; } catch(Exception e){
	 * return false; } }
	 */
}
