package routineDiet.model;

public class RoutineDiet {
    private int routineIndex;
    private int userCode;
    private String day;
    private int foodIndex;
    private String mealTime;

    public int getRoutineIndex() {
        return routineIndex;
    }
    public void setRoutineIndex(int routineIndex) {
        this.routineIndex = routineIndex;
    }
    public int getUserCode() {
        return userCode;
    }
    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public int getFoodIndex() {
        return foodIndex;
    }
    public void setFoodIndex(int foodIndex) {
        this.foodIndex = foodIndex;
    }
    public String getMealTime() {return mealTime;}
    public void setMealTime(String mealTime) {this.mealTime = mealTime;}

}
