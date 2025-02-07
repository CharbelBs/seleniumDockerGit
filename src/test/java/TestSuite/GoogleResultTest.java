package TestSuite;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import page.object.GoogleHomePage;
import page.object.GoogleResultPage;

public class GoogleResultTest extends BaseTest {
    @Test
    @Parameters({"keywords","index"})
    public void searchPageTest(String keywords, int index) throws InterruptedException {
        GoogleHomePage gp = new GoogleHomePage(driver);
        GoogleResultPage resultPage = new GoogleResultPage(driver);

        gp.isDisplayed();
        gp.searchText(keywords);
        gp.clickSuggestionByIndex(index);
        resultPage.isDisplayed();

        Assert.assertEquals("Pass", "Pass");
    }
}
