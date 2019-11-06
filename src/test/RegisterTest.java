package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class RegisterTest {

    public String baseUrl = "http://192.168.0.103:86/";
    String driverPath = "D:\\webdrivers\\chromedriver.exe";
    public WebDriver driver;

    String firstName = "Andy";
    String lastName = "Carvajal";
    String email = "andy.carvajal@gmail.com";
    String username = "andy-as";
    String password = "Ganbarimasu17*";
    String confirmPass = "Ganbarimasu17";
    String secQuestion = "soy grande?";
    String secAnswer = "si";

    @BeforeTest
    public void launchBrowser() {
        System.out.println("lauching browser");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @Test
    public void verifyRegisterPage() {
        String errorMessage = "Page not loaded";
        driver.findElement(By.id("ctl00_LoginView_RegisterLink")).click();
        boolean isLoginPageDisplayed = driver.findElement(By.xpath("//h2[contains(text(), 'Register')]")).isDisplayed();
        Assert.assertTrue(isLoginPageDisplayed, errorMessage);
    }

    @Test
    public void verifyAllErrorLabels() {
        WebElement registerForm = driver.findElement(By.id("adasd"));
        List<WebElement> registerInputs = registerForm.findElements(By.tagName("input"));

        for(int i = 0; i < registerInputs.size(); i++){
            System.out.println(registerInputs.get(i).getAttribute("id"));
        }
    }

    @AfterTest
    public void terminateBrowser() {
        driver.close();
    }
}
