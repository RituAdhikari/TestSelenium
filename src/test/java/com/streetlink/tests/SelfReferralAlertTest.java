package com.streetlink.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.streetlink.base.BaseClass;
import com.streetlink.pages.LocationPage;
import com.streetlink.pages.RoughSleeperdetailPage;
import com.streetlink.pages.StartPage;
import com.streetlink.pages.TimePage;

public class SelfReferralAlertTest extends BaseClass{
	StartPage startPage = new StartPage();
	LocationPage location = new LocationPage();
	TimePage time =  new TimePage();
	RoughSleeperdetailPage detailsRS = new RoughSleeperdetailPage();
	
	@Test(priority = 0)
	public void verifyStartMOPAlert(){
		startPage.launchStartPage();
		startPage.acceptCookies();
		startPage.clickOnHelpYourself();
		startPage.clickOnMakeAnAlert();
		//Assert	
	}
	
/**	@throws IOException 
 * @Test(priority = 1)
	public void verifythepostcode() throws InterruptedException {
		location.checkPostCode();
		Thread.sleep(5000);
		startPage.clickOnNext();
		Assert.assertEquals(location.getpostCodeWarningMsg(), "Sorry, that’s not a full postcode. Please enter a postcode with at least 5 characters.");	
	}  **/
	
	@Test(priority = 2)
	public void verifythelocation() throws InterruptedException, IOException {
		location.chooseAddress();
		Thread.sleep(5000);
		startPage.clickOnNext();
		//Assert.assertEquals(location.getLocationPageTitle(), "Describe the location and access - StreetLink");
		
	}
	
	/**
	 * This test is for verifying special chars which are not allowed
	 */
	@Test(priority = 3)
	public void verifyNotAllowedSpecialCharOnLocationDes(){
		location.fillDescribeLocation("Testing Special Chars not allowed < > & / %");
		startPage.clickOnNext();
		Assert.assertEquals(location.getWarningMsg(), "Sorry, our system doesn’t accept these special characters: < > & / %");	
	}
	
	/**
	 * This test is for verifying warning for min char range
	 */
	@Test(priority = 4)
	public void verifyMinAndMaxCharOnLocationDes(){
		location.fillDescribeLocation("Testing Min Char range");
		startPage.clickOnNext();
		Assert.assertEquals(location.getWarningMsg(), "The description looks a little short. Outreach workers have a better chance of finding you if you give more detail.");	
	}
	
	/**
	 * This test is for verifying  for allowed char range
	 */
	@Test(priority = 5)
	public void verifyAllowedSpecCharOnLocationDes(){
		location.fillDescribeLocation("Auto Testing Location Description with + = @ * : ; ? (-)");
		startPage.clickOnNext();
		//Assert.assertEquals(location.getWaringMsg(), "The description looks a little short. Outreach workers have a better chance of finding you if you give more detail.");	
	}
	
	/**
	 * This test is for verifying warning for min char range
	 */
	@Test(priority = 5)
	public void verifyBySelctingOnlyHours(){
		time.selectHours();
		time.selectToHours();
		startPage.clickOnNext();
		Assert.assertEquals(time.getWarningMsg(), "Select the hours and minutes to show the time you were at the location. These fields must be completed.");
	}
	
	@Test(priority = 6)
	public void verifyByFillingTime(){
		time.selectMins();
		time.selectToMint();
		startPage.clickOnNext();
		startPage.clickOnNext();
		//Assert.assertEquals(startPage.getPageTitle(), "Tell us about the person sleeping rough");
	}
	
	@Test(priority = 7)
	public void verifyContactDetails(){
		detailsRS.fillFirstName("AutoTestSR");
		detailsRS.fillLastName("Testing");
		detailsRS.chooseGenderSR();
		detailsRS.chooseAgeSR();
		detailsRS.fillappearance("Auto person testing appearance with + = @ * : ; ? (-)");
		detailsRS.checkIamHappy();
		detailsRS.clickOnSubmitBtn();
		Assert.assertEquals(detailsRS.getFirstWarning(),"Please add your contact number");
	}
	
	@Test(priority = 8)
	public void verifyWithIncorrectEmail(){
		detailsRS.addConatctNum();
		detailsRS.addEmail("testIncorrectEmail");
		detailsRS.clickOnSubmitBtn();
		Assert.assertEquals(detailsRS.getFirstWarning(),"Enter an email address in the correct format, such as example@example.com");
	}
	
	@Test(priority = 9)
	public void verifyUncheckedConatctMe(){
		detailsRS.enterEmail();
		detailsRS.checkIamHappy();
		detailsRS.clickOnSubmitBtn();
		Assert.assertEquals(detailsRS.getWarningOnUncheckContactMe(),"Check the box to give your consent for StreetLink or Outreach teams to contact you.");
	}
	
	@Test(priority = 10)
	public void verifyAbleToSubmitSRAlert(){
		detailsRS.checkIamHappy();
		detailsRS.clickOnSubmitBtn();
		Assert.assertEquals(detailsRS.IsLocalCouncilBtnVisible(),true);
	}	
}
