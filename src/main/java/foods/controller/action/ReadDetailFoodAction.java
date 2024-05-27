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

		FoodDao dao = FoodDao.getInstance();

		int foodIndex = Integer.parseInt(request.getParameter("foodIndex"));

		try {
			FoodResponseDto food = dao.getFoodByIndex(foodIndex);

			if (food != null) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				JSONObject object = new JSONObject(food);
				
//				int foodIndex = object.getInt("foodIndex");
//				int userCode = object.getInt("userCode");
//				int foodCategoryIndex = object.getInt("foodCategoryIndex");
//				String foodName = object.getString("foodName");
//				int protein = object.getInt("protein");
//				int calory = object.getInt("calory");
//				int carbs = object.getInt("carbs");
//				int size = object.getInt("size");
//				String createDate = object.getString("createDate");
				response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        
				System.out.println(object);
				
				JSONObject jsonResponse = new JSONObject();
		        jsonResponse.put("status", 200);
		        jsonResponse.put("message", "음식 상세정보 가져오기 완료");
		        
		        response.getWriter().write(jsonResponse.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
