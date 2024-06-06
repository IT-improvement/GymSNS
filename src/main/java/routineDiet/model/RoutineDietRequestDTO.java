package routineDiet.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RoutineDietRequestDTO {
    private String day;
    private int routineIndex;
    private int foodIndex;
    private int userCode;
    private Timestamp date;
    private String mealTime;

    public RoutineDietRequestDTO() {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sdf.format(System.currentTimeMillis());
        date = Timestamp.valueOf(time);
    }

    public Timestamp getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public int getRoutineIndex() { return routineIndex;}
    public void setRoutineIndex(int routineIndex) {
        this.routineIndex = routineIndex;
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
    public String getMealTime() {return mealTime;}
    public void setMealTime(String mealTime) {this.mealTime = mealTime;}
}
