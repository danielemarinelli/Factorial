package Tests;

import Pages.Factorial;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HosteworldTest extends TestBase{

    Factorial factorial = null;

    @Test(priority = 1)
    public void verifyCorrectWebPage() {
        factorial = new Factorial(driver());
        String description = factorial.factorialPage();
        Assert.assertEquals(description,"Factoriall","WebPage isn't correct");
    }

    @Test(priority = 2)
    public void verifyFactorial() throws Exception {
        factorial = new Factorial(driver());
        factorial.calculate();
        //String result = factorial.calculate();
        //Assert.assertEquals(result,"factorial","Factorial didn't calculate correctly");
    }

    @Test(priority = 3)
    public void verifyInsertingAlphabeticCharacter() throws Exception {
        factorial = new Factorial(driver());
        String message = factorial.wrongDigit();
        Assert.assertEquals(message,"Please enter an integer");
    }

    @Test(priority = 4)
    public void Factorial_API_Test() throws Exception {
            getFactorial();
    }
}
