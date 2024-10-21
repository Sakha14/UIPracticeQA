package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import schema.DataHolder;

public class PersonalDetailsPOM {

	private WebDriver driver;

	@FindBy(xpath = "(//*[@Class = '_feature-block_4vh3d_22'])[2]")
	private WebElement selectModel;

	@FindBy(xpath = "(//*[text() = 'SUMMARY'])[2]")
	private WebElement summaryButton;

	@FindBy(xpath = "//button[text() = 'SEND TO RETAILER']")
	private WebElement sendToRetailer;

	@FindBy(xpath = "//button[@Class = 'jlr-date-picker__trigger addon-input__item']")
	private WebElement calender;

	@FindBy(className = "month-dropdown")
	private WebElement selectMonth;

	@FindBy(id = "November")
	private WebElement month;

	@FindBy(xpath = "(//*[@Class = 'day-cell ng-binding ng-scope'])[22]")
	private WebElement selectDate;

//    @FindBy(xpath = "//*[@class='mfp-iframe-alt-wrap']")
//    WebElement personalDetailsPopUp;

	@FindBy(xpath = "//div[@id='personalDetailsTitle_1']")
	WebElement personalDetailsTitle;

	@FindBy(xpath = "//li[@id='Mr']")
	private WebElement mr;

	@FindBy(id = "personalDetailsFirstName")
	private WebElement firstNameInput;

	@FindBy(id = "personalDetailsLastName")
	private WebElement lastNameInput;

	@FindBy(id = "personalDetailsEmailAddress")
	WebElement emailAddressInput;

	@FindBy(id = "personalDetailsMobileNumber")
	WebElement phoneNumberInput;

	@FindBy(xpath = "//*[text() = 'Manual Entry']")
	private WebElement manualEntry;

	@FindBy(id = "pcaAddressLookupBuildingName")
	private WebElement BuildingName;

	@FindBy(id = "pcaAddressLookupBuildingNumber")
	private WebElement BuildingNumber;

	@FindBy(id = "pcaAddressLookupSubBuilding")
	private WebElement SubBuilding;

	@FindBy(id = "pcaAddressLookupAddressLine1")
	WebElement AddressLine1;

	@FindBy(id = "pcaAddressLookupAddressLine2")
	WebElement AddressLine2;

	@FindBy(id = "pcaAddressLookupAddressLine3")
	WebElement AddressLine3;

	@FindBy(id = "pcaAddressLookupCity")
	WebElement City;

	@FindBy(id = "pcaAddressLookupPostalCode")
	WebElement PostalCode;

	@FindBy(id = "retailerLookupSearch")
	WebElement Search;

	@FindBy(xpath = "//*[@Class = 'av-checkbox av-optional id-eMail col-xs-12 col-sm-12 col-md-3 col-lg-3 av-item-container']")
	WebElement Email;

	@FindBy(id = "telephone")
	WebElement Telephone;

	@FindBy(id = "postDirectMail")
	WebElement Post;

	@FindBy(id = "smsTextMessage")
	WebElement Sms;

	@FindBy(xpath = "(//*[@class='btn wdg-button'])[2]")
	WebElement submitButton;

	// Constructor
	public PersonalDetailsPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void carModel() throws Exception {

		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", selectModel);

		selectModel.click();
		summaryButton.click();

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait until the desired element is visible
		// WebElement myElement =
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()
		// = 'SEND TO RETAILER']")));
		Thread.sleep(4000);
		sendToRetailer.click();

	}

	public void fillPersonalDetails(DataHolder details) throws InterruptedException {

		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(3));

		emailAddressInput.click();
		emailAddressInput.sendKeys(details.getEmail());

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 300)");

		personalDetailsTitle.click();
		System.out.println("Is displayed: " + personalDetailsTitle.isDisplayed());
		mr.click();
		System.out.println("Selected: " + mr);
	}

	public void fillDetails(DataHolder details) throws Exception {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(3));

		firstNameInput.click();
		firstNameInput.sendKeys(details.getFirstName());

		lastNameInput.click();
		lastNameInput.sendKeys(details.getLastName());

		phoneNumberInput.click();
		phoneNumberInput.sendKeys(details.getPhone());

		manualEntry.click();

		BuildingName.click();
		BuildingName.sendKeys(details.getBuildingName());

		BuildingNumber.click();
		BuildingNumber.sendKeys(details.getBuildingNumber());

		SubBuilding.click();
		SubBuilding.sendKeys(details.getSubBuildingName());

		AddressLine1.click();
		AddressLine1.sendKeys(details.getAddressline1());

		AddressLine2.click();
		AddressLine2.sendKeys(details.getAddressline2());

		AddressLine3.click();
		AddressLine3.sendKeys(details.getAddressline3());

		City.click();
		City.sendKeys(details.getCity());

		PostalCode.click();
		PostalCode.sendKeys(details.getPostalcode());



			Wait<WebDriver> wait2 = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
					.pollingEvery(Duration.ofSeconds(3));
			calender.click();

			selectMonth.click();

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", month);
			month.click();
			
			js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; background: yellow;');",
					selectDate);
			selectDate.click();
			System.out.println("Date is selected");



		Search.click();
		System.out.println("SearchBox Clicked");
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; background: yellow;');", Search);
		Search.sendKeys(details.getSearch());
		System.out.println("TextEntered");

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		if (Email.isDisplayed()) {
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].click();", Email);
			Email.click();
		}

		if (submitButton.isDisplayed() && submitButton.isEnabled()) {
			JavascriptExecutor js3 = (JavascriptExecutor) driver;
			js3.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; background: yellow;');",
					submitButton);
			submitButton.click();
			System.out.println("Submit clicked");
		} else {
			System.out.println("Submit button is either not visible or not enabled.");
		}
	}

}
