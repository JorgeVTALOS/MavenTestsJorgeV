package chrome;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.AssortedUtils;

import java.io.IOException;

public class ExtentReportDemo
{
    ExtentHtmlReporter reporter;
    ExtentReports extent;
    ExtentTest logger;
    ExtentTest logger2;
    WebDriver driver;


    @BeforeMethod
    public void setup()
    {

        reporter = new ExtentHtmlReporter("./Reports/ReportExtent.html");

        extent = new ExtentReports();

        extent.attachReporter(reporter);

        logger= extent.createTest("accessTest");

    }


    @Test(priority = 2)
    public void accessTest() throws Exception
    {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get("https://www.google.com");

        System.out.println("This page title is ..." + driver.getTitle());

        Assert.assertTrue(driver.getTitle().contains("Amazon"));
    }

    @Test(priority = 1)
    public void accessTest2() throws Exception
    {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get("https://www.amazon.com");

        System.out.println("This page title is ..." + driver.getTitle());

        Assert.assertTrue(driver.getTitle().contains("Amazon"));
    }


    @AfterMethod
    public void tearDown(ITestResult result) throws Exception
    {

        try {

            System.out.println(ITestResult.FAILURE);

            if (result.getStatus()==ITestResult.FAILURE)
            {
                String temp = AssortedUtils.captureScreenshot(driver);

                logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
                logger2.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
            }

            Thread.sleep(5000);

            extent.flush();

            driver.close();

        } catch (IOException e) {

            System.out.println("tearDown Failed "+e.getMessage());

        }
    }
}
