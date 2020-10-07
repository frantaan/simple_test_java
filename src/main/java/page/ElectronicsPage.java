package page;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElectronicsPage extends NavigationPage {

    @Step("Открыть каталог '{catalogName}'")
    public void openCatalog(String catalogName) {
        WebElement page = DriverManager.getDriver().findElement(By.xpath(String.format("//ul[@data-autotest-id='subItems']//*[text() = '%s']", catalogName)));
        page.click();
    }
}
