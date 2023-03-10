package jupitorToys.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.aventstack.extentreports.Status;

import jupitorToys.objectRepository.CartPage;
import jupitorToys.objectRepository.ContactPage;
import jupitorToys.objectRepository.HomePage;
import jupitorToys.objectRepository.ShopPage;

public class InterviewTest extends BaseTest{

	@Test
	public void validateErrorMessageOnContactPage() {
		
		// Verify if Home Page is open or not
		parentPage.getInstanceOf(HomePage.class).clickOnHomeTab();
		String homePageUrl = parentPage.getCurrentURL();
		if(homePageUrl.contains("home")) {
			extentTest.get().log(Status.PASS, "Home Page is opened");
		}else {
			extentTest.get().log(Status.FAIL, "Home Page is not opened");
		}
		Assert.assertEquals(homePageUrl, "https://jupiter.cloud.planittesting.com/#/home");
		
		
		// Click on Contact tab and verify if it is open
		parentPage.getInstanceOf(HomePage.class).clickOnContactTab();
		ContactPage contactPage = parentPage.getInstanceOf(ContactPage.class);
		String contactPageHeaderMessage = contactPage.getHeaderMessage();
		if(contactPageHeaderMessage.contains("welcome")) {
			extentTest.get().log(Status.PASS, "Contact Page is opened");
		}else {
			extentTest.get().log(Status.FAIL, "Contact Page is not opened");
		}
		Assert.assertEquals(contactPageHeaderMessage, "We welcome your feedback");
		
		
		// Click on submit button without populating fields
		contactPage.clickOnSubmitButton();
		extentTest.get().log(Status.INFO, "Submit button is clicked");
		
		// Is Error Message Displayed
		boolean isErrorOnForenameameField = contactPage.isErrorMessageDisplayedOnfirstNameField();
		if(isErrorOnForenameameField) {
			extentTest.get().log(Status.PASS, "Error Message displyed on forename field");
		}else {
			extentTest.get().log(Status.FAIL, "Error Message not displyed on forename field");
		}
		softAssert.assertEquals(isErrorOnForenameameField, true);
		
		boolean isErrorOnEmailField = contactPage.isErrorMessageDisplayedOnEmailField();
		if(isErrorOnEmailField) {
			extentTest.get().log(Status.PASS, "Error Message displyed on email field");
		}else {
			extentTest.get().log(Status.FAIL, "Error Message not displyed on email field");
		}
		softAssert.assertEquals(isErrorOnEmailField, true);
		
		boolean isErrorOnMessageField = contactPage.isErrorMessageDisplayedOnMessageField();
		if(isErrorOnMessageField) {
			extentTest.get().log(Status.PASS, "Error Message displyed on message field");
		}else {
			extentTest.get().log(Status.FAIL, "Error Message not displyed on message field");
		}
		softAssert.assertEquals(isErrorOnMessageField, true);
		
		
		// Verifying Error Message
		String forenameFieldError = contactPage.getForenameErrorMessage();
		if(forenameFieldError.equalsIgnoreCase("Forename is required")) {
			extentTest.get().log(Status.PASS, "Correct error message on forename field: "+forenameFieldError);
		}else {
			extentTest.get().log(Status.FAIL, "Incorrect error message on forename field");
		}
		softAssert.assertEquals(forenameFieldError, "Forename is required");
		
		String emailFieldError = contactPage.getEmailErrorMessage();
		if(emailFieldError.equalsIgnoreCase("Email is required")) {
			extentTest.get().log(Status.PASS, "Correct error message on email field: "+emailFieldError);
		}else {
			extentTest.get().log(Status.FAIL, "Incorrect error message on email field");
		}
		softAssert.assertEquals(emailFieldError, "Email is required");
		
		String messageFieldError = contactPage.getMessageErrorMessage();
		if(messageFieldError.equalsIgnoreCase("Message is required")) {
			extentTest.get().log(Status.PASS, "Correct error message on message field: "+messageFieldError);
		}else {
			extentTest.get().log(Status.FAIL, "Incorrect error message on message field");
		}
		softAssert.assertEquals(messageFieldError, "Message is required");
		
		
		// Populate mandatory fields
		contactPage.enterForename(propReader.getDataFromPropertiesFile("forename"));
		extentTest.get().log(Status.INFO, "Forename field is populated with: "+propReader.getDataFromPropertiesFile("forename"));
		contactPage.enterEmail(propReader.getDataFromPropertiesFile("email"));
		extentTest.get().log(Status.INFO, "Email field is populated with: "+propReader.getDataFromPropertiesFile("email"));
		contactPage.enterMessage(propReader.getDataFromPropertiesFile("message"));
		extentTest.get().log(Status.INFO, "Message field is populated with: "+propReader.getDataFromPropertiesFile("message"));
		
		
		// Verify if error message still there
		boolean isErrorOnForenameame = contactPage.isErrorMessageDisplayedOnfirstNameField();
		if(isErrorOnForenameame) {
			extentTest.get().log(Status.FAIL, "Error message should not be there");
		}else {
			extentTest.get().log(Status.PASS, "Error message is not displayed after populating forename field");
		}
		softAssert.assertEquals(isErrorOnForenameame, false);
		
		boolean isErrorOnEmail = contactPage.isErrorMessageDisplayedOnEmailField();
		if(isErrorOnEmail) {
			extentTest.get().log(Status.FAIL, "Error message should not be there");
		}else {
			extentTest.get().log(Status.PASS, "Error message is not displayed after populating email field");
		}
		softAssert.assertEquals(isErrorOnEmail, false);
		
		boolean isErrorOnMessage = contactPage.isErrorMessageDisplayedOnMessageField();
		if(isErrorOnMessage) {
			extentTest.get().log(Status.FAIL, "Error message should not be there");
		}else {
			extentTest.get().log(Status.PASS, "Error message is not displayed after populating message field");
		}
		softAssert.assertEquals(isErrorOnMessage, false);
		
		softAssert.assertAll();
	}

