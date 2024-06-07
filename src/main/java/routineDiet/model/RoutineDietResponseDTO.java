package routineDiet.model;

public class RoutineDietResponseDTO {
    private int foodIndex;
    private String name;
    private String category;
    private String day;
    private int routineIndex;
    private String mealTime;

    public int getRoutineIndex() {
        return routineIndex;
    }
    public void setRoutineIndex(int routineIndex) {
        this.routineIndex = routineIndex;
    }
    public int getFoodIndex() {
        return foodIndex;
    }
    public void setFoodIndex(int foodIndex) {
        this.foodIndex = foodIndex;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getMealTime() {return mealTime;}
    public void setMealTime(String mealTime) {this.mealTime = mealTime;}
}
