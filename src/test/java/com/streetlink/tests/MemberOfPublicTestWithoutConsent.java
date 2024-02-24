package com.streetlink.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.streetlink.base.BaseClass;
import com.streetlink.pages.LocationPage;
import com.streetlink.pages.RefferdetailPage;
import com.streetlink.pages.RoughSleeperdetailPage;
import com.streetlink.pages.StartPage;
import com.streetlink.pages.TimePage;

public class MemberOfPublicTestWithoutConsent extends BaseClass{
	StartPage startPage = new StartPage();
	LocationPage location = new LocationPage();
	TimePage time =  new TimePage();
	RoughSleeperdetailPage detailsRS = new RoughSleeperdetailPage();
	RefferdetailPage reffDetailPage = new RefferdetailPage();
	
	@Test(priority = 0)
	public void verifyStartMOPAlert(){
		startPage.launchStartPage();
		startPage.clickOnHelpSomeone();
		startPage.acceptCookies();
		startPage.clickOnMakeAnAlert();	
		//Assert.assertEquals(location.getLocationPageTitle(), "Where is the person sleeping rough? - StreetLink");
	}
	
	@Test(priority = 1)
	public void verifythepostcode() throws InterruptedException {
		location.checkPostCode();
		Thread.sleep(5000);
		startPage.clickOnNext();
		Assert.assertEquals(location.getpostCodeWarningMsg(), "Sorry, that’s not a full postcode. Please enter a postcode with at least 5 characters.");	
	}
	
	@Test(priority = 2)
	public void verifythelocation() throws InterruptedException {
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
	 * This test is for verifying warning for min char range
	 */
	@Test(priority = 5)
	public void verifyAllowedSpecCharOnLocationDes(){
		location.fillDescribeLocation("Auto Testing Location Description with + = @ * : ; ? (-)");
		startPage.clickOnNext();
		//Assert.assertEquals(location.getWaringMsg(), "The description looks a little short. Outreach workers have a better chance of finding you if you give more detail.");	
	}
	
	@Test(priority = 6)
	public void verifyBySelctingOnlyHours(){
		time.selectHours();
		startPage.clickOnNext();
		Assert.assertEquals(time.getWarningMsg(), "Select the hours and minutes to show the time you saw the person sleeping rough. These fields must be completed.");
	}
	
	@Test(priority = 7)
	public void verifyBySelctingOnlyMinutes(){
		time.removeValueFrmHours();
		time.selectMins();
		startPage.clickOnNext();
		Assert.assertEquals(time.getWarningMsg(), "Select the hours and minutes to show the time you saw the person sleeping rough. These fields must be completed.");
	}
	
	@Test(priority = 8)
	public void verifyWarningOnBlankValesOnTime(){
		time.removeValueFrmMinutes();
		startPage.clickOnNext();
		Assert.assertEquals(time.getWarningMsg(), "Select the hours and minutes to show the time you saw the person sleeping rough. These fields must be completed.");
	}
	
	@Test(priority = 9)
	public void verifyByFillingTime(){
		time.selectHours();
		time.selectMins();
		startPage.clickOnNext();
		time.checkAndGetHrText();
		//Assert
	}
	
	/**
	 * This test is for checking mandatory fields
	 */
	@Test(priority = 10)
	public void verifyMandatoryFields(){
		detailsRS.chooseGender();
		detailsRS.chooseAge();
		startPage.clickOnNext();
		//Assert
	}
	
	/**
	 * This test case is for verifying special char 
	 *  which are not allowed on text boxes of RoughSleeperdetail page
	 */
	@Test(priority = 11)
	public void verifyNotAllowedSplChar(){
		detailsRS.fillappearance("Testing Special Chars not allowed < > & / %");
		detailsRS.fillOutreachWorker("Testing Special Chars not allowed < > & / %");
		startPage.clickOnNext();
		Assert.assertEquals( detailsRS.warningText(), "Sorry, our system doesn’t accept these special characters: < > & / %");	
	}
	
	@Test(priority = 12)
	public void verifyRoughSleeperDeatils(){
		detailsRS.fillappearance("Auto Testing person look like with + = @ * : ; ? (-)");
		detailsRS.fillOutreachWorker("Auto Testing outreach worker should be aware of with + = @ * : ; ? (-)");
		startPage.clickOnNext();
		//Assert
	}
	
	@Test(priority=13)
	public void verifyAbletoSubmitMOPAlert(){
		reffDetailPage.clickOnWithoutEmailAndConsentBtn();
		//Assert.assertEquals(reffDetailPage.checkThankYouPage(), "Thank you");
	}
 }
