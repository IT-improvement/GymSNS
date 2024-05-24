package foodCategory.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import foodCategory.model.FoodCategoryDao;
import foodCategory.model.FoodCategoryRequestDto;
import foodCategory.model.FoodCategoryResponseDto;
import user.controller.Action;

public class ReadAllFoodCategoryAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FoodCategoryDao dao = FoodCategoryDao.getInstance();
		
		HttpSession session = request.getSession();
		
//		int userCode = session.getAttribute("userCode");
		int userCode = Integer.parseInt(request.getParameter("userCode"));
		
		List<FoodCategoryResponseDto> foodCategory = dao.getAllFoodCategoriesByUserCode(userCode);
		
		JSONArray jsonArray = new JSONArray(foodCategory);
//		for(FoodCategoryRequestDto foodCategory : foodCategories) {
//			JSONObject object = new JSONObject();
//			
//			object.put("foodCategory", foodCategory.getFoodCategoryIndex());
//			object.put("userCode", foodCategory.getUserCode());
//			object.put("categoryName", foodCategory.getCategoryName());
//			object.put("categoryImageUrl", foodCategory.getCategoryImageUrl());
//			
//			jsonArray.put(object);
//		}
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        System.out.println(jsonArray);
		
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", 200);
        jsonResponse.put("message", "카테고리 리스트 가져오기 완료");
        
        response.getWriter().write(jsonResponse.toString());
	}
	
}
