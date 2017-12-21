/**
 * 
 */
package hybrid.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author virat
 *
 */
public class TestBase extends GenericMethods {
	
InputStream stream;
	
	//public static Properties Repository= new Properties();
	
	public void LoadRepository_and_OpenBrowser() throws Exception
	{
		LoadRepository();
		selectBrowser(Repository.getProperty("browser"));
		driver.get(Repository.getProperty("url"));
		implicitWait(10);
	}
	
	public void LoadRepository() throws IOException
	{
		
		stream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/java/hybrid/Data/DataSheet.properties"));
		Repository.load(stream);
		
		stream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/java/hybrid/Properties/Login.properties"));
		Repository.load(stream);
		
		stream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/java/hybrid/Properties/Exportdata.properties"));
		Repository.load(stream);
		
	}
		
		/*public static WebElement getLocator(String locator) throws Exception {
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
		}*/
		
		public void closeBrowser(){
			driver.quit();
		}
		


}
