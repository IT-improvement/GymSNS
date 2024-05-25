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
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int index = rs.getInt(1);
				int categoryIndex = rs.getInt(2);
				int userCode = rs.getInt(3);
				String name = rs.getString(4);
				String categoryName = rs.getString(5);
				String content = rs.getString(6);
				Timestamp createDate = rs.getTimestamp(7);
				Timestamp modDate = rs.getTimestamp(8);
				
				exercises.add(new ExerciseResponseDto(index, categoryIndex, userCode, name, categoryName, content, createDate, modDate));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return exercises;
	}

	public ExerciseResponseDto findExerciseOne(ExerciseRequestDto exerciseDto) {
		ExerciseResponseDto exercise = null;
		String sql = "";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseDto.getIndex());
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int index = rs.getInt(1);
				int categoryIndex = rs.getInt(2);
				int userCode = rs.getInt(3);
				String name = rs.getString(4);
				String categoryName = rs.getString(5);
				String content = rs.getString(6);
				Timestamp createDate = rs.getTimestamp(7);
				Timestamp modDate = rs.getTimestamp(8);
				
				exercise = new ExerciseResponseDto(index, categoryIndex, userCode, name, categoryName, content, createDate, modDate);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return exercise;
	}

	public boolean createExercise(ExerciseRequestDto exerciseDto) {
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

	public boolean updateExercise(ExerciseRequestDto exerciseDto) {
		boolean isUpdated = true;
		String sql = "";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseDto.getIndex());
			pstmt.setInt(2, exerciseDto.getCategoryIndex());
			pstmt.setInt(3, exerciseDto.getUserCode());
			pstmt.setString(4, exerciseDto.getName());
			pstmt.setString(5, exerciseDto.getContent());
			
			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isUpdated = false;
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return isUpdated;
	}
}