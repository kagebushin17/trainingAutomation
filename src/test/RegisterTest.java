package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

public class RegisterTest {

    public String baseUrl = "http://192.168.0.103:86/Register.aspx";
    String driverPath = "D:\\webdrivers\\chromedriver.exe";
    public WebDriver driver;

    @BeforeMethod
    public void launchBrowser() {
        System.out.println("Creating driver");
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
        boolean areErrorLabelsPresent = false;
        ArrayList errorLbls = getNodeErrorListByID();

        driver.findElement(By.id("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton")).click();

        try {
            for (int i = 0; i < errorLbls.size(); i++) {
                if(driver.findElement(By.id(errorLbls.get(i).toString())).isDisplayed()) {
                    areErrorLabelsPresent = true;
                }
            }
            Assert.assertTrue(areErrorLabelsPresent, "didnt find all errror labels");
        } catch (Exception e) {
            Assert.assertTrue(areErrorLabelsPresent, "didnt find all errror labels");
        }
    }
    @Test
    public void verifyRegisterWithTakenUsername() {
        ArrayList<WebElement> inputList = getRegisterInputsById();
        ArrayList<String> inputData = getRegisterFormDataInput("Ganbarimasu17*");

        for (int i = 0; i < inputList.size(); i++) {
            inputList.get(i).clear();
        }

        for(int i = 0; i < inputList.size(); i++){
            inputList.get(i).sendKeys(inputData.get(i).toString());
        }

        driver.findElement(By.id("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton")).click();
        boolean isTakenUsernameErrorDisplayed = driver.findElement(By.id("ctl00_Main_InfoLabel")).isDisplayed();
        Assert.assertTrue(isTakenUsernameErrorDisplayed, "didnt find username taken error msg");

    }
    @Test
    public void verifyRegisterWithDifferentPassword() {
        ArrayList<WebElement> inputList = getRegisterInputsById();
        ArrayList<String> inputData = getRegisterFormDataInput("Ganbarimasu2960*");

        for (int i = 0; i < inputList.size(); i++) {
            inputList.get(i).clear();
        }

        for (int i = 0; i < inputList.size(); i++) {
            inputList.get(i).sendKeys(inputData.get(i).toString());
        }

        driver.findElement(By.id("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton")).click();
        boolean isPasswordCompareErrorLabel = driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordCompare")).isDisplayed();
        Assert.assertTrue(isPasswordCompareErrorLabel, "didnt find username taken error msg");
    }
    @AfterMethod
    public void terminateBrowser() {
        System.out.println("Deleting driver");
        driver.quit();
    }

    @AfterClass
    public void completeExecution() {
        System.out.println("Completing execution class: " + this.getClass().toString());
    }

    private ArrayList<String> getNodeErrorListByID(){
        ArrayList<String> currentList = new ArrayList<>();
        currentList.add("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstNameRequired");
        currentList.add("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastNameRequired");
        currentList.add("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_EmailRequired");
        currentList.add("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserNameRequired");
        currentList.add("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordRequired");
        currentList.add("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPasswordRequired");
        currentList.add("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_QuestionRequired");
        currentList.add("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_AnswerRequired");
        return  currentList;
    }

    private ArrayList<WebElement> getRegisterInputsById() {
        ArrayList<WebElement> registerInputList = new ArrayList<>();
        registerInputList.add(driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName")));
        registerInputList.add(driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName")));
        registerInputList.add(driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email")));
        registerInputList.add(driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName")));
        registerInputList.add(driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password")));
        registerInputList.add(driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword")));
        registerInputList.add(driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question")));
        registerInputList.add(driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Answer")));
        return registerInputList;
    }

    private ArrayList<String> getRegisterFormDataInput(String confirmPassword) {
        ArrayList<String> inputDataList= new ArrayList<>();
        inputDataList.add("testuno");
        inputDataList.add("testuno");
        inputDataList.add("test1@gmail.com");
        inputDataList.add("test1");
        inputDataList.add("Ganbarimasu17*");
        inputDataList.add(confirmPassword);
        inputDataList.add("favorite book");
        inputDataList.add("lovecraft");
        return inputDataList;
    }
}
