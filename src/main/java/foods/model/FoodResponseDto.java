package foods.model;

import java.sql.Timestamp;

public class FoodResponseDto {
    private int foodIndex;
    private int userCode;
    private int foodCategoryIndex;
    private String foodName;
    private int protein;
    private int calory;
    private int carbs;
    private int size;
    private Timestamp createDate;

    public FoodResponseDto() {
    }

    public FoodResponseDto(int foodIndex, int userCode, int foodCategoryIndex, String foodName, int protein, int calory, int carbs, int size, Timestamp createDate) {
        this.foodIndex = foodIndex;
        this.userCode = userCode;
        this.foodCategoryIndex = foodCategoryIndex;
        this.foodName = foodName;
        this.protein = protein;
        this.calory = calory;
        this.carbs = carbs;
        this.size = size;
        this.createDate = createDate;
    }

    public int getFoodIndex() {
        return foodIndex;
    }

    public void setFoodIndex(int foodIndex) {
        this.foodIndex = foodIndex;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getFoodCategoryIndex() {
        return foodCategoryIndex;
    }

    public void setFoodCategoryIndex(int foodCategoryIndex) {
        this.foodCategoryIndex = foodCategoryIndex;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCalory() {
        return calory;
    }

    public void setCalory(int calory) {
        this.calory = calory;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
