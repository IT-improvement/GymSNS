package exerciseCategory.model;

public class ExerciseCategoryRequestDto {
	private int index;
	private String name;

	public ExerciseCategoryRequestDto(String name) {
		this.name = name;
	}

	public ExerciseCategoryRequestDto(int index) {
		this.index = index;
	}

	public ExerciseCategoryRequestDto(int index, String name) {
		this.index = index;
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
