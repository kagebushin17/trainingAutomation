package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalDriver implements IDriver {

    private WebDriver driver;
    private String baseURL;
    private String browserType;

    public LocalDriver(String startURL, String browserType){
        this.baseURL = startURL;
        this.browserType = browserType;
    }

    @Override
    public void buildDriver() {
        try{
            if(this.browserType.toLowerCase().equals("firefox")){
                System.setProperty("webdriver.gecko.driver", "src\\driver\\geckodriver.exe");
                this.driver = new FirefoxDriver();
            }
            else if(browserType.toLowerCase().equals("chrome")){
                System.setProperty("webdriver.chrome.driver", "src\\driver\\chromedriver.exe");
                this.driver = new ChromeDriver();
            }
            this.driver.get(this.baseURL);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public WebDriver getDriver() {
        return this.driver;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }
}
