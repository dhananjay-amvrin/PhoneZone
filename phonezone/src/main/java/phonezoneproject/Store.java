package phonezoneproject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Store {
	private WebDriver driver;

	@FindBy(xpath = "//ol[@class='breadcrumb']")

	private WebElement breadcrumbText;

	@FindBy(xpath = "//h3[@class='title1']")

	private WebElement pageHeaderText;

	@FindBy(id = "store_name")

	private WebElement storeNameTextBox;

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

	private WebElement storeNameErrorText;

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

	@FindBys(@FindBy(xpath = "//div[@class='alert alert-success fade in alert-dismissable alert-cust']"))

	private List<WebElement> successMessage;

	public Store(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getBreadcrumb() {
		return breadcrumbText.getText();
	}

	public String getPageHeader() {
		return pageHeaderText.getText();
	}

	public void setStoreName(String storeName) {
		if (storeName != null && !storeName.isEmpty()) {
			storeNameTextBox.clear();
			storeNameTextBox.sendKeys(storeName);
		}
	}

	public void setAddress(String address) {
		if (address != null && !address.isEmpty()) {
			addressTextBox.clear();
			addressTextBox.sendKeys(address);
		}
	}

	public void setCity(String city) {
		if (city != null && !city.isEmpty()) {
			cityTextBox.clear();
			cityTextBox.sendKeys(city);
		}
	}

	public void setState(String state) {
		if (state != null && !state.isEmpty()) {
			stateTextBox.clear();
			stateTextBox.sendKeys(state);
		}
	}

	public void setCountry(String country) {
		if (country != null && !country.isEmpty()) {
			countryTextBox.clear();
			countryTextBox.sendKeys(country);
		}
	}

	public void setPinCode(String pinCode) {
		if (pinCode != null && !pinCode.isEmpty()) {
			pinCodeDropdown.clear();
			pinCodeDropdown.sendKeys(pinCode);
		}
	}

	public void selectStatus(String status) {
		Select listbox = new Select(statusDropdown);
		if (status != null && !status.isEmpty()) {
			listbox.selectByVisibleText(status);
		}
	}

	public void submit() {
		submitButton.click();
	}

	public String getStoreNameError() {
		return storeNameErrorText.getText();
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
		return successMessage.get(0).getText();
	}

	public String verifyBreadcrumb() {
		return this.getBreadcrumb();
	}

	public String verifyPageTitle() {
		return this.getPageHeader();
	}

	public String addStore(String storeName, String address, String city, String state, String country, String pinCode,
			String status) {
		StringBuffer errorMessages = new StringBuffer();
		this.setStoreName(storeName);
		this.setAddress(address);
		this.setCity(city);
		this.setState(state);
		this.setCountry(country);
		this.setPinCode(pinCode);
		this.selectStatus(status);
		this.submit();

		if (successMessage.size()>0 && successMessage != null)
			return this.getsuccessMessage();
		else
			errorMessages.append(this.getStoreNameError());
			errorMessages.append(System.getProperty("line.separator"));
			errorMessages.append(this.getAddressError());
			errorMessages.append(System.getProperty("line.separator"));
			errorMessages.append(this.getCityError());
			errorMessages.append(System.getProperty("line.separator"));
			errorMessages.append(this.getStateError());
			errorMessages.append(System.getProperty("line.separator"));
			errorMessages.append(this.getCountryError());
			errorMessages.append(System.getProperty("line.separator"));
			errorMessages.append(this.getPinCodeError());
			return errorMessages.toString();

	}

	public String editStore(String storeName, String address, String city, String state, String country, String pinCode,
			String status) {
		StringBuffer errorMessages = new StringBuffer();
		this.setStoreName(storeName);
		this.setAddress(address);
		this.setCity(city);
		this.setState(state);
		this.setCountry(country);
		this.setPinCode(pinCode);
		this.selectStatus(status);
		this.submit();

		if (successMessage.size()>0 && successMessage != null)
			return this.getsuccessMessage();
		else
			errorMessages.append(this.getStoreNameError());
			errorMessages.append(System.getProperty("line.separator"));
			errorMessages.append(this.getAddressError());
			errorMessages.append(System.getProperty("line.separator"));
			errorMessages.append(this.getCityError());
			errorMessages.append(System.getProperty("line.separator"));
			errorMessages.append(this.getStateError());
			errorMessages.append(System.getProperty("line.separator"));
			errorMessages.append(this.getCountryError());
			errorMessages.append(System.getProperty("line.separator"));
			errorMessages.append(this.getPinCodeError());
			return errorMessages.toString();

	}

}
