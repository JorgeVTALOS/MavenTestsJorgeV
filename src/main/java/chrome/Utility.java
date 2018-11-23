package chrome;

import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class Utility
{

    public static String getScreenshot(WebDriver driver)
    {
        TakesScreenshot ts=(TakesScreenshot) driver;

        File src=ts.getScreenshotAs(OutputType.FILE);

        String path=System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".jpg";

        File destination=new File(path);

        try
        {
            FileUtils.getFileUtils().copyFile(src, destination);
        } catch (IOException e)
        {
            System.out.println("Capture Failed "+e.getMessage());
        }

        return path;
    }

}