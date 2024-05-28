package foods.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class FoodDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public FoodDao() {
    	
    }
    
    private static FoodDao instance = new FoodDao();

    public static FoodDao getInstance() {
    	return instance;
    }
    
    public FoodDao(Connection conn) {
        this.conn = conn;
    }

    public void closeResources() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create
    public boolean insertFood(FoodRequestDto foodRequestDto) {
        String sql = "INSERT INTO foods (user_code, food_category_index, food_name, protein, calory, carbs, size, create_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodRequestDto.getUserCode());
            pstmt.setInt(2, foodRequestDto.getFoodCategoryIndex());
            pstmt.setString(3, foodRequestDto.getFoodName());
            pstmt.setInt(4, foodRequestDto.getProtein());
            pstmt.setInt(5, foodRequestDto.getCalory());
            pstmt.setInt(6, foodRequestDto.getCarbs());
            pstmt.setInt(7, foodRequestDto.getSize());
            pstmt.setTimestamp(8, foodRequestDto.getCreateDate());
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    // Read
    public FoodResponseDto getFoodByIndex(int foodIndex) {
        String sql = "SELECT * FROM foods WHERE food_index = ?";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodIndex);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new FoodResponseDto(
                        rs.getInt("food_index"),
                        rs.getInt("user_code"),
                        rs.getInt("food_category_index"),
                        rs.getString("food_name"),
                        rs.getInt("protein"),
                        rs.getInt("calory"),
                        rs.getInt("carbs"),
                        rs.getInt("size"),
                        rs.getTimestamp("create_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }

    public List<FoodResponseDto> getAllFoodsByUserCode(int userCode) {
        List<FoodResponseDto> foods = new ArrayList<>();
        String sql = "SELECT * FROM foods WHERE user_code = 1001 UNION SELECT * FROM foods WHERE user_code = ?";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	System.out.println("되는중");
                foods.add(new FoodResponseDto(
                        rs.getInt("food_index"),
                        rs.getInt("user_code"),
                        rs.getInt("food_category_index"),
                        rs.getString("food_name"),
                        rs.getInt("protein"),
                        rs.getInt("calory"),
                        rs.getInt("carbs"),
                        rs.getInt("size"),
                        rs.getTimestamp("create_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return foods;
    }

    // Update
    public boolean updateFood(int foodIndex,FoodRequestDto foodRequestDto) {
        String sql = "UPDATE foods SET user_code = ?, food_category_index = ?, food_name = ?, protein = ?, calory = ?, carbs = ?, size = ?, create_date = ? WHERE food_index = ?";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodRequestDto.getUserCode());
            pstmt.setInt(2, foodRequestDto.getFoodCategoryIndex());
            pstmt.setString(3, foodRequestDto.getFoodName());
            pstmt.setInt(4, foodRequestDto.getProtein());
            pstmt.setInt(5, foodRequestDto.getCalory());
            pstmt.setInt(6, foodRequestDto.getCarbs());
            pstmt.setInt(7, foodRequestDto.getSize());
            pstmt.setTimestamp(8, foodRequestDto.getCreateDate());
            pstmt.setInt(9, foodIndex);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    // Delete
    public boolean deleteFood(int foodIndex) {
        String sql = "DELETE FROM foods WHERE food_index = ?";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodIndex);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    public boolean isFoodExists(FoodRequestDto foodDto) {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM foods WHERE user_code = ? AND food_category_index = ? AND food_name = ? AND protein = ? AND calory = ? AND carbs = ? AND size = ?")) {

            pstmt.setInt(1, foodDto.getUserCode());
            pstmt.setInt(2, foodDto.getFoodCategoryIndex());
            pstmt.setString(3, foodDto.getFoodName());
            pstmt.setInt(4, foodDto.getProtein());
            pstmt.setInt(5, foodDto.getCalory());
            pstmt.setInt(6, foodDto.getCarbs());
            pstmt.setInt(7, foodDto.getSize());

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

    public boolean existsById(int foodIndex) {
        String sql = "SELECT COUNT(*) FROM foods WHERE food_index = ?";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, foodIndex);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}