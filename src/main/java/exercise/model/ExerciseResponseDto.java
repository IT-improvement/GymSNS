package exercise.model;

import java.sql.Timestamp;

public class ExerciseResponseDto {
	private int index;
	private int categoryIndex;
	private int userCode;
	private String name;
	private String categoryName;
	private String content;
	private Timestamp createDate;
	private Timestamp modDate;

	public ExerciseResponseDto(int index, int categoryIndex, int userCode, String name, String categoryName, String content, Timestamp createDate, Timestamp modDate) {
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

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCategoryIndex() {
		return categoryIndex;
	}

	public void setCategoryIndex(int categoryIndex) {
		this.categoryIndex = categoryIndex;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}
}