package enums;

public enum NavigationPageNames {
    ELECTRONICS("Электроника");

    private final String name;

    NavigationPageNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}