package diary.model;

import java.sql.Timestamp;

public class DiaryRequestDTO {
	private int dairyIndex;
	private String content;
	private Timestamp diaryDate;
	
	public int getDairyIndex() {
		return dairyIndex;
	}
	public void setDairyIndex(int dairyIndex) {
		this.dairyIndex = dairyIndex;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDiaryDate() {
		return diaryDate;
	}
	public void setDiaryDate(Timestamp diaryDate) {
		this.diaryDate = diaryDate;
	}
}