	@Test(dataProvider = "data-provider")
	public void validateSuccessfulSubmission(String forename, String email, String message) {
		
		// Verify if Home Page is open or not
		parentPage.getInstanceOf(HomePage.class).clickOnHomeTab();
		String homePageUrl = parentPage.getCurrentURL();
		if(homePageUrl.contains("home")) {
			extentTest.get().log(Status.PASS, "Home Page is opened");
		}else {
			extentTest.get().log(Status.FAIL, "Home Page is not opened");
		}
		Assert.assertEquals(homePageUrl, "https://jupiter.cloud.planittesting.com/#/home");
		
		// Click on Contact tab and verify if it is open
		parentPage.getInstanceOf(HomePage.class).clickOnContactTab();
		ContactPage contactPage = parentPage.getInstanceOf(ContactPage.class);
		String contactPageHeaderMessage = contactPage.getHeaderMessage();
		if(contactPageHeaderMessage.contains("welcome")) {
			extentTest.get().log(Status.PASS, "Contact Page is opened");
		}else {
			extentTest.get().log(Status.FAIL, "Contact Page is not opened");
		}
		Assert.assertEquals(contactPageHeaderMessage, "We welcome your feedback");
		
		
		// Populate Mandatory Field
		contactPage.enterForename(forename);
		extentTest.get().log(Status.INFO, "Forename field is populated with: "+forename);
		contactPage.enterEmail(email);
		extentTest.get().log(Status.INFO, "Email field is populated with: "+email);
		contactPage.enterMessage(message);
		extentTest.get().log(Status.INFO, "Message field is populated with: "+message);
		
		// Click on submit button without populating fields
		contactPage.clickOnSubmitButton();
		extentTest.get().log(Status.INFO, "Submit button is clicked");
		
		// Validating success message
		String sucessMessage = "Thanks "+forename+", we appreciate your feedback.";
		if(sucessMessage.equalsIgnoreCase(contactPage.getSuccessMessage())) {
			extentTest.get().log(Status.PASS, "Form for forename: "+forename+" is submitted successfully");
		}else {
			extentTest.get().log(Status.FAIL, "Form for forename: "+forename+" is not submitted successfully");
		}
		Assert.assertEquals(sucessMessage, contactPage.getSuccessMessage());	
	}
	
