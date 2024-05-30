package feed.model;

import feed.controller.action.FeedCommentsObject;

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
	private List<FeedCommentsObject> comments = new ArrayList<>();
	private boolean isFavorite;
	private int favoriteCount;

	public Feed() {
		
	}

	public Feed(String title, String content, int feedIndex, int userCode, Timestamp createDate) {
		super();
		this.title = title;
		this.content = content;
		this.feedIndex = feedIndex;
		this.userCode = userCode;
		this.createDate = createDate;
	}
	
	public Feed(String title, String content, int feedIndex, int userCode, List<FeedCommentsObject> comments) {
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

	public List<FeedCommentsObject> getComments() {
		return comments;
	}

	public void setComments(List<FeedCommentsObject> comments) {
		this.comments = comments;
	}


	public String getComment() {return comment;}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setFavorite(boolean favorite) {
		isFavorite = favorite;
	}

	public boolean getIsFavorite() {
		return isFavorite;
	}
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public boolean setIsFavorite() {
		return isFavorite;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

}
