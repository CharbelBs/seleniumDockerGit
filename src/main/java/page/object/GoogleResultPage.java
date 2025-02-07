package page.object;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleResultPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public GoogleResultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "News")
    private WebElement newsLink;

    public boolean isDisplayed() {
        this.wait.until(ExpectedConditions.visibilityOf(newsLink));
        return true;
    }
}
