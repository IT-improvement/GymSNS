package foods.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import foods.model.FoodDao;
import foods.model.FoodResponseDto;
import user.controller.Action;

public class ReadAllFoodAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

		FoodDao dao = FoodDao.getInstance();

		HttpSession session = request.getSession();

//		int userCode = session.getAttribute("userCode");
		int userCode = Integer.parseInt(request.getParameter("userCode"));

		List<FoodResponseDto> foods = dao.getAllFoodsByUserCode(userCode);

		JSONArray foodJsonArray = new JSONArray();

		for(FoodResponseDto food : foods){
        	JSONObject foodObj = new JSONObject();

			foodObj.put("foodName", food.getFoodName());
			foodObj.put("calory", food.getCalory());
			foodObj.put("foodIndex", food.getFoodIndex());
			foodObj.put("size", food.getSize());
			foodObj.put("carbs", food.getCarbs());
			foodObj.put("protein", food.getProtein());
			foodObj.put("userCode", userCode);
			foodObj.put("createDate", food.getCreateDate());
			foodObj.put("foodCategoryIndex", food.getFoodCategoryIndex());
			foodJsonArray.put(foodObj);
		}

        System.out.println(foodJsonArray);
        response.getWriter().write(foodJsonArray.toString());
	}
}
