package DemoQApages;

import DemoQABase.DemoQABaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckBoxPage extends DemoQABaseTest {
    public CheckBoxPage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy (className = "main-header")
    public WebElement title;

    @FindBy (css = ".rct-collapse.rct-collapse-btn")
    public List<WebElement> ListOfCollapseExpendButtons;
    @FindBy (css = ".rct-icon.rct-icon-expand-open")
    public List<WebElement>ListoFOpened;

    @FindBy (css = ".rct-icon.rct-icon-expand-close")
    public List<WebElement>ListOfClosed;

    @FindBy (className = "rct-checkbox")
    public List<WebElement>checkboxes;

    @FindBy (className = "rct-title")
    public List<WebElement>titlesOfCheckBoxes;

    @FindBy (className = "rct-title")
    public WebElement listItemTitle;
    @FindBy (css = ".rct-icon.rct-icon-expand-all")
    public WebElement expandAllButton;

    @FindBy (css = ".rct-icon.rct-icon-collapse-all")
    public WebElement collapseAllButton;

    @FindBy (className = "rct-checkbox")
    public List<WebElement>ListOfCheckboxes;

    @FindBy (className = "rct-checkbox")
    public WebElement checkboxButton;

    @FindBy (css = ".rct-icon.rct-icon-uncheck")
    public List<WebElement>ListOfUnchecked;

    @FindBy (css = ".rct-icon.rct-icon-check")
    public List<WebElement>ListOfChecked;

    @FindBy (css = ".rct-node.rct-node-parent.rct-node-expanded")
    public List<WebElement>ListOfParents;

    @FindBy (css = ".rct-node.rct-node-leaf")
    public List<WebElement>ListOfChilds;

    public void clickOnCollapseExpendButton(){

    for(int i = 0; i< ListOfCollapseExpendButtons.size(); i++)   {
        ListOfCollapseExpendButtons.get(i).click();
    }
    }
    public void clickOnCollapseExpendButtonOnly(){
        ListOfCollapseExpendButtons.get(0).click();
    }
    public void clickOnClosedFolders(){
        for(int i = 0; i<ListOfClosed.size(); i++){
            ListOfClosed.get(i).click();
        }
    }
   public void checkboxList(){
        for(int i = 0; i<checkboxes.size(); i++){
            checkboxes.get(0).click();
        }
   }
   public void clickOnExpandAllButton(){
        expandAllButton.click();
   }
   public void clickOnCollapseAllButton(){
        collapseAllButton.click();
   }

   public WebElement findCheckboxButton(){
        return checkboxButton;
   }
   public WebElement findListItemTitle(){
        return listItemTitle;
   }
//    public void expandAllFoldersOneByOne(){
//        for(int i = 0; i< ListOfCollapseExpendButtons.size(); i++)   {
//            ListOfCollapseExpendButtons.get(i).click();
//            js.executeScript("window.scrollBy(0,50)");
//        }
//    }
}
