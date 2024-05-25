package diet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void createDiet(Diet diet) throws SQLException {
        String query = "INSERT INTO diets (user_code, food_index, total_calories, total_protein, create_date, mod_date) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, diet.getUserCode());
            pstmt.setInt(2, diet.getFoodIndex());
            pstmt.setInt(3, diet.getTotalCalories());
            pstmt.setInt(4, diet.getTotalProtein());
            pstmt.setTimestamp(5, diet.getCreateDate());
            pstmt.setTimestamp(6, diet.getModDate());
            pstmt.executeUpdate();
        }catch (Exception e) {
        	e.printStackTrace();
        } finally {
            closeResources();
        }
    }

 // READ: 특정 식단을 ID로 조회
    public Diet getDietById(int dietIndex) throws SQLException {
        Diet diet = null;
        String query = "SELECT * FROM diets WHERE diet_index = ?";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, dietIndex);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                diet = new Diet();
                diet.setDietIndex(rs.getInt("diet_index"));
                diet.setUserCode(rs.getInt("user_code"));
                diet.setFoodIndex(rs.getInt("food_index"));
                diet.setTotalCalories(rs.getInt("total_calories"));
                diet.setTotalProtein(rs.getInt("total_protein"));
                diet.setCreateDate(rs.getTimestamp("create_date"));
                diet.setModDate(rs.getTimestamp("mod_date"));
            }
        } finally {
            closeResources();
        }
        return diet;
    }

    // READ: 모든 식단을 조회
    public List<Diet> getAllDiets() throws SQLException {
        List<Diet> diets = new ArrayList<>();
        String query = "SELECT * FROM diets";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Diet diet = new Diet();
                diet.setDietIndex(rs.getInt("diet_index"));
                diet.setUserCode(rs.getInt("user_code"));
                diet.setFoodIndex(rs.getInt("food_index"));
                diet.setTotalCalories(rs.getInt("total_calories"));
                diet.setTotalProtein(rs.getInt("total_protein"));
                diet.setCreateDate(rs.getTimestamp("create_date"));
                diet.setModDate(rs.getTimestamp("mod_date"));
                diets.add(diet);
            }
        } finally {
            closeResources();
        }
        return diets;
    }

    // UPDATE: 특정 식단을 업데이트
    public void updateDiet(Diet diet) throws SQLException {
        String query = "UPDATE diets SET user_code = ?, food_index = ?, total_calories = ?, total_protein = ?, create_date = ?, mod_date = ? WHERE diet_index = ?";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, diet.getUserCode());
            pstmt.setInt(2, diet.getFoodIndex());
            pstmt.setInt(3, diet.getTotalCalories());
            pstmt.setInt(4, diet.getTotalProtein());
            pstmt.setTimestamp(5, diet.getCreateDate());
            pstmt.setTimestamp(6, diet.getModDate());
            pstmt.setInt(7, diet.getDietIndex());
            pstmt.executeUpdate();
        } finally {
            closeResources();
        }
    }

    // DELETE: 특정 식단을 삭제
    public void deleteDiet(int dietIndex) throws SQLException {
        String query = "DELETE FROM diets WHERE diet_index = ?";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, dietIndex);
            pstmt.executeUpdate();
        } finally {
            closeResources();
        }
    }
    
}
