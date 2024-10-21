package jlr;

import org.openqa.selenium.WebDriver; // Make sure to import WebDriver
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.HomePagePOM;
import pom.PersonalDetailsPOM;
import schema.DataHolder;
import utilities.ExcelUtilities;
import pom.DefenderPagePOM;
import org.openqa.selenium.WebDriver;

public class Gui {
    private WebDriver driver; // Declare the driver at the class level

    @BeforeClass
    public void setup() {
        // Automatically manage ChromeDriver version with WebDriverManager
    	System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\snalwade\\eclipse-workspace\\UITesting\\test-output\\chromedriver.exe");

        // Initialize the WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set implicit wait
        driver.get("https://www.jaguarlandrover.com/");
    }

    @Test
    public void testGui() {
        try {
            ExcelUtilities excelUtils = new ExcelUtilities();
            String excelFilePath = ".\\datafiles\\data.xlsx";
            
            List<DataHolder> personalDetailsList = excelUtils.readExcelData(excelFilePath);

            if (!personalDetailsList.isEmpty()) {
                DataHolder data = personalDetailsList.get(0);
                
                HomePagePOM hp = new HomePagePOM(driver);
                hp.clickDefenderHub();
                hp.closePopup();

                DefenderPagePOM dp = new DefenderPagePOM(driver);
                dp.clickExploreOCTA();
                dp.clickbuildAndOrderButton();

                PersonalDetailsPOM pd = new PersonalDetailsPOM(driver);
             //   pd.openPersonalDetailsPopup(data);
                pd.carModel();
                pd.fillPersonalDetails(data);
                pd.fillDetails(data); // Pass the DataHolder object here
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @AfterClass
//    public void closeBrowser() {
//        if (driver != null) {
//            driver.quit(); // Ensure the browser closes after tests
//        }
//    }
}
