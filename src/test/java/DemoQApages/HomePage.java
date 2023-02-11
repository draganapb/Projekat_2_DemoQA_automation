package DemoQApages;

import DemoQABase.DemoQABaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends DemoQABaseTest {

    public HomePage (){
        PageFactory.initElements(driver, this);
    }

     @FindBy (className = "banner-image")
     public WebElement joinNowSection;

    @FindBy (xpath = "/html/body/div[2]/header/a/img")
    public WebElement logo;

    @FindBy (className = "card-body")
    public List<WebElement>listOfCards;

    @FindBy (className = "card-up")
    public List<WebElement>listOfTitles;

    @FindBy (tagName = "header")
    public WebElement header;
    @FindBy(tagName = "footer")
    public WebElement footer;




    public void clickOnJoinNow(){
        joinNowSection.click();
    }
    public void clickOnLogo(){
        logo.click();
    }

    public void clickOnElements(){
        for(int i = 0; i<listOfCards.size(); i++){
          if( listOfCards.get(i).getText().equalsIgnoreCase("Elements")){
            listOfCards.get(i).click();
            }


        }
    }
    public void clickOnAllCards() throws InterruptedException {
        Thread.sleep(3000);
        scrollIntoView(listOfTitles.get(5));
        List<String> elementLinks = new ArrayList<>(List.of("elements", "forms", "alertsWindows", "widgets", "interaction", "books"));
        for(int i = 0; i< listOfCards.size(); i++){
            scrollIntoView(listOfCards.get(5));
            listOfTitles.get(i).click();
            Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/" + elementLinks.get(i));
            Assert.assertTrue(logo.isDisplayed());
            scrollIntoView(logo);
            clickOnLogo();
        }
    }
}
