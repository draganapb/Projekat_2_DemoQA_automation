package DemoQApages;

import DemoQABase.DemoQABaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrokenLinksImagesPage extends DemoQABaseTest {
    public BrokenLinksImagesPage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "main-header")
    public WebElement title;
    @FindBy (css = "img[src = '/images/Toolsqa.jpg']")
    public WebElement validImage;
    @FindBy (css = "img[src = '/images/Toolsqa_1.jpg']")
    public WebElement invalidImage;
    @FindBy (linkText = "Click Here for Valid Link")
    public WebElement validLink;
    @FindBy (linkText = "Click Here for Broken Link")
    public WebElement invalidLink;
    public void clickOnValidLink(){
        validLink.click();
    }
    public void clickOnInvalidLink(){
        invalidLink.click();
    }
}
