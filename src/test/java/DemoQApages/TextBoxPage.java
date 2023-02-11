package DemoQApages;

import DemoQABase.DemoQABaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TextBoxPage extends DemoQABaseTest {
    public TextBoxPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "main-header")
    public WebElement title;

    @FindBy(id = "userName")
    public WebElement fullNameTextField;

    @FindBy(id = "userEmail")
    public WebElement emailTextField;

    @FindBy(id = "currentAddress")
    public WebElement currentAddressTextField;

    @FindBy(id = "permanentAddress")
    public WebElement permanentAddressTextField;

    @FindBy(id = "submit")
    public WebElement submitButton;
    @FindBy(id = "name")
    public WebElement fullNameLabel;
    @FindBy(id = "email")
    public WebElement emailLabel;

    @FindBy(tagName = "p")
    public List<WebElement> addressResult;

    public void insertFullname(String fullName) {
        fullNameTextField.clear();
        fullNameTextField.sendKeys(fullName);
    }

    public void insertEmail(String email) {
        emailTextField.clear();
        emailTextField.sendKeys(email);
    }

    public void insertCurrentAddress(String currentAddress) {
        currentAddressTextField.clear();
        currentAddressTextField.sendKeys(currentAddress);
    }

    public void insertPermanentAddress(String permanentAddress) {
        permanentAddressTextField.clear();
        permanentAddressTextField.sendKeys(permanentAddress);
    }

    public void clickOnSubmitButton() {
        submitButton.click();
    }

    public String findParagraphCurrentAddress() {
        String currentAddress = null;
        for (int i = 0; i < addressResult.size(); i++) {
            if (addressResult.get(i).getText().contains("Current Address")) {
                currentAddress = addressResult.get(i).getText();
            }
        }
        return currentAddress;
    }

    public String findParagraphPermanentAddress() {
        String permanentAddress = null;
        for (int i = 0; i < addressResult.size(); i++) {
            if (addressResult.get(i).getText().contains("Permananet Address")) {
                permanentAddress = addressResult.get(i).getText();
            }
        }
        return permanentAddress;
    }
}
