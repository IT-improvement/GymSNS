package dietcategory.model;

public class DietCategoryRequestDto {
    private int index;
    private String name;

    public DietCategoryRequestDto(String name) {
        this.name = name;
    }

    public DietCategoryRequestDto(int index) {
        this.index = index;
    }

    public DietCategoryRequestDto(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
