package test;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;

public class LoginTest extends base.BaseTest {

    public String baseUrl = "http://192.168.0.103:86/";
    String driverPath = "D:\\webdrivers\\chromedriver.exe";
    public WebDriver driver;
    String username = "kagebushin17";
    String password = "Ganbarimasu17*";
    BasePage basePage;
    LoginPage loginPage;

//    @BeforeTest
//    public void launchBrowser() {
//        System.out.println("lauching browser");
//        System.setProperty("webdriver.chrome.driver", driverPath);
//        driver = new ChromeDriver();
//        basePage = new BasePage(driver);
//        basePage.setUrlToDriver(baseUrl);
//    }

    private void preconditionsLoginPage() {
        loginPage = navigationPage.goToLoginPage();
    }

    @Test
    public void verifyLoginPage() {
        preconditionsLoginPage();
        System.out.println("verifyLoginPage");
        Assert.assertTrue(loginPage.verifyPageLoadLogin());
    }

    @Test
    public void verifyLoginWithValidCredentials() {
        preconditionsLoginPage();
        Assert.assertTrue(loginPage.sendValidCredentials());
    }

    @Test
    public void verifyLoginWithBadCredentials() {
        preconditionsLoginPage();
        Assert.assertTrue(loginPage.sendInValidCredentials());
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println("--Completing execution LoginPageTest--");
    }
}
