package exerciseCategory.model;

public class ExerciseCategory {
	private int index;
	private String name;
	
	public ExerciseCategory(int index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}
}