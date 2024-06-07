package foodCategory.controller.action;

import foodCategory.model.FoodCategoryDao;
import foodCategory.model.FoodCategoryResponseDto;
import org.json.JSONObject;
import user.controller.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ReadDetailFoodCategoryAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int foodCategoryIndex = Integer.parseInt(request.getParameter("foodCategoryIndex"));
        FoodCategoryDao dao = FoodCategoryDao.getInstance();

        try{
            FoodCategoryResponseDto foodCategory = dao.getFoodCategoryByIndex(foodCategoryIndex);

            if(foodCategory != null){
                JSONObject foodCategoryObj = new JSONObject();
                foodCategoryObj.put("categoryName", foodCategory.getCategoryName());
                foodCategoryObj.put("categoryImageUrl", foodCategory.getCategoryImageUrl());

                response.getWriter().write(foodCategoryObj.toString());
            }else{
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("status", 404);
                jsonResponse.put("message", "카테고리를 찾을 수 없습니다.");
                response.getWriter().write(jsonResponse.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", 500);
            jsonResponse.put("message", "카테고리 상세정보 가져오기 실패");
            response.getWriter().write(jsonResponse.toString()); 
        }

    }
}
