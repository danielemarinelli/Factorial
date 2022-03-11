package Pages;

import Tests.TestBase;
import core.ExcelDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

public class Factorial extends TestBase {

    @FindBy(xpath =".//input[@id='number']")
    private WebElement numberField;

    @FindBy(xpath =".//h1[text()='The greatest factorial calculator!']")
    private WebElement pageDescription;

    @FindBy(xpath =".//button[@id='getFactorial']")
    private WebElement calculateButton;

    @FindBy(xpath =".//p[@id='resultDiv']")
    private WebElement result;

    List<Map<String, String>> dataFromExcelFile;


    public Factorial(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    public String factorialPage() {
            System.out.println("WEB PAGE IS DISPLAYING: "+pageDescription.getText());
            return driver.getTitle();
    }

    public void calculate() throws Exception {
        dataFromExcelFile = ExcelDataProvider.getTestData();
        String num = dataFromExcelFile.get(0).get("NUMBER");
        numberField.click();
        numberField.sendKeys(num);
        calculateButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(result));
        Thread.sleep(500);
        int f = fatt(num);
        String[] s= result.getText().split(" ");
        System.out.println("Calculating factorial of "+num+" ----->>> "+f);
        Assert.assertEquals(Integer.toString(f),s[5],"### The calculation is wrong ###");
    }

    public String wrongDigit() throws Exception {
        dataFromExcelFile = ExcelDataProvider.getTestData();
        String cha = dataFromExcelFile.get(0).get("CHARACTER");
        numberField.click();
        numberField.sendKeys(cha);
        calculateButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(result));
        Thread.sleep(500);
        String f= result.getText();
        return f;
    }

}
