package diary.model;

import java.sql.Timestamp;

public class DiaryRequestDTO {
	private int userCode;
	private String content;
	private Timestamp diary_date;
	
	public int getUserCode() {
		return userCode;
	}
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDiary_date() {
		return diary_date;
	}
	public void setDiary_date(Timestamp diary_date) {
		this.diary_date = diary_date;
	}
}
