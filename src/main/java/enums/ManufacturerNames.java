package enums;

public enum ManufacturerNames {
    LG("LG"),
    SAMSUNG("Samsung");

    private final String locator;

    ManufacturerNames(String locator) {
        this.locator = locator;
    }

    public String getName() {
        return locator;
    }
}
