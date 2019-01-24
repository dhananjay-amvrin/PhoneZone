package phonezoneproject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ChangePassword {

	private WebDriver driver;
	
	//page elements locator

	@FindBy(xpath = "//ol[@class='breadcrumb']")

	private WebElement breadcrumbText;

	@FindBy(xpath = "//h3[@class='title1']")

	private WebElement pageHeaderText;

	@FindBy(id = "new_password")

	private WebElement newPasswordTextBox;

	@FindBy(id = "re_new_password")

	private WebElement confirmPasswordTextBox;

	@FindBy(id = "add_btn")

	private WebElement submitButton;

	@FindBy(id = "new_password-error")

	private WebElement newPasswordErrorText;

	@FindBy(id = "re_new_password-error")

	private WebElement confirmPasswordErrorText;	
	
	@FindBys(@FindBy(xpath = "//div[@class='alert alert-success fade in alert-dismissable alert-cust']"))

	private List<WebElement> successMessage;

	//page methods
	
	public ChangePassword(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getBreadcrumb() {
		return breadcrumbText.getText();
	}

	public String getPageHeader() {
		return pageHeaderText.getText();
	}

	public void setNewPassword(String newPassword) {
		newPasswordTextBox.sendKeys(newPassword);
	}

	public void setConfirmPassword(String newPassword) {
		confirmPasswordTextBox.sendKeys(newPassword);
	}

	public void submit() {
		submitButton.click();
	}
	
	private String getsuccessMessage() {
		return successMessage.get(0).getText();
	}

	public String getNewPasswordError() {
		return newPasswordErrorText.getText();
	}

	public String getConfirmPasswordError() {
		return confirmPasswordErrorText.getText();
	}
	
	public String changePassword(String password) {
		StringBuffer errorMessages = new StringBuffer();
		if (password != null && !password.isEmpty())
			this.setNewPassword(password);
			this.setConfirmPassword(password);
			this.submit();
		if (successMessage.size()>0 && successMessage != null)
			return this.getsuccessMessage();
		else
			errorMessages.append(this.getNewPasswordError());
			errorMessages.append(System.getProperty("line.separator"));
			errorMessages.append(this.getConfirmPasswordError());		
			return errorMessages.toString();
	}

}
