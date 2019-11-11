package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationPage extends base.BasePage {

    @FindBy(id = "ctl00_LoginView_LoginLink")
    private WebElement loginLink;

    @FindBy(id = "ctl00_LoginView_RegisterLink")
    private WebElement registerLink;

    @FindBy(id = "ctl00_TopMenuRepeater_ctl00_MenuLink")
    private WebElement homeMenu;

    @FindBy(id = "ctl00_TopMenuRepeater_ctl01_MenuLink")
    private WebElement postanAdLink;

    @FindBy(id = "ctl00_TopMenuRepeater_ctl02_MenuLink")
    private WebElement myAdsProfile;

    public NavigationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage goToLoginPage(){
        clickOnElement(loginLink);
        return new LoginPage(driver);
    }

    public RegisterPage goToRegisterPage(){
        clickOnElement(registerLink);
        return new RegisterPage(driver);
    }
}
