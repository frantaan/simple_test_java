package test;


import enums.CatalogNames;
import enums.ManufacturerNames;
import enums.NavigationPageNames;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.ElectronicsPage;
import page.NavigationPage;
import page.ProductPage;
import page.TvCatalogPage;
import report.AllureManager;

public class CatalogTests extends BaseWeb {

    @Test(description = "Catalog test", dataProvider = "dataProvider")
    public void CatalogTvFilterManufacturerAndPriceFrom(String priceFrom){
        NavigationPage navigationPage = new NavigationPage();
        navigationPage.openPage(NavigationPageNames.ELECTRONICS.getName());
        AllureManager.takeScreenshotToAllure();

        ElectronicsPage electronicsPage = new ElectronicsPage();
        electronicsPage.openCatalog(CatalogNames.TV.getName());
        AllureManager.takeScreenshotToAllure();

        TvCatalogPage tvCatalogPage = new TvCatalogPage();
        tvCatalogPage.chooseManufacturer(ManufacturerNames.LG.getName());
        tvCatalogPage.chooseManufacturer(ManufacturerNames.SAMSUNG.getName());
        tvCatalogPage.fillPriceFrom(priceFrom);
        AllureManager.takeScreenshotToAllure();
        tvCatalogPage.openFirstProduct();
        tvCatalogPage.switchTab();
        AllureManager.takeScreenshotToAllure();
        ProductPage productPage = new ProductPage();
        String title = productPage.getProductTitle();
        int price =  Integer.parseInt(productPage.getProductPrice().replaceAll("\\s",""));

        Assert.assertTrue(title.contains(ManufacturerNames.LG.getName())
                        || title.contains(ManufacturerNames.SAMSUNG.getName()), "Title haven't LG or SAMSUNG");
        Assert.assertTrue(price >= 20000, "Price less than 20000");
    }

    @DataProvider(name = "dataProvider", parallel=true)
    public Object[][] dataProvider() {
        return new Object[][] {
                new Object[] { "20000" },
                new Object[] { "24000" },
        };
    }
}
