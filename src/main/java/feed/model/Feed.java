package feed.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Feed {
	private String title;
	private String content;
	private String comment;
	private int feedIndex;
	private int userCode;
	private Timestamp createDate;
	private Timestamp modDate;
	private List<String> comments = new ArrayList<>();
	
	public Feed() {
		
	}

	public Feed(String title, String content, int feedIndex, int userCode) {
		super();
		this.title = title;
		this.content = content;
		this.feedIndex = feedIndex;
		this.userCode = userCode;
	}
	
	public Feed(String title, String content, int feedIndex, int userCode, List<String> comments) {
		super();
		this.title = title;
		this.content = content;
		this.feedIndex = feedIndex;
		this.userCode = userCode;
		this.comments = comments;
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

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public void addComment(String comment) {
		this.comments.add(comment);
	}
	public void setComment(String comment) {this.comment = comment;}

	public String getComment() {return comment;}
}
