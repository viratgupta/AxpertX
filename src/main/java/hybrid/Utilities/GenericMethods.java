/**
 * 
 */
package hybrid.Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class GenericMethods {
	
	public static Properties Repository= new Properties();
	public static WebDriver driver;
	public static ExtentReports extent;
	public  ExtentTest test;
	public ITestResult result;
	
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir")+"/src/test/resources/Reports/ExtentReport "+formater.format(calendar.getTime()) +".html",false);
	}
	
	public static WebDriver selectBrowser(String browser){
		if (browser.equalsIgnoreCase("firefox"))
		{
			System.out.println("Browser is FIREFOX");
			/*String GeckoDriverPath= System.getProperty("user.dir")+"/src/main/resources/BrowsersDrivers/geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", GeckoDriverPath);*/
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			return driver;
		} 
		else if (browser.equalsIgnoreCase("chrome")) 
		{
			System.out.println("Browser is CHROME");
			String ChromeDriverPath= System.getProperty("user.dir")+"/src/main/resources/BrowserDrivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver",ChromeDriverPath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			return driver;
		} 
		else if (browser.equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			return driver;
		}
		return null;
	}
	
	
	public void selectDropDownValue(WebElement element, String dropDownValue){
		implicitWait(5);
		Select select = new Select(element);
		Reporter.log("selecting "+dropDownValue+" value from dropdown");
		select.selectByValue(dropDownValue);
	}
	
	public void selectDropDownValueUsingIndex(WebElement element1, int index){
		Select select1 = new Select(element1);
		Reporter.log("selecting "+index+" value from dropdown");
		select1.selectByIndex(index);
	}
	
	public void selectByText(WebElement ddelement, String dropdownText)
	{
		implicitWait(10);
		Select dropdown= new Select(ddelement);
		dropdown.selectByVisibleText(dropdownText);
	}
	
	public void selectListofRecords(WebElement dropdownElement)
	{
		 Select dropdown = new Select(dropdownElement);
		    //Get all options
		    List<WebElement> dd = dropdown.getOptions();

		    //Get the length
		     //System.out.println(dd.size());
		     int AllValues= dd.size()-1;
		     String tp= String.valueOf(AllValues);
		     Reporter.log("Total Records"+tp);
		     
		    if(AllValues>0)
		    {
		    	//print the lidt of records.
		    	for (int j = 1; j < dd.size(); j++) {
			        Reporter.log(dd.get(j).getText());
			    }
		    }
		    else
		    {
		    	Reporter.log("No Record Found");
		    	Assert.fail();
		    }
		    
	}
	
	
	public void SidebarMenu(WebElement Fst_element, WebElement child_element) throws Exception
	{
		implicitWait(15);
		Actions action = new Actions(driver);
		action.moveToElement(Fst_element).moveToElement(child_element).click().perform();
		}
	
	public void SwitchFrame(WebElement frameName) throws InterruptedException
	{
		
		driver.switchTo().frame(frameName);
		Thread.sleep(2000);
	}
	
	public void implicitWait(int timeInsec){
		//Reporter.log("waiting for page to load...");
		try{
		driver.manage().timeouts().implicitlyWait(timeInsec, TimeUnit.SECONDS);
		//Reporter.log("Page is loaded");
		}
		catch(Throwable error){
			Reporter.log("Timeout for Page Load Request to complete after "+ timeInsec + " seconds");
			Assert.assertTrue(true, "Timeout for page load request after "+timeInsec+" second");
		}
	}
	
	public void driverwait(int timeToWaitInSec) throws InterruptedException{
	//	Reporter.log("waiting for "+timeToWaitInSec+" seconds...");
		Thread.sleep(timeToWaitInSec*1000);
	}
	
	public static void explicitWait(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void WaitForAlertBox()
	{
		WebDriverWait wait = new WebDriverWait(driver, 15, 100);
		wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public String getTextFromAlertBox()
	{
		Alert alert= driver.switchTo().alert();
		String alertmsg= alert.getText();
		return alertmsg ;
	}
	
	 public void scrollUp() throws InterruptedException {
		driverwait(3); 
		
		((JavascriptExecutor)

		driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
	      
	    }
	 
		public void ChildWindowHandle()
		 {
			for (String winHandle : driver.getWindowHandles())
			{
			    driver.switchTo().window(winHandle); 
			}
		 }
	public void validateTooltip(String ER,String tooltipElement) throws Exception
	{
		String ExpectedResult= ER;
		String ActualResult= getWebElement(tooltipElement).getAttribute("title");
		if(ExpectedResult.equalsIgnoreCase(ActualResult))
		{
			Reporter.log("ActualToolTip is "+ActualResult);
			Reporter.log("Testcase PASS");
		}
		else
		{
			Reporter.log("Testcase FAIL");
			Assert.fail();
		}
	}
	
	public void validatecondition(String ExpectedResult, String ActualElement) throws Exception
	{
		String Actual= getWebElement(ActualElement).getText();
		//System.out.println("Actual Heading "+Actual);
		if(ExpectedResult.equalsIgnoreCase(Actual))
		{
			Reporter.log("ExpectedResult= "+ExpectedResult+ " "+"ActualResult= "+Actual);
			Reporter.log("Testcase PASS");		
		}
		else{
			Reporter.log("Testcase FAIL");
			Assert.fail();
		}
	}
	
	public void validateElementIsDisplayed(String ElementPresence) throws Exception
	{
		driverwait(2);
		if(getWebElement(ElementPresence).isDisplayed())
		{
			Reporter.log("Testcase PASS");
		}
		else
		{
			Reporter.log("Test case FAIL");
			Assert.fail();
		}
	}
	public void validateElementIsSelected(String ElementPresence) throws Exception
	{
		driverwait(2);
		if(getWebElement(ElementPresence).isSelected())
		{
			Reporter.log("Testcase PASS");
		}
		else
		{
			Reporter.log("Test case FAIL");
			Assert.fail();
		}
	}
	
	public void doubleClick(WebElement doubleclickElement)
	{
		Actions action = new Actions(driver).doubleClick(doubleclickElement);
		action.build().perform();
		implicitWait(2);
	}
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		
		  File dir = new File(downloadPath);
		  File[] dirContents = dir.listFiles();
		  for (int i = 0; i < dirContents.length; i++) {
		      if (dirContents[i].getName().equals(fileName)) {
		          // File has been found, it can now be deleted
		          dirContents[i].delete();
		          return true;
		      }
		          }
		      return false;
		  }
	public void  ValidateElementIsPresent(String ElementName) throws Exception
	{
		WebElement deleteElement = null;
	    try {
	        deleteElement = getWebElement(ElementName);
	    } 
	    catch (NoSuchElementException e) {

	    }
	   Assert.assertTrue(deleteElement != null, "Testcase PASS");
	}
	
	public static WebElement getLocator(String locator) throws Exception {
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.className(locatorValue));
		
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
	public static List<WebElement> getLocators(String locator) throws Exception {
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
	public WebElement getWebElement(String locator) throws Exception{
		return getLocator(Repository.getProperty(locator));
	}
	
	public List<WebElement> getWebElements(String locator) throws Exception{
		return getLocators(Repository.getProperty(locator));
	}
		
		public void getresult(ITestResult result) throws IOException {
			if (result.getStatus() == ITestResult.SUCCESS) {
				test.log(LogStatus.PASS, result.getName()+ " TEST IS PASS");
			} else if (result.getStatus() == ITestResult.SKIP) {
				test.log(LogStatus.SKIP, result.getName() + " TEST IS SKIP, BECAUSE OF:-" + result.getThrowable());
			} else if (result.getStatus() == ITestResult.FAILURE) {
				test.log(LogStatus.FAIL, result.getName() + " TEST IS FAIL" + result.getThrowable());
			} else if (result.getStatus() == ITestResult.STARTED) {
				test.log(LogStatus.INFO, result.getName() + " TEST IS STARTED");
			}
		}

		@AfterMethod()
		public void afterMethod(ITestResult result) throws IOException {
			getresult(result);
		}

		@BeforeMethod()
		public void beforeMethod(Method result) {
			test = extent.startTest(result.getName());
			test.log(LogStatus.INFO, result.getName() + " test Started");
		}
		
		@AfterClass(alwaysRun = true)
		public void endTest() {
			//driver.quit();
			extent.endTest(test);
			extent.flush();
		}

	

}
