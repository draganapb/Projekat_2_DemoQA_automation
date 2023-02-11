package DemoQApages;

import DemoQABase.DemoQABaseTest;
import org.apache.hc.core5.http.io.SessionOutputBuffer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LinksPage extends DemoQABaseTest {
    public LinksPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "main-header")
    public WebElement title;

    @FindBy(id = "simpleLink")
    public WebElement homeLink;

    @FindBy(id = "dynamicLink")
    public WebElement dynamicLink;
    @FindBy(id = "created")
    public WebElement apiLinkCreated;
    @FindBy(id = "no-content")
    public WebElement apiLinkNoContent;
    @FindBy(id = "linkResponse")
    public WebElement response;
    @FindBy (id = "moved")
    public WebElement apiLinkMoved;

    @FindBy (id = "bad-request")
    public WebElement apiLinkBadRequest;
    @FindBy (id = "unauthorized")
    public WebElement apiLinkUnauthorized;
    @FindBy (id = "forbidden")
    public WebElement apiLinkForbidden;
    @FindBy (id = "invalid-url")
    public WebElement apiLinkNotFound;

    public void clickOnHomeLink() {
        homeLink.click();
    }

    public void clickOnDynamicLink() {
        dynamicLink.click();
    }

    public void clickOnCreateLink() {
        apiLinkCreated.click();
        scrollIntoView(apiLinkNoContent);
    }

    public void clickOnNoContentLink() {
        apiLinkNoContent.click();
    }
    public void clickOnMovedLink(){
        apiLinkMoved.click();
    }
    public void clickOnBadRequestLink(){
        apiLinkBadRequest.click();
    }

    public void clickOnUnauthorized(){
        apiLinkUnauthorized.click();
    }
    public void clickOnForbidden(){
        apiLinkForbidden.click();
    }
    public void clickOnNotFound(){
        apiLinkNotFound.click();
    }
}


