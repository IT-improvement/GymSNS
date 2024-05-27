package exercise.model;

import java.sql.Timestamp;

public class Exercise {
	private int index;
	private int categoryIndex;
	private String categoryName;
	private int userCode;
	private String userId;
	private String userName;
	private String name;
	private String content;
	private Timestamp createDate;
	private Timestamp modDate;

	public Exercise(int index, int categoryIndex, String categoryName, int userCode, String userId, String userName, String name,  String content, Timestamp createDate, Timestamp modDate) {
		this.index = index;
		this.categoryIndex = categoryIndex;
		this.userCode = userCode;
		this.userId = userId;
		this.userName = userName;
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

	public String getCategoryName() {
		return categoryName;
	}

	public int getUserCode() {
		return userCode;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getName() {
		return name;
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