/**
 * 
 */
package hybrid.Pages.LoginToApplication;

import org.testng.Reporter;

import hybrid.Utilities.TestBase;

/**
 * @author virat
 *
 */
public class LoginToApplication extends TestBase {
	
public void loginToAxpertX() throws Exception{
		
		getWebElement("SelectProject").sendKeys(Repository.getProperty("ProjectName"));
		getWebElement("AppUserName").sendKeys(Repository.getProperty("Username"));
		getWebElement("AppPassword").sendKeys(Repository.getProperty("Password"));
		getWebElement("LoginButton").click();
		implicitWait(3);
		if(getWebElement("AppTitle").isDisplayed())
		{
			Reporter.log("Login is successful");
		}
		else
		{
			Reporter.log("Login FAIL");
		}
		
		
}

}
