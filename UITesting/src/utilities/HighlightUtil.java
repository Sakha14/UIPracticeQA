package utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighlightUtil {

    private WebDriver driver;

    public HighlightUtil(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Highlights the given WebElement by changing its border color.
     * 
     * @param element The WebElement to highlight.
     */
    public void highlightElement(WebElement element) {
        // JavaScript to set border and background color
        String originalStyle = element.getAttribute("style");
        String highlightStyle = "border: 3px solid red; background-color: yellow;";
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Set the highlight style
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, highlightStyle);

        // Optionally reset the style after a delay
        try {
            Thread.sleep(2000); // Keep highlight for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Reset to the original style
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
    }
}
