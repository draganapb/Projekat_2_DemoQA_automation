package DemoQApages;

import DemoQABase.DemoQABaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage extends DemoQABaseTest {
public RegistrationPage(){
    PageFactory.initElements(driver, this);
}
     @FindBy (xpath = "/html/body/div[1]/div[1]/div/div[1]/div[2]/a")
     public WebElement registrationButton;

     @FindBy(id = "first-name")
     public WebElement FirstNameTextField;

     @FindBy(id = "last-name")
     public WebElement LastNameTextField;

     @FindBy (id = "email")
     public WebElement EmailTextField;

    public @FindBy (id = "mobile")
    WebElement MobileTextField;

    //DROPDOWN!
     @FindBy (id = "city")
    public WebElement CityTextField;

     @FindBy (id = "country")
     public WebElement DropdownList;

     @FindBy (css = "option[selected = 'selected']")
     public List<WebElement> Placeholder;


     @FindBy (id = "message")
     public WebElement MessageTextBox;

     @FindBy (id ="code")
     public WebElement CodeTextField;
    
     @FindBy (css = ".btn.btn-block.btn-primary")
            //btn btn-block btn-primary
     public WebElement SendButton;

     @FindBy (css = ".alert.alert-error")
            //
     public WebElement errorNotification;

     @FindBy (css = ".alert.alert-success")
     public WebElement confirmedNotification;
     @FindBy (className = "modal__close")
     public WebElement closePopUpButton;

    public void clickOnRegistrationButton(){
        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova.get(1));
        registrationButton.click();
    }
    public void insertFirstName(String firstName){
        FirstNameTextField.clear();
        FirstNameTextField.sendKeys("Dragana");
    }
    public void insertLastName(String lastName){
        LastNameTextField.clear();
        LastNameTextField.sendKeys("Topic");
    }
    public void insertEmail(String email){
        EmailTextField.clear();
        EmailTextField.sendKeys("draganapb@gmail.com");
    }
    public void insertMobile(int mobile){
        MobileTextField.clear();
        MobileTextField.sendKeys(String.valueOf(mobile));
    }
    public void insertCity(String city){
        CityTextField.clear();
        CityTextField.sendKeys(city);
    }
    public void insertMessage(String message){
        MessageTextBox.clear();
        MessageTextBox.sendKeys(message);
    }
    public void insertCode(String code){

        CodeTextField.sendKeys(code);
    }
    public void clickOnSendButton(){
        SendButton.click();
    }
    public void clickOnClosePopUpButton(){
        closePopUpButton.click();
    }
}