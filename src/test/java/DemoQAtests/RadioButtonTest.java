package DemoQAtests;

import DemoQABase.ExcelReader;
import DemoQApages.ElemPage;
import DemoQApages.HomePage;
import DemoQApages.RadioButtonPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RadioButtonTest extends RadioButtonPage {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver.manage().window().maximize();
        driver.get(homeUrl);
        homePage = new HomePage();
        elemPage = new ElemPage();
        radioButtonPage = new RadioButtonPage();
        excelReader = new ExcelReader("src/test/java/TestData6.xlsx");

    }
    public void goToRadioButton(){
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        scrollIntoView(elemPage.CardItems.get(2));
        elemPage.clickOnRadioButton();
    }
    public void assertRadioButtonClickability(){
        List<String> valueList = new ArrayList<>(List.of("Yes", "Impressive", "No"));
       // List<String>

    }
    public void assertRadioButtonYesSelected(){
        Assert.assertEquals(radioButtonPage.notification.getText(), "You have selected Yes");
        Assert.assertEquals(driver.getCurrentUrl(), radioButtonUrl);
        Assert.assertTrue(radioButtonPage.yesRadioButton.isSelected(), "Radio button Yes is not selected");
    }
    public void assertRadioButtonImpressiveSelected(){
        Assert.assertEquals(radioButtonPage.notification.getText(), "You have selected Impressive");
        Assert.assertEquals(driver.getCurrentUrl(), radioButtonUrl);
        Assert.assertTrue(radioButtonPage.impressiveRadioButton.isSelected(), "Radio button impressive is not selected");
    }
    public void assertRadioButtonNoSelected(){
        Assert.assertEquals(driver.getCurrentUrl(), radioButtonUrl);
        Assert.assertTrue(radioButtonPage.noRadioButton.isSelected(), "Radio button 'No'is not selected");
        Assert.assertEquals(radioButtonPage.notification.getText(),"You have selected No");
    }
    @Test
    public void validateRadioButtonLink(){
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        scrollIntoView(elemPage.CardItems.get(2));
        elemPage.clickOnRadioButton();
        Assert.assertEquals(driver.getCurrentUrl(), radioButtonUrl);
        Assert.assertEquals(radioButtonPage.title.getText(), "Radio Button");
    }
    @Test (priority = 10)
    public void validateRadioButtonYes(){
        goToRadioButton();
        radioButtonPage.clickOnYesRadioButton();
        assertRadioButtonYesSelected();

    }
    @Test (priority = 20)
    public void validateRadioButtonImpressive() {
        goToRadioButton();
        radioButtonPage.clickOnImpressiveButton();
        assertRadioButtonImpressiveSelected();
    }

    @Test (priority = 30)
    public void validateRadioButtonNo(){
        goToRadioButton();
        radioButtonPage.clickOnNoRadioButton();
        assertRadioButtonNoSelected();

    }
    @Test (priority = 40)
    public void validateAllRadioButtonsSelection(){
        goToRadioButton();
        radioButtonPage.clickOnYesRadioButton();
        assertRadioButtonYesSelected();
        radioButtonPage.clickOnImpressiveButton();
        assertRadioButtonImpressiveSelected();
        radioButtonPage.clickOnNoRadioButton();
        assertRadioButtonNoSelected();

    }
    @AfterMethod

    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
