package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected String currentUrl;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUrlToDriver(String url) {
        this.driver.get(url);
        this.currentUrl = url;
    }

    public boolean typeOnElement(WebElement element, String text) {
        try {
            if(waitForElementVisible(element)) {
                element.sendKeys(text);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Didn't find the web element");
        }
        return false;
    }

    public String getTextFromElement(WebElement element) {
        String elementText = "";
        if(waitForElementVisible(element)) {
            elementText = element.getText();
        }
        return elementText;
    }

    public boolean clickOnElement(WebElement element) {
        if(waitForElementVisible(element)) {
            element.click();
            return true;
        }
        return false;
    }

    public boolean waitForElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean waitForElementVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
