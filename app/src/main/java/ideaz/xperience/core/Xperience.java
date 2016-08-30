package ideaz.xperience.core;

public class Xperience {
    private String name;
    private String description;

    public Xperience(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
