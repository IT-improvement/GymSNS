package exercise.model;

import java.sql.Timestamp;

public class ExerciseResponseDto {
	private int index;
	private int categoryIndex;
	private int userCode;
	private String userId;
	private String userName;
	private String name;
	private String categoryName;
	private String content;
	private Timestamp createDate;
	private Timestamp modDate;
	
	public ExerciseResponseDto() {
		
	}
	
	public ExerciseResponseDto(int index, int categoryIndex, String categoryName, int userCode, String userId, String userName, String name, String content, Timestamp createDate, Timestamp modDate) {
		this.index = index;
		this.categoryIndex = categoryIndex;
		this.categoryName = categoryName;
		this.userCode = userCode;
		this.userId = userId;
		this.userName = userName;
		this.name = name;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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