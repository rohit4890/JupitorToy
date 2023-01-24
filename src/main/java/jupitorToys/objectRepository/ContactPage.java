package jupitorToys.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactPage extends BasePage{
	
	private By headerMessage = By.xpath("//strong[text()='We welcome your feedback']");
	private By foreNameField = By.id("forename");
	private By surnameField = By.id("surname");
	private By emailField = By.id("email");
	private By phoneField = By.id("telephone");
	private By messageField = By.id("message");
	private By submitButton = By.xpath("//a[text()='Submit']");
	
	private By foreNameError = By.id("forename-err");
	private By emailError = By.id("email-err");
	private By messageError = By.id("message-err");
	
	private By successMsg = By.xpath("//div[@class='alert alert-success']");
	
	public ContactPage(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getForeNameField() {
		return getElementWithWait_presenseOfElementLocated(foreNameField);
	}
	public WebElement getSurnameField() {
		return getElementWithWait_presenseOfElementLocated(surnameField);
	}
	public WebElement getEmailField() {
		return getElementWithWait_presenseOfElementLocated(emailField);
	}
	public WebElement getPhoneField() {
		return getElementWithWait_presenseOfElementLocated(phoneField);
	}
	public WebElement getMessageField() {
		return getElementWithWait_presenseOfElementLocated(messageField);
	}
	public WebElement getSubmitButton() {
		return getElementWithWait_elementToBeClickable(submitButton);
	}

	public String getSuccessMessage() {
		return getElementWithWait_presenseOfElementLocated(successMsg).getText();
	}
	
	public String getForenameErrorMessage() {
		if(isErrorMessageDisplayedOnfirstNameField() == true) {
			WebElement element =  getElementWithWait_presenseOfElementLocated(foreNameError);
			return element.getText();
		}else {
			return null;
		}
	}
	public String getEmailErrorMessage() {
		if(isErrorMessageDisplayedOnEmailField() == true) {
			WebElement element =  getElementWithWait_presenseOfElementLocated(emailError);
			return element.getText();
		}else {
			return null;
		}
	}
	public String getMessageErrorMessage() {
		if(isErrorMessageDisplayedOnMessageField() == true) {
			WebElement element =  getElementWithWait_presenseOfElementLocated(messageError);
			return element.getText();
		}else {
			return null;
		}
	}
	
	public boolean isErrorMessageDisplayedOnfirstNameField() {
		return isElementDisplayedOnPage(foreNameError);
	}
	public boolean isErrorMessageDisplayedOnEmailField() {
		return isElementDisplayedOnPage(emailError);
	}
	public boolean isErrorMessageDisplayedOnMessageField() {
		return isElementDisplayedOnPage(messageError);
	}
	
	public String getHeaderMessage() {
		return getElementWithWait_presenseOfElementLocated(headerMessage).getText();
	}
	
	public void clickOnSubmitButton() {
		getSubmitButton().click();
	}
	
	public void enterForename(String forename) {
		getForeNameField().sendKeys(forename);
	}
	public void enterEmail(String email) {
		getEmailField().sendKeys(email);
	}
	public void enterMessage(String message) {
		getMessageField().sendKeys(message);
	}
}
