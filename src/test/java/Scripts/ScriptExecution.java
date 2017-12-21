/**
 * 
 */
package Scripts;

import org.testng.Reporter;
import org.testng.annotations.Test;

import hybrid.Pages.ExportData.ExportData;
import hybrid.Pages.LoginToApplication.LoginToApplication;
import hybrid.Utilities.TestBase;

/**
 * @author virat
 *
 */
public class ScriptExecution extends TestBase {

	@Test(priority=0)
	public void LoadAllRepositoriesAndEnterURL() throws Exception
	{
		Reporter.log("Open Browser and Entering the URL");
		LoadRepository_and_OpenBrowser();
	}
	
	@Test(priority=1)
	public void LoginToApp() throws Exception
	{
		Reporter.log("Login to the Application");
		LoginToApplication logintoApp= new LoginToApplication();
		logintoApp.loginToAxpertX();
	}
	@Test(priority=2)
	public void ExportDataPresence() throws Exception
	{
		 Reporter.log("Verify the presence of Export Data Functionality");
		 ExportData expdata= new ExportData(); 
		 expdata.VerfiyExportDataPresence();
	}
	@Test(priority=3)
	public void OpenExportdatawindow() throws Exception
	{
		Reporter.log("Verify whether Export Data window is openning or not");
		ExportData expdata1= new ExportData();
		expdata1.OpenExportDataWindow();
		
	}
	@Test(priority=4)
	public void VerfiyMandatoryFields() throws Exception
	{
		Reporter.log("Verify all the Manditory fields are present");
		ExportData mand= new ExportData();
		mand.MandatoryFields();
	}
	@Test(priority=5)
	public void VerifyDataSearchTooltip() throws Exception
	{
		Reporter.log("Verify Tooltip for Data Search");
		ExportData dataserachtt= new ExportData();
		dataserachtt.Export_DataSearchToolTip();
	}
	@Test(priority=6)
	public void VerifyFilterTooltip() throws Exception
	{
		Reporter.log("Verify Tooltip for Filter");
		ExportData filtertt= new ExportData();
		filtertt.Export_FilterToolTip();
	}
	@Test(priority=7)
	public void VerifyExportTooltip() throws Exception
	{
		Reporter.log("Verify Tooltip for Export");
		ExportData Exporttt= new ExportData();
		Exporttt.Export_ExportToolTip();
	}
	@Test(priority=8)
	public void VerifyCompleteTooltip() throws Exception
	{
		Reporter.log("Verify Tooltip for Complete");
		ExportData completett= new ExportData();
		completett.Export_CompleteToolTip();
	}
	@Test(priority=9)
	public void VerifyNextbuttonWithoutSelectingForm() throws Exception
	{
		Reporter.log("Verify Next button functionality without selecting Form/Page");
		ExportData nxtbtnnoform= new ExportData();
		getWebElement("NextButton").click();
		nxtbtnnoform.GetAlertMessage();
	}
	@Test(priority=10)
	public void VerifyFormSelectPresenceOfData() throws Exception
	{
		Reporter.log("Selecting the Form/Page from dropdown and verify the presence of data");
		ExportData formselectdd= new ExportData();
		formselectdd.SelectForm();
	}
	@Test(priority=11)
	public void VerifySelectPageFields() throws Exception
	{
		Reporter.log("Verify the functionality of 'Select Form' dropdown field");
		ExportData selectcolumn1= new ExportData();
		selectcolumn1.SelectPagefromForm();
	}
	@Test(priority=12)
	public void VerifyNextButtonFunctionalityWithoutFields() throws Exception
	{
		Reporter.log("Verify Next button functionality without selecting fields to column2");
		ExportData nextbtnnodata= new ExportData();
		//nextbtnnodata.NavigateToPreviousPage();
		//nextbtnnodata.SelectPageFieldsUsingDoubleClickFromColumn2ToColumn1();
		getWebElement("NextButton").click();
		nextbtnnodata.GetAlertMessage();
	}
	@Test(priority=13)
	public void VerifyMoveFieldFromColumn1ToColumn2() throws Exception
	{
		Reporter.log("Move the Fields from column1 to column2");
		ExportData movefld1= new ExportData();
		movefld1.SelectPageFieldsUsingDoubleClickFromColumn1ToColumn2();
	}
	@Test(priority=14)
	public void VerifyMoveFieldFromColumn2ToColumn1() throws Exception
	{
		Reporter.log("Move fields form column2 to column1");
		ExportData movec2toc1= new ExportData();
		movec2toc1.SelectPageFieldsUsingDoubleClickFromColumn2ToColumn1();
	}
	@Test(priority=15)
	public void VerifyNextButtonFunctionalityWithFields() throws Exception
	{
		Reporter.log("Verify Next Button functionality along with fields");
		ExportData nextbtn1= new ExportData();
		nextbtn1.SelectPageFieldsUsingDoubleClickFromColumn1ToColumn2();
		getWebElement("NextButton").click();
		validateElementIsDisplayed("ConditionTitle");
	}
	
