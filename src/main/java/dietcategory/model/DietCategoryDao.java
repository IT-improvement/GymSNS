package dietcategory.model;

import exerciseCategory.model.ExerciseCategoryRequestDto;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DietCategoryDao {
    private static DietCategoryDao instance = new DietCategoryDao();
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private DietCategoryDao() {
    }

    public static DietCategoryDao getInstance() {
        return instance;
    }

    public List<DietCategoryResponseDto> findDietCategoryAll() {
        List<DietCategoryResponseDto> dietCategories = new ArrayList<>();
        String sql = "SELECT diet_category_index, name"
                + " FROM diet_categories";
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int index = rs.getInt(1);
                String name = rs.getString(2);

                dietCategories.add(new DietCategoryResponseDto(index, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return dietCategories;
    }

    public boolean createDietCategory(DietCategoryRequestDto dietCategoryDto) {
        boolean isAdded = true;
        String sql = "INSERT INTO diet_categories (user_code, name) " +
                "VALUES (?, ?)";

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);

            //pstmt.setInt(1, exerciseCategoryDto.getUserCode());
            pstmt.setString(2, dietCategoryDto.getName());

            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
            isAdded = false;
        } finally {
            DBManager.close(conn, pstmt);
        }

        return isAdded;
    }

    public boolean deleteDietCategory(DietCategoryRequestDto dietCategoryDto) {
        boolean isDeleted = true;
        String sql = "DELETE FROM diet_categories " +
                "WHERE diet_category_index = ? AND user_code = ?";

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, dietCategoryDto.getIndex());
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

    public boolean updateDietCategory(DietCategoryRequestDto dietCategoryDto) {
        boolean isUpdated = true;
        String sql = "UPDATE diet_categories "
                + "SET name = ? "
                + "WHERE diet_category_index = ? AND user_code = ?";

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, dietCategoryDto.getName());
            pstmt.setInt(2, dietCategoryDto.getIndex());
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

    public boolean doesCategoryExist(DietCategoryRequestDto dietCategoryDto) {
        boolean doesCategoryExist = false;
        String sql = "SELECT * FROM diet_categories "
                + "WHERE diet_category_index = ?";

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, dietCategoryDto.getIndex());

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
