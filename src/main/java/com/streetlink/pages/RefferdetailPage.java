package com.streetlink.pages;

import com.streetlink.actiondriver.WebActionsHelper;

public class RefferdetailPage{
	WebActionsHelper webAction = new WebActionsHelper();
	
	public void clickOnSubmitBtn() {
		webAction.scrollToElement("//div[@class='src__GridRow-sc-1jwfkx4-0 kYQZAe mb-30']");
		webAction.JSClickByXpath("//button[text()='Submit']");
	}
	
	public void clickOnWithoutEmailAndConsentBtn() {
		webAction.JSClickByXpath("//button[text()='Submit without email and consent']");
	}
	
	public void fillFirstName(String firstName) {
		webAction.findElementById("firstName").sendKeys(firstName);
	}
	
	public void fillLastName(String lastName) {
		webAction.findElementById("lastName").sendKeys(lastName);
	}
	
	public void fillEmail(String email) {
		webAction.findElementById("email").clear();
		webAction.findElementById("email").sendKeys(email);
	}
	
	public String warningText(){
		return webAction.getTextOfOnlyParentElement("//p[@class='govuk-error-message']",".//span[@class='govuk-visually-hidden']");		
	}
	
	public void clickOnAddConsentBtn(){
		webAction.JSClickByXpath("//div[@class='modal-body']//button[@class='src__StyledButton-sc-19ocyxv-0 dmnuEl btn-blue']");
	}
	
	public void checkOnConsent(){
		webAction.JSClickByXpath("//input[@class='src__StyledInput-sc-1hkoucn-1 khSIWV']");
		webAction.JSClickByXpath("(//input[@class='src__StyledInput-sc-1hkoucn-1 khSIWV'])[2]");
		webAction.JSClickByXpath("(//input[@class='src__StyledInput-sc-1hkoucn-1 khSIWV'])[3]");
		webAction.JSClickByXpath("(//input[@class='src__StyledInput-sc-1hkoucn-1 khSIWV'])[4]");
	}
	
	public String checkThankYouPage() {
		return webAction.findElementByTagName("h1").getText().trim();
	}
	
	/**
	 * This method is for generating unique email Id 
	 */
	public void enterEmail(){
		String ranNum = String.valueOf(webAction.getRandomNum(0 ,1000));
		fillEmail("autoTest" + ranNum + "@mailinator.com");
	}
	
	


}