	//***********************  FILTER PAGE  *************************************
	
	@Test(priority=16)
	public void VerifyConditionPageTitle() throws Exception
	{
		Reporter.log("Verify the title of condition Page");
		validatecondition(Repository.getProperty("ConditionPageTitle"), "ConditionTitle");
	}
	@Test(priority=17)
	public void SkipConditionPage() throws Exception
	{
		Reporter.log("Skipping Condition Page and clicking on Next Button and Navigating to Export Page");
		getWebElement("NextButton").click();
		validateElementIsDisplayed("FileTypeLabel");
	}
	@Test(priority=18)
	public void VerifyConditionSelectField() throws Exception
	{
		Reporter.log("Verify the functionality of select 'Field' dropdown on condition page");
		getWebElement("PreviousPage").click();
		ExportData slctflds= new ExportData();
		slctflds.checkFieldvalues();
	}
	@Test(priority=19)
	public void SelectValuefromFieldValue() throws Exception
	{
		Reporter.log("Selecting the value from select dropdown field");
		selectDropDownValue(getWebElement("FieldSelectdd"), Repository.getProperty("ConditionFieldDD"));
	}
	@Test(priority=20)
	public void VerifyConditionDropdown() throws Exception
	{
		Reporter.log("Verify the functionality of Condition Dropdown field");
		selectListofRecords(getWebElement("Conditiondd"));
	}
	@Test(priority=21)
	public void selectconditionfield() throws Exception
	{
		Reporter.log("Selecting 'EqualTo' value in 'Condition' dropdown field");
		selectDropDownValue(getWebElement("Conditiondd"),Repository.getProperty("ConditionSelectField"));
	}
	
