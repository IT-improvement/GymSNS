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

	public List<DiaryResponseDTO> readDiaryGroup5(int userCode, int page) {
		List<DiaryResponseDTO> list = new ArrayList<DiaryResponseDTO>();
		conn = DBManager.getConnection();
		int count = 0;
		int number = 5 * (page - 1);
		try {
			String sql = "select * from diary where user_code=? limit ?,5";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);
			pstmt.setInt(2, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				DiaryResponseDTO dto = new DiaryResponseDTO();
				dto.setDairyIndex(rs.getInt("diary_index"));
				dto.setContent(rs.getString("content"));
				dto.setDiaryDate(rs.getTimestamp("diary_date"));
				list.add(dto);
			}
			System.out.println("다이어리 읽기 완료");
		} catch (Exception e) {
			System.out.println("다이어리 읽기 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public boolean checkEnd(int userCode, int page) {
		conn = DBManager.getConnection();
		int count = 0;
		int number = 5 * (page - 1);
		try {
			String sql = "select * from diary where user_code=? limit ?,5";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);
			pstmt.setInt(2, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				System.out.println("count"+count);
			}
			System.out.println("다이어리 읽기 완료");
		} catch (Exception e) {
			System.out.println("다이어리 읽기 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count == 5 ? false : true;
	}

	public void writeDiary(DiaryRequestDTO dto) {
		conn = DBManager.getConnection();
		try {
			String sql = "INSERT INTO diary(user_code, content, diary_date)" + "VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserCode());
			pstmt.setString(2, dto.getContent());
			pstmt.setTimestamp(3, dto.getDiary_date());

			pstmt.execute();
			System.out.println("다이어리 생성 완료");
		} catch (Exception e) {
			System.out.println("다이어리 생성 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	public List<DiaryResponseDTO> readDiaryDate(int userCode, Timestamp date) {
		List<DiaryResponseDTO> list = new ArrayList<DiaryResponseDTO>();
		conn = DBManager.getConnection();
		System.out.println(date);
		try {
			String sql = "SELECT * FROM diary WHERE user_code=? and diary_date=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);
			pstmt.setTimestamp(2, date);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DiaryResponseDTO dto = new DiaryResponseDTO();
				dto.setDairyIndex(rs.getInt("diary_index"));
				dto.setUserCode(rs.getInt("user_code"));
				dto.setContent(rs.getString("content"));
				dto.setDiaryDate(rs.getTimestamp("diary_date"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("다이어리 읽기(날짜) 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public List<Diary> readDiaryMonth(int userCode, Timestamp startMonth, Timestamp endMonth) {
		List<Diary> diaryListItem = new ArrayList<Diary>();
		conn = DBManager.getConnection();
		System.out.println("성공");
		try {
			String sql = "SELECT * FROM diary WHERE user_code=? and diary_date BETWEEN ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);
			pstmt.setTimestamp(2, startMonth);
			pstmt.setTimestamp(3, endMonth);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Diary diary = new Diary();
				diary.setDairyIndex(rs.getInt("diary_index"));
				diary.setUserCode(rs.getInt("user_code"));
				diary.setContent(rs.getString("content"));
				diary.setDiaryDate(rs.getTimestamp("diary_date"));
				diaryListItem.add(diary);
			}
		} catch (Exception e) {
			System.out.println("다이어리 읽기(달) 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return diaryListItem;
	}

	public void updateDiary(int diaryIndex, String content) {
		conn = DBManager.getConnection();
		try {
			String sql = "UPDATE  diary SET content=? WHERE diary_index=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, diaryIndex);

			pstmt.execute();
		} catch (Exception e) {
			System.out.println("다이어리 수정 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	public void deleteDiary(int diaryIndex) {
		conn = DBManager.getConnection();
		try {
			String sql = "DELETE FROM diary WHERE diary_index=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, diaryIndex);

			pstmt.execute();
		} catch (Exception e) {
			System.out.println("다이어리 삭제 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

}
