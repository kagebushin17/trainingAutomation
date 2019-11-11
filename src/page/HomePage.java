package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;

public class HomePage extends base.BasePage {

    @FindBy(id = "ctl00_TopMenuRepeater_ctl00_MenuLink")
    private WebElement navHomeMenu;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean loadHomePage() {
        return locatorsLoadOnPage(getLocatorMainListByID());
    }

    private ArrayList<String> getLocatorMainListByID(){
        ArrayList<String> currentList = new ArrayList<>();
        currentList.add("ctl00_Main_CategoryBrowser_TopCategoryList_ctl01_NestedSubCategoryRepeater_ctl01_SubCategoryButton");
        currentList.add("nav_header");
        currentList.add("color_bar");
        currentList.add("footer_nav");
        currentList.add("title");
        currentList.add("ctl00_Main_CategoryBrowser_TopCategoryList");
        return  currentList;
    }

    public boolean loadPageHomeByClickNavMenu(){
        navHomeMenu.click();
        return loadHomePage();
    }
}
