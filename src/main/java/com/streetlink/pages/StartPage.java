package com.streetlink.pages;
import java.time.Duration;

import com.streetlink.actiondriver.WebActionsHelper;

public class StartPage{
	WebActionsHelper webAction = new WebActionsHelper();
	
	/**
	 * This method is for opening public URL
	 */
	public void launchStartPage() {
		webAction.openURL();
	}
	
	public void clickOnHelpSomeone() {
		webAction.JSClickByXpath("//button[text()[contains(.,'Help someone')]]");
	}
	
	public void acceptCookies() {
		webAction.findElementByXpath("//button[text()='Accept cookies']").click();
	}
	
	public void clickOnMakeAnAlert() {
		webAction.JSClickByXpath("//button[text()='Make an alert']");		
	}
	
	public void clickOnNext() {  
		webAction.JSClickByXpath("//button[text()='Next']");
	}
	
	public void clickOnHelpYourself() {
		webAction.findElementByXpath("//button[text()[contains(.,'help for yourself')]]").click();
	}
	
	public String getPageTitle(){
		webAction.waitForElementByXpath(Duration.ofSeconds(10),"//h1");
		return webAction.findElementByTagName("h1").getText().trim();
	}

}
