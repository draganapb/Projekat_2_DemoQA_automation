package DemoQAtests;

import DemoQABase.DemoQABaseTest;
import DemoQABase.ExcelReader;
import DemoQApages.HomePage;
import DemoQApages.RegistrationPage;
import org.openqa.selenium.ScriptKey;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTest extends DemoQABaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException, InterruptedException {
        driver.manage().window().maximize();
        driver.get(homeUrl);
        homePage = new HomePage();
        registrationPage = new RegistrationPage();
        excelReader = new ExcelReader("src/test/java/TestData6.xlsx");
    }
    public void assertSuccessfulRegistration(){
        waitForVisibility(registrationPage.confirmedNotification);
        Assert.assertTrue(registrationPage.confirmedNotification.isDisplayed());
        Assert.assertEquals(registrationPage.confirmedNotification.getText(), "We have received your message. We will soon get in touch.");
        Assert.assertEquals(driver.getCurrentUrl(), registrationUrl);
    }
    public void assertUnsuccessfulRegistration(){
        waitForVisibility(registrationPage.errorNotification);
        Assert.assertTrue(registrationPage.errorNotification.isDisplayed());
        Assert.assertEquals(registrationPage.errorNotification.getText(), "Sorry ! Unable to verify that you are human.");
        Assert.assertEquals(driver.getCurrentUrl(), registrationFormUrl);

    }
    @Test
    public void userIsRegistered() throws InterruptedException {

    for (int i = 1; i <= excelReader.getLastRow("Registration"); i++) {
            String firstName = excelReader.getStringData("Registration", i, 0);
            String lastName = excelReader.getStringData("Registration", i, 1);
            String email = excelReader.getStringData("Registration", i, 2);
            int mobile = excelReader.getIntegerData("Registration", i, 3);
            String city = excelReader.getStringData("Registration", i, 4);
            String message = excelReader.getStringData("Registration", i, 5);
            homePage.clickOnJoinNow();
            registrationPage.clickOnRegistrationButton();
            registrationPage.insertFirstName(firstName);
            registrationPage.insertLastName(lastName);
            registrationPage.insertEmail(email);
            registrationPage.insertMobile(mobile);
            //  registrationPage.selectByValue(registrationPage.DropdownList, "1");
            registrationPage.selectByText(registrationPage.DropdownList, "Serbia");
            registrationPage.insertCity(city);
            registrationPage.insertMessage(message);
            registrationPage.insertCode("JbFk");
            registrationPage.clickOnSendButton();
            assertUnsuccessfulRegistration();
            //Assert.assertThat
        Thread.sleep(4000);
            registrationPage.clickOnClosePopUpButton();
//    js.executeScript("window.scrollBy(0,280)");

        }
    }
}
