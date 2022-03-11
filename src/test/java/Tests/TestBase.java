package Tests;


import com.relevantcodes.extentreports.LogStatus;
import core.*;
import core.API.APIMethods;
import core.API.Environments;
import core.API.RestSession;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {

    public WebDriver driver;
    private TestReporter reporter;
    private RestSession session;

    @BeforeSuite
    public void initSuite() throws Exception {
        TestConfig.load(System.getenv("env"));
        TestConfig.addProperty("browser",System.getenv("browser"));
        TestConfig.addProperty("env",System.getenv("env"));
        reporter = new TestReporter();
    }

    @BeforeClass
    public void initDriver() {
        driver =  new DriverFactory().getDriver(TestConfig.getProperty("browser"));
    }


    public WebDriver driver() {
        return driver;
    }

    public void moveOverElementInPageAndClick(WebElement ele){
        Actions action = new Actions(driver());
        action.moveToElement(ele).click(ele).build().perform();
    }

    public int fatt(String x) {
        int i;
        int f=1;
        for(i=1; i<=Integer.parseInt(x); i=i+1) { f=f*i; }
        return f;
    }



    @BeforeMethod
    public void launchApp() {
        driver.get(TestConfig.getProperty("HosteworldURL"));
    }

    @BeforeMethod
    public void initTestReport(Method method){
        reporter.startReporting(method.getName(), driver);
    }

    public TestReporter reporter(){
        if(reporter!=null){
            return reporter;
        }
        return null;
    }

    @AfterMethod
    public void closeReport(){
        reporter.endReporting();
    }

    @AfterClass
    public void cleanUp() {
        if(driver!=null) {
            driver.close();
        }
    }

    @AfterSuite
    public void clearReport(){
        reporter.flushReport();
    }

    @AfterMethod
    public void takeScreenShotIfFailure(ITestResult result) throws IOException {
        if(ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot camera =((TakesScreenshot)driver);
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            System.out.println("Screenshot taken: " + screenShot.getAbsolutePath());
            File DestFile = new File("./ScreenShots/"+result.getName()+"_Fail.png");
            FileHandler.copy(screenShot,DestFile);
        }

    }

    @AfterMethod
    public void testStatusInExtentReport(ITestResult result) {
        if(ITestResult.FAILURE == result.getStatus()){
            reporter().report(LogStatus.FAIL,"Failed test is: "+result.getName());
            reporter().report(LogStatus.FAIL,result.getThrowable());
        }else if(ITestResult.SUCCESS == result.getStatus()){
            reporter().report(LogStatus.PASS,"Test passed: "+result.getName());
        }else if(ITestResult.SKIP == result.getStatus()){
            reporter().report(LogStatus.SKIP,"Test skipped: "+result.getName());
        }
    }

    public ResponseBody getFactorial() throws Exception {
        session = new RestSession();
        Response resp = session.sendRequest(APIMethods.GET_API);
        Assert.assertEquals(resp.getStatusCode(),200);
        System.out.println();
        System.out.println("######################");
        System.out.println("API ANSWERED WITH 200 OK CODE");
        System.out.println("######################");
        return resp.getBody();
    }

    @BeforeTest
    public void initTest() throws Exception {
        Environments.load();
    }

}