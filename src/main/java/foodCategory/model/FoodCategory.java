package foodCategory.model;

import java.sql.Date;

public class FoodCategory {
    private int foodCategoryIndex;
    private int userCode;
    private String categoryName;
    private String categoryImageUrl;
    private Date createDate;
    
    
	public FoodCategory(int foodCategoryIndex, int userCode, String categoryName, String categoryImageUrl,
			Date createDate) {
		super();
		this.foodCategoryIndex = foodCategoryIndex;
		this.userCode = userCode;
		this.categoryName = categoryName;
		this.categoryImageUrl = categoryImageUrl;
		this.createDate = createDate;
	}
	
	public FoodCategory() {
		
	}
	
	public int getFoodCategoryIndex() {
		return foodCategoryIndex;
	}
	public void setFoodCategoryIndex(int foodCategoryIndex) {
		this.foodCategoryIndex = foodCategoryIndex;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}