package exerciseCategory.model;

public class ExerciseCategoryRequestDto {
	private int index;
	private int userCode;
	private String name;

	public ExerciseCategoryRequestDto(int userCode) {
		this.userCode = userCode;
	}

	public ExerciseCategoryRequestDto(int index, int userCode) {
		this.index = index;
		this.userCode = userCode;
	}

	public ExerciseCategoryRequestDto(int index, int userCode, String name) {
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
