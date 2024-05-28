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
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		FoodCategoryDao dao = FoodCategoryDao.getInstance();

		int userCode = Integer.parseInt(request.getParameter("userCode"));

		List<FoodCategoryResponseDto> foodCategories = dao.getAllFoodCategoriesByUserCode(userCode);

		JSONArray jsonArray = new JSONArray();

		for (FoodCategoryResponseDto foodCategory : foodCategories) {
			JSONObject foodCategoryObj = new JSONObject();

			foodCategoryObj.put("foodCategoryIndex", foodCategory.getFoodCategoryIndex());
			foodCategoryObj.put("userCode", foodCategory.getUserCode());
			foodCategoryObj.put("categoryName", foodCategory.getCategoryName());
			foodCategoryObj.put("categoryImageUrl", foodCategory.getCategoryImageUrl());

			jsonArray.put(foodCategoryObj);
		}

		System.out.println(jsonArray.toString());

		response.getWriter().write(jsonArray.toString());
	}
}
