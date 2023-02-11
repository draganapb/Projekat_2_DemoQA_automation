package DemoQAtests;

import DemoQABase.DemoQABaseTest;
import DemoQABase.ExcelReader;
import DemoQApages.CheckBoxPage;
import DemoQApages.ElemPage;
import DemoQApages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckBoxTest extends DemoQABaseTest {
    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver.manage().window().maximize();
        driver.get(homeUrl);
        homePage = new HomePage();
        elemPage = new ElemPage();
        checkBoxPage = new CheckBoxPage();
        excelReader = new ExcelReader("src/test/java/TestData6.xlsx");

    }
    public void goToCheckBox() {
        scrollIntoView(homePage.listOfCards.get(0));
        homePage.clickOnElements();
        elemPage.clickOnCheckBox();
    }
    public void expandAllFoldersOneByOne(){
        for(int i = 0; i< checkBoxPage.ListOfCollapseExpendButtons.size(); i++)   {
            checkBoxPage.ListOfCollapseExpendButtons.get(i).click();
            js.executeScript("window.scrollBy(0,50)");
        }
    }
    public void collapseAllFoldersOneByOne(){
        for(int i = 0; i<checkBoxPage.ListoFOpened.size(); i++){
            checkBoxPage.ListoFOpened.get(i).click();
            js.executeScript("window.scrollBy(50,0)");
        }
    }

    @Test
    public void checkBoxLink() throws InterruptedException {
        goToCheckBox();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), checkBoxUrl);
        Assert.assertEquals(checkBoxPage.title.getText(), "Check Box");
    }
    @Test
    public void expandAndCollapseFoldersOneByOne() throws InterruptedException {
        goToCheckBox();
        Thread.sleep(3000);
        js.executeScript("window, 80");
        expandAllFoldersOneByOne();
        Assert.assertEquals(checkBoxPage.ListOfCollapseExpendButtons.size(), checkBoxPage.ListoFOpened.size());
        collapseAllFoldersOneByOne();
        Assert.assertEquals(checkBoxPage.ListOfCollapseExpendButtons.size(), checkBoxPage.ListOfClosed.size());
    }
    @Test
    public void expandAllDirectly(){
        goToCheckBox();
        checkBoxPage.clickOnExpandAllButton();
        Assert.assertEquals(checkBoxPage.ListOfCollapseExpendButtons.size(), checkBoxPage.ListoFOpened.size());
        Assert.assertEquals(checkBoxPage.ListOfClosed.size(), 0);

    }
    @Test
    public void collapseAllDirectly(){   //asser
        goToCheckBox();
        checkBoxPage.clickOnExpandAllButton();
        Assert.assertEquals(checkBoxPage.ListOfCollapseExpendButtons.size(), checkBoxPage.ListoFOpened.size());
        Assert.assertEquals(checkBoxPage.ListOfClosed.size(), 0);
        checkBoxPage.clickOnCollapseAllButton();
        Assert.assertEquals(checkBoxPage.ListOfCollapseExpendButtons.size(), 1);

    }
    @Test
    public void checkAllCheckedBoxesDirectly() throws InterruptedException {
    goToCheckBox();
    checkBoxPage.ListOfUnchecked.get(0).click();
    checkBoxPage.clickOnExpandAllButton();
    js.executeScript("window.scrollBy(0,100)");
    Thread.sleep(4000);
    Assert.assertEquals(checkBoxPage.ListOfChecked.size(), checkBoxPage.ListOfCheckboxes.size());

    }
    @Test
    public void uncheckAllCheckedBoxesDirectly() throws InterruptedException {
        goToCheckBox();
        checkBoxPage.ListOfUnchecked.get(0).click();
        checkBoxPage.clickOnExpandAllButton();
        js.executeScript("window.scrollBy(0,100)");
        Thread.sleep(4000);
        Assert.assertEquals(checkBoxPage.ListOfChecked.size(), checkBoxPage.ListOfCheckboxes.size());
        scrollIntoView(checkBoxPage.ListOfCollapseExpendButtons.get(0));
        checkBoxPage.ListOfChecked.get(0).click();
        Assert.assertEquals(checkBoxPage.ListOfUnchecked.size(), checkBoxPage.ListOfCheckboxes.size());

    }
    @Test
    public void check3ClosedFoldersListed(){
        goToCheckBox();
        checkBoxPage.clickOnCollapseExpendButtonOnly();
        Assert.assertEquals(checkBoxPage.ListOfClosed.size(), 3);

    }
    @Test
    public void check2ClosedFoldersListed(){
        goToCheckBox();
        checkBoxPage.clickOnCollapseExpendButtonOnly();
        checkBoxPage.ListOfClosed.get(0).click();
        Assert.assertEquals(checkBoxPage.ListOfClosed.size(), 2);

    }
    @Test
    public void check1ClosedFolderListed(){
        goToCheckBox();
        checkBoxPage.clickOnCollapseExpendButtonOnly();
        checkBoxPage.ListOfClosed.get(0).click();
        checkBoxPage.ListOfClosed.get(1).click();
        Assert.assertEquals(checkBoxPage.ListOfClosed.size(), 1);

    }


    @Test
    public void TestCheckParentChildRelation() throws InterruptedException {
        goToCheckBox();
        List<String> folders = new ArrayList<>(List.of("Desktop", "Documents", "Office", "Downloads", "WorkSpace")); // lista roditelja kroz koju se mora proci
        for(int i = 0; i< checkBoxPage.ListOfCollapseExpendButtons.size(); i++)   {
            checkBoxPage.ListOfCollapseExpendButtons.get(i).click();
            js.executeScript("window.scrollBy(0,50)");
        }
        List<WebElement> itemList = checkBoxPage.ListOfParents; // pravimo listu svih roditelja , ima ih 6
        for(int j=0; j<folders.size(); j++){                     //prolazimo kroz listu imena foldera tj roditelja koje treba izlistati
            String folder = folders.get(j);
            int totalCheckedElements = 0;
            for (int i = 0; i<itemList.size(); i++) {           // za svako ime roditelja iz gornje for petlje, prolazimo kroz web element iz liste
                if(itemList.get(i).findElement(By.className("rct-title")).getText().equalsIgnoreCase(folder)) { // ako web element ima subelement
                                                                             // sa klasom rct-title koji je jednak imenu foldera, onda sledi kod
                    WebElement checkboxButton = itemList.get(i).findElement(By.className("rct-checkbox")); // izvlacimo checkbox element za takav pronadjen roditelj
                    checkboxButton.click();                                               // kliknemo tj selektujemo checkbox ispred takvog roditelja

                    List<WebElement> subparentElements = itemList.get(i).findElements(By.cssSelector(".rct-node.rct-node-parent.rct-node-expanded")); //za takav izdvojen roditelj element, trazimo svu njegovu decu koji imaju osobinu roditelja tj trazimo subfoldere
                    List<WebElement> childElements = itemList.get(i).findElements(By.cssSelector(".rct-node.rct-node-leaf")); // za takav izdvojen roditelj trazimo krajnju decu, tj fajlove
                    List<WebElement> checkedElements = itemList.get(i).findElements(By.cssSelector(".rct-icon.rct-icon-check")); // za tkaav izdvojen roditelj, trazimo checkirane elemente
                    int numberOfChilds = childElements.size();
                    int numberOfCheckedElements = checkedElements.size();
                    int numberofSubparent = subparentElements.size();
                    totalCheckedElements = numberOfChilds + numberofSubparent + 1; //broj dece(fajlova), broj dece(foldera), sam element koji se obradjuje
                    Assert.assertEquals(totalCheckedElements, numberOfCheckedElements); //asert na kraju mora da bude da je broj checkiranih jednak broju dece {fajlova) + broj dece koji su roditelji(subfoldera) + sam parent koji je checkiran tokom ovog if uslova
                    Thread.sleep(3000);
                    checkboxButton.click(); // uncheckiramo tog roditelja, kako bi sledeca iteracija mogla poceti tako nema ni jedan roditelj selektovan
                }
            }
        }
    }
}


