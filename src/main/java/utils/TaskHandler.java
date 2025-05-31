package utils;

import Locators.FastGem_Homepage;
import Locators.Login_FastGem;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class TaskHandler implements Login_FastGem, FastGem_Homepage {

    private WebDriver driver;

    public void fetchWorkOrders() {
        System.out.println("Bot: Fetching work orders...");
        // TODO: Call your Selenium page method
        // e.g., new WorkOrderPage(driver).fetchWorkOrders();
        System.out.println("Establishing WebDriver to control browser");
        webAutomationInitiator();
        System.out.println("Logging into Web Application");
        //This can be a Login Module
        driver.get("https://nspllc.fastgem.net/");

        //This part can be Feature module
        String userType = "admin";
        TestDataController testDataController = new TestDataController("src/test/resources/testdata/users.csv");
        HashMap<String, String> creds = testDataController.getCredentialsByUserType(userType);
        driver.findElement(fastgemUsername).sendKeys(creds.get("username"));
        driver.findElement(fastgemPassword).sendKeys(creds.get("password"));
        driver.findElement(loginButton).click();
        if(driver.findElement(statsFetchTime).isDisplayed()){
            ExtentReportManager.logPass("Logged into FastGem" + driver.findElement(statsFetchTime).getText());
        }else{
            ExtentReportManager.logFail("Unable to login to FastGem");
        }
        Assert.assertTrue("Last Work Order Fetch Time : ", driver.findElement(statsFetchTime).isDisplayed());

    }

    public void searchWorkOrder(String query) {
        System.out.println("Bot: Searching for workorder: " + query);
        // TODO: Implement with Selenium
        // e.g., new WorkOrderPage(driver).searchWorkOrder(query);
    }

    public void postDateWorkOrder(String workorderId) {
        System.out.println("Bot: Post-dating workorder: " + workorderId);
        // TODO: Implement with Selenium
        // e.g., new WorkOrderPage(driver).postDateWorkOrder(workorderId);
    }

    public void arbAction() {
        System.out.println("Bot: Executing ARB task...");
        // TODO: Implement with Selenium
        // e.g., new ARBPage(driver).performARB();
    }

    public void webAutomationInitiator(){
        driver = DriverFactory.initDriver();
        ExtentReportManager.initReport();
        ExtentReportManager.createTest("FastGem Workorder Automation");
    }
}
