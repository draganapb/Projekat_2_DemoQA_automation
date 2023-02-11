package DemoQAtests;

import DemoQABase.DemoQABaseTest;
import DemoQABase.ExcelReader;
import DemoQApages.ButtonsPage;
import DemoQApages.ElemPage;
import DemoQApages.HomePage;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ButtonsTest extends DemoQABaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver.manage().window().maximize();
        driver.get(homeUrl);
        homePage = new HomePage();
        elemPage = new ElemPage();
        buttonsPage = new ButtonsPage();
        excelReader = new ExcelReader("src/test/java/TestData6.xlsx");


    }
    public void goToButtons(){
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        scrollIntoView(elemPage.CardItems.get(2));
        elemPage.clickOnButtons();
    }
    public void assertNoAction(){
        Assert.assertFalse(isDisplayed(buttonsPage.doubleClickMessage));
        Assert.assertFalse(isDisplayed(buttonsPage.clickMessage));
        Assert.assertFalse(isDisplayed(buttonsPage.rightClickMessage));
        Assert.assertEquals(driver.getCurrentUrl(), buttonsURL);
    }
    public void assertDoubleClickButtonAction(){
        String actionMessage = "You have done a double click";
        Assert.assertTrue(buttonsPage.doubleClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.doubleClickMessage.getText(), actionMessage);
        Assert.assertEquals(driver.getCurrentUrl(), buttonsURL);

    }
    public void assertRightClickButtonAction(){
        String actionMessage = "You have done a right click";
        Assert.assertTrue(buttonsPage.rightClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.rightClickMessage.getText(),actionMessage );
        Assert.assertEquals(driver.getCurrentUrl(), buttonsURL);
    }
    public void assertClickMeButtonAction(){
        String actionMessage = "You have done a dynamic click";
        Assert.assertTrue(buttonsPage.clickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.clickMessage.getText(), actionMessage);
        Assert.assertEquals(driver.getCurrentUrl(), buttonsURL);
    }
    @Test
    public void validateButtonsLink(){
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        scrollIntoView(elemPage.CardItems.get(2));
        elemPage.clickOnButtons();
        Assert.assertEquals(driver.getCurrentUrl(), buttonsURL );
        Assert.assertEquals(buttonsPage.title.getText(), "Buttons");

    }
    @Test
    public void validateDoubleClickButton(){
        goToButtons();
        buttonsPage.doubleClickWithJS(buttonsPage.doubleClickButton);
        assertDoubleClickButtonAction();
    }
    @Test
    public void NoActionWithClickOnDoubleClickButton(){
        goToButtons();
        buttonsPage.clickOndoubleClickButton();
        assertNoAction();
    }
    @Test
    public void NoActionWithRightClickOnDoubleClickButton(){
        goToButtons();
        buttonsPage.rightClickOnDoubleClickButton();
        assertNoAction();
    }
    @Test
    public void validateRightClickButton() throws InterruptedException {
        goToButtons();
        buttonsPage.rightClickOnRightClickButton();
        assertRightClickButtonAction();
    }
    @Test
    public void noActionWithClickOnRightButton(){
        goToButtons();
        buttonsPage.clickOnRightClickButton();
        assertNoAction();

    }
    @Test
    public void noActionWithDoubleClickOnRightButton(){
        goToButtons();
        buttonsPage.doubleClickWithJS(buttonsPage.rightClickButton);
        assertNoAction();

    }
    @Test
    public void validateClickOnClickButton(){
        goToButtons();
        buttonsPage.clickOnClickMeButton();
        assertClickMeButtonAction();
    }
    @Test
    public void noActionWithDoubleClickOnClickButton(){
        goToButtons();
        buttonsPage.doubleClickWithJS(buttonsPage.clickMeButton);
        assertNoAction();
    }
    @Test
    public void noActionWithRightClickOnClickButton(){
        goToButtons();
        buttonsPage.rightClickOnClickButton();
        assertNoAction();
    }
   @Test
   public void actionFlow() throws InterruptedException {
       goToButtons();
       buttonsPage.doubleClickWithJS(buttonsPage.doubleClickButton);
       assertDoubleClickButtonAction();
       Thread.sleep(3000);
       buttonsPage.rightClickOnRightClickButton();
       assertRightClickButtonAction();
       Thread.sleep(3000);
       scrollIntoView(buttonsPage.buttonList.get(2));
       buttonsPage.clickOnClickMeButton();


    }
}
