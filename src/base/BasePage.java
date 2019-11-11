package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

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

    /*Here we can click on any tag by id */
    public boolean clickSpecificNodeByID(String idSelected){
        //driver.get(currentUrl);
        WebElement element = driver.findElement(By.id(idSelected));
        if( element != null){
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

    /*Here we can verify if the main tags appear at the page*/
    public boolean locatorsLoadOnPage(ArrayList<String> locators){
        boolean isElementVisible = false;
        for (String locator : locators){
            if(driver.findElement(By.id(locator)).isDisplayed()){
                if(waitForElementVisible(driver.findElement(By.id(locator)))){
                    isElementVisible = true;
                    return isElementVisible;
                }
            }
            else{
                return isElementVisible;
            }
        }
        return isElementVisible;
    }

    public boolean visibilityBySpecificLocatorByID(String locatorID){
        if(driver.findElements(By.id(locatorID)).size() > 0){
            if(!waitForElementVisible(driver.findElement(By.id(locatorID))))
                return false;
        }
        return true;
    }
}
