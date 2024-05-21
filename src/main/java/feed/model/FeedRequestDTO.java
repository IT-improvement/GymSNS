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
}
