package page;
import config.Configuration;
import config.ConfigurationManager;
import driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class AbstractPageObject {

    protected AbstractPageObject() {
        Configuration configuration = ConfigurationManager.getConfiguration();
        int timeout = Integer.parseInt(configuration.timeout());
        PageFactory.initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(), timeout), this);
    }

    public void switchTab(){
        WebDriver driver = DriverManager.getDriver();
        ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        String currentWindowHandle = driver.getWindowHandle();
        for (String window:windowHandles){
            if (window != currentWindowHandle){
                driver.switchTo().window(window);
            }
        }
    }

    void clickJs(WebElement element){
        JavascriptExecutor ex=(JavascriptExecutor) DriverManager.getDriver();
        ex.executeScript("arguments[0].click()", element);
    }

    WebDriverWait getDriverWait(){
        return DriverManager.getDriverWait();
    }

    void input(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
}
