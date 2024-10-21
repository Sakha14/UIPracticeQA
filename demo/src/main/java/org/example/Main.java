package main.java.org.example;

import main.java.pomClasses.formPOM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import schema.dataHolder;
import utilities.ExcelUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Main {
    private WebDriver driver;
    private Properties properties;

    @BeforeClass
    public void setup() throws InterruptedException {
        properties = new Properties();
        //clearOldScreenshots();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String chromeDriverPath = properties.getProperty("chromeDriverPath");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

//    @Test(priority = 1)
//    public void clearOldScreenshots() {
//        //Properties properties = new Properties();
//        String screenshotFilePath = properties.getProperty("screenshotFilePath");
//        File directory = new File("screenshotFilePath");
//        if (directory.exists()) {
//            for (File file : directory.listFiles()) {
//                if (!file.isDirectory()) {
//                    file.delete();
//                }
//            }
//            System.out.println("Old screenshots deleted.");
//        } else {
//            directory.mkdirs(); // Create directory if it does not exist
//        }
//    }



    @Test(priority = 1)
    public void filldetails() {
        try {

            String formUrl = properties.getProperty("formUrl");
            String excelFilePath = properties.getProperty("excelFilePath");

            driver.get(formUrl);

            // Initialize ExcelUtilities and read data
            ExcelUtilities excelUtils = new ExcelUtilities();
            List<dataHolder> personalDetailsList = excelUtils.readExcelData(excelFilePath);

            if (personalDetailsList != null && !personalDetailsList.isEmpty()) {
                dataHolder data = personalDetailsList.get(0);

                //verifyPageTitle:
                String expectedTitle = "DEMOQA";
                String actualTitle = driver.getTitle();
                Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match");
                System.out.println("Test Case 1: verifyPageTitle: Passed sucessfully");

                //Verify main logo is displayed or not
                WebElement logo = driver.findElement(By.cssSelector("img[src = '/images/Toolsqa.jpg']"));
                Assert.assertTrue(logo.isDisplayed(), "Main logo is not displayed");
                System.out.println("Test case 2: Verify Logo: Passed sucessfully");

                formPOM form = new formPOM(driver);
                form.filldetails(data);

            } else {
                System.out.println("No personal details found in the Excel file.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 2)
    public void takeScreenshot() throws IOException, InterruptedException {
      //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        formPOM form = new formPOM(driver);
        form.takescreenshot();
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
