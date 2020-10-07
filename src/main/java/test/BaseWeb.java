package test;

import config.Configuration;
import config.ConfigurationManager;
import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import report.AllureManager;

@Listeners({TestListener.class})
public abstract class BaseWeb {

    @BeforeSuite
    public void beforeSuite() {
        AllureManager.setAllureEnvironmentInformation();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void preCondition(@Optional("chrome") String browser) {
        Configuration configuration = ConfigurationManager.getConfiguration();

        DriverFactory driverFactory = new DriverFactory();
        WebDriver driver = driverFactory.createInstance(browser);
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
        DriverManager.getDriver().get(configuration.url());
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        DriverManager.quit();
    }
}
