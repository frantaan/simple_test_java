package page;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationPage extends AbstractPageObject {

    @FindBy(xpath = "//div[@data-position='bottom']")
    private WebElement tip;

    @Step("Открыть страницу '{name}'")
    public void openPage(String name) {
        WebElement page = DriverManager.getDriver().findElement(By.xpath(String.format("//div[@data-zone-name='category-link']//*[text() = '%s']", name)));
        this.tip.click();
        clickJs(page);
    }
}
