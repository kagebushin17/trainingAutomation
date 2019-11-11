package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import page.NavigationPage;
import utils.DriverHelper;

public class BaseTest {

    public static WebDriver driver;
    protected NavigationPage navigationPage;

    @BeforeMethod
    @Parameters({"browser", "startURL", "driverType"})
    public void setUpDriver(String browser, String startURL, String driverType) {
        System.out.println("Creating driver");
        driver = createDriver(browser, startURL, driverType);
        navigationPage = new NavigationPage(driver);
    }

    private WebDriver createDriver(String browserType, String startURL,String driverType){
        DriverHelper driverHelper = new DriverHelper(startURL, browserType, driverType);
        driverHelper.createDriver();
        return driverHelper.getDriver();
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Closing driver");
        driver.quit();
    }

    @AfterMethod
    public void afterTest(){
        System.out.println("AfterTest");
    }
}
