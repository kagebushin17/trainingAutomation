package utils;

import org.openqa.selenium.WebDriver;

public class DriverHelper {

    private WebDriver driver;
    private String baseURL;
    private String browserType;
    private String driverType;

    public DriverHelper(String starURL, String browser, String driverType){
        this.baseURL = starURL;
        this.browserType = browser;
        this.driverType = driverType;
    }

    public void createDriver(){
        if(driverType.toLowerCase().equals("localdriver")){
            setUpLocalDriver();
        }
        else if(driverType.toLowerCase().equals("remotedriver")){
            //setUpRemoteDriver();
        }
        else
            System.out.println("The driver does not exits!");
    }

    private void setUpLocalDriver(){
        LocalDriver localDriver = new LocalDriver(this.baseURL, this.browserType);
        localDriver.buildDriver();
        this.driver = localDriver.getDriver();
    }

//    private void setUpRemoteDriver(){
//        RemoteDriver remoteDriver = new RemoteDriver(this.baseURL, browserType);
//        remoteDriver.buildDriver();
//        this.driver = remoteDriver.getDriver();
//    }

    public WebDriver getDriver(){
        return this.driver;
    }

}
