package diary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class DiaryDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DiaryDAO() {

	}

	private static DiaryDAO instance = new DiaryDAO();

	public static DiaryDAO getInstance() {
		return instance;
	}
	
	public void writeDiary(DiaryRequestDTO dto) {
		
		conn = DBManager.getConnection();
		try {
			String sql = "INSERT INTO diary(user_code, content, diary_date)"
					+ "VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserCode());
			pstmt.setString(2, dto.getContent());
			pstmt.setTimestamp(3, dto.getDiary_date());
			
			pstmt.execute();
		} catch (Exception e) {
			
		}
	}
	
	public List<Diary> readDiary(){
		List<Diary> diaryListItem = new ArrayList<Diary>();
		conn = DBManager.getConnection();
		try {
			String sql = "SELECT * FROM diary";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Diary diary = new Diary();
				diary.setDairyIndex(rs.getInt(1));
				diary.setUserCode(rs.getInt(2));
				diary.setContent(rs.getString(3));
				diary.setDiaryDate(rs.getTimestamp(4));
				diaryListItem.add(diary);
			}
		} catch (Exception e) {
			System.out.println("다이어리 읽기 오류");
			e.printStackTrace();
		}
		return diaryListItem;
	}

}
