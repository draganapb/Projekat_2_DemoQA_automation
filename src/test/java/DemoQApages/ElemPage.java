package DemoQApages;

import DemoQABase.DemoQABaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ElemPage extends DemoQABaseTest {
  public ElemPage(){
      PageFactory.initElements(driver, this);
   }
    @FindBy (className = "main-header")
    public WebElement title;

    @FindBy (className = "text")
    public List<WebElement> CardItems;

    public void clickOnTextBox(){
        for(int i = 0; i<CardItems.size(); i++){
            if(CardItems.get(i).getText().equalsIgnoreCase("Text Box")){
                CardItems.get(i).click();
            }
        }
    }
    public void clickOnCheckBox(){
        for(int i = 0; i<CardItems.size(); i++){
            if(CardItems.get(i).getText().equalsIgnoreCase("Check Box")){
                CardItems.get(i).click();
            }
        }
    }
    public void clickOnRadioButton(){
        for(int i = 0; i<CardItems.size(); i++){
            if(CardItems.get(i).getText().equalsIgnoreCase("Radio Button")){
                CardItems.get(i).click();
            }
        }
    }
    public void clickOnWebTables(){
        for(int i = 0; i<CardItems.size(); i++){
            if(CardItems.get(i).getText().equalsIgnoreCase("Web Tables")){
                CardItems.get(i).click();
            }
        }
    }
    public void clickOnButtons(){
        for(int i = 0; i<CardItems.size(); i++){
            if(CardItems.get(i).getText().equalsIgnoreCase("Buttons")){
                CardItems.get(i).click();
            }
        }
    }
    public void clickOnLinks(){
        for(int i = 0; i<CardItems.size(); i++){
            if(CardItems.get(i).getText().equalsIgnoreCase("Links")){
                CardItems.get(i).click();
            }
        }
    }
    public void clickOnBrokenLinksImagesLink(){
        for(int i = 0; i<CardItems.size(); i++){
            if(CardItems.get(i).getText().equalsIgnoreCase("Broken Links - Images")){
                CardItems.get(i).click();
            }
        }
    }
}
