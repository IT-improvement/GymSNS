package diary.model;

import java.sql.Connection;
import java.sql.Timestamp;

import util.DBManager;

public class DiaryDAO {

	private DiaryDAO() {

	}

	private static DiaryDAO instance = new DiaryDAO();

	public static DiaryDAO getInstance() {
		return instance;
	}
	
	public DiaryResponseDTO diaryAllRead() {
		DiaryResponseDTO responseDTO = new DiaryResponseDTO();
		
		return responseDTO;
	}
	
	public void writeDiary(DiaryRequestDTO dto) {
		int code = dto.getUserCode();
		String content = dto.getContent();
		Timestamp date = dto.getDiary_date();
		
		Connection conn = DBManager.getConnection();
		try {
			String sql = "INSERT INTO diary(user_code, content, diary_date)"
					+ "VALUES(?, ?, ?)";
		} catch (Exception e) {
		}
	}

}
