package DemoQAtests;

import DemoQABase.DemoQABaseTest;
import DemoQABase.ExcelReader;
import DemoQApages.ElemPage;
import DemoQApages.HomePage;
import DemoQApages.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;


public class HomeTest extends DemoQABaseTest {
    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver.manage().window().maximize();
        driver.get(homeUrl);
        homePage = new HomePage();
        registrationPage = new RegistrationPage();
        elemPage = new ElemPage();
        excelReader = new ExcelReader("src/test/java/TestData6.xlsx");
    }


    @Test
    public void logoClickability(){
        homePage.clickOnLogo();
        Assert.assertEquals(homeUrl, driver.getCurrentUrl());
        Assert.assertTrue(homePage.logo.isDisplayed());
        Assert.assertNotNull(homePage.header.findElement(By.tagName("a")));
        Assert.assertTrue(homePage.header.findElement(By.tagName("a")).getAttribute("href").equalsIgnoreCase(homeUrl));
        //  ispitujemo postoji li link gde je logo i da li je jednak URL page-u
       // "https://demoqa.com/"
    }

    @Test
    public void joinNowSectionClickability() throws InterruptedException {
        homePage.clickOnJoinNow();
        switchToOtherTab();
        Assert.assertEquals(registrationUrl, driver.getCurrentUrl());
        Assert.assertTrue(registrationPage.registrationButton.isDisplayed());
    }

    @Test
    public void validateAllCardsClickability() throws InterruptedException {

        homePage.clickOnAllCards();
    }
    @Test
    public void validateFooterSection(){
        Assert.assertTrue(isDisplayed(homePage.footer));
        Assert.assertEquals(homePage.footer.getText(), "© 2013-2020 TOOLSQA.COM | ALL RIGHTS RESERVED.");
    }

}
