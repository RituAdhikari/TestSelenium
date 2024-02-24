package com.streetlink.pages;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.streetlink.actiondriver.WebActionsHelper;
import com.streetlink.base.BaseClass;

public class LocationPage extends BaseClass{
	WebActionsHelper webAction = new WebActionsHelper();
	String excelSheetPath = ".\\src\\main\\resources\\LA_Postcodes.xlsx";
   
	/**
     * This method is for selecting address from List
     * @throws InterruptedException
     * @author ritua
     */		
	public void chooseAddress() throws InterruptedException {	
		String getPostCode = fillPostcodeLAwise();
		WebElement autoOptions = webAction.findElementById("typeAddressOrPlace");
		autoOptions.clear();
		webAction.handlingKeyEvent(autoOptions,getPostCode);
		List<WebElement> getAllChildDivs = webAction.findElementsByXpath("//div[contains(@class,'pac-item')]");	
		int getRanNum = webAction.getRandomNum(1, getAllChildDivs.size() - 1);
		System.out.println(getRanNum);
		if (getAllChildDivs.size() > 0) {
			for (int i = 0; i < getAllChildDivs.size(); i++) {
				System.out.println("All Address^^^^^ " + getAllChildDivs.get(i).getText());
				if (i == getRanNum) {
					System.out.println("selected Address!!!!!!!! " + getAllChildDivs.get(i).getText());
					try {
						// Scroll into view before clicking
	                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getAllChildDivs.get(i));  
	                    // Wait for the element to be clickable
	                    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
	                    wait.until(ExpectedConditions.elementToBeClickable(getAllChildDivs.get(i)));
	                    // Click the element
	                    webAction.mouseClick(getAllChildDivs.get(i));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    // Handle the exception or log it as needed
	                }
				//	webAction.mouseClick(getAllChildDivs.get(i));
					break;
				}
			}
		}
	}
	
	/**
	 * This method is for testing location with LA wise
	 */
	public String fillPostcodeLAwise(){
		try {
			return webAction.getDataFrmExcel(excelSheetPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * This method is for testing less that 5 char poscodes
	 * @throws InterruptedException
	 */
	public void checkPostCode() throws InterruptedException{
	//	chooseAddress("BT1");
	} 
	
	public String getpostCodeWarningMsg() {
		return webAction.findElementByXpath("//span[@class='src__ErrorText-sc-kkeaok-0 dWhZjv']").getText().trim();
	}

	public void fillDescribeLocation(String text){
		webAction.scrollToElement("//div[@class='src__GridRow-sc-1jwfkx4-0 kYQZAe errorSpacing']");
		WebElement locationTextBox = webAction.findElementByXpath("//textarea[@name='locationDetail']");
		locationTextBox.clear();
		locationTextBox.sendKeys(text);
	}

	public String getWarningMsg() {
		return webAction.getTextOfOnlyParentElement("//p[@id='more-detail-error' and @class='govuk-error-message']",".//span[@class='govuk-visually-hidden']");
	}
 }
