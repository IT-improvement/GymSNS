package dietcategory.model;

public class DietCategory {
    private int index;
    private String name;

    public DietCategory(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
