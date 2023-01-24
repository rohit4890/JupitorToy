package jupitorToys.objectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BasePage extends ParentPage{

	public BasePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	public WebElement getElement_withoutWait(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			return element;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public WebElement getElementWithWait_presenseOfElementLocated(By locator) {
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return element;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public WebElement getElementWithWait_elementToBeClickable(By locator) {
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			return element;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public List<WebElement> getElementListWithWait_presenseOfElementLocated(By locator) {
		List<WebElement> element = null;
		try {
			element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			return element;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public boolean isElementDisplayedOnPage(By locator) {
		if (driver.findElements(locator).size() != 0) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String getCurrentURL() {
		String url = driver. getCurrentUrl();
		return url;
	}
}
