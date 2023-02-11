package DemoQAtests;

import DemoQABase.DemoQABaseTest;
import DemoQABase.ExcelReader;
import DemoQApages.ElemPage;
import DemoQApages.HomePage;
import DemoQApages.WebTablesPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class WebTablesTest extends DemoQABaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver.manage().window().maximize();
        driver.get(homeUrl);
        homePage = new HomePage();
        elemPage = new ElemPage();
        webTablesPage = new WebTablesPage();
        excelReader = new ExcelReader("src/test/java/TestData6.xlsx");
    }
    public void goToWebTables(){
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        scrollIntoView(elemPage.CardItems.get(2));
        elemPage.clickOnWebTables();
    }

    public void assertUnsuccessfullyAddedContact(){
        Assert.assertTrue(webTablesPage.registrationFormWindow.isDisplayed());
        Assert.assertTrue(webTablesPage.submitButton.isDisplayed());
        Assert.assertTrue(webTablesPage.firstNameTextField.isDisplayed());
    }
    @Test
    public void validateWebTablesLink() {
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        scrollIntoView(elemPage.CardItems.get(2));
        elemPage.clickOnWebTables();
        Assert.assertEquals(driver.getCurrentUrl(), webTableUrl);
        Assert.assertEquals(webTablesPage.title.getText(), "Web Tables");

    }
    @Test
    public void validateAddButtonClickability() throws InterruptedException {
        goToWebTables();
        webTablesPage.clickOnAddButton();
        waitForVisibility(webTablesPage.registrationFormWindow);
        Assert.assertTrue(webTablesPage.registrationFormWindow.isDisplayed());
        Assert.assertTrue(webTablesPage.submitButton.isDisplayed());
        webTablesPage.clickOnCloseButtonForm();

    }
    @Test
    public void successfullyAddedNewContact(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
            String validFirstName = excelReader.getStringData("WebTable", i, 0);
            String validLastName = excelReader.getStringData("WebTable", i, 1);
            String validEmail = excelReader.getStringData("WebTable", i, 2);
            String validAge = excelReader.getStringData("WebTable", i, 3);
            String validSalary = excelReader.getStringData("WebTable", i, 4);
            String validDepartment = excelReader.getStringData("WebTable", i, 5);

            webTablesPage.clickOnAddButton();
            webTablesPage.insertFirstname(validFirstName);
            webTablesPage.insertLastName(validLastName);
            webTablesPage.insertEmail(validEmail);
            webTablesPage.insertAge(validAge);
            webTablesPage.insertSalary(validSalary);
            webTablesPage.insertDepartment(validDepartment);
            webTablesPage.clickOnSubmitButton();
            Assert.assertTrue(webTablesPage.addButton.isDisplayed());
            Assert.assertTrue(webTablesPage.searchBox.isDisplayed());
            Assert.assertEquals(webTablesPage.title.getText(), "Web Tables");
}
    Assert.assertEquals(webTablesPage.delete.size(), 6);

    }

    @Test
    public void unsuccessfullyAddedContactWithoutFirstName(){

            goToWebTables();
            Assert.assertEquals(webTablesPage.delete.size(), 3);
            webTablesPage.clickOnAddButton();

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
            String validLastName = excelReader.getStringData("WebTable", i, 1);
            String validEmail = excelReader.getStringData("WebTable", i, 2);
            String validAge = excelReader.getStringData("WebTable", i, 3);
            String validSalary = excelReader.getStringData("WebTable", i, 4);
            String validDepartment = excelReader.getStringData("WebTable", i, 5);

            webTablesPage.firstNameTextField.clear();
            webTablesPage.insertLastName(validLastName);
            webTablesPage.insertEmail(validEmail);
            webTablesPage.insertAge(validAge);
            webTablesPage.insertSalary(validSalary);
            webTablesPage.insertDepartment(validDepartment);
            webTablesPage.clickOnSubmitButton();
            assertUnsuccessfullyAddedContact();
        }

    }
    @Test
    public void unsuccessfullyAddedContactWithoutLastName(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickOnAddButton();

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
            String validFirstName = excelReader.getStringData("WebTable", i, 0);
            String validEmail = excelReader.getStringData("WebTable", i, 2);
            String validAge = excelReader.getStringData("WebTable", i, 3);
            String validSalary = excelReader.getStringData("WebTable", i, 4);
            String validDepartment = excelReader.getStringData("WebTable", i, 5);

            webTablesPage.insertFirstname(validFirstName);
            webTablesPage.lastNameTextField.clear();
            webTablesPage.insertEmail(validEmail);
            webTablesPage.insertAge(validAge);
            webTablesPage.insertSalary(validSalary);
            webTablesPage.insertDepartment(validDepartment);
            webTablesPage.clickOnSubmitButton();
            assertUnsuccessfullyAddedContact();

        }

    }
    @Test
    public void unsuccessfullyAddedContactWithoutEmail(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickOnAddButton();

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
                String validFirstName = excelReader.getStringData("WebTable", i, 0);
                String validLastName = excelReader.getStringData("WebTable", i, 1);
                String validAge = excelReader.getStringData("WebTable", i, 3);
                String validSalary = excelReader.getStringData("WebTable", i, 4);
                String validDepartment = excelReader.getStringData("WebTable", i, 5);

                webTablesPage.insertFirstname(validFirstName);
                webTablesPage.insertLastName(validLastName);
                webTablesPage.emailTextField.clear();
                webTablesPage.insertAge(validAge);
                webTablesPage.insertSalary(validSalary);
                webTablesPage.insertDepartment(validDepartment);
                webTablesPage.clickOnSubmitButton();
                assertUnsuccessfullyAddedContact();
        }
    }
    @Test
    public void unsuccessfullyAddedContactWithoutAge(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickOnAddButton();

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
            String validFirstName = excelReader.getStringData("WebTable", i, 0);
            String validLastName = excelReader.getStringData("WebTable", i, 1);
            String validEmail = excelReader.getStringData("WebTable", i, 2);
            String validSalary = excelReader.getStringData("WebTable", i, 4);
            String validDepartment = excelReader.getStringData("WebTable", i, 5);

            webTablesPage.insertFirstname(validFirstName);
            webTablesPage.insertLastName(validLastName);
            webTablesPage.insertEmail(validEmail);
            webTablesPage.ageTextField.clear();
            webTablesPage.insertSalary(validSalary);
            webTablesPage.insertDepartment(validDepartment);
            webTablesPage.clickOnSubmitButton();
            assertUnsuccessfullyAddedContact();
        }
    }
    @Test
    public void unsuccessfullyAddedContactWithoutSalary(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickOnAddButton();

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
            String validFirstName = excelReader.getStringData("WebTable", i, 0);
            String validLastName = excelReader.getStringData("WebTable", i, 1);
            String validEmail = excelReader.getStringData("WebTable", i, 2);
            String validAge = excelReader.getStringData("WebTable", i, 3);
            String validDepartment = excelReader.getStringData("WebTable", i, 5);
            webTablesPage.insertFirstname(validFirstName);
            webTablesPage.insertLastName(validLastName);
            webTablesPage.insertEmail(validEmail);
            webTablesPage.insertAge(validAge);
            webTablesPage.salaryTextField.clear();
            webTablesPage.insertDepartment(validDepartment);
            webTablesPage.clickOnSubmitButton();
            assertUnsuccessfullyAddedContact();
        }
    }
    @Test
    public void unsuccessfullyAddedContactWithoutDepartment() {
            goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickOnAddButton();

        for (int i = 1; i <= excelReader.getLastRow("WebTable"); i++) {
            String validFirstName = excelReader.getStringData("WebTable", i, 0);
            String validLastName = excelReader.getStringData("WebTable", i, 1);
            String validEmail = excelReader.getStringData("WebTable", i, 2);
            String validAge = excelReader.getStringData("WebTable", i, 3);
            String validSalary = excelReader.getStringData("WebTable", i, 4);

            webTablesPage.insertFirstname(validFirstName);
            webTablesPage.insertLastName(validLastName);
            webTablesPage.insertEmail(validEmail);
            webTablesPage.insertAge(validAge);
            webTablesPage.insertSalary(validSalary);
            webTablesPage.departmentTextField.clear();
            webTablesPage.clickOnSubmitButton();
            assertUnsuccessfullyAddedContact();
            }
        }
    @Test
    public void unsuccessfullyAddedContactWithoutDataInput(){
        goToWebTables();
        webTablesPage.clickOnAddButton();

        Assert.assertEquals(webTablesPage.delete.size(), 3);

        webTablesPage.firstNameTextField.clear();
        webTablesPage.lastNameTextField.clear();
        webTablesPage.emailTextField.clear();
        webTablesPage.ageTextField.clear();
        webTablesPage.salaryTextField.clear();
        webTablesPage.departmentTextField.clear();
        webTablesPage.submitButton.clear();
        assertUnsuccessfullyAddedContact();
    }
    @Test
    public void unsuccessfullyAddedContactWithInvalidFirstName(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickOnAddButton();

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
            String invalidFirstName = excelReader.getStringData("WebTable", i, 6);
            String validLastName = excelReader.getStringData("WebTable", i, 1);
            String validEmail = excelReader.getStringData("WebTable", i, 2);
            String validAge = excelReader.getStringData("WebTable", i, 3);
            String validSalary = excelReader.getStringData("WebTable", i, 4);
            String validDepartment = excelReader.getStringData("WebTable", i, 5);

            webTablesPage.insertFirstname(invalidFirstName);
            webTablesPage.insertLastName(validLastName);
            webTablesPage.insertEmail(validEmail);
            webTablesPage.insertAge(validAge);
            webTablesPage.insertSalary(validSalary);
            webTablesPage.insertDepartment(validDepartment);
            webTablesPage.clickOnSubmitButton();
            assertUnsuccessfullyAddedContact();
        }
    }
    @Test
    public void unsuccessfullyAddedContactWithInvalidLastName(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickOnAddButton();

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
            String validFirstName = excelReader.getStringData("WebTable", i, 0);
            String invalidLastName = excelReader.getStringData("WebTable", i, 7);
            String validEmail = excelReader.getStringData("WebTable", i, 2);
            String validAge = excelReader.getStringData("WebTable", i, 3);
            String validSalary = excelReader.getStringData("WebTable", i, 4);
            String validDepartment = excelReader.getStringData("WebTable", i, 5);

            webTablesPage.insertFirstname(validFirstName);
            webTablesPage.insertLastName(invalidLastName);
            webTablesPage.insertEmail(validEmail);
            webTablesPage.insertAge(validAge);
            webTablesPage.insertSalary(validSalary);
            webTablesPage.insertDepartment(validDepartment);
            webTablesPage.clickOnSubmitButton();
            assertUnsuccessfullyAddedContact();
        }
    }
    @Test
    public void unsuccessfullyAddedContactWithInvalidEmail(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickOnAddButton();

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
        String validFirstName = excelReader.getStringData("WebTable", i, 0);
        String validLastName = excelReader.getStringData("WebTable", i, 1);
        String invalidEmail = excelReader.getStringData("WebTable", i, 8);
        String validAge = excelReader.getStringData("WebTable", i, 3);
        String validSalary = excelReader.getStringData("WebTable", i, 4);
        String validDepartment = excelReader.getStringData("WebTable", i, 5);

        webTablesPage.insertFirstname(validFirstName);
        webTablesPage.insertLastName(validLastName);
        webTablesPage.insertEmail(invalidEmail);
        webTablesPage.insertAge(validAge);
        webTablesPage.insertSalary(validSalary);
        webTablesPage.insertDepartment(validDepartment);
        webTablesPage.clickOnSubmitButton();
        assertUnsuccessfullyAddedContact();
    }
    }
    @Test
    public void unsuccessfullyAddedContactWithInvalidAge(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickOnAddButton();

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
            String validFirstName = excelReader.getStringData("WebTable", i, 0);
            String validLastName = excelReader.getStringData("WebTable", i, 1);
            String validEmail = excelReader.getStringData("WebTable", i, 2);
            String invalidAge = excelReader.getStringData("WebTable", i, 9);
            String validSalary = excelReader.getStringData("WebTable", i, 4);
            String validDepartment = excelReader.getStringData("WebTable", i, 5);

            webTablesPage.insertFirstname(validFirstName);
            webTablesPage.insertLastName(validLastName);
            webTablesPage.insertEmail(validEmail);
            webTablesPage.insertAge(invalidAge);
            webTablesPage.insertSalary(validSalary);
            webTablesPage.insertDepartment(validDepartment);
            webTablesPage.clickOnSubmitButton();
            assertUnsuccessfullyAddedContact();
        }
    }
    @Test
    public void unsuccessfullyAddedContactWithInvalidSalary(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickOnAddButton();

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
            String validFirstName = excelReader.getStringData("WebTable", i, 0);
            String validLastName = excelReader.getStringData("WebTable", i, 1);
            String validEmail = excelReader.getStringData("WebTable", i, 2);
            String validAge = excelReader.getStringData("WebTable", i, 3);
            String invalidSalary = excelReader.getStringData("WebTable", i, 10);
            String validDepartment = excelReader.getStringData("WebTable", i, 5);

            webTablesPage.insertFirstname(validFirstName);
            webTablesPage.insertLastName(validLastName);
            webTablesPage.insertEmail(validEmail);
            webTablesPage.insertAge(validAge);
            webTablesPage.insertSalary(invalidSalary);
            webTablesPage.insertDepartment(validDepartment);
            webTablesPage.clickOnSubmitButton();
            assertUnsuccessfullyAddedContact();
        }
    }
    @Test
    public void unsuccessfullyAddedContactWithInvalidDepartment(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickOnAddButton();

        for (int i = 1; i<=excelReader.getLastRow("WebTable"); i++) {
            String validFirstName = excelReader.getStringData("WebTable", i, 0);
            String validLastName = excelReader.getStringData("WebTable", i, 1);
            String validEmail = excelReader.getStringData("WebTable", i, 2);
            String validAge = excelReader.getStringData("WebTable", i, 3);
            String validSalary = excelReader.getStringData("WebTable", i, 4);
            String invalidDepartment = excelReader.getStringData("WebTable", i, 11);

            webTablesPage.insertFirstname(validFirstName);
            webTablesPage.insertLastName(validLastName);
            webTablesPage.insertEmail(validEmail);
            webTablesPage.insertAge(validAge);
            webTablesPage.insertSalary(validSalary);
            webTablesPage.insertDepartment(invalidDepartment);
            webTablesPage.clickOnSubmitButton();
            assertUnsuccessfullyAddedContact();
        }
    }
    @Test
    public void deleteAllContacts(){
        goToWebTables();
        Assert.assertEquals(webTablesPage.delete.size(), 3);
        webTablesPage.clickAnddeleteAllContacts();
        Assert.assertEquals(webTablesPage.delete.size(), 0);

    }
    @Test
    public void editAndFindEditedContact() throws InterruptedException {
        goToWebTables();
        webTablesPage.clickAndEditContacts();
        webTablesPage.SearchInSearchBox();
        Assert.assertNotNull(webTablesPage.OddContacts);
    }



}