	@Test
	public void verifyTotalAmount() {
		
		String product = null;
		int productCount = 0;
		boolean isProductAvailable = false;
		
		// Verify if Home Page is open or not
		parentPage.getInstanceOf(HomePage.class).clickOnHomeTab();
		String homePageUrl = parentPage.getCurrentURL();
		if(homePageUrl.contains("home")) {
			extentTest.get().log(Status.PASS, "Home Page is opened");
		}else {
			extentTest.get().log(Status.FAIL, "Home Page is not opened");
		}
		Assert.assertEquals(homePageUrl, "https://jupiter.cloud.planittesting.com/#/home");
		
		
		// Verify if Shop Page is open or not
		parentPage.getInstanceOf(HomePage.class).clickOnShopTab();
		String shopPageUrl = parentPage.getCurrentURL();
		if(shopPageUrl.contains("shop")) {
			extentTest.get().log(Status.PASS, "Shop Page is opened");
		}else {
			extentTest.get().log(Status.FAIL, "Shop Page is not opened");
		}
		
		
		// Buy Stuffed Frog
		product = "Stuffed Frog";
		productCount = 2;
		ShopPage shopPage = parentPage.getInstanceOf(ShopPage.class);
		double singleStuffedFrogPrice_shopPage = shopPage.getProductPriceOnShopPage(product);
		extentTest.get().log(Status.INFO, "Price of one "+product+" on shop page is: "+singleStuffedFrogPrice_shopPage);

		double totalPriceForStuffedFrog_shopPage = shopPage.buyProduct(product, productCount);
		if(totalPriceForStuffedFrog_shopPage == 0.0) {
			extentTest.get().log(Status.FAIL, product+" is not available on shop page");
		}else {
			extentTest.get().log(Status.PASS, "Price of "+productCount+" "+product+" based on buyProduct() methond is: "+totalPriceForStuffedFrog_shopPage);
		}
		
		
		// Navigate to cart tab
		parentPage.getInstanceOf(HomePage.class).clickOnCartTab();
		String cartPageUrl = parentPage.getCurrentURL();
		if(cartPageUrl.contains("cart")) {
			extentTest.get().log(Status.PASS, "Cart Page is opened");
		}else {
			extentTest.get().log(Status.FAIL, "Cart Page is not opened");
		}
		Assert.assertEquals(cartPageUrl, "https://jupiter.cloud.planittesting.com/#/cart");
		
		// Verify if product got added
		CartPage cartPage = parentPage.getInstanceOf(CartPage.class);
		isProductAvailable = cartPage.isProductAdded(product);
		if(isProductAvailable) {
			extentTest.get().log(Status.PASS, product+" is added to cart");
		}else {
			extentTest.get().log(Status.FAIL, product+" is not added to cart");
		}
		softAssert.assertTrue(isProductAvailable);
		
		// Verify single price and total price on cart page
		double singleStuffedFrogPrice_cartPage = cartPage.getSingleProductPrice(product);
		extentTest.get().log(Status.INFO, "Price of one "+product+" on cart page is: "+singleStuffedFrogPrice_cartPage);
		if(singleStuffedFrogPrice_shopPage == singleStuffedFrogPrice_cartPage) {
			extentTest.get().log(Status.PASS, "Price of one "+product+" on shop page:"+singleStuffedFrogPrice_shopPage+" and on cart page"+singleStuffedFrogPrice_cartPage+" is same");
		}else {
			extentTest.get().log(Status.PASS, "Price of one "+product+" on shop page:"+singleStuffedFrogPrice_cartPage+" and on cart page"+singleStuffedFrogPrice_cartPage+" is not same");
		}
		softAssert.assertEquals(singleStuffedFrogPrice_shopPage, singleStuffedFrogPrice_cartPage);
		
		
		double totalPriceForStuffedFrog_cartPage = cartPage.getTotalProductAmount(product);
		extentTest.get().log(Status.INFO, "Price of "+productCount+" "+product+" on cart page is: "+totalPriceForStuffedFrog_cartPage);
		if(totalPriceForStuffedFrog_shopPage == totalPriceForStuffedFrog_cartPage) {
			extentTest.get().log(Status.PASS, "Total price of "+productCount+" "+product+" on shop page:"+totalPriceForStuffedFrog_shopPage+" and on cart page"+totalPriceForStuffedFrog_cartPage+" is same");
		}else {
			extentTest.get().log(Status.FAIL, "Total price of "+productCount+" "+product+" on shop page:"+totalPriceForStuffedFrog_shopPage+" and on cart page"+totalPriceForStuffedFrog_cartPage+" is not same");
		}
		softAssert.assertEquals(totalPriceForStuffedFrog_shopPage, totalPriceForStuffedFrog_cartPage);
		
		
		// Empty variables
		productCount = 0;
		product = null;
		isProductAvailable = false;
		
		// Click on shop tab
		parentPage.getInstanceOf(HomePage.class).clickOnShopTab();
		String shopPageUrl1 = parentPage.getCurrentURL();
		if(shopPageUrl1.contains("shop")) {
			extentTest.get().log(Status.PASS, "Shop Page is opened");
		}else {
			extentTest.get().log(Status.FAIL, "Shop Page is not opened");
		}
		Assert.assertEquals(shopPageUrl1, "https://jupiter.cloud.planittesting.com/#/shop");
		
		
		
		
	
		// Buy Fluffy Bunny
		product = "Fluffy Bunny";
		productCount = 5;
		double singleFluffyBunnyPrice_shopPage = shopPage.getProductPriceOnShopPage(product);
		extentTest.get().log(Status.INFO, "Price of one "+product+" on shop page is: "+singleFluffyBunnyPrice_shopPage);
		
		double totalPriceForFluffyBunny_shopPage = shopPage.buyProduct(product, productCount);
		if(totalPriceForFluffyBunny_shopPage == 0.0) {
			extentTest.get().log(Status.FAIL, product+" is not available on shop page");
		}else {
			extentTest.get().log(Status.PASS, "Price of "+productCount+" "+product+" based on buyProduct() methond is: "+totalPriceForFluffyBunny_shopPage);
		}

				
		// Navigate to cart tab
		parentPage.getInstanceOf(HomePage.class).clickOnCartTab();
		String cartPageUrl1 = parentPage.getCurrentURL();
		if(cartPageUrl1.contains("cart")) {
			extentTest.get().log(Status.PASS, "Cart Page is opened");
		}else {
			extentTest.get().log(Status.FAIL, "Cart Page is not opened");
		}
		Assert.assertEquals(cartPageUrl1, "https://jupiter.cloud.planittesting.com/#/cart");
				
		
		// Verify if product got added on cart page
		isProductAvailable = cartPage.isProductAdded(product);
		if(isProductAvailable) {
			extentTest.get().log(Status.PASS, product+" is added to cart");
		}else {
			extentTest.get().log(Status.FAIL, product+" is not added to cart");
		}
		softAssert.assertTrue(isProductAvailable);
		
		
		// Verify single price and total price on cart page
		double singleFluffyBunnyPrice_cartPage = cartPage.getSingleProductPrice(product);
		extentTest.get().log(Status.INFO, "Price of one "+product+" on cart page is: "+singleFluffyBunnyPrice_cartPage);
		if(singleFluffyBunnyPrice_shopPage == singleFluffyBunnyPrice_cartPage) {
			extentTest.get().log(Status.PASS, "Price of one "+product+" on shop page:"+singleFluffyBunnyPrice_shopPage+" and on cart page"+singleFluffyBunnyPrice_cartPage+" is same");
		}else {
			extentTest.get().log(Status.PASS, "Price of one "+product+" on shop page:"+singleFluffyBunnyPrice_shopPage+" and on cart page"+singleFluffyBunnyPrice_cartPage+" is not same");
		}
		softAssert.assertEquals(singleFluffyBunnyPrice_shopPage, singleFluffyBunnyPrice_cartPage);
		
		double totalPriceForFluffyBunny_cartPage = cartPage.getTotalProductAmount(product);
		extentTest.get().log(Status.INFO, "Price of "+productCount+" "+product+" on cart page is: "+totalPriceForFluffyBunny_cartPage);
		if(totalPriceForFluffyBunny_shopPage == totalPriceForFluffyBunny_cartPage) {
			extentTest.get().log(Status.PASS, "Total price of "+productCount+" "+product+" on shop page:"+totalPriceForFluffyBunny_shopPage+" and on cart page"+totalPriceForFluffyBunny_cartPage+" is same");
		}else {
			extentTest.get().log(Status.FAIL, "Total price of "+productCount+" "+product+" on shop page:"+totalPriceForFluffyBunny_shopPage+" and on cart page"+totalPriceForFluffyBunny_cartPage+" is not same");
		}
		softAssert.assertEquals(totalPriceForFluffyBunny_shopPage, totalPriceForFluffyBunny_cartPage);
		
		
		// Empty variables
		productCount = 0;
		product = null;
		isProductAvailable = false;
				
		// Click on shop tab
		parentPage.getInstanceOf(HomePage.class).clickOnShopTab();
		String shopPageUrl2 = parentPage.getCurrentURL();
		if(shopPageUrl2.contains("shop")) {
			extentTest.get().log(Status.PASS, "Shop Page is opened");
		}else {
			extentTest.get().log(Status.FAIL, "Shop Page is not opened");
		}
		Assert.assertEquals(shopPageUrl2, "https://jupiter.cloud.planittesting.com/#/shop");
		
		
		
		
	
		// Buy Valentine Bear
		productCount = 3;
		product = "Valentine Bear";
		double singleValentineBearPrice_shopPage = shopPage.getProductPriceOnShopPage(product);
		extentTest.get().log(Status.INFO, "Price of one "+product+" on shop page is: "+singleValentineBearPrice_shopPage);
		
		double totalPriceForValentineBear_shopPage = shopPage.buyProduct(product, productCount);
		if(totalPriceForValentineBear_shopPage == 0.0) {
			extentTest.get().log(Status.FAIL, product+" is not available on shop page");
		}else {
			extentTest.get().log(Status.PASS, "Price of "+productCount+" "+product+" based on buyProduct() methond is: "+totalPriceForValentineBear_shopPage);
		}
	
		
						
		// Navigate to cart tab
		parentPage.getInstanceOf(HomePage.class).clickOnCartTab();
		String cartPageUrl3 = parentPage.getCurrentURL();
		if(cartPageUrl3.contains("cart")) {
			extentTest.get().log(Status.PASS, "Cart Page is opened");
		}else {
			extentTest.get().log(Status.FAIL, "Cart Page is not opened");
		}
		Assert.assertEquals(cartPageUrl3, "https://jupiter.cloud.planittesting.com/#/cart");
						
		
		// Verify if product got added
		isProductAvailable = cartPage.isProductAdded(product);
		softAssert.assertTrue(isProductAvailable);
		if(isProductAvailable) {
			extentTest.get().log(Status.PASS, product+" is added to cart");
		}else {
			extentTest.get().log(Status.FAIL, product+" is not added to cart");
		}
		softAssert.assertTrue(isProductAvailable);
		
		
		// Verify single price and total price on cart page
		double singleValentineBearPrice_cartPage = cartPage.getSingleProductPrice(product);
		extentTest.get().log(Status.INFO, "Price of one "+product+" on cart page is: "+singleValentineBearPrice_cartPage);
		if(singleValentineBearPrice_shopPage == singleValentineBearPrice_cartPage) {
			extentTest.get().log(Status.PASS, "Price of one "+product+" on shop page:"+singleValentineBearPrice_shopPage+" and on cart page"+singleValentineBearPrice_cartPage+" is same");
		}else {
			extentTest.get().log(Status.PASS, "Price of one "+product+" on shop page:"+singleValentineBearPrice_shopPage+" and on cart page"+singleValentineBearPrice_cartPage+" is not same");
		}
		softAssert.assertEquals(singleValentineBearPrice_shopPage, singleValentineBearPrice_cartPage);
		
		double totalPriceForValentineBear_cartPage = cartPage.getTotalProductAmount(product);
		extentTest.get().log(Status.INFO, "Price of "+productCount+" "+product+" on cart page is: "+totalPriceForValentineBear_cartPage);
		if(totalPriceForValentineBear_shopPage == totalPriceForValentineBear_cartPage) {
			extentTest.get().log(Status.PASS, "Total price of "+productCount+" "+product+" on shop page:"+totalPriceForValentineBear_shopPage+" and on cart page"+totalPriceForValentineBear_cartPage+" is same");
		}else {
			extentTest.get().log(Status.FAIL, "Total price of "+productCount+" "+product+" on shop page:"+totalPriceForValentineBear_shopPage+" and on cart page"+totalPriceForValentineBear_cartPage+" is not same");
		}
		softAssert.assertEquals(totalPriceForValentineBear_shopPage, totalPriceForValentineBear_cartPage);
		
		
		
		// Calculate Total and verify from cart page
		double total_shopPage = totalPriceForStuffedFrog_shopPage + totalPriceForFluffyBunny_shopPage + totalPriceForValentineBear_shopPage;
		double totalOnCartPage = cartPage.getTotalFromCartPage();
		if(total_shopPage == totalOnCartPage) {
			extentTest.get().log(Status.PASS, "Total on shop page: "+total_shopPage+" is same as total on cart page "+totalOnCartPage);
		}else {
			extentTest.get().log(Status.PASS, "Total on shop page: "+total_shopPage+" is not same as total on cart page "+totalOnCartPage);
		}
		softAssert.assertEquals(total_shopPage, totalOnCartPage);
		
		
		softAssert.assertAll();
	}
}
