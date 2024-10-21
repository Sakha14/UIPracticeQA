package main.java.pomClasses;

import org.apache.xmlbeans.impl.jam.JProperty;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import schema.dataHolder;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.util.Properties;

public class formPOM {

    private WebDriver driver;
    private Properties properties = new Properties();

    @FindBy(id = "firstName")
    private WebElement firstname;

    @FindBy(id = "lastName")
    private WebElement lastname;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindBy(xpath = "(//*[@Class = 'custom-control custom-radio custom-control-inline'])[1]")
    private WebElement radiobuttonMale;

    @FindBy(id = "userNumber")
    private WebElement phonenumber;

    @FindBy(id = "dateOfBirthInput")
    private WebElement calendar;

    @FindBy(className = "react-datepicker__year-select")
    private WebElement year;

    @FindBy(className = "react-datepicker__month-select")
    private WebElement month;

    @FindBy(xpath = "//div[@class='react-datepicker__day react-datepicker__day--014']")   //Specific date //*[@aria-label = 'Choose Tuesday, January 14th, 1997']
    private WebElement dob;

    @FindBy(id = "subjectsInput")
    private WebElement subject;

    @FindBy(xpath = "(//*[@class = 'custom-control custom-checkbox custom-control-inline'])[1]")
    private WebElement checkBoxSports;

    @FindBy(xpath = "//label[text() = 'Select picture']")
    private WebElement selectFile;

    @FindBy(id = "currentAddress")
    private WebElement currentAddress;

    @FindBy(xpath = "(//div[@Class = ' css-1wa3eu0-placeholder'])[1]")
    private WebElement state;

    @FindBy(xpath = "(//div[@Class = ' css-1wa3eu0-placeholder'])[2]")
    private WebElement city;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id = 'example-modal-sizes-title-lg']")
    private WebElement formDetails;

    @FindBy(xpath = "//*[@Class = 'modal-content']")
    private WebElement formResult;


    public formPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void filldetails(dataHolder details) throws InterruptedException, IOException {

        firstname.click();
        firstname.sendKeys(details.getFirstname());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //verifyFirstName:
        String expectedFirstName = "Sakharam";
        String actualFirstName = firstname.getAttribute("value");
        Assert.assertEquals(actualFirstName, expectedFirstName, "Firstname does not match");
        System.out.println("Test Case 3: Firstname: " +firstname.getAttribute("value")+  " entered sucessfully");

        lastname.click();
        lastname.sendKeys(details.getLastname());
        //verify LastName:
        String expectedLirstName = "Nalwade";
        String actualLirstName = lastname.getAttribute("value");
        Assert.assertEquals(actualLirstName, expectedLirstName, "Firstname does not match");
        System.out.println("Test Case 4: Lirstname: " +lastname.getAttribute("value")+  " entered sucessfully");

        email.click();
        email.sendKeys(details.getEmail());
        //verify Email:
        String expectedEmail = "nalwade@gmail.com";
        String actualEmail = email.getAttribute("value");
        Assert.assertEquals(actualEmail, expectedEmail, "Firstname does not match");
        System.out.println("Test Case 5: Email: " +email.getAttribute("value")+  " entered sucessfully");

        radiobuttonMale.click();
        //Verify mail radio button
        Assert.assertFalse(radiobuttonMale.isSelected(),"Radio button does not selected");
        System.out.println("Test Case 5: Radio button selected successfully");

        phonenumber.click();
        phonenumber.sendKeys(details.getPhonenumber());
        //verify Phonenumber:
        String expectedPhoneNumber = "8312092453";
        String actualPhoneNumber = phonenumber.getAttribute("value");
        Assert.assertEquals(actualPhoneNumber, expectedPhoneNumber, "Phonenumber does not match");
        System.out.println("Test Case 6: Phone number: " +phonenumber.getAttribute("value")+  " entered sucessfully");

        calendar.click();
        year.click();
        year.findElement(By.xpath("//option[@value='1997']")).click();

        month.click();
        month.findElement(By.xpath("//option[@value='0']")).click();

        dob.click();
        //Verify DOB
        String expectedDOB = "14 Jan 1997";
        String actualDOB = calendar.getAttribute("value");
        Assert.assertEquals(actualDOB, expectedDOB, "Date of Birth does not match");
        System.out.println("Test Case 7: Date of Birth: " +calendar.getAttribute("value")+  " entered sucessfully");

        Thread.sleep(3000);

        subject.click();
        subject.sendKeys(details.getSubject());
        //Verify Subject
        String expectedSubject = "abcxyz";
        String actualSubject = subject.getAttribute("value");
        Assert.assertEquals(actualSubject, expectedSubject, "Subject does not match");
        System.out.println("Test Case 8: Subject: "+subject.getAttribute("value")+ " entered successfully");

        checkBoxSports.click();
        //Verify checkbox
        Assert.assertFalse(checkBoxSports.isSelected(),"Check box does not selected");
        System.out.println("Test Case 9: Check box clicked successfully");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300)");

        currentAddress.click();
        currentAddress.sendKeys(details.getAddress());
        //Verify CurrentAddres
        String expectedCurrentAddress = "Pune";
        String actualCurrentAddress = currentAddress.getAttribute("value");
        Assert.assertEquals(actualCurrentAddress, expectedCurrentAddress, "Current Address does not match");
        System.out.println("Test Case 10: Current Address: "+currentAddress.getAttribute("value")+ " entered successfully");

        submitButton.click();
        //System.out.println("Submit Clicked");
        //Verify Details displayed or not
        Assert.assertTrue(formDetails.isDisplayed(), "Submited details are not displayed");
        System.out.println("Test case 11: Verify form submitted: Passed sucessfully");

    }

    public void takescreenshot() throws IOException, InterruptedException {

       // Properties properties = new Properties();
        String screenshotFilePath = properties.getProperty("screenshotFilePath");
        String fileName = "Submit_Form_" + System.currentTimeMillis() + ".png";
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(fileName));
            System.out.println("Screenshot is captured");
        }catch (IOException e){
            System.err.println("Error saving screenshot: " + e.getMessage());
        }
    }


}




