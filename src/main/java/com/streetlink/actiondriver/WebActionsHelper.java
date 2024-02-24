package com.streetlink.actiondriver;



import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.streetlink.base.BaseClass;
import com.streetlink.utility.Log;
import com.streetlink.utility.ReadConfig;


/**
 * This class is for reusable methods
 * @author rituadhikari
 *
 */
public class WebActionsHelper  extends BaseClass{
	ReadConfig readConfig = new ReadConfig();
	// Initialize Log4j logs
		private static final Logger logger = LogManager.getLogger(Log.class);
		public static XSSFWorkbook workbook;
		public static FileInputStream inputStream;
		public static FileOutputStream outputStream;
		public static XSSFSheet sheet;
		public static Row row;
		public static Cell cell;
	public void scrollToElement(String Element) {
		WebElement ele = getDriver().findElement(By.xpath(Element));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}
	
	public void removeElementByJS(WebElement spanElement) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].remove()", spanElement);
	}

	/**
	 *  moves the mouse to the center of the specified element (ele) and then clicks on it
	 * @param ele
	 */
	public void mouseClick(WebElement ele) {
		Actions act = new Actions(getDriver());
		act.moveToElement(ele).click().build().perform();
	}

	/**
	 *  Use Actions class to send keys and simulate keyup
	 */
	public void handlingKeyEvent(WebElement ele,String value) {
		Actions actions = new Actions(getDriver());
		actions.sendKeys(ele, value).perform();
		actions.sendKeys(Keys.ARROW_DOWN).perform(); // This simulates a key-up event
	}
	
	public boolean findElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			// System.out.println("Location not found: "+locatorName);
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element at");

			} else {
				System.out.println("Unable to locate element at");
			}
		}
		return flag;
	}
	
	public boolean isDisplayed(WebDriver driver, WebElement ele) {
		boolean flag = false;
		//flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				System.out.println("The element is Displayed");
			} else {
				System.out.println("The element is not Displayed");
			}
		} else {
			System.out.println("Not displayed ");
		}
		return flag;
	}
	
	public void selectDropDownByIndexId(String id,int index){
		Select dropDown=new Select(findElementById(id));
		dropDown.selectByIndex(index);
	}
	
	public void selectDropDownByVisibleTextID(String id, String Text){
		Select dropDown=new Select(findElementById(id));
		dropDown.selectByVisibleText(Text);
	}

	public void selectDropDownByValueID(String id, String value){
		Select dropDown=new Select(findElementById(id));
		dropDown.selectByValue(value);
	}
	
	public void selectDropDownByclassName( String classname,int index){
		Select dropDown=new Select(findElementByClassName(classname));
		dropDown.selectByIndex(index);
	}
	
	/**
	 * This method is for locating element by Xpath and index of drop-down
	 * @param Xpath
	 * @param index
	 */
	public void selectDropDownByIndex(String Xpath,int index){
		Select dropDown=new Select(findElementByXpath(Xpath));
		dropDown.selectByIndex(index);
	}
	
	public void selectDropDownByValue(String xpath, String value){
		Select dropDown=new Select(findElementById(xpath));
		dropDown.selectByValue(value);
	}

	public void selectDropDownByVisibleText(String xpath, String Text){
		Select dropDown=new Select(findElementByXpath(xpath));
		dropDown.selectByVisibleText(Text);
	}
	
	/**
	 * Get text of Selected option from Drop-down
	 * @param xpath
	 * @return String
	 */
	public String getSelectedOption(String xpath){
		Select dropdownElement = new Select(findElementByXpath(xpath));
		// Get the selected option
        WebElement selectedOption = dropdownElement.getFirstSelectedOption();
        String getSelectedOption = selectedOption.getText();
        System.out.println("Selected Option: " + getSelectedOption);
		return getSelectedOption;
	}

	public boolean isSelected(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isSelected();
			if (flag) {
				System.out.println("The element is Selected");
			} else {
				System.out.println("The element is not Selected");
			}
		} else {
			System.out.println("Not selected ");
		}
		return flag;
	}
	
	public boolean isEnabled(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isEnabled();
			if (flag) {
				System.out.println("The element is Enabled");
			} else {
				System.out.println("The element is not Enabled");
			}
		} else {
			System.out.println("Not Enabled ");
		}
		return flag;
	}

	/**
	 * Type text at location
	 * 
	 * @param locatorName
	 * @param text
	 * @return - true/false
	 */
	
	public boolean type(WebElement ele, String text) {
		boolean flag = false;
		try {
			flag = ele.isDisplayed();
			ele.clear();
			ele.sendKeys(text);
			// logger.info("Entered text :"+text);
			flag = true;
		} catch (Exception e) {
			System.out.println("Location Not found");
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully entered value");
			} else {
				System.out.println("Unable to enter value");
			}

		}
		return flag;
	}

	
	public boolean selectBySendkeys(String value,WebElement ele) {
		boolean flag = false;
		try {
			ele.sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Select value from the DropDown");		
			} else {
				System.out.println("Not Selected value from the DropDown");
				// throw new ElementNotFoundException("", "", "")
			}
		}
	}
	
	public boolean mouseHoverByJavaScript(WebElement ele) {
		boolean flag = false;
		try {
			WebElement mo = ele;
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("MouseOver Action is performed");
			} else {
				System.out.println("MouseOver Action is not performed");
			}
		}
	}

	/**
	 * This method is for click on element by X-path using JS 
	 * @param Element
	 */
	public  void JSClickByXpath(String Element) {
		try {
			
			WebElement ele = getDriver().findElement(By.xpath(Element));
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].click();", ele);
		}
		catch (Exception e) {
			throw e;
		} 
	}
	
	public boolean switchToFrameByIndex(WebDriver driver,int index) {
		boolean flag = false;
		try {
			//new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with index \"" + index + "\" is selected");
			} else {
				System.out.println("Frame with index \"" + index + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue : Frame ID wish to switch
	 * 
	 */
	
	public boolean switchToFrameById(WebDriver driver,String idValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Id \"" + idValue + "\" is selected");
			} else {
				System.out.println("Frame with Id \"" + idValue + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue : Frame Name wish to switch
	 * 
	 */
	
	public boolean switchToFrameByName(WebDriver driver,String nameValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is selected");
			} else if (!flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
			}
		}
	}
	
	public boolean switchToDefaultFrame(WebDriver driver) {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				// SuccessReport("SelectFrame ","Frame with Name is selected");
			} else if (!flag) {
				// failureReport("SelectFrame ","The Frame is not selected");
			}
		}
	}
	
	public void mouseOverElement(WebDriver driver,WebElement element) {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				System.out.println(" MouserOver Action is performed on ");
			} else {
				System.out.println("MouseOver action is not performed on");
			}
		}
	}

	public boolean mouseover(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(ele).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			/*
			 * if (flag) {
			 * SuccessReport("MouseOver ","MouserOver Action is performed on \""+locatorName
			 * +"\""); } else {
			 * failureReport("MouseOver","MouseOver action is not performed on \""
			 * +locatorName+"\""); }
			 */
		}
	}
	
	public boolean draggable(WebDriver driver,WebElement source, int x, int y) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDropBy(source, x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {
		
			return false;
			
		} finally {
			if (flag) {
				System.out.println("Draggable Action is performed on \""+source+"\"");			
			} else if(!flag) {
				System.out.println("Draggable action is not performed on \""+source+"\"");
			}
		}
	}
	
	public boolean draganddrop(WebDriver driver,WebElement source, WebElement target) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDrop(source, target).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("DragAndDrop Action is performed");
			} else if(!flag) {
				System.out.println("DragAndDrop Action is not performed");
			}
		}
	}
	
	
	public boolean slider(WebDriver driver,WebElement ele, int x, int y) {
		boolean flag = false;
		try {
			// new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(driver).dragAndDropBy(ele, x, y).build().perform();// 150,0
			Thread.sleep(5000);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Slider Action is performed");
			} else {
				System.out.println("Slider Action is not performed");
			}
		}
	}
	
	
	public boolean rightclick(WebDriver driver,WebElement ele) {
		boolean flag = false;
		try {
			Actions clicker = new Actions(driver);
			clicker.contextClick(ele).perform();
			flag = true;
			return true;
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("RightClick Action is performed");
			} else {
				System.out.println("RightClick Action is not performed");
			}
		}
	}
	
	
	public boolean switchWindowByTitle(WebDriver driver,String windowTitle, int count) {
		boolean flag = false;
		try {
			Set<String> windowList = driver.getWindowHandles();

			String[] array = windowList.toArray(new String[0]);

			driver.switchTo().window(array[count-1]);

			if (driver.getTitle().contains(windowTitle)){
				flag = true;
			}else{
				flag = false;
			}
			return flag;
		} catch (Exception e) {
			//flag = true;
			return false;
		} finally {
			if (flag) {
				System.out.println("Navigated to the window with title");
			} else {
				System.out.println("The Window with title is not Selected");
			}
		}
	}
	
	public boolean switchToNewWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Window is Navigated with title");				
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}
	
	public boolean switchToOldWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[0].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Focus navigated to the window with title");			
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}
	
	public int getColumncount(WebElement row) {
		List<WebElement> columns = row.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;
	}
	
	
	public int getRowCount(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}
	
	
	/**
	 * Verify alert present or not
	 * 
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 * 
	 */
	
	public boolean Alert(WebDriver driver) {
		boolean presentFlag = false;
		Alert alert = null;

		try {
			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (!presentFlag) {
				System.out.println("The Alert is handled successfully");		
			} else{
				System.out.println("There was no alert to handle");
			}
		}

		return presentFlag;
	}
	
	public boolean launchUrl(WebDriver driver,String url) {
		boolean flag = false;
		try {
			driver.navigate().to(url);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Successfully launched \""+url+"\"");				
			} else {
				System.out.println("Failed to launch \""+url+"\"");
			}
		}
	}
	
	
	public boolean isAlertPresent(WebDriver driver) 
	{ 
		try 
		{ 
			driver.switchTo().alert(); 
			return true; 
		}   // try 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		}   // catch 
	}
	
	
	public String getTitle(String xpath){
		waitForElementByXpath(Duration.ofSeconds(5), xpath);
		String text = getDriver().getTitle();
		return text;
	}
	
	
	public String getCurrentURL()  {
		boolean flag = false;

		String text = getDriver().getCurrentUrl();
		if (flag) {
			System.out.println("Current URL is: \""+text+"\"");
		}
		return text;
	}
	
	
	public boolean click1(WebElement locator, String locatorName) {
		boolean flag = false;
		try {
			locator.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Able to click on \""+locatorName+"\"");
			} else {
				System.out.println("Click Unable to click on \""+locatorName+"\"");
			}
		}
	}
	
	/**
	 * This method is to wait until the title is present and matches the expected title
	 * @param timeoutInSeconds
	 * @param expectedTitle
	 */
	public void waitForTitle(Duration timeoutInSeconds , String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
		wait.until(ExpectedConditions.titleIs(expectedTitle));
	}
	
	public void waitForElementByXpath(Duration timeoutInSeconds, String xpath) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(findElementByXpath(xpath)));
	}
	/**
	 * 
	 * @param driver
	 * @param element
	 * @param timeOut
	 */
	public void fluentWait(WebDriver driver,WebElement element, int timeOut) {
	    Wait<WebDriver> wait = null;
	    try {
	        wait = new FluentWait<WebDriver>((WebDriver) driver)
	        		.withTimeout(Duration.ofSeconds(20))
	        	    .pollingEvery(Duration.ofSeconds(2))
	        	    .ignoring(Exception.class);
	        wait.until(ExpectedConditions.visibilityOf(element));
	        element.click();
	    }catch(Exception e) {
	    }
	}
	
	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		//File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

		try {
		//	FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		// This new path for jenkins
		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
				+ dateName + ".png";
		return newImageString;
	}
	
	public String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}
	

		public void waitForElement(By locator, Duration timeout, String element) {
			try {
				 WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
				wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			 logger.error("Error while waiting for element: {}", e.getMessage());
		        e.printStackTrace();
				}	
	}
		
	public void openURL() {
		getDriver().get(readConfig.getPublicURL());	
	}
	
	public By locateById(String element) {
		return By.id(element);
	}

	public By locateByClassName(String element) {
		return By.className(element);
	}

	public By locateByCssSelector(String element) {
		return By.cssSelector(element);
	}

	public By locateByLinkText(String element) {
		return By.linkText(element);
	}

	public By locateByName(String element) {
		return By.name(element);
	}

	public By locateByPartialLinkText(String element) {
		return By.partialLinkText(element);
	}

	public By locateByXpath(String element) {
		return By.xpath(element);
	}

	public By locateByTagName(String element) {
		return By.tagName(element);
	}

	public WebElement findElementById(String element){
		waitForElement(locateById(element), Duration.ofSeconds(20), element);
		return getDriver().findElement(By.id(element));
	}
	
	public WebElement findElementByXpath(String element){
		waitForElement(locateByXpath(element), Duration.ofSeconds(20), element);
		return getDriver().findElement(By.xpath(element));
	}

	public WebElement findElementByClassName(String element) {
		waitForElement(locateByClassName(element), Duration.ofSeconds(20), element);
		return getDriver().findElement(By.className(element));
	}
	
	public WebElement findElementByName(String element) {
		waitForElement(locateByName(element), Duration.ofSeconds(20), element);
		return getDriver().findElement(By.name(element));
	}

	public WebElement findElementByTagName(String element) {
		waitForElement(locateByTagName(element), Duration.ofSeconds(20), element);
		return getDriver().findElement(By.tagName(element));
	}
	
	public List<WebElement> findElementsById(String element) {
		waitForElement(locateById(element), Duration.ofSeconds(20), element);
		return getDriver().findElements(By.id(element));
	}

	public List<WebElement> findElementsByTagName(String element){
		waitForElement(locateByTagName(element), Duration.ofSeconds(10), element);
		return getDriver().findElements(By.tagName(element));
	}

	public List<WebElement> findElementsByXpath(String element) {
		waitForElement(locateByXpath(element), Duration.ofSeconds(10), element);
		return getDriver().findElements(By.xpath(element));
	}
	
	public WebElement findElementByCss(String element) {
		waitForElement(locateByCssSelector(element), Duration.ofSeconds(10), element);
		return getDriver().findElement(By.cssSelector(element));
	}

	public List<WebElement> findElementsByCss(String element) {
		waitForElement(locateByCssSelector(element), Duration.ofSeconds(10), element);
		return getDriver().findElements(By.cssSelector(element));
	}

	public WebElement findElementByLinkText(String element) {
		waitForElement(locateByLinkText(element), Duration.ofSeconds(10), element);
		return getDriver().findElement(By.linkText(element));
	}
	
	public List<WebElement> findElementsByLinkText(String element) {
		waitForElement(locateByLinkText(element), Duration.ofSeconds(10), element);
		return getDriver().findElements(By.linkText(element));
	}

	public WebElement findElementByPartialLinkText(String element) {
		waitForElement(locateByPartialLinkText(element), Duration.ofSeconds(10), element);
		return getDriver().findElement(By.partialLinkText(element));
	}

	public List<WebElement> findElementsByClassName(String element) {
		waitForElement(locateByClassName(element), Duration.ofSeconds(10), element);
		return getDriver().findElements(By.className(element));
	}
		
	public int getRandomNum(int startRange, int endRange) {
		Random random = new Random();
        return random.nextInt(endRange - startRange + 1) + startRange;
	}	
	
	/**
	 * This method is for retrieving text of only desired element and ignore other
	 * @param mainXpath
	 * @param childXpath
	 * @return String
	 * @author ritua
	 */
	public String getTextOfOnlyParentElement(String mainXpath, String childXpath) {
		WebElement mainElement = findElementByXpath(mainXpath);
		List<WebElement> followingElements = mainElement.findElements(By.xpath(childXpath));
		for (WebElement followElement : followingElements) {
			removeElementByJS(followElement);
		}
		// Retrieve text content of the modified <p> element
		String textConetentToBe = mainElement.getText().trim();
		System.out.println("Text content needs: " + textConetentToBe);
		return textConetentToBe;
	}
	
	public void chooseRadioButtonByIndex(String xpath) {
		WebElement mainElement = findElementByXpath(xpath);
		// Locate all radio buttons within the div
		List<WebElement> radioButtons = mainElement.findElements(By.tagName("input"));
		// Generate a random index to select a random radio button
		int randomIndex = new Random().nextInt(radioButtons.size());
		// Click on the randomly selected radio button
		radioButtons.get(randomIndex).click();
	}
	
	/**
	 * This method is for reading data from excel w.r.t LA param
	 * @param excelFilePath
	 * @param LocalAuth
	 * @throws IOException
	 * @author ritua
	 */
	/**public String getDataFrmExcel(String excelFilePath, String LocalAuth) throws IOException {
		String cellValue = "";
		List<String> postcodes = new ArrayList<>();
		boolean dataFound = false; // Flag variable to indicate whether data is found
		try {
			File xlfile = new File(excelFilePath);
			FileInputStream inputStream = new FileInputStream(xlfile);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheet("sheet1");
			outerloop: // Label for the outer loop
			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
					Cell cell = row.getCell(j);
					if (cell != null && cell.getStringCellValue().trim().equals(LocalAuth)) {
						Cell postcodeCell = row.getCell(j + 1);
						if (postcodeCell != null) {
							String postcode = postcodeCell.getStringCellValue().trim();
							String[] postcodeParts = postcode.split(",");
							List<String> container = Arrays.asList(postcodeParts);

							// Randomly choose a postcode from the container
							if (!container.isEmpty()) {
								Random rand = new Random();
								String randomPostcode = container.get(rand.nextInt(container.size()));
								return randomPostcode;
							}
						}
					}
				}
			}
			workbook.close();
			// Check if data was found, if not, handle accordingly
			if (!dataFound) {
				System.out.println("Data not found.");
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw e; // Rethrow the exception to indicate failure
		}
		return null;
	}	**/
	
	/**
	 * This method is for randomly pick LA from excel and choose Postcoode
	 * @param excelFilePath
	 * @return randomPostcode
	 * @throws IOException
	 */
	  public String getDataFrmExcel(String excelFilePath) throws IOException {
	        try {
	            File xlfile = new File(excelFilePath);
	            FileInputStream inputStream = new FileInputStream(xlfile);
	            Workbook workbook = new XSSFWorkbook(inputStream);
	            Sheet sheet = workbook.getSheet("sheet1");

	            List<String> postcodes = new ArrayList<>();
	            boolean dataFound = false; // Flag variable to indicate whether data is found
	            
	            Random rand = new Random();
	            int randomRowIndex = rand.nextInt(sheet.getPhysicalNumberOfRows()); // Randomly select a row
	            System.out.println(randomRowIndex);
	            Row row = sheet.getRow(randomRowIndex);
	            Cell localAuthCell = row.getCell(0); // Assuming Local Authority is in the first cell of the row
	            System.out.println(localAuthCell);
	            if (localAuthCell != null) {
	                String localAuth = localAuthCell.getStringCellValue().trim();
	                Cell postcodeCell = row.getCell(1); // Assuming postcodes are in the second cell of the row
	                System.out.println(localAuth);
	                if (postcodeCell != null) {
	                    String postcode = postcodeCell.getStringCellValue().trim();
	                    String[] postcodeParts = postcode.split(",");
	                    postcodes = Arrays.asList(postcodeParts);

	                    if (!postcodes.isEmpty()) {
	                        String randomPostcode = postcodes.get(rand.nextInt(postcodes.size())); // Randomly choose a postcode
	                        return randomPostcode;
	                    }
	                }
	            }
	            workbook.close();
	            // Check if data was found, if not, handle accordingly
	            if (!dataFound) {
	                System.out.println("Data not found.");
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	            throw e; // Rethrow the exception to indicate failure
	        }
	        return null;
	    }
}
