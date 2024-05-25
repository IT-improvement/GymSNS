package exercise.model;

import java.sql.Timestamp;

public class Exercise {
	private int index;
	private int categoryIndex;
	private int userCode;
	private String name;
	private String categoryName;
	private String content;
	private Timestamp createDate;
	private Timestamp modDate;

	public Exercise(int index, int categoryIndex, int userCode, String name, String categoryName, String content, Timestamp createDate, Timestamp modDate) {
		this.index = index;
		this.categoryIndex = categoryIndex;
		this.userCode = userCode;
		this.name = name;
		this.categoryName = categoryName;
		this.content = content;
		this.createDate = createDate;
		this.modDate = modDate;
	}

	public int getIndex() {
		return index;
	}

	public int getCategoryIndex() {
		return categoryIndex;
	}

	public int getUserCode() {
		return userCode;
	}

	public String getName() {
		return name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getContent() {
		return content;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}
}