	@Test(priority=22)
	public void VerifyValue1fieldWithSpecialChar() throws Exception
	{
		Reporter.log("Verify the functionality of Value1 Field with Special characters");
		getWebElement("Value1Field").sendKeys(Repository.getProperty("ConditionValue1SpecalChar"));
	}
	@Test(priority=23)
	public void VerifyValidValue1field() throws Exception
	{
		Reporter.log("Verify the functionality of Value1 Field With correct data");
		getWebElement("Value1Field").clear();
		getWebElement("Value1Field").sendKeys(Repository.getProperty("ConditionValue1"));
	}
	@Test(priority=24)
	public void VerifyValue2fieldDissable() throws Exception
	{
		Reporter.log("Verify Value2 Field is dissable");
		validateElementIsDisplayed("Value2Dissable");
	}
	@Test(priority=25)
	public void VerifyValue2fieldWithBetweenCondition() throws Exception
	{
		Reporter.log("Verify Value2 Field with 'BETWEEN' Condition, and value-2 field is enabled");
		ExportData betweencond= new ExportData();
		betweencond.CheckValue2FieldwithBetweenCondition();
	}
	@Test(priority=26)
	public void VerifyAddRowButton() throws Exception
	{
		Reporter.log("Validating Add Condition button and it is adding new row or not");
		validateElementIsDisplayed("AddCondition");
		getWebElement("AddCondition").click();
		validateElementIsDisplayed("SecondAddConditionRow");
	}
	@Test(priority=27)
	public void VerifyAddRowButtonWithMultipleRows() throws Exception
	{
		Reporter.log("Verifying Add Rows by adding multple rows for conditions(Adding 3-Rows)");
		getWebElement("AddCondition").click();
		//getWebElement("AddCondition").click();
		validateElementIsDisplayed("ThirdAddConditionRow");
	}
	@Test(priority=28)
	public void VerifyADDconditionButton() throws Exception
	{
		Reporter.log("Validating ADD condition button on filter page");
		validateElementIsDisplayed("FirstAddButton");
	}
	@Test(priority=29)
	public void VerifyORconditionButton() throws Exception
	{
		Reporter.log("Validating OR condition button on filter page");
		validateElementIsDisplayed("FirstORButon");
	}
	@Test(priority=30)
	public void VerifyBothButtonAreSelected() throws Exception
	{
		Reporter.log("Verifying both conditions buttons are selected at the same time");
		ExportData addor= new ExportData();
		addor.ADDandORButtons();
	}
	@Test(priority=31)
	public void NavigateToDataSearchpage() throws Exception
	{
		Reporter.log("After adding or deleting the conditions rows, Navigating to Data Search Page");
		getWebElement("PreviousPage").click();
		validateElementIsDisplayed("SelectFormLebel");
	}
	@Test(priority=32)
	public void NavigateToFilterPageToCheckData() throws Exception
	{
		Reporter.log("Navigating to Filter Page and check the presence of data");
		getWebElement("NextButton").click();
		validateElementIsDisplayed("SecondAddConditionRow");
	}
	@Test(priority=33)
	public void VerifyRemoveRowButton() throws Exception
	{
		Reporter.log("Validating Remove Condition button and it is removing added rows");
		getWebElement("ThirdRemoveRow").click();
		getWebElement("SecondRemovedRow").click();
		//ValidateElementIsPresent("ThirdRemoveRow");
		selectconditionfield();
	}
	@Test(priority=34)
	public void NavigateToExportPage() throws Exception
	{
		Reporter.log("Clicking on NextButton,navigating to Export Page");
		selectconditionfield();
		getWebElement("NextButton").click();
		validateElementIsDisplayed("FileTypeLabel");
	}
	
	//************************************EXPORT PAGE**********************************************
	
	@Test(priority=35)
	public void ExportFileDataTypeAsText() throws Exception
	{
		Reporter.log("Selecting 'Text' File Type from FileType Dropdown");
		selectByText(getWebElement("FileType"), Repository.getProperty("TextFileType"));
	}
	@Test(priority=36)
	public void ExportFileDataTypeAsCSV() throws Exception
	{
		Reporter.log("Selecting 'CSV' File Type from FileType Dropdown");
		selectByText(getWebElement("FileType"), Repository.getProperty("CSVFileType"));
	}
	@Test(priority=37)
	public void VerfiySeparatorFieldisDissabled() throws Exception
	{
		Reporter.log("Selecting 'CSV' as File and check whether 'Separator' field is dissabled");
		validateElementIsDisplayed("DissabledSeparator");
	}
	@Test(priority=38)
	public void TextFileType_SeparatorFieldIsEnabled() throws Exception
	{
		Reporter.log("Selecting FileType as Text,and check whether 'Separator' Field is Enabled");
		selectByText(getWebElement("FileType"), Repository.getProperty("TextFileType"));
		getWebElement("Seperator").isEnabled();
	}
	@Test(priority=39)
	public void VerifyFileNamewithCSVFiletype() throws Exception
	{
		Reporter.log("Selecting FileType as CSV,and check FileName with extension");
		selectByText(getWebElement("FileType"), Repository.getProperty("CSVFileType"));
		ExportData fileext= new ExportData();
		fileext.validateFileName();
	}
	@Test(priority=40)
	public void VerifyFileNamewithTEXTFiletype() throws Exception
	{
		Reporter.log("Selecting FileType as TEXT,and check FileName with extension");
		selectByText(getWebElement("FileType"), Repository.getProperty("TextFileType"));
		ExportData fileext1= new ExportData();
		fileext1.validateFileName();
	}
	@Test(priority=41)
	public void VerifyFileNameWithPageName() throws Exception
	{
		Reporter.log("Validating FileName with selected Page/Form");
		ExportData filenamepage= new ExportData();
		filenamepage.ValidateFileNameWithPage();
	}
	@Test(priority=42)
	public void VerifyNavigatingToNextPageWithoutFileName() throws Exception
	{
		Reporter.log("Verifying without Filename navigating to Next Page");
		getWebElement("ExportFileName").clear();
		getWebElement("NextButton").click();
		ExportData altnext= new ExportData();
		altnext.GetAlertMessage();
		getWebElement("ExportFileName").sendKeys(Repository.getProperty("SelectPage"));
	}
	@Test(priority=43)
	public void VerifyPresenceofDataonExportPage() throws Exception
	{
		Reporter.log("Verifying after navigationg to previous and again naviagating to Export page data is present or not");
		getWebElement("PreviousPage").click();
		getWebElement("NextButton").click();
		ExportData valpfn= new ExportData();
		valpfn.ValidateFileNameWithPage();
	}
	@Test(priority=44)
	public void VerifyWithHeaderFunctionality() throws Exception
	{
		Reporter.log("Selecting 'With Header' Checkbox");
		getWebElement("WithHeader").click();
		validateElementIsSelected("WithHeader");
	}
	@Test(priority=45)
	public void VerifyWithQuoteFunctionality() throws Exception
	{
		Reporter.log("Selecting 'With Quote' checkbox if TEXT filetype is selected");
		ExportData withq= new ExportData();
		withq.WithQuoteValidation();
		validateElementIsSelected("WithQuote");
	}
	@Test(priority=46)
	public void ValidateWithQuoteIsDissable() throws Exception
	{
		Reporter.log("Validate 'WithQuote' is dissable when selected filetype is CSV");
		selectByText(getWebElement("FileType"), Repository.getProperty("CSVFileType"));
		ExportData disquote= new ExportData();
		disquote.dissabledWithQuote();
	}
	//************************************ COMPLETE PAGE ***************************************************
	
