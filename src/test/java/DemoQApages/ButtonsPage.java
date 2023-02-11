package DemoQApages;

import DemoQABase.DemoQABaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ButtonsPage extends DemoQABaseTest {

    public ButtonsPage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy (className = "main-header")
    public WebElement title;

    @FindBy (id = "doubleClickBtn")
    public WebElement doubleClickButton;

    @FindBy (id = "rightClickBtn")
    public WebElement rightClickButton;

    @FindBy (id = "PmCMP")
    public WebElement clickMeButton;

    @FindBy (id = "doubleClickMessage")
    public WebElement doubleClickMessage;

    @FindBy (id = "rightClickMessage")
    public WebElement rightClickMessage;
    @FindBy (id = "dynamicClickMessage")
    public WebElement clickMessage;
    @FindBy (css = ".btn.btn-primary")
    public List<WebElement> buttonList;
    public void clickOndoubleClickButton(){
        doubleClickButton.click();
    }

    public void clickOnRightClickButton(){
        rightClickButton.click();
    }
    public void rightClickOnRightClickButton() throws InterruptedException {
     Actions actions = new Actions(driver);
//        Thread.sleep(3000);
//        actions.contextClick(buttonsPage.rightClickButton).perform();
        for(int i = 0; i< buttonList.size(); i++){
            if(buttonList.get(i).getText().equalsIgnoreCase("Right Click Me")){
                actions.contextClick(buttonList.get(i)).perform();
            }
        }

    }
    public void rightClickOnDoubleClickButton(){
      //  Actions actions = new Actions(driver);
        //actions.contextClick(buttonsPage.rightClickButton).perform();

    }
    public void rightClickOnClickButton(){
        Actions actions = new Actions(driver);
        actions.contextClick(buttonsPage.rightClickButton).perform();
    }
    public void clickOnClickMeButton() {
        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i).getText().equalsIgnoreCase("Click Me")) {
                buttonList.get(i).click();
            }
//        }
            // clickMeButton.click();
        }
    }
    public void doubleClickWithJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick', { bubbles: true }));", element);
    }

}
