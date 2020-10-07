package driver;

import config.Configuration;
import config.ConfigurationManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class RemoteDriverManager implements IDriver {

    private static final Logger logger = LogManager.getLogger(RemoteDriverManager.class);

    @Override
    public WebDriver createInstance(String browser) {
        RemoteWebDriver remoteWebDriver = null;
        Configuration configuration = ConfigurationManager.getConfiguration();
        try {
            String selenoidURL = String.format("http://%s:%s/wd/hub", configuration.selenoidUrl(), configuration.selenoidPort());
            remoteWebDriver = new RemoteWebDriver(new URL(selenoidURL), getCapability(browser));
        } catch (MalformedURLException e) {
            logger.error("Selenoid URL is invalid or selenoid is not available");
            logger.error(String.format("Browser: %s", browser), e);
        } catch (IllegalArgumentException e) {
            logger.error(String.format("Browser %s is not valid or recognized", browser), e);
        }

        return remoteWebDriver;
    }

    private static MutableCapabilities getCapability(String browser) {
        MutableCapabilities mutableCapabilities;
        Configuration configuration = ConfigurationManager.getConfiguration();
        DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());
        switch (driverManagerType) {
            case CHROME:
                mutableCapabilities = defaultChromeOptions(browser, configuration.chromeVersion());
                break;
            case FIREFOX:
                mutableCapabilities = defaultChromeOptions(browser, configuration.firefoxVersion());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + driverManagerType);
        }
        return mutableCapabilities;
    }

    private static MutableCapabilities defaultChromeOptions(String browser, String version) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", version);
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        return capabilities;
    }
}
