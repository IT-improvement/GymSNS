package routine.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RoutineRequestDTO {
	private String day;
	private int routineIndex;
	private int exerciseIndex;
	private int userCode;
	private Timestamp date;
	
	public RoutineRequestDTO() {
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
	public int getRoutineIndex() {
		return routineIndex;
	}
	public void setRoutineIndex(int routineIndex) {
		this.routineIndex = routineIndex;
	}
	public int getExerciseIndex() {
		return exerciseIndex;
	}
	public void setExerciseIndex(int exerciseIndex) {
		this.exerciseIndex = exerciseIndex;
	}
	public int getUserCode() {
		return userCode;
	}
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	
	
}
