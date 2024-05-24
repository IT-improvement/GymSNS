package foods.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import foods.model.FoodDao;
import user.controller.Action;

public class DeleteFoodAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int foodIndex = Integer.parseInt(request.getParameter("foodIndex"));

		FoodDao dao = FoodDao.getInstance();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		JSONObject object = new JSONObject();

		try {
			dao.deleteFood(foodIndex);
			object.put("status", 200);
			object.put("message", "음식 삭제 완료");
			System.out.println("음식 삭제 완료");
		} catch (Exception e) {
			response.getWriter().write(object.toString());
		}
	}
}
