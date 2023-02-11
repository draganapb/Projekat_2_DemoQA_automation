package DemoQAtests;

import DemoQABase.DemoQABaseTest;
import DemoQABase.ExcelReader;
import DemoQApages.*;
import DemoQApages.ElemPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TextBoxTest extends DemoQABaseTest {


    @BeforeMethod
    public void pageSetUp() throws IOException, InterruptedException {
        driver.manage().window().maximize();
        driver.get(homeUrl);
        homePage = new HomePage();
        elemPage = new ElemPage();
        textBoxPage = new TextBoxPage();
        excelReader = new ExcelReader("src/test/java/TestData6.xlsx");
    }

    public void goToTextBoxForm() {
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        elemPage.clickOnTextBox();
    }
                            //assert da su svi podaci ispisani i paragrafi ne(popunjeni), broj redova
    public void assertSubmittedFormWithAllDataInput(String validFullName, String validEmail, String currentAddress, String permanentAddress) {
        int result = 4;
        String currentAddressLabel = textBoxPage.findParagraphCurrentAddress();
        String permanentAddressLabel = textBoxPage.findParagraphPermanentAddress();
        Assert.assertEquals(textBoxPage.addressResult.size(), result);
        Assert.assertEquals(textBoxPage.fullNameLabel.getText(), "Name:" + validFullName);
        Assert.assertEquals(textBoxPage.emailLabel.getText(), "Email:" + validEmail);
        Assert.assertEquals(currentAddressLabel, "Current Address :" + currentAddress);
        Assert.assertEquals(permanentAddressLabel, "Permananet Address :" + permanentAddress);
        Assert.assertEquals(driver.getCurrentUrl(), textBoxUrl);
    }

    public void assertSubmittedFormWithoutFullName(String validEmail, String currentAddress, String permanentAddress) {
        String currentAddressLabel = textBoxPage.findParagraphCurrentAddress();
        String permanentAddressLabel = textBoxPage.findParagraphPermanentAddress();
        int result = 3;

        Assert.assertEquals(textBoxPage.addressResult.size(), result);
        Assert.assertFalse(isDisplayed(textBoxPage.fullNameLabel));
        Assert.assertTrue(isDisplayed(textBoxPage.emailLabel));
        Assert.assertEquals(textBoxPage.emailLabel.getText(), "Email:" + validEmail);
        Assert.assertEquals(currentAddressLabel, "Current Address :" + currentAddress);
        Assert.assertEquals(permanentAddressLabel, "Permananet Address :" + permanentAddress);

    }

    public void assertSubmittedFormWithoutEmail(String validFullName, String currentAddress, String permanentAddress) {
        String currentAddressLabel = textBoxPage.findParagraphCurrentAddress();
        String permanentAddressLabel = textBoxPage.findParagraphPermanentAddress();

        Assert.assertEquals(textBoxPage.addressResult.size(), 3);
        Assert.assertEquals(textBoxPage.fullNameLabel.getText(), "Name:" + validFullName);
        Assert.assertFalse(isDisplayed(textBoxPage.emailLabel));
        Assert.assertEquals(currentAddressLabel, "Current Address :" + currentAddress);
        Assert.assertEquals(permanentAddressLabel, "Permananet Address :" + permanentAddress);
    }

    public void assertSubmittedFormWithoutCurrentAddress(String validFullName, String validEmail, String permanentAddress) {
        String currentAddressLabel = textBoxPage.findParagraphCurrentAddress();
        String permanentAddressLabel = textBoxPage.findParagraphPermanentAddress();
        Assert.assertEquals(textBoxPage.addressResult.size(), 3);
        Assert.assertEquals(textBoxPage.fullNameLabel.getText(), "Name:" + validFullName);
        Assert.assertEquals(textBoxPage.emailLabel.getText(), "Email:" + validEmail);
        Assert.assertNull(currentAddressLabel);
        Assert.assertEquals(permanentAddressLabel, "Permananet Address :" + permanentAddress);
    }

    public void assertSubmittedFormWithoutPermanentAddress(String validFullName, String validEmail, String currentAddress) {
        String currentAddressLabel = textBoxPage.findParagraphCurrentAddress();
        String permanentAddressLabel = textBoxPage.findParagraphPermanentAddress();
        Assert.assertEquals(textBoxPage.addressResult.size(), 3);
        Assert.assertEquals(textBoxPage.fullNameLabel.getText(), "Name:" + validFullName);
        Assert.assertEquals(textBoxPage.emailLabel.getText(), "Email:" + validEmail);
        Assert.assertEquals(currentAddressLabel, "Current Address :" + currentAddress);
        Assert.assertNull(permanentAddressLabel);
    }

    public void assertUnsuccessfullySubmittedForm(){
        Assert.assertEquals(textBoxPage.addressResult.size(), 0);
        Assert.assertEquals(driver.getCurrentUrl(), textBoxUrl);
    }


    @Test
    public void validateTextBoxLink() {
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        elemPage.clickOnTextBox();
        Assert.assertEquals(driver.getCurrentUrl(), textBoxUrl);
        Assert.assertTrue(textBoxPage.title.isDisplayed());
        System.out.println(textBoxPage.title.getText());

    }

    @Test
    public void successfullySubmittedFormWithValidData() throws InterruptedException {

        goToTextBoxForm();
        for (int i = 1; i <= excelReader.getLastRow("TextBox"); i++) {
            String validFullName = excelReader.getStringData("TextBox", i, 0);
            String validEmail = excelReader.getStringData("TextBox", i, 1);
            String currentAddress = excelReader.getStringData("TextBox", i, 2);
            String permanentAddress = excelReader.getStringData("TextBox", i, 3);

            textBoxPage.insertFullname(validFullName);
            textBoxPage.insertEmail(validEmail);
            textBoxPage.insertCurrentAddress(currentAddress);
            textBoxPage.insertPermanentAddress(permanentAddress);
            scrollIntoView(textBoxPage.submitButton);
            textBoxPage.clickOnSubmitButton();
            assertSubmittedFormWithAllDataInput(validFullName, validEmail, currentAddress, permanentAddress);

        }
    }

    @Test
    public void successfullySubmittedFormWithoutFullName() throws InterruptedException {
        goToTextBoxForm();
        for (int i = 1; i <= excelReader.getLastRow("TextBox"); i++) {
            String validEmail = excelReader.getStringData("TextBox", i, 1);
            String currentAddress = excelReader.getStringData("TextBox", i, 2);
            String permanentAddress = excelReader.getStringData("TextBox", i, 3);

            textBoxPage.fullNameTextField.clear();
            textBoxPage.insertEmail(validEmail);
            textBoxPage.insertCurrentAddress(currentAddress);
            textBoxPage.insertPermanentAddress(permanentAddress);
            scrollIntoView(textBoxPage.submitButton);
            textBoxPage.clickOnSubmitButton();
            assertSubmittedFormWithoutFullName(validEmail, currentAddress, permanentAddress);

        }
    }

    @Test
    public void successfullySubmittedFormWithoutEmail() throws InterruptedException {
        goToTextBoxForm();
        for (int i = 1; i <= excelReader.getLastRow("TextBox"); i++) {
            String validFullName = excelReader.getStringData("TextBox", i, 0);
            String currentAddress = excelReader.getStringData("TextBox", i, 2);
            String permanentAddress = excelReader.getStringData("TextBox", i, 3);

            textBoxPage.insertFullname(validFullName);
            textBoxPage.emailTextField.clear();
            textBoxPage.insertCurrentAddress(currentAddress);
            textBoxPage.insertPermanentAddress(permanentAddress);
            scrollIntoView(textBoxPage.submitButton);
            textBoxPage.clickOnSubmitButton();
            assertSubmittedFormWithoutEmail(validFullName, currentAddress, permanentAddress);

        }
    }

    @Test
    public void successfullySubmittedFormWithoutCurrentAddress() throws InterruptedException {
        goToTextBoxForm();
        for (int i = 1; i <= excelReader.getLastRow("TextBox"); i++) {
            String validFullName = excelReader.getStringData("TextBox", i, 0);
            String validEmail = excelReader.getStringData("TextBox", i, 1); //"ff@gmail.com";
            String permanentAddress = excelReader.getStringData("TextBox", i, 3);
            textBoxPage.insertFullname(validFullName);
            textBoxPage.insertEmail(validEmail);
            textBoxPage.currentAddressTextField.clear();
            textBoxPage.insertPermanentAddress(permanentAddress);
            scrollIntoView(textBoxPage.submitButton);
            textBoxPage.clickOnSubmitButton();
            assertSubmittedFormWithoutCurrentAddress(validFullName, validEmail, permanentAddress);

        }
    }

    @Test
    public void successfullySubmittedFormWithoutPermanentAddress() throws InterruptedException {
        goToTextBoxForm();
        for (int i = 1; i <= excelReader.getLastRow("TextBox"); i++) {
            String validFullName = excelReader.getStringData("TextBox", i, 0);
            String validEmail = excelReader.getStringData("TextBox", i, 1);
            String currentAddress = excelReader.getStringData("TextBox", i, 2);

            textBoxPage.insertFullname(validFullName);
            textBoxPage.insertEmail(validEmail);
            textBoxPage.insertCurrentAddress(currentAddress);
            textBoxPage.permanentAddressTextField.clear();
            scrollIntoView(textBoxPage.submitButton);
            textBoxPage.clickOnSubmitButton();
            assertSubmittedFormWithoutPermanentAddress(validFullName, validEmail, currentAddress);

        }
    }

    @Test
    public void unsuccessfullySubmittedFormWithInvalidFullName() {
        goToTextBoxForm();
        for (int i = 1; i <= excelReader.getLastRow("TextBox"); i++) {

            String invalidFullName = excelReader.getStringData("TextBox", i, 6);
            String validEmail = excelReader.getStringData("TextBox", i, 1);
            String currentAddress = excelReader.getStringData("TextBox", i, 2);
            String permanentAddress = excelReader.getStringData("TextBox", i, 3);

            textBoxPage.insertFullname(invalidFullName);
            textBoxPage.insertEmail(validEmail);
            textBoxPage.insertCurrentAddress(currentAddress);
            textBoxPage.insertPermanentAddress(permanentAddress);
            scrollIntoView(textBoxPage.submitButton);
            textBoxPage.clickOnSubmitButton();
            assertUnsuccessfullySubmittedForm();

        }
    }

    @Test
    public void unsuccessfullySubmittedFormWithInvalidEmail() {
        goToTextBoxForm();
        for (int i = 1; i <= excelReader.getLastRow("TextBox"); i++) {
            String validFullName = excelReader.getStringData("TextBox", i, 0);
            String invalidEmail = excelReader.getStringData("TextBox", i, 7);
            String currentAddress = excelReader.getStringData("TextBox", i, 2);
            String permanentAddress = excelReader.getStringData("TextBox", i, 3);

            textBoxPage.insertFullname(validFullName);
            textBoxPage.insertEmail(invalidEmail);
            textBoxPage.insertCurrentAddress(currentAddress);
            textBoxPage.insertPermanentAddress(permanentAddress);
            scrollIntoView(textBoxPage.submitButton);
            textBoxPage.clickOnSubmitButton();
            assertUnsuccessfullySubmittedForm();
        }
    }
    @Test
    public void unsuccessfullySubmittedFormWithNoDataInput(){
        goToTextBoxForm();
        textBoxPage.fullNameTextField.clear();
        textBoxPage.emailTextField.clear();
        textBoxPage.currentAddressTextField.clear();
        textBoxPage.permanentAddressTextField.clear();
        scrollIntoView(textBoxPage.submitButton);
        textBoxPage.clickOnSubmitButton();
        assertUnsuccessfullySubmittedForm();

    }
}