package foods.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public boolean insertFood(Food food) {
        String sql = "INSERT INTO foods (user_code, food_category_index, food_name, protein, calory, carbs, size, create_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, food.getUserCode());
            pstmt.setInt(2, food.getFoodCategoryIndex());
            pstmt.setString(3, food.getFoodName());
            pstmt.setInt(4, food.getProtein());
            pstmt.setInt(5, food.getCalory());
            pstmt.setInt(6, food.getCarbs());
            pstmt.setInt(7, food.getSize());
            pstmt.setTimestamp(8, food.getCreateDate());
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

    public List<Food> getAllFoodsByUserCode(int userCode) {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM foods WHERE user_code = 0 UNION SELECT * FROM foods WHERE user_code = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                foods.add(new Food(
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
    public boolean updateFood(Food food) {
        String sql = "UPDATE foods SET user_code = ?, food_category_index = ?, food_name = ?, protein = ?, calory = ?, carbs = ?, size = ?, create_date = ? WHERE food_index = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, food.getUserCode());
            pstmt.setInt(2, food.getFoodCategoryIndex());
            pstmt.setString(3, food.getFoodName());
            pstmt.setInt(4, food.getProtein());
            pstmt.setInt(5, food.getCalory());
            pstmt.setInt(6, food.getCarbs());
            pstmt.setInt(7, food.getSize());
            pstmt.setTimestamp(8, food.getCreateDate());
            pstmt.setInt(9, food.getFoodIndex());
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
}