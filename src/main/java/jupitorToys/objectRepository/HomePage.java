package jupitorToys.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends BasePage{
	
	private By homeTab = By.cssSelector("li#nav-home>a[href$='home']");
	private By shopTab = By.cssSelector("li#nav-shop>a[href$='shop']");
	private By contactTab = By.cssSelector("li#nav-contact>a[href$='contact']");
	private By cartTab = By.cssSelector("li#nav-cart>a[href$='cart']");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	// Calling generic methods from BasePage class to get WebElement of given locators
	public WebElement getHomeTab() {
		return getElementWithWait_elementToBeClickable(homeTab);
	}

	public WebElement getShopTab() {
		return getElementWithWait_elementToBeClickable(shopTab);
	}

	public WebElement getContactTab() {
		return getElementWithWait_elementToBeClickable(contactTab);
	}

	public WebElement getCartTab() {
		return getElementWithWait_elementToBeClickable(cartTab);
	}
	
	// Clicks on home tab 
	public void clickOnHomeTab() {
		getHomeTab().click();
	}
	
	// Click on shop tab
	public void clickOnShopTab() {
		getShopTab().click();
	}
	
	// Click on contact tab
	public void clickOnContactTab() {
		getContactTab().click();
	}
		
	// Click on shop tab
	public void clickOnCartTab() {
		getCartTab().click();
	}
}