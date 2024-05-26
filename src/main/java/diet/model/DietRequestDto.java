package diet.model;

import java.sql.Timestamp;

public class DietRequestDto {
	private int dietIndex;
	private int userCode;
	private int foodIndex;
	private int totalCalories;
	private int totalProtein;
	private Timestamp createDate;
    private Timestamp modDate;
	
	public DietRequestDto(int userCode, int foodIndex, int totalCalories, int totalProtein) {
		super();
		this.userCode = userCode;
		this.foodIndex = foodIndex;
		this.totalCalories = totalCalories;
		this.totalProtein = totalProtein;
		this.createDate = new Timestamp(System.currentTimeMillis());
		this.modDate = new Timestamp(System.currentTimeMillis());
	}
	
	public DietRequestDto(int dietIndex, int userCode, int foodIndex, int totalCalories, int totalProtein) {
		super();
		this.dietIndex = dietIndex;
		this.userCode = userCode;
		this.foodIndex = foodIndex;
		this.totalCalories = totalCalories;
		this.totalProtein = totalProtein;
	}
	
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

	public int getDietIndex() {
		return dietIndex;
	}
	public void setDietIndex(int dietIndex) {
		this.dietIndex = dietIndex;
	}
	public int getUserCode() {
		return userCode;
	}
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	public int getFoodIndex() {
		return foodIndex;
	}
	public void setFoodIndex(int foodIndex) {
		this.foodIndex = foodIndex;
	}
	public int getTotalCalories() {
		return totalCalories;
	}
	public void setTotalCalories(int totalCalories) {
		this.totalCalories = totalCalories;
	}
	public int getTotalProtein() {
		return totalProtein;
	}
	public void setTotalProtein(int totalProtein) {
		this.totalProtein = totalProtein;
	}
}
