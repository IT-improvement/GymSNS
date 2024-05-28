package foods.controller.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import foods.model.FoodDao;
import foods.model.FoodResponseDto;
import user.controller.Action;

public class ReadDetailFoodAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		int foodIndex = Integer.parseInt(request.getParameter("foodIndex"));
		FoodDao dao = FoodDao.getInstance();

		try {
			FoodResponseDto food = dao.getFoodByIndex(foodIndex);

			if (food != null) {
				JSONObject foodObj = new JSONObject();
				foodObj.put("foodIndex", food.getFoodIndex());
				foodObj.put("userCode", food.getUserCode());
				foodObj.put("foodCategoryIndex", food.getFoodCategoryIndex());
				foodObj.put("foodName", food.getFoodName());
				foodObj.put("protein", food.getProtein());
				foodObj.put("calory", food.getCalory());
				foodObj.put("carbs", food.getCarbs());
				foodObj.put("size", food.getSize());
				foodObj.put("createDate", food.getCreateDate());

				response.getWriter().write(foodObj.toString());
			} else {
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("status", 404);
				jsonResponse.put("message", "음식을 찾을 수 없습니다.");
				response.getWriter().write(jsonResponse.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", 500);
			jsonResponse.put("message", "음식 상세정보 가져오기 실패");
			response.getWriter().write(jsonResponse.toString());
		}
	}
}
