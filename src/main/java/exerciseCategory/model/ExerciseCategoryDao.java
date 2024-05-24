package exerciseCategory.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class ExerciseCategoryDao {
	private static ExerciseCategoryDao instance = new ExerciseCategoryDao();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ExerciseCategoryDao() { }

	public static ExerciseCategoryDao getInstance() {
		return instance;
	}
	
	public List<ExerciseCategoryResponseDto> findExerciseCategoryAll(ExerciseCategoryRequestDto exerciseCategoryDto) {
		List<ExerciseCategoryResponseDto> exerciseCategories = new ArrayList<>();
		String sql = "";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseCategoryDto.getUserCode());
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int index = rs.getInt(1);
				int userCode = rs.getInt(2);
				String name = rs.getString(3);
				
				exerciseCategories.add(new ExerciseCategoryResponseDto(index, userCode, name));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return exerciseCategories;
	}

	public boolean addExerciseCategory(ExerciseCategoryRequestDto exerciseCategoryDto) {
		boolean isAdded = true;
		String sql = "";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseCategoryDto.getUserCode());
			
			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isAdded = false;
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return isAdded;
	}

	public boolean deleteExerciseCategory(ExerciseCategoryRequestDto exerciseCategoryDto) {
		boolean isDeleted = true;
		String sql = "";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseCategoryDto.getIndex());
			pstmt.setInt(2, exerciseCategoryDto.getUserCode());
			
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