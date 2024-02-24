package com.streetlink.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.streetlink.actiondriver.WebActionsHelper;

public class TimePage {
	WebActionsHelper webAction = new WebActionsHelper();
	
	public void selectHours(){
		int randomIndex = webAction.getRandomNum(1,24);
		System.out.println("selected Hours:"+ randomIndex);
		webAction.selectDropDownByIndex("//select[@name='hour']", randomIndex);
	}
	
	public void selectMins(){
		int randomIndex = webAction.getRandomNum(1,4);
		System.out.println("selected Minutes:"+ randomIndex);
		webAction.selectDropDownByIndex("//select[@name='minute']", randomIndex);
	}
	
	public String getWarningMsg(){
		return webAction.findElementByXpath("//span[@class='src__ErrorText-sc-kkeaok-0 dWhZjv']").getText().trim();
	}
	
	public void removeValueFrmHours(){
		webAction.selectDropDownByIndex("//select[@name='hour']", 0);
	}
	
	public void removeValueFrmMinutes(){
		webAction.selectDropDownByIndex("//select[@name='minute']", 0);
	}
	
	//List<String> nightTimeList = Arrays.asList("21","22","23","00","01","02","03","04","05","06","07","08");
	public void checkAndGetHrText(){
		List<String> dayTimeList = Arrays.asList("09","10","11","12","13","14","15","16","17","18","19","20");
		String getSelectedTime = webAction.getTextOfOnlyParentElement("//div[@class='displayTime']//p", "//div[@class='displayTime']//a");
		String[] getHrText = getSelectedTime.split(":");
		String gethour = getHrText[0];
		System.out.println("Hours: " + gethour);
		if(dayTimeList.contains(gethour)){	
			webAction.JSClickByXpath("//button[text()='I’m sure - continue']");
		}
		else {
			webAction.JSClickByXpath("//button[text()='Next']");
		}
	}
	
	public void selectToHours(){
		int randomIndex = webAction.getRandomNum(1,24);
		System.out.println("selected Hours:"+ randomIndex);
		webAction.selectDropDownByIndex("//select[@name='toHour']", randomIndex);
	}
	
	public void selectToMint(){
		int randomIndex = webAction.getRandomNum(1,4);
		System.out.println("selected Minutes:"+ randomIndex);
		webAction.selectDropDownByIndex("//select[@name='toMinute']", randomIndex);
	}
	
	public void removeValueToHours(){
		webAction.selectDropDownByIndex("//select[@name='tohour']", 0);
	}
	
	public void removeValueToMinutes(){
		webAction.selectDropDownByIndex("//select[@name='tominute']", 0);
	}
 }
