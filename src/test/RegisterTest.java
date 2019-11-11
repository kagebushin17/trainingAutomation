package test;

import Entities.UserRegister;
import org.testng.Assert;
import org.testng.annotations.*;
import page.RegisterPage;

public class RegisterTest  extends base.BaseTest{
    RegisterPage registerPage;

    @BeforeClass
    public void setUpClass() {
        System.out.println("**Executing RegisterPage**");
    }

    private void preconditionsRegisterPage() {
        registerPage = navigationPage.goToRegisterPage();
    }

    private UserRegister createUser() {
        UserRegister newUser = new UserRegister();
        newUser.setFirstName("Pablito");
        newUser.setLastName("Clavo Clavito");
        newUser.setEmail("pablito@fresamiedo.net");
        newUser.setUserName("kagebushin17");
        newUser.setPassword("Ganbarimasu17*");
        newUser.setConfirmPassword("Ganbarimasu17*");
        newUser.setSecurityQuestion("What is the name of my first dog?");
        newUser.setSecurityAnswer("Bandido");
        return newUser;
    }

    @Test
    public void verifyRegisterPage() {
        preconditionsRegisterPage();
        Assert.assertTrue(registerPage.verifyRegisterLoad());
    }

    @Test
    public void verifyAllErrorLabels() {
        preconditionsRegisterPage();
        Assert.assertTrue(registerPage.verifyAllErrorMessagesDisplayedByEmptyInputs());
    }

    @Test
    public void verifyRegisterWithTakenUsername() {
        UserRegister currentUser = createUser();
        preconditionsRegisterPage();
        System.out.println("Testing with username: " + currentUser.getUserName());
        Assert.assertTrue(registerPage.verifyErrorByUserNameTaken(currentUser));
    }

    @Test
    public void verifyRegisterWithDifferentPassword() {
        preconditionsRegisterPage();
        Assert.assertTrue(registerPage.verifyErrorByDiferentPasword());
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


}
