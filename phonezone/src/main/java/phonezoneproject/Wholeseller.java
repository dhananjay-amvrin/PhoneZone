package phonezoneproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Wholeseller {
	private WebDriver driver;

	@FindBy(xpath = "//ol[@class='breadcrumb']")

	private WebElement breadcrumbText;

	@FindBy(xpath = "//h3[@class='title1'and contains(text(), 'Add Wholeseller')]")

	private WebElement pageHeaderText;

	@FindBy(id = "store_name")

	private WebElement wholesellerNameTextBox;

	@FindBy(id = "address")

	private WebElement addressTextBox;

	@FindBy(id = "city")

	private WebElement cityTextBox;

	@FindBy(id = "state")

	private WebElement stateTextBox;

	@FindBy(id = "country")

	private WebElement countryTextBox;

	@FindBy(id = "pin_code")

	private WebElement pinCodeDropdown;

	@FindBy(id = "status")

	private WebElement statusDropdown;

	@FindBy(id = "add_btn")

	private WebElement submitButton;

	@FindBy(id = "store_name-error")

	private WebElement wholesellerNameErrorText;

	@FindBy(id = "address-error")

	private WebElement addressErrorText;

	@FindBy(id = "city-error")

	private WebElement cityErrorText;

	@FindBy(id = "state-error")

	private WebElement stateErrorText;

	@FindBy(id = "country-error")

	private WebElement countryErrorText;

	@FindBy(id = "pin_code-error")

	private WebElement pinCodeErrorText;
	
	@FindBy(xpath = ".//div[@class='alert alert-success fade in alert-dismissable alert-cust']")

	private WebElement successMessage;

	public Wholeseller(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getBreadcrumb() {
		return breadcrumbText.getText();
	}

	public String getPageHeader() {
		return pageHeaderText.getText();
	}

	public void setWholesellerName(String wholesellerName) {
		wholesellerNameTextBox.sendKeys(wholesellerName);
	}

	public void setAddress(String address) {
		addressTextBox.sendKeys(address);
	}

	public void setCity(String city) {
		cityTextBox.sendKeys(city);
	}

	public void setState(String state) {
		stateTextBox.sendKeys(state);
	}

	public void setCountry(String country) {
		countryTextBox.sendKeys(country);
	}

	public void setPinCode(String pinCode) {
		pinCodeDropdown.sendKeys(pinCode);
	}

	public void selectStatus(String status) {
		Select listbox = new Select(statusDropdown);
		listbox.selectByValue(status);
	}

	public void submit() {
		submitButton.click();
	}

	public String getWholesellerNameError() {
		return wholesellerNameErrorText.getText();
	}

	public String getAddressError() {
		return addressErrorText.getText();
	}

	public String getCityError() {
		return cityErrorText.getText();
	}

	public String getStateError() {
		return stateErrorText.getText();
	}

	public String getCountryError() {
		return countryErrorText.getText();
	}

	public String getPinCodeError() {
		return pinCodeErrorText.getText();
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
	
	public String addWholeseller(String wholesellerName, String address, String city, String state, String country, String pinCode, String status) {
		StringBuffer errorMessages = new StringBuffer();
		this.setWholesellerName(wholesellerName);
		this.setAddress(address);
		this.setCity(city);
		this.setState(state);
		this.setCountry(country);
		this.setPinCode(pinCode);
		this.selectStatus(status);
		this.submit();
		
		if (successMessage.isDisplayed())
			return this.getsuccessMessage();
		else
			errorMessages.append(this.getWholesellerNameError());
			errorMessages.append(this.getAddressError());
			errorMessages.append(this.getCityError());
			errorMessages.append(this.getStateError());
			errorMessages.append(this.getCountryError());
			errorMessages.append(this.getPinCodeError());
			return errorMessages.toString();		
	}
	
	public String editWholeseller(String wholesellerName, String address, String city, String state, String country, String pinCode, String status) {
		StringBuffer errorMessages = new StringBuffer();
		this.setWholesellerName(wholesellerName);
		this.setAddress(address);
		this.setCity(city);
		this.setState(state);
		this.setCountry(country);
		this.setPinCode(pinCode);
		this.selectStatus(status);
		this.submit();
		
		if (successMessage.isDisplayed())
			return this.getsuccessMessage();
		else
			errorMessages.append(this.getWholesellerNameError());
			errorMessages.append(this.getAddressError());
			errorMessages.append(this.getCityError());
			errorMessages.append(this.getStateError());
			errorMessages.append(this.getCountryError());
			errorMessages.append(this.getPinCodeError());
			return errorMessages.toString();			
	}


}
