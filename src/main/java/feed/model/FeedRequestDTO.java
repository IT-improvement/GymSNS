package feed.model;

import java.sql.Timestamp;

public class FeedRequestDTO {
	private String title;
	private String content;
	private int feedIndex;
	private int userCode;
	private Timestamp createDate;
	private Timestamp modDate;
	private String comment;
	
	public FeedRequestDTO(int userCode, String title, String content) {
		this.userCode = userCode;
		this.title = title;
		this.content = content;
	}
	
	public FeedRequestDTO(String title, String content, int feedIndex ) {
		this.feedIndex = feedIndex;
		this.title = title;
		this.content = content;
	}
	
	public FeedRequestDTO(int feedIndex) {
		this.feedIndex = feedIndex;
	}
	
	public FeedRequestDTO(int feedIndex, int userCode) {
		this.feedIndex = feedIndex;
		this.userCode = userCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFeedIndex() {
		return feedIndex;
	}

	public void setFeedIndex(int feedIndex) {
		this.feedIndex = feedIndex;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
