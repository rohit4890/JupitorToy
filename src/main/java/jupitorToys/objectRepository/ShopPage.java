package jupitorToys.objectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopPage extends BasePage{
	
	String name;
	String start = "//h4[text()=\'";
	String end = "\']";
	String mylocator;
	
	private By products = By.xpath("//h4");
	private By price = By.xpath(mylocator+"//parent::div//p//span");
	private By buyButton = By.xpath(mylocator+"//parent::div//p//a");

	public ShopPage(WebDriver driver) {
		super(driver);
	}

	public List<WebElement> getAllProducts(){
		return getElementListWithWait_presenseOfElementLocated(products);
	}
	public String getPriceField(String productName) {
		this.mylocator = start+productName+end;
		price = By.xpath(mylocator+"//parent::div//p//span");
		return getElementWithWait_presenseOfElementLocated(price).getText();
	}
	public WebElement getBuyButton() {
		this.mylocator = start+name+end;
		buyButton = By.xpath(mylocator+"//parent::div//p//a");
		return getElementWithWait_presenseOfElementLocated(buyButton);
	}
	
	public double buyProduct(String productName, int quantity) {
		this.name = productName;
		double totalAmount = 0.0;
		boolean isProductAvailable = false;
		
		List<WebElement> list = getAllProducts();
		for (WebElement element : list) {
		    if(element.getText().equalsIgnoreCase(productName)) {
		    	isProductAvailable = true;
		    	break;
		    }
		}
		if(isProductAvailable) {
			for(int i = 0; i<quantity; i++) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				totalAmount = totalAmount + getProductPriceOnShopPage(productName);
				clickOnBuyButton();
			}
			return totalAmount;
		}
		return 0.0;
	}
	
	public void clickOnBuyButton() {
		getBuyButton().click();
	}
	
	public double getProductPriceOnShopPage(String productName) {
		String amount = getPriceField(productName);
		int index = amount.indexOf("$")+1;
		String amount1 = amount.substring(index);
		double price = Double.parseDouble(amount1);
		return price;
	}
}
