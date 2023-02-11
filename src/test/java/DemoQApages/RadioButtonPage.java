package DemoQApages;

import DemoQABase.DemoQABaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RadioButtonPage extends DemoQABaseTest {
    public RadioButtonPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "main-header")
    public WebElement title;

    @FindBy(className = "custom-control-label")
    public List<WebElement>ListOfRadioButtons;

    @FindBy (className = "mt-3")
    public WebElement notification;
    @FindBy(id = "yesRadio")
    public WebElement yesRadioButton;
    @FindBy (id = "impressiveRadio")
    public WebElement impressiveRadioButton;
    @FindBy (id = "noRadio")
    public WebElement noRadioButton;


    public void clickOnYesRadioButton() {
        for(int i = 0; i< ListOfRadioButtons.size(); i++){
           if( ListOfRadioButtons.get(i).getText().equalsIgnoreCase("Yes")){
               ListOfRadioButtons.get(i).click();
           }
        }
    }
    public void clickOnImpressiveButton(){
        for(int i = 0; i< ListOfRadioButtons.size(); i++){
            if( ListOfRadioButtons.get(i).getText().equalsIgnoreCase("Impressive")){
                ListOfRadioButtons.get(i).click();
            }
        }
    }
    public void clickOnNoRadioButton(){
        for(int i = 0; i< ListOfRadioButtons.size(); i++){
            if( ListOfRadioButtons.get(i).getText().equalsIgnoreCase("No")){
                ListOfRadioButtons.get(i).click();
            }
        }
    }
}
