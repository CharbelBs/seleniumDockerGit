package TestSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setUpDriver() throws MalformedURLException {
    	
    	String host = "localhost";
    	if(System.getProperty("HUB_HOST") != null)
    		host = System.getProperty("HUB_HOST");
    	
    	 String hubURL = "http://"+ host +":4444/wd/hub";
         String browser = System.getProperty("BROWSER");
         if (browser.equals("firefox")) {
             FirefoxOptions options = new FirefoxOptions();
             this.driver = new RemoteWebDriver(new URL(hubURL), options);
         } else {
             ChromeOptions options = new ChromeOptions();          
             this.driver = new RemoteWebDriver(new URL(hubURL), options);
         }
/*
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled"); 
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");


        driver = new EdgeDriver(options);
        */
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        
        driver.get("https://www.google.com");
        acceptCookies();
       }

       public void acceptCookies() {
           try {
               WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

               // Locate the "Alle akzeptieren" button and click it
               WebElement acceptCookiesButton = wait.until(
                   ExpectedConditions.elementToBeClickable(By.xpath("//button[.='Alle akzeptieren']"))
               );
               acceptCookiesButton.click();
               System.out.println("Cookies accepted.");
           } catch (Exception e) {
               System.out.println("No cookie banner found, continuing test.");
           }
       }

    @AfterTest
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}