package DemoQAtests;

import DemoQABase.DemoQABaseTest;
import DemoQABase.ExcelReader;
import DemoQApages.BrokenLinksImagesPage;
import DemoQApages.ElemPage;
import DemoQApages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class BrokenLinksImagesTests extends DemoQABaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver.manage().window().maximize();
        driver.get(homeUrl);
        homePage = new HomePage();
        elemPage = new ElemPage();
        brokenLinksImagesPage = new BrokenLinksImagesPage();
        excelReader = new ExcelReader("src/test/java/TestData6.xlsx");
    }
    public void goToBrokenLinksImages(){
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        scrollIntoView(elemPage.CardItems.get(2));
        elemPage.clickOnBrokenLinksImagesLink();
    }
    @Test
    public void validateBrokenLinksImagesLink(){
        goToBrokenLinksImages();
        Assert.assertEquals(brokenLinksImagesPage.title.getText(), "Broken Links - Images");
        Assert.assertEquals(driver.getCurrentUrl(), brokenLinksImagesUrl);
    }
    @Test
    public void validImageIsDisplayed(){
        goToBrokenLinksImages();
        Assert.assertTrue(isDisplayed(brokenLinksImagesPage.validImage), "Valid image is not present");
        Assert.assertEquals(driver.getCurrentUrl(), brokenLinksImagesUrl);
    }
    @Test
    public void invalidImageIsDisplayed(){
        goToBrokenLinksImages();
        Assert.assertTrue(isDisplayed(brokenLinksImagesPage.invalidImage), "Invalid image is not present");
        Assert.assertEquals(driver.getCurrentUrl(), brokenLinksImagesUrl);
    }

    @Test
    public void validateValidLink(){
        goToBrokenLinksImages();
        scrollIntoView(brokenLinksImagesPage.validLink);
        brokenLinksImagesPage.clickOnValidLink();
        Assert.assertEquals(driver.getCurrentUrl(), homeUrl);
        Assert.assertTrue(isDisplayed(homePage.joinNowSection));
    }

    @Test
    public void validateInvalidLinkDoesntWork(){
        goToBrokenLinksImages();
        scrollIntoView(brokenLinksImagesPage.invalidLink);
        brokenLinksImagesPage.clickOnInvalidLink();
        Assert.assertEquals(driver.getCurrentUrl(), "http://the-internet.herokuapp.com/status_codes/500");
        Assert.assertNotEquals(driver.getCurrentUrl(), homeUrl);

    }

}
