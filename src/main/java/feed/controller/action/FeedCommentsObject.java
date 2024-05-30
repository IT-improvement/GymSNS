package feed.controller.action;

public class FeedCommentsObject {
    private int feedCommentsIndex;
    private String comment;

    public FeedCommentsObject() {  }

    public FeedCommentsObject(int feedCommentsIndex, String comment) {
        this.feedCommentsIndex = feedCommentsIndex;
        this.comment = comment;
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
}
