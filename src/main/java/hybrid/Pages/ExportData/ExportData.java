/**
 * 
 */
package hybrid.Pages.ExportData;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import hybrid.Utilities.TestBase;

/**
 * @author virat
 *
 */
public class ExportData extends TestBase {
	
	//****************************** DATA SOURCE PAGE METHODS *****************************************************
	public void VerfiyExportDataPresence() throws Exception
	{
		getWebElement("Utilities").click();
		validateElementIsDisplayed("Exportdata");
	}
	public void OpenExportDataWindow() throws Exception
	{
		//getWebElement("Utilities").click();
		getWebElement("Exportdata").click();
		driverwait(3);
		validatecondition("Export Data", "ExportHeading");
		SwitchFrame(getWebElement("ExportFrame"));
		implicitWait(3);
	}
	public void Export_DataSearchToolTip() throws Exception
	{
		validateTooltip("Data Search", "DataSearchTooltip");
		
	}
	public void Export_FilterToolTip() throws Exception
	{
		validateTooltip("Filter", "FilterTooltip");
	}
	public void Export_ExportToolTip() throws Exception
	{
		validateTooltip("Export", "ExportTooltip");
	}
	public void Export_CompleteToolTip() throws Exception
	{
		validateTooltip("Complete", "CompleteTooltip");
	}
	public void SelectForm() throws Exception
	{
		selectListofRecords(getWebElement("SelectForm"));
		
	}
	public void SelectPagefromForm() throws Exception
	{
		selectByText(getWebElement("SelectForm"), Repository.getProperty("SelectPage"));
		explicitWait(getWebElement("ColumnFirstField"), 2);
		validateElementIsDisplayed("ColumnFirstField");
	}
	public void SelectPageFieldsUsingDoubleClickFromColumn1ToColumn2() throws Exception
	{
		doubleClick(getWebElement("ColumnFirstField"));
		doubleClick(getWebElement("ColumnFirstField"));
		doubleClick(getWebElement("ColumnFirstField"));
		validateElementIsDisplayed("Column2field");
		
	}
	public void SelectPageFieldsUsingDoubleClickFromColumn2ToColumn1() throws Exception
	{
		doubleClick(getWebElement("Column2field"));
		doubleClick(getWebElement("Column2field"));
		doubleClick(getWebElement("Column2field"));
		validateElementIsDisplayed("ColumnFirstField");
	}
	public void NavigateToPreviousPage() throws Exception
	{
		getWebElement("PreviousPage").click();
	}
	public void GetAlertMessage() throws Exception
	{
		implicitWait(2);
		String AlertMsg=getWebElement("AlertMessage").getText();
		Reporter.log("Alert Message '"+ AlertMsg+"'");
	}
	public void MandatoryFields() throws Exception
	{
		if(getWebElement("MandatoryFields").getText().contains("*"))
		{
			Reporter.log("Mandatory Fields are present");
			Reporter.log("Testcase PASS");
		}
		else{
			Reporter.log("Testcase FAIL");
			Assert.fail();
		}
	}
	//******************************* FILTER PAGE METHODS ***********************************************************
	public void checkFieldvalues() throws Exception
	{
		Select dropdown = new Select(getWebElement("FieldSelectdd"));
	    List<WebElement> dd = dropdown.getOptions();
	     int AllValues= dd.size()-1;
	     if(AllValues>0)
	     {
	    	 Reporter.log("Field has "+AllValues +" values");
	    	 Reporter.log("Testcase PASS");
	     }
	     else
	     {
	    	 Reporter.log("Testcase FAIL");
	    	 Assert.fail();
	     }
	}
	public void CheckValue2FieldwithBetweenCondition() throws Exception
	{
		selectByText(getWebElement("Conditiondd"), Repository.getProperty("BetweenCondition"));
		if(getWebElement("value2field").isEnabled())
		{
			Reporter.log("Field is Enabled");
			Reporter.log("Testcase PASS");
		}
		else
		{
			Reporter.log("Test case FAIL");
		}
	}
	//****************************** EXPORT PAGE METHODS ************************************************************
	
	public void validateFileName() throws Exception
	{
		driverwait(2);
		String FileName1= getWebElement("ExportFileName").getAttribute("value");
		//System.out.println("filename = "+FileName1);
		if(FileName1.contains(".csv"))
		{
			Reporter.log("Selected FileName is '"+FileName1+"'");
			Reporter.log("Testcase PASS");
		}
		else if(FileName1.contains(".txt"))
		{
			Reporter.log("Selected FileName is '"+FileName1+"'");
			Reporter.log("Testcase PASS");
		}
		else
		{
			Reporter.log("Testcase FAIL");
			Assert.fail();
		}
	}
	public void ValidateFileNameWithPage() throws Exception
	{
		String FileName2= getWebElement("ExportFileName").getAttribute("value");
		//System.out.println("fileepagename"+FileName2);
		if(FileName2.contains(Repository.getProperty("SelectPage")))
		{
			Reporter.log("FileName is '"+FileName2+"'");
			Reporter.log("Testcase PASS");
		}
		else
		{
			Reporter.log("Testcase FAIL");
			Assert.fail();
		}
	}
	public void WithQuoteValidation() throws Exception
	{
		Select filety= new Select(getWebElement("FileType"));
		String DDvalue= filety.getFirstSelectedOption().getText();
			if(DDvalue.equals("CSV"))
			{
				Reporter.log("Selected File Type is CSV Format, change FileType to TEXT type");
			}
			else
			{
				getWebElement("WithQuote").click();
				Reporter.log("'With Quote' Selected  ");
			}
		} 
	public void ValidatingIncorrectDataMEssage() throws Exception
	{
		getWebElement("FilterTooltip").click();
		selectDropDownValue(getWebElement("FieldSelectdd"), Repository.getProperty("ConditionFieldDD"));
		selectDropDownValue(getWebElement("Conditiondd"), Repository.getProperty("ConditionSelectField"));
		getWebElement("Value1Field").sendKeys(Repository.getProperty("IncorrectValueInCondn"));
		getWebElement("NextButton").click();
		implicitWait(3);
		getWebElement("NextButton").click();
	}
	public void dissabledWithQuote() throws Exception
	{
		if(getWebElement("WithQuote").getAttribute("disabled").contains("disabled"))
		{
			Reporter.log("Testcase is PASS");
		}
		else
		{
			Reporter.log("Testcase FAIL");
		}
	}
	public void exportdatawithoutCondition() throws Exception
	{
		getWebElement("FilterTooltip").click();
		getWebElement("ClearCondition").click();
		getWebElement("NextButton").click();
		getWebElement("NextButton").click();
		GetAlertMessage();
	}
	public void ADDandORButtons() throws Exception
	{
		if(getWebElement("FirstORButon").isSelected())
		{
			Reporter.log("Selected Radio button is OR condition Button");
			Reporter.log("Testcase PASS");
		}
		else if(getWebElement("FirstAddButton").isSelected())
		{
			Reporter.log("Selected Radio button is ADD condition Button");
			Reporter.log("Testcase PASS");
		}
		else
		{
			Reporter.log("Testcase is FAIL");
		}
	}
	
	}

