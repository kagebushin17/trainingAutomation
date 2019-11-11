package page;

import Entities.UserRegister;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.lang.reflect.*;

import java.util.ArrayList;

public class RegisterPage extends base.BasePage {

    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName")
    private WebElement firstNameElement;

    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName")
    private WebElement lastNameElement;

    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email")
    private WebElement emailElement;

    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName")
    private WebElement userNameElement;

    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password")
    private WebElement passwordElement;

    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword")
    private WebElement confirmPasswordElement;

    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question")
    private WebElement questionElement;

    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Answer")
    private WebElement answerElement;

    @FindBy(id = "ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton")
    private WebElement submitElement;

    public RegisterPage(WebDriver driver) {
        super(driver);
        //setURLToDriver("http://192.168.0.103:86/Register.aspx");
        PageFactory.initElements(driver, this);
    }

    public void clickRegisterLink(){
        clickSpecificNodeByID("ctl00_LoginView_RegisterLink");
    }

    private UserRegister createUserInformation(){
        UserRegister newUser = new UserRegister();
        newUser.setFirstName("Pablito");
        newUser.setLastName("Clavo Clavito");
        newUser.setEmail("pablito@fresamiedo.net");
        newUser.setUserName("pablitox");
        newUser.setPassword("Ganbarimasu17*");
        newUser.setConfirmPassword("Ganbarimasu17*");
        newUser.setSecurityQuestion("What is the name of my first dog?");
        newUser.setSecurityAnswer("Bandido");
        return newUser;
    }

    private UserRegister createEmptyRegister(){
        UserRegister newPerson = new UserRegister();
        newPerson.setFirstName("");
        newPerson.setLastName("");
        newPerson.setEmail("");
        newPerson.setUserName("");
        newPerson.setPassword("");
        newPerson.setConfirmPassword("");
        newPerson.setSecurityQuestion("");
        newPerson.setSecurityAnswer("");
        return newPerson;
    }

    public void createValidUser(){
        UserRegister currentUser = createUserInformation();
    }

    private boolean writeOnRegisterInputs(UserRegister currentUser){
        boolean typeOnElementFailed = false;

        typeOnElement(firstNameElement, currentUser.getFirstName());

        //set lastName at input
        typeOnElement(lastNameElement, currentUser.getLastName());

        //set email at input
        typeOnElement(emailElement, currentUser.getEmail());

        //set userName at input
        typeOnElement(userNameElement, currentUser.getUserName());

        //set password at input
        typeOnElement(passwordElement, currentUser.getPassword());

        //set repassword at input
        typeOnElement(confirmPasswordElement, currentUser.getConfirmPassword());

        //set question at input
        typeOnElement(questionElement, currentUser.getSecurityQuestion());

        //set answer at input
        typeOnElement(answerElement, currentUser.getSecurityAnswer());

        clickOnElement(submitElement);
        return true;
    }

    public boolean verifyRegisterLoad(){
        return locatorsLoadOnPage(getLocatorList());
    }

    public boolean verifyAllErrorMessagesDisplayedByEmptyInputs(){
        if(!writeOnRegisterInputs(createEmptyRegister()))
            return false;
        return locatorsLoadOnPage(getNodeErrorListByID()); //Fix
    }

    public boolean verifyErrorByDiferentPasword(){
        UserRegister personTest = createUserInformation();
        personTest.setConfirmPassword("1234567890ab-"); //set diferentPassword
        if(!writeOnRegisterInputs(personTest))
            return false;
        return visibilityBySpecificLocatorByID("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordCompare");
    }

    public boolean verifyErrorByUserNameTaken(UserRegister personTest){
        if(!writeOnRegisterInputs(personTest))
            return false;
        return visibilityBySpecificLocatorByID("ctl00_Main_InfoLabel");
    }

    private ArrayList<String> getLocatorList(){
        ArrayList<String> currentList = new ArrayList<>();
        currentList.add("ctl00_Main_CreateUserWizardControl");
        currentList.add("nav_header");
        currentList.add("footer_nav");
        currentList.add("user_assistance");
        currentList.add("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton");
        return currentList;
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
        return currentList;
    }
}
