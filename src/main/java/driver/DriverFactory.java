package driver;

import config.Configuration;
import config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverFactory implements IDriver {

    public WebDriver createInstance(String browser) {
        return new driver.RemoteDriverManager().createInstance(browser);
    }

    public WebDriverWait createInstance() {
        Configuration configuration = ConfigurationManager.getConfiguration();
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Long.parseLong(configuration.waitDuration())));
    }
}