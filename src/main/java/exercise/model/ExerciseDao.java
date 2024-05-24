package exercise.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class ExerciseDao {
	private static ExerciseDao instance = new ExerciseDao();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ExerciseDao() { }

	public static ExerciseDao getInstance() {
		return instance;
	}
	
	public List<ExerciseResponseDto> findExerciseAll(ExerciseRequestDto exerciseDto) {
		List<ExerciseResponseDto> exercises = new ArrayList<>();
		String sql = "";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseDto.getUserCode());
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int index = rs.getInt(1);
				int categoryIndex = rs.getInt(2);
				int userCode = rs.getInt(3);
				String name = rs.getString(4);
				String content = rs.getString(5);
				Timestamp createDate = rs.getTimestamp(6);
				Timestamp modDate = rs.getTimestamp(7);
				
				exercises.add(new ExerciseResponseDto(index, categoryIndex, userCode, name, content, createDate, modDate));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return exercises;
	}

	public boolean addExercise(ExerciseRequestDto exerciseDto) {
		boolean isAdded = true;
		String sql = "";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseDto.getUserCode());
			
			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isAdded = false;
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return isAdded;
	}

	public boolean deleteExercise(ExerciseRequestDto exerciseDto) {
		boolean isDeleted = true;
		String sql = "";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseDto.getIndex());
			pstmt.setInt(2, exerciseDto.getUserCode());
			
			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isDeleted = false;
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return isDeleted;
	}
}