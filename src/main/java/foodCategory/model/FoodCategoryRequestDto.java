package foodCategory.model;

public class FoodCategoryRequestDto {
    private int userCode;
    private String categoryName;
    private String categoryImageUrl;

    public FoodCategoryRequestDto() {
    	
    }
    
    public FoodCategoryRequestDto(int userCode, String categoryName, String categoryImageUrl) {
        this.userCode = userCode;
        this.categoryName = categoryName;
        this.categoryImageUrl = categoryImageUrl;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }
}