	@Test(priority=47)
	public void NavigatingToCompletePage() throws Exception
	{
		Reporter.log("Navigationg to COMLETE Page");
		getWebElement("NextButton").click();
		validateElementIsDisplayed("DownloadButton");
	}
	@Test(priority=48)
	public void ValidateExportDataMessageWithCondition() throws Exception
	{
		Reporter.log("Navigating to Complete Page and validate the message shown after records gets Exported With Conditions");
		ExportData navcomp= new ExportData();
		navcomp.GetAlertMessage();
	}
	@Test(priority=49)
	public void ValidateExportDataMessageWithoutCondition() throws Exception
	{
		Reporter.log("Navigating to Complete Page and validate the message shown after records gets Exported without conditions");
		ExportData withoutcondnmsg= new ExportData();
		withoutcondnmsg.exportdatawithoutCondition();
	}
	@Test(priority=50)
	public void ValidateFileIsDownloaded() throws Exception
	{
		Reporter.log("On Click of Download validating file is downloaded or not");
		getWebElement("DownloadButton").click();
		driverwait(3);
		isFileDownloaded(Repository.getProperty("DownloadedFilePath"),Repository.getProperty("SelectPage")+".txt");
	}
	@Test(priority=51)
	public void NavigatingToPreviousPageFormCompletePage() throws Exception
	{
		Reporter.log("Navigationg To any previous(ExportPage) from Complete Page");
		getWebElement("PreviousPage").click();
		validateElementIsDisplayed("FileTypeLabel");
	}
	@Test(priority=52)
	public void NavigatingThroughIcons() throws Exception
	{
		Reporter.log("Navigationg To any page throwugh icons");
		getWebElement("DataSearchTooltip").click();
		implicitWait(3);
		validateElementIsDisplayed("SelectFormLebel");
	}
	@Test(priority=53)
	public void NavigatingToCompletePageThroughIcons() throws Exception
	{
		Reporter.log("Navigationg To Complete page throwugh icons");
		getWebElement("CompleteTooltip").click();
		validateElementIsDisplayed("DownloadButton");
	}
	@Test(priority=54)
	public void EnterIncorrectDataAndValidate() throws Exception
	{
		Reporter.log("Entering Incorrect Data and validating it is throwing message or not");
		ExportData incmsg= new ExportData();
		incmsg.ValidatingIncorrectDataMEssage();
		incmsg.GetAlertMessage();
	}
	@Test(priority=55)
	public void VerifyingDoneButton() throws Exception
	{
		Reporter.log("Closing the Export Window after Exported records gets exported");
		getWebElement("DoneButton").click();
		driver.switchTo().defaultContent();
		validateElementIsDisplayed("AppTitle");
	}
	
	
}
