package feed.model;

import java.sql.Timestamp;
import java.util.List;

public class FeedRequestDTO {
	private String title;
	private String content;
	private int feedIndex;
	private int userCode;
	private int commentIndex;
	private Timestamp createDate;
	private Timestamp modDate;
	private List<String> comments;
	private String comment;
	private String imageURL;



	public FeedRequestDTO() {}

	public FeedRequestDTO(int userCode, String title, String content, String imageURL) {
		this.userCode = userCode;
		this.title = title;
		this.content = content;
		this.imageURL = imageURL;
	}
	
	public FeedRequestDTO(String title, String content, int feedIndex, int userCode ) {
		this.feedIndex = feedIndex;
		this.title = title;
		this.content = content;
		this.userCode = userCode;
	}
	
	public FeedRequestDTO(int feedIndex) {
		this.feedIndex = feedIndex;
	}
	
	public FeedRequestDTO(int feedIndex, int userCode) {
		this.feedIndex = feedIndex;
		this.userCode = userCode;
	}

	public FeedRequestDTO(int feedIndex, int userCode, String comment) {
		this.feedIndex = feedIndex;
		this.userCode = userCode;
		this.comment = comment;
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




	public int getCommentIndex() {return commentIndex;}
	public void setCommentIndex(int commentIndex) {this.commentIndex = commentIndex;}

	public List<String> getComments() {
		return comments;
	}

	public String getComment() {
		return comment;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getImageURL() {
		return imageURL;
	}
}
