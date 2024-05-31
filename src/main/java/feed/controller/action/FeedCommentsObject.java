package feed.controller.action;

public class FeedCommentsObject {
    private int feedCommentsIndex;
    private String comment;
    private String userName;
    private int userCode;
    private String userId;


    public FeedCommentsObject() {  }

    public FeedCommentsObject(int feedCommentsIndex, String comment, String userId, String userName, int userCode) {
        this.feedCommentsIndex = feedCommentsIndex;
        this.comment = comment;
        this.userId = userId;
        this.userName = userName;
        this.userCode = userCode;
    }

    public void setFeedCommentsIndex(int feedCommentsIndex) {
        this.feedCommentsIndex = feedCommentsIndex;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getFeedCommentsIndex() {
        return feedCommentsIndex;
    }

    public String getComment() {
        return comment;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}