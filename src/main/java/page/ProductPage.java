package page;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends NavigationPage {

    @FindBy(xpath = "//div[@data-apiary-widget-id='/content/productCardTitle']//h1")
    private WebElement productTitle;

    @FindBy(xpath = "//div[@data-apiary-widget-name='@MarketNode/RecommendedOffers']/div[1]//span[@data-autotest-value]/span[1]")
    private WebElement productPrice;

    @Step("Получить название продукта")
    public String getProductTitle(){
        DriverManager.getDriverWait().until(ExpectedConditions.visibilityOf(this.productTitle));
        return this.productTitle.getText();
    }

    @Step("Получить цену продукта")
    public String getProductPrice(){
        return this.productPrice.getText();
    }
}
