package page;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class TvCatalogPage extends NavigationPage{

    @FindBy(id = "glpricefrom")
    private WebElement inputFilterPriceFrom;

    @FindBy(name = "Производитель LG")
    private WebElement chooseLg;

    @FindBy(xpath = "//button[text() = 'Показать всё']")
    private WebElement showAll;

    @FindBy(name = "Поле поиска")
    private WebElement searchField;

    @FindBy(xpath = "//article[@data-autotest-id='product-snippet'][1]//h3[@data-zone-name='title']")
    private WebElement firstProductInCatalogTitle;

    @FindBy(xpath = "//div[@data-zone-name=\"SearchResults\"]/div/div[2]")
    private WebElement searchPopup;

    @Step("Ввести '{price}' в поле 'цена от'")
    public void fillPriceFrom(String price) {
        this.inputFilterPriceFrom.sendKeys(price);
    }

    @Step("Выбрать производителя '{manufacturer}' в фильтре")
    public void chooseManufacturer(String manufacturer){
        getDriverWait().until(ExpectedConditions.elementToBeClickable(this.showAll));
        this.showAll.click();
        getDriverWait().until(ExpectedConditions.visibilityOf(this.searchField));
        input(this.searchField,manufacturer);
        getDriverWait().until(webDriver -> webDriver.findElement(By.name(String.format("Производитель %s", manufacturer))));
        WebElement element = DriverManager.getDriver().findElement(By.name(String.format("Производитель %s", manufacturer)));
        new Actions(DriverManager.getDriver()).moveToElement(element).pause(Duration.ofMillis(200)).click().build().perform();
    }

    @Step("Открыть первый продукт из отфильтрованного списка")
    public void openFirstProduct(){
        getDriverWait().until(ExpectedConditions.invisibilityOf(this.searchPopup));
        this.firstProductInCatalogTitle.click();
    }
}
