package diet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import foods.model.FoodRequestDto;
import util.DBManager;

public class DietDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DietDao() {

	}

	private static DietDao instance = new DietDao();

	public static DietDao getInstance() {
		return instance;
	}

	public DietDao(Connection conn) {
		this.conn = conn;
	}

	public void closeResources() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createDiet(DietRequestDto dietRequestDto) throws SQLException {
		String sql = "INSERT INTO diets (user_code, food_index, total_calories, total_protein) VALUES (?, ?, ?, ?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dietRequestDto.getUserCode());
			pstmt.setInt(2, dietRequestDto.getFoodIndex());
			pstmt.setInt(3, dietRequestDto.getTotalCalories());
			pstmt.setInt(4, dietRequestDto.getTotalProtein());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}

	public DietResponseDto getDietByDietIndex(int dietIndex) throws SQLException {
		String sql = "SELECT * FROM diets WHERE diet_index = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dietIndex);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new DietResponseDto(rs.getInt("diet_index"), 
						rs.getInt("user_code"), 
						rs.getInt("food_index"),
						rs.getInt("total_calories"), 
						rs.getInt("total_protein"), 
						rs.getTimestamp("create_date"),
						rs.getTimestamp("mod_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return null;
	}

	public List<DietResponseDto> getAllDietsByUserCode(int userCode) throws SQLException {
		List<DietResponseDto> diets = new ArrayList<>();
		String sql = "SELECT * FROM diets WHERE user_code = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				diets.add(new DietResponseDto(
						rs.getInt("diet_index"), 
						rs.getInt("user_code"), 
						rs.getInt("food_index"),
						rs.getInt("total_calories"), 
						rs.getInt("total_protein"), 
						rs.getTimestamp("create_date"),
						rs.getTimestamp("mod_date")
				));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return diets;
	}


	public boolean updateDiet(int dietIndex, DietRequestDto dietRequestDto) throws SQLException {
		
		String sql = "UPDATE diets SET user_code = ?, food_index = ?, total_calories = ?, total_protein = ?,  mod_date = ? WHERE diet_index = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dietRequestDto.getUserCode());
			pstmt.setInt(2, dietRequestDto.getFoodIndex());
			pstmt.setInt(3, dietRequestDto.getTotalCalories());
			pstmt.setInt(4, dietRequestDto.getTotalProtein());
			pstmt.setTimestamp(5, Timestamp.from(Instant.now()));
			pstmt.setInt(6, dietIndex);
			int rowsUpdated = pstmt.executeUpdate();
			return rowsUpdated > 0;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		} finally{
			closeResources();
		}
	}

	public void deleteDiet(int dietIndex) throws SQLException {
		String sql = "DELETE FROM diets WHERE diet_index = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dietIndex);
			pstmt.executeUpdate();
		} finally {
			closeResources();
		}
	}

	public boolean isDietExists(DietRequestDto dietDto) {
		try (Connection conn = DBManager.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM diets WHERE user_code = ? AND food_index = ? AND total_protein = ? AND total_calories = ?")) {

			pstmt.setInt(1, dietDto.getUserCode());
			pstmt.setInt(2, dietDto.getFoodIndex());
			pstmt.setInt(3, dietDto.getTotalProtein());
			pstmt.setInt(4, dietDto.getTotalCalories());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existsById(int dietIndex) throws SQLException {
		String sql = "SELECT COUNT(*) FROM diets WHERE diet_index=?";
		try (Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, dietIndex);
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
