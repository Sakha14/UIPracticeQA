package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class HomePagePOM {

	private WebDriver driver;

	@FindBy(xpath = "(//*[@href=\"https://www.landrover.co.uk/defender/index.html\"])[3]")
	private WebElement defenderHub;

	@FindBy(xpath = "//*[@id = 'psyma_close_link_container_text']") // span[@id = 'psyma_close_link_container_text']
	private WebElement closePopUp;

	public HomePagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickDefenderHub() {

		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 200);");
		defenderHub.click();
		System.out.println("Title: " + driver.getTitle());
	}

	public void closePopup() throws Exception {
		
		 WebDriverWait wait = new WebDriverWait(driver, 10);

        // Wait for the loading element to be visible
        WebElement closepopupClick = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id = 'psyma_close_link_container_text']")));
        closepopupClick.click();
	}
}
