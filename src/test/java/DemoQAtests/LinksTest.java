package DemoQAtests;

import DemoQABase.DemoQABaseTest;
import DemoQABase.ExcelReader;
import DemoQApages.ElemPage;
import DemoQApages.HomePage;
import DemoQApages.LinksPage;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;


public class LinksTest extends DemoQABaseTest {
    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver.manage().window().maximize();
        driver.get(homeUrl);
        homePage = new HomePage();
        elemPage = new ElemPage();
        linksPage = new LinksPage();
        excelReader = new ExcelReader("src/test/java/TestData6.xlsx");

    }
    public void goToLinks(){
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        scrollIntoView(elemPage.CardItems.get(2));
        elemPage.clickOnLinks();
    }
    public void assertOneTabIsOpen(){
        assert driver.getWindowHandles().size() == 1;
    }
    public void assertTwoTabsAreOpened(){
        assert driver.getWindowHandles().size() == 2;
        Assert.assertEquals(driver.getCurrentUrl(), homeUrl);
        driver.switchTo().window(driver.getWindowHandle());
    }
    public void assertThreeTabsAreOpened(){
        assert driver.getWindowHandles().size() == 3;
        Assert.assertEquals(driver.getCurrentUrl(), linksUrl);
        driver.switchTo().window(driver.getWindowHandle());
    }
    @Test
    public void validateLinksLink(){
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        scrollIntoView(elemPage.CardItems.get(2));
        elemPage.clickOnLinks();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/links");
        Assert.assertEquals(linksPage.title.getText(), "Links");

    }
    @Test
    public void validateHomeLinkOpensNewTab(){
        goToLinks();
        assertOneTabIsOpen();
        linksPage.clickOnHomeLink();
        switchToOtherTab();
        assertTwoTabsAreOpened();
    }

    @Test
    public void validateDynamicLinkOpensNewTab(){
        goToLinks();
        assertOneTabIsOpen();
        linksPage.clickOnDynamicLink();
        switchToOtherTab();
        assertTwoTabsAreOpened();
    }

    @Test
    public void validateBothLinksOpenNewTab() throws InterruptedException {
        goToLinks();
        assertOneTabIsOpen();
        linksPage.clickOnHomeLink();
        switchToOtherTab();
        assertTwoTabsAreOpened();
        linksPage.goToPreviousTab();
        linksPage.clickOnDynamicLink();
        assertThreeTabsAreOpened();
    }
    @Test
    public void apiCallIsSentForCreated(){
        goToLinks();
        linksPage.clickOnCreateLink();
        Assert.assertEquals(linksPage.response.getText(), "Link has responded with staus 201 and status text Created");
        Assert.assertEquals(driver.getCurrentUrl(), linksUrl);
    }
    @Test
    public void apiCallIsSentForNoContent(){
        goToLinks();
        linksPage.clickOnNoContentLink();
        Assert.assertEquals(linksPage.response.getText(), "Link has responded with staus 204 and status text No Content");
        Assert.assertEquals(driver.getCurrentUrl(), linksUrl);
    }
    @Test
    public void apiCallIsSentForMoved(){
        goToLinks();
        linksPage.clickOnMovedLink();
        Assert.assertEquals(linksPage.response.getText(), "Link has responded with staus 301 and status text Moved Permanently");
        Assert.assertEquals(driver.getCurrentUrl(), linksUrl);
        System.out.println(linksPage.response.getText());
    }
    @Test
    public void apiCallIsSentForBadRequest(){
        goToLinks();
        linksPage.clickOnBadRequestLink();
        Assert.assertEquals(linksPage.response.getText(), "Link has responded with staus 400 and status text Bad Request");
        Assert.assertEquals(driver.getCurrentUrl(), linksUrl);
        System.out.println(linksPage.response.getText());
    }
    @Test
    public void apiCallIsSentForUnauthorized() throws InterruptedException {
        goToLinks();
        scrollIntoView(linksPage.apiLinkUnauthorized);
        linksPage.clickOnUnauthorized();
        Thread.sleep(3000);
        Assert.assertEquals(linksPage.response.getText(), "Link has responded with staus 401 and status text Unauthorized");
        Assert.assertEquals(driver.getCurrentUrl(), linksUrl);
        System.out.println(linksPage.response.getText());
    }
    @Test
    public void apiCallIsSentForForbidden() throws InterruptedException {
        goToLinks();
        scrollIntoView(linksPage.apiLinkForbidden);
        linksPage.clickOnForbidden();
        Thread.sleep(3000);
        Assert.assertEquals(linksPage.response.getText(), "Link has responded with staus 403 and status text Forbidden");
        Assert.assertEquals(driver.getCurrentUrl(), linksUrl);
        System.out.println(linksPage.response.getText());
    }
    @Test
    public void apiCallIsSentForNotFound() throws InterruptedException {
        goToLinks();
        scrollIntoView(linksPage.apiLinkForbidden);
        linksPage.clickOnNotFound();
        Thread.sleep(3000);
        Assert.assertEquals(linksPage.response.getText(), "Link has responded with staus 404 and status text Not Found");
        Assert.assertEquals(driver.getCurrentUrl(), linksUrl);
        System.out.println(linksPage.response.getText());
    }
}
