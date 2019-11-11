package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class LoginPage extends base.BasePage {

    @FindBy(id = "ctl00_Main_LoginConrol_UserName")
    private WebElement userNameElement;

    @FindBy(id = "ctl00_Main_LoginConrol_Password")
    private WebElement userPasswordElement;

    @FindBy(id = "ctl00_Main_LoginConrol_LoginButton")
    private WebElement userLoginBtnElement;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean verifyPageLoadLogin(){
        //return locatorsLoadOnPage(getLocatorList());
        try {
            return driver.findElement(By.id("ctl00_Main_LoginConrol")).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    public boolean sendValidCredentials() {
        String userName = "kagebushin17";
        boolean typeOnElementFaild = false;
        if(!typeOnElement(userNameElement, userName))
            return typeOnElementFaild;
        if(!typeOnElement(userPasswordElement, "Ganbarimasu17*"))
            return typeOnElementFaild;

        if(clickOnElement(userLoginBtnElement)){
            return driver.findElement(By.id("ctl00_LoginView_MemberName")).getText().equals(userName);
        }
        return false;
    }

    public boolean sendInValidCredentials() {
        String userName = "123123123";
        typeOnElement(userNameElement, userName);
        typeOnElement(userPasswordElement, "1234567890AB-");
        userLoginBtnElement.click();
        try {
            WebElement element = driver.findElement(By.xpath("//*[@id=\"ctl00_Main_LoginConrol\"]/tbody/tr/td/table/tbody/tr[4]/td"));
            if (element != null) {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    public void clickNodeLogin(){
        clickSpecificNodeByID("ctl00_LoginView_LoginLink");
    }

    private ArrayList<String> getLocatorList(){
        ArrayList<String> tagList = new ArrayList<>();
        tagList.add("ctl00_Main_LoginConrol");
//        tagList.add("user_assistance");
//        tagList.add("color_bar");
//        tagList.add("ctl00_Main_LoginConrol_LoginButton");
//        tagList.add("ctl00_Main_LoginConrol_RememberMe");
//        tagList.add("ctl00_Main_ForgotPasswordButton");
//        tagList.add("footer_nav");
        return tagList;
    }
}
