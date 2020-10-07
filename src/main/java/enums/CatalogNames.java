package enums;

public enum CatalogNames {
    TV("Телевизоры");

    private final String name;

    CatalogNames(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
