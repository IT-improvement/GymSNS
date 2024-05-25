package diet.model;

public class DietRequestDto {
	private int dietIndex;
	private int userCode;
	private int foodIndex;
	private int totalCalories;
	private int totalProtein;
	
	public DietRequestDto(int userCode, int foodIndex, int totalCalories, int totalProtein) {
		super();
		this.userCode = userCode;
		this.foodIndex = foodIndex;
		this.totalCalories = totalCalories;
		this.totalProtein = totalProtein;
	}
	
	public DietRequestDto(int dietIndex, int userCode, int foodIndex, int totalCalories, int totalProtein) {
		super();
		this.dietIndex = dietIndex;
		this.userCode = userCode;
		this.foodIndex = foodIndex;
		this.totalCalories = totalCalories;
		this.totalProtein = totalProtein;
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
