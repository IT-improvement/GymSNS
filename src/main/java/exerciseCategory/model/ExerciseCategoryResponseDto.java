package exerciseCategory.model;

public class ExerciseCategoryResponseDto {
	private int index;
	private int userCode;
	private String name;

	public ExerciseCategoryResponseDto(int index, String name) {
		this.index = index;
		this.name = name;
	}

	public ExerciseCategoryResponseDto(int index, int userCode, String name) {
		this.index = index;
		this.userCode = userCode;
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}