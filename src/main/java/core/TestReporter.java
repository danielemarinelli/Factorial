package core;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import java.io.File;

public class TestReporter {

    private ExtentReports extent;
    private ExtentTest test;
    private WebDriver driver;

    public TestReporter(){
        extent = new ExtentReports("./TestReport.html", true);
        File file = new File("./ScreenShots");
        if(!file.exists()){
            file.mkdir();
        }
    }

    public void startReporting(String testName, WebDriver driver){
        this.driver =  driver;
        test = extent.startTest(testName, "");
    }

    public void endReporting(){
        extent.endTest(test);
    }

    public void flushReport(){
        extent.flush();
        extent.close();
    }

    public void report(LogStatus status, String stepName, String description){
        if(test!=null){
            test.log(status, stepName, description);
        }
    }

    public void report(LogStatus status, String description){
        if(test!=null){
            test.log(status, description);
        }
    }

    public void report(LogStatus status,  Throwable description){
        if(test!=null){
            test.log(status, description);
        }
    }

}