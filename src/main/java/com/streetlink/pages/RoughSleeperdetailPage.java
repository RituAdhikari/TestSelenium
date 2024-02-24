package com.streetlink.pages;

import java.util.Random;

import com.streetlink.actiondriver.WebActionsHelper;

public class RoughSleeperdetailPage{
	WebActionsHelper webAction = new WebActionsHelper();
	
	public void chooseGender(){
		webAction.scrollToElement("//div[@class='src__GridCol-sc-gufadr-0 SXmev mb-30']");
		webAction.chooseRadioButtonByIndex("//div[@class='src__GridCol-sc-gufadr-0 SXmev mb-30']");	
	}
	
	public void chooseAge(){
		webAction.chooseRadioButtonByIndex("(//div[@class='src__GridCol-sc-gufadr-0 SXmev mb-30'])[2]");	
	}
	
	public void fillappearance(String value) {
		webAction.findElementByXpath("//textarea[@name='personLookLike']").clear();
		webAction.findElementByXpath("//textarea[@name='personLookLike']").sendKeys(value);
	}
	
	public void fillOutreachWorker(String value) {
		webAction.findElementByXpath("//textarea[@name='awareOf']").clear();
		webAction.findElementByXpath("//textarea[@name='awareOf']").sendKeys(value);
	}
	
	public String warningText(){
		return webAction.getTextOfOnlyParentElement("//p[@id='more-detail-error' and @class='govuk-error-message']",".//span[@class='govuk-visually-hidden']");		
	}
	
	public String getAgeWar() {
		return webAction.findElementByXpath("//span[@class='src__ErrorText-sc-kkeaok-0 dWhZjv']").getText().trim();
	}
	
	public void fillFirstName(String firstName){
		webAction.findElementByXpath("//input[@name='firstName']").sendKeys(firstName);
	}
	
	public void fillLastName(String lastName){
		webAction.findElementByXpath("//input[@name='lastName']").sendKeys(lastName);
	}
	
	public void chooseGenderSR(){
		webAction.scrollToElement("//legend[contains(text(), 'Gender')]/ancestor::div[@class='src__GridCol-sc-gufadr-0 SXmev']");
		webAction.chooseRadioButtonByIndex("//legend[contains(text(), 'Gender')]/ancestor::div[@class='src__GridCol-sc-gufadr-0 SXmev']");	
	}
	
	public void chooseAgeSR(){
		webAction.scrollToElement("//legend[contains(text(), 'Age estimate')]/ancestor::div[@class='src__GridCol-sc-gufadr-0 SXmev']");
		webAction.chooseRadioButtonByIndex("//legend[contains(text(), 'Age estimate')]/ancestor::div[@class='src__GridCol-sc-gufadr-0 SXmev']");	
	}
	
	public void clickOnSubmitBtn() {
		webAction.scrollToElement("//div[@class='src__GridRow-sc-1jwfkx4-0 kYQZAe mb-30']");
		webAction.JSClickByXpath("//button[text()='Submit']");
	}
	
	public void checkIamHappy(){
		webAction.JSClickByXpath("//input[@class='src__StyledInput-sc-1hkoucn-1 khSIWV']");
	}
	
	public String getFirstWarning(){
		return webAction.getTextOfOnlyParentElement("//p[@class='govuk-error-message']",".//span[@class='govuk-visually-hidden']");		
	}
	
	public String getWarningOnUncheckContactMe() {
		return webAction.findElementByXpath("//span[@class='src__ErrorText-sc-kkeaok-0 dWhZjv']").getText().trim();
	}
	
	public String generateRandomUKPhoneNumber() {
		// Random generator for different parts of the phone number
        Random random = new Random();
        // Generate the area code
        StringBuilder areaCode = new StringBuilder("0");
        areaCode.append(random.nextInt(8) + 2);  // 2, 3, 4, 5, 6, 7
        // Generate the remaining digits of the phone number
        StringBuilder localNumber = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            localNumber.append(random.nextInt(10));
        }
        System.out.println("phone num: "+localNumber.toString());
        // Combine the parts to form the complete phone number
        return areaCode.toString() + localNumber.toString();
    }
	
	public void addConatctNum() {
		String contactNum = generateRandomUKPhoneNumber();
		webAction.findElementById("phone").sendKeys(contactNum);
	}
	
	public void addEmail(String email ){	
		webAction.findElementById("email").clear();
		webAction.findElementById("email").sendKeys(email);
	}
	
	/**
	 * This method is for generating unique email Id 
	 */
	public void enterEmail(){
		String ranNum = String.valueOf(webAction.getRandomNum(0 ,1000));
		addEmail("autoTest" + ranNum + "@mailinator.com");
	}
	
	public boolean IsLocalCouncilBtnVisible(){
		return webAction.findElementByXpath("//button[@class='src__StyledButton-sc-19ocyxv-0 dmnuEl btn-blue']").isDisplayed();
	}
}
