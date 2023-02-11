package DemoQABase;
import DemoQApages.*;
import com.github.dockerjava.api.model.Links;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class DemoQABaseTest {

public  static WebDriver driver;
public WebDriverWait wdwait;
public JavascriptExecutor js;
public ExcelReader excelReader;

public RegistrationPage registrationPage;
public HomePage homePage;
public ElemPage elemPage;
public TextBoxPage textBoxPage;
public CheckBoxPage checkBoxPage;
public RadioButtonPage radioButtonPage;
public WebTablesPage webTablesPage;
public ButtonsPage buttonsPage;
public LinksPage linksPage;
public BrokenLinksImagesPage brokenLinksImagesPage;
public String homeUrl;
public String registrationUrl;
public String registrationFormUrl;
public String elementsUrl;
public String textBoxUrl;
public String checkBoxUrl;
public String radioButtonUrl;
public String webTableUrl;
public String buttonsURL;
public String linksUrl;
public String brokenLinksImagesUrl;
@BeforeClass
    public void setUp() throws IOException {

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    wdwait =new WebDriverWait(driver, Duration.ofSeconds(10));
    js = (JavascriptExecutor) driver;
    Actions actions = new Actions(driver);


    excelReader = new ExcelReader("src/test/java/TestData6.xlsx");
    homeUrl = excelReader.getStringData("URL", 1, 0);
    registrationUrl = excelReader.getStringData("URL", 1, 1);
    registrationFormUrl = excelReader.getStringData("URL", 1, 2);
    elementsUrl = excelReader.getStringData("URL", 1, 3);
    textBoxUrl = excelReader.getStringData("URL", 1, 4);
    checkBoxUrl = excelReader.getStringData("URL",1, 5);
    radioButtonUrl = excelReader.getStringData("URL", 1, 6);
    webTableUrl = excelReader.getStringData("URL", 1, 7);
    buttonsURL = excelReader.getStringData("URL", 1, 8);
    linksUrl = excelReader.getStringData("URL", 1, 9);
    brokenLinksImagesUrl = excelReader.getStringData("URL", 1, 10);



}

    public boolean isDisplayed(WebElement element) {
        boolean webelement = false;
        try {
            webelement = element.isDisplayed();
        } catch (Exception e) {

        }
        return webelement;
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForVisibility(WebElement element) {
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickability(WebElement element) {
        wdwait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void selectByText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void switchToOtherTab(){
        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova.get(1));
    }
    public void goToPreviousTab(){
        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova.get(0));
    }
}

