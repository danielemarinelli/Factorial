package Tests;

import Pages.ViteApp;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViteAppTest extends TestBase{

    ViteApp v_app = null;

    @Test(priority = 1)
    public void verifyBasePriceChange() throws InterruptedException {
        v_app = new ViteApp(driver());
        String price = v_app.basePriceModified();
        Assert.assertEquals(price,"5.00","Price didn't change to 5.00");
    }

    @Test(priority = 2)
    public void verifyInsertionPricesFromTestData() throws InterruptedException {
        v_app = new ViteApp(driver());
        String tot_price = v_app.addPricesFromTestData();
        Assert.assertEquals(tot_price,"8.36","Prices didn't round correctly");
    }

    @Test(priority = 3)
    public void verifyCancelInternalSurcharge() throws InterruptedException {
        v_app = new ViteApp(driver());
        String tot_price = v_app.trashButton();
        Assert.assertEquals(tot_price,"7.59","Prices didn't round correctly");
    }

    @Test(priority = 4)
    public void verifyLabelLength() throws InterruptedException {
        v_app = new ViteApp(driver());
        String label = v_app.changeLabel();
        Assert.assertEquals(label,"This label is too short!","Label check");
    }

    @Test(priority = 5)
    public void verifyNegativePrice() throws InterruptedException {
        v_app = new ViteApp(driver());
        String neg = v_app.negativePrice();
        Assert.assertEquals(neg,"Cannot be negative!","Check negative prices");
    }

    @Test(priority = 6)
    public void verifyNewTotalPrice() throws InterruptedException {
        v_app = new ViteApp(driver());
        String newTotal = v_app.newTotalPrice();
        Assert.assertEquals(newTotal,"8.00","Check new total price");
    }

}
