package DemoQApages;

import DemoQABase.DemoQABaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class WebTablesPage extends DemoQABaseTest {
    public WebTablesPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "main-header")
    public WebElement title;

    @FindBy(id = "addNewRecordButton")
    public WebElement addButton;

    @FindBy(className = "modal-content")
    public WebElement registrationFormWindow;

    @FindBy(id = "submit")
    public WebElement submitButton;


    @FindBy(className = "close")
    public WebElement closeFormButton;

    @FindBy(id = "searchBox")
    public WebElement searchBox;
    @FindBy(id = "firstName")
    public WebElement firstNameTextField;
    @FindBy(id = "lastName")
    public WebElement lastNameTextField;
    @FindBy(id = "userEmail")
    public WebElement emailTextField;
    @FindBy(id = "age")
    public WebElement ageTextField;
    @FindBy(id = "salary")
    public WebElement salaryTextField;
    @FindBy(id = "department")
    public WebElement departmentTextField;

    @FindBy(css = ".rt-tr.-odd")
    public List<WebElement> OddContacts;

    @FindBy(css = ".rt-tr.-even")
    public WebElement EvenContacts;

    @FindBy(className = "rt-tr-group")
    public List<WebElement> ListOfContacts;
    @FindBy(css = "span[title = 'Delete']")
    public List<WebElement> delete;
    @FindBy(css = "span[title = 'Edit']")
    public List<WebElement> edit;

    @FindBy(className = "rt-td")
    public List<WebElement> dataCellsList;

    public void clickOnAddButton() {
        addButton.click();
    }

    public void clickOnSubmitButton() {
        submitButton.click();
    }


    public void clickOnCloseButtonForm() {
        closeFormButton.click();
    }

    public void SearchInSearchBox() {
        searchBox.clear();
        searchBox.sendKeys("Test");
    }

    public void insertFirstname(String firstName) {
        firstNameTextField.clear();
        firstNameTextField.sendKeys(firstName);
    }

    public void insertLastName(String lastName) {
        lastNameTextField.clear();
        lastNameTextField.sendKeys(lastName);
    }

    public void insertEmail(String email) {
        emailTextField.clear();
        emailTextField.sendKeys(email);
    }

    public void insertAge(String age) {
        ageTextField.clear();
        ageTextField.sendKeys((String.valueOf(age)));
    }

    public void insertSalary(String salary) {
        salaryTextField.clear();
        salaryTextField.sendKeys((String.valueOf(salary)));
    }

    public void insertDepartment(String department) {
        departmentTextField.clear();
        departmentTextField.sendKeys(department);
    }

    public int countContacts() {
        int brojac = 0;
        for (int i = 0; i < delete.size(); i++) {
            brojac = brojac + 1;
            System.out.println(brojac);

        }
        return brojac;
    }


    public void clickAnddeleteAllContacts(){
        for(int i =delete.size()-1;i>=0; i--){
          delete.get(i).click();
        }
    }
    public void clickAndEditContacts() throws InterruptedException {


            edit.get(0).click();
            firstNameTextField.clear();
            firstNameTextField.sendKeys("Test");
            Thread.sleep(3000);
            submitButton.click();
            Thread.sleep(3000);




    }


}





