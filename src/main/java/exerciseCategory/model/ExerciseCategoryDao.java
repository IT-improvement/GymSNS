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

	public List<ExerciseCategoryResponseDto> findExerciseCategoryAll() {
		List<ExerciseCategoryResponseDto> exerciseCategories = new ArrayList<>();
		String sql = "SELECT exercise_category_index, name "
					+ "FROM exercise_categories ";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int index = rs.getInt(1);
				String name = rs.getString(2);
				
				exerciseCategories.add(new ExerciseCategoryResponseDto(index, name));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return exerciseCategories;
	}

	public boolean createExerciseCategory(ExerciseCategoryRequestDto exerciseCategoryDto) {
		boolean isAdded = true;
		String sql = "INSERT INTO exercise_categories (user_code, name) " +
					"VALUES (?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			//pstmt.setInt(1, exerciseCategoryDto.getUserCode());
			pstmt.setString(2, exerciseCategoryDto.getName());

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
		String sql = "DELETE FROM exercise_categories " +
					"WHERE exercise_category_index = ? AND user_code = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseCategoryDto.getIndex());
			//pstmt.setInt(2, exerciseCategoryDto.getUserCode());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isDeleted = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isDeleted;
	}

	public boolean updateExerciseCategory(ExerciseCategoryRequestDto exerciseCategoryDto) {
		boolean isUpdated = true;
		String sql = "UPDATE exercise_categories "
				+ "SET name = ? "
				+ "WHERE exercise_category_index = ? AND user_code = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, exerciseCategoryDto.getName());
			pstmt.setInt(2, exerciseCategoryDto.getIndex());
			//pstmt.setInt(2, exerciseCategoryDto.getUserCode());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isUpdated = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isUpdated;
	}

	public boolean doesCategoryExist(ExerciseCategoryRequestDto exerciseCategoryDto) {
		boolean doesCategoryExist = false;
		String sql = "SELECT * FROM exercise_categories "
				+ "WHERE exercise_category_index = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseCategoryDto.getIndex());

			rs = pstmt.executeQuery();

			if (rs.next())
				doesCategoryExist = true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt);
		}

		return doesCategoryExist;
	}
}