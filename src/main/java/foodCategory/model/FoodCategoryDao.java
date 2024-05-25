package foodCategory.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class FoodCategoryDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public FoodCategoryDao() {
    	
    }
    
    private static FoodCategoryDao instance = new FoodCategoryDao();
    
    public static FoodCategoryDao getInstance() {
    	return instance;
    }

    public FoodCategoryDao(Connection conn) {
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

    public void addFoodCategory(FoodCategoryRequestDto foodCategoryRequestDto) {
        String sql = "INSERT INTO food_categories (user_code, category_name, category_image_url, create_date) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodCategoryRequestDto.getUserCode());
            pstmt.setString(2, foodCategoryRequestDto.getCategoryName());
            pstmt.setString(3, foodCategoryRequestDto.getCategoryImageUrl());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public FoodCategoryResponseDto getFoodCategoryByIndex(int foodCategoryIndex) {
        String sql = "SELECT * FROM food_categories WHERE food_category_index = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodCategoryIndex);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new FoodCategoryResponseDto(
                        rs.getInt("food_category_index"),
                        rs.getInt("user_code"),
                        rs.getString("category_name"),
                        rs.getString("category_image_url"),
                        rs.getDate("create_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }
    
    public List<FoodCategoryResponseDto> getAllFoodCategoriesByUserCode(int userCode) {
        List<FoodCategoryResponseDto> foodCategories = new ArrayList<>();
        String sql = "SELECT * FROM food_categories WHERE user_code = ? OR user_code = 1001";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                foodCategories.add(new FoodCategoryResponseDto(
                        rs.getInt("food_category_index"),
                        rs.getInt("user_code"),
                        rs.getString("category_name"),
                        rs.getString("category_image_url"),
                        rs.getDate("create_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return foodCategories;
    }

    public int updateFoodCategory(int foodCategoryIndex, FoodCategoryRequestDto foodCategoryRequestDto) {
        String sql = "UPDATE food_categories SET category_name = ?, category_image_url = ?, user_code = ? WHERE food_category_index = ?";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, foodCategoryRequestDto.getCategoryName());
            pstmt.setString(2, foodCategoryRequestDto.getCategoryImageUrl());
            pstmt.setInt(3, foodCategoryRequestDto.getUserCode());
            pstmt.setInt(4, foodCategoryIndex);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return 0;
    }

    public int deleteFoodCategory(int foodCategoryIndex) {
        String sql = "DELETE FROM food_categories WHERE food_category_index = ?";
        try {
        	conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodCategoryIndex);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return 0;
    }
}

