package chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.Test;
import utils.AssortedUtils;

import java.io.File;

public class PhantomJSTest
{
    @Test(priority = 1, description = "Opens Page and Take a Screenshot and Save it.")
    public void verifyPageTitle()
    {
        WebDriver driver = new PhantomJSDriver();

        driver.get("http://sahitest.com/demo/linkTest.htm");
        driver.findElement(By.linkText("linkByContent")).click();
        driver.findElement(By.linkText("Back")).click();

        AssortedUtils.captureScreenshot(driver);
    }

}
