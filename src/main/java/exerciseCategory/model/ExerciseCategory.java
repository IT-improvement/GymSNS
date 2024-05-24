package exerciseCategory.model;

public class ExerciseCategory {
	private int index;
	private int userCode;
	private String name;
	
	public ExerciseCategory(int index, int userCode, String name) {
		this.index = index;
		this.userCode = userCode;
		this.name = name;
	}
	
	public int getIndex() {
		return index;
	}

	public int getUserCode() {
		return userCode;
	}

	public String getName() {
		return name;
	}
}