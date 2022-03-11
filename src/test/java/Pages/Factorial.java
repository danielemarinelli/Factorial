package Pages;

import Tests.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ViteApp extends TestBase {

    @FindBy(xpath =".//input[@id='number']")
    private WebElement numberField;

    @FindBy(xpath =".//h1[text()=\"The greatest factorial calculator!\"]")
    private WebElement basePrice;



    public ViteApp(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void factorialPage() {


    }

    public String addPricesFromTestData() throws InterruptedException {
        //adding  Alloy surcharge
        labelField.click();
        labelField.clear();
        labelField.sendKeys("Alloy surcharge");
        labelField.sendKeys(Keys.TAB);
        spriceField.clear();
        spriceField.sendKeys("2.15");
        secondCheckFlag.click();
        Thread.sleep(1000);
        Assert.assertEquals(priceDisplayedAlloy_surcharge.getText(),"2.15");
        //adding  Scrap surcharge
        labelField.click();
        labelField.clear();
        labelField.sendKeys("Scrap surcharge");
        labelField.sendKeys(Keys.TAB);
        spriceField.clear();
        spriceField.sendKeys("3.14");
        thirdCheckFlag.click();
        Thread.sleep(1000);
        Assert.assertEquals(priceDisplayedScrap_surcharge.getText(),"3.14");
        //adding Internal surcharge
        labelField.click();
        labelField.clear();
        labelField.sendKeys("Internal surcharge");
        labelField.sendKeys(Keys.TAB);
        spriceField.clear();
        spriceField.sendKeys("0.7658");
        fourthCheckFlag.click();
        Thread.sleep(1000);
        Assert.assertEquals(priceDisplayedInternal_surcharge.getText(),"0.77");
        //adding External surcharge
        labelField.click();
        labelField.clear();
        labelField.sendKeys("External surcharge");
        labelField.sendKeys(Keys.TAB);
        spriceField.clear();
        spriceField.sendKeys("1");
        fifthCheckFlag.click();
        Thread.sleep(1000);
        Assert.assertEquals(priceDisplayedExternal_surcharge.getText(),"1.0");
        //adding Storage surcharge
        labelField.click();
        labelField.clear();
        labelField.sendKeys("Storage surcharge");
        labelField.sendKeys(Keys.TAB);
        spriceField.clear();
        spriceField.sendKeys("0.3");
        sixthCheckFlag.click();
        Thread.sleep(1000);
        Assert.assertEquals(priceDisplayedStorage_surcharge.getText(),"0.3");

        return sumField.getText();
    }

    public String trashButton() throws InterruptedException {
        addPricesFromTestData();
        internalSurcharge.click();
        trashInternalSurcharge.click();
        Thread.sleep(1500);
        return sumField.getText();
    }

    public String changeLabel() throws InterruptedException {
        addPricesFromTestData();
        sixthPencilIcon.click();
        sixthLabelField.clear();
        sixthLabelField.sendKeys("T");
        Thread.sleep(1500);
        return msgWhenShortLabelIcon.getText();
    }

    public String negativePrice() throws InterruptedException {
        addPricesFromTestData();
        scrap.click();
        thirdPencilIcon.click();
        thirdPrice.clear();
        thirdPrice.sendKeys("-2.15");
        Thread.sleep(1500);
        return msgNegativePrice.getText();
    }

    public String newTotalPrice() throws InterruptedException {
        addPricesFromTestData();
        alloy.click();
        secondPencilIcon.click();
        secondPrice.clear();
        secondPrice.sendKeys("1.79");
        secondCheckFlag.click();
        Thread.sleep(1500);
        System.out.println(sumField.getText());
        return sumField.getText();
    }

}
