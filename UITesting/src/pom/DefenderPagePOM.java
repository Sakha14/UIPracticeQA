package pom;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DefenderPagePOM {

	private WebDriver driver;

	@FindBy(xpath = "(//span[@Class = \'cta-content\'])[12]") // *[@Class = \"primary-link icon-arrow-right\"])[1]
	WebElement ExploreOCTA;

	@FindBy(xpath = "//*[@Class = 'primary-link icon-ignite-configure']") // a[@aria-label = 'I WANT ONE:DEFENDER OCTA']
	WebElement buildAndOrderButton;

	public DefenderPagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Methods

	public void clickExploreOCTA() throws InterruptedException {

		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

		Point location = ExploreOCTA.getLocation();
		System.out.println(location);

		Thread.sleep(3000);
		ExploreOCTA.click();
		System.out.println("Clicked");

	}

	public void clickbuildAndOrderButton() {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)", "");

		buildAndOrderButton.click();
	}

}
