package TestSuite;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.object.GoogleHomePage;

public class GoogleHomeTest extends BaseTest{

	
	   @Test
	    public void searchTest() {
	        GoogleHomePage gp = new GoogleHomePage(driver);
	 
	        gp.isDisplayed();
	        Assert.assertEquals(1, 1);
	    }
}
