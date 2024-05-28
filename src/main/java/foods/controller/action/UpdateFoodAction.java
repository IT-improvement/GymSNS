package foods.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import foods.model.FoodDao;
import foods.model.FoodRequestDto;
import user.controller.Action;
import util.ApiResponseManager;

public class UpdateFoodAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String indexStr = request.getParameter("foodIndex");
			String userCodeStr = request.getParameter("userCode");

			if (indexStr == null || userCodeStr == null) {
				// 필수 매개변수가 null이면 오류 반환
				JSONObject jsonResponse = ApiResponseManager.getStatusObject(400, "Missing required parameters");
				response.getWriter().write(jsonResponse.toString());
				return;
			}

			int foodIndex = Integer.parseInt(indexStr);
			int userCode = Integer.parseInt(userCodeStr);

			String foodName = request.getParameter("foodName");
			int foodCategoryIndex = Integer.parseInt(request.getParameter("foodCategoryIndex"));
			int protein = Integer.parseInt(request.getParameter("protein"));
			int calory = Integer.parseInt(request.getParameter("calory"));
			int carbs = Integer.parseInt(request.getParameter("carbs"));
			int size = Integer.parseInt(request.getParameter("size"));

			FoodDao dao = FoodDao.getInstance();
			FoodRequestDto foodDto = new FoodRequestDto(userCode, foodCategoryIndex, foodName, protein, calory, carbs, size);

			if (dao.updateFood(foodIndex, foodDto)) {
				JSONObject jsonResponse = ApiResponseManager.getStatusObject(200, "Food Update is finished successfully");
				response.getWriter().write(jsonResponse.toString());
			} else {
				JSONObject jsonResponse = ApiResponseManager.getStatusObject(500, "Failed to update food");
				response.getWriter().write(jsonResponse.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JSONObject jsonResponse = ApiResponseManager.getStatusObject(400, "Invalid input data");
			response.getWriter().write(jsonResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonResponse = ApiResponseManager.getStatusObject(500, "Failed to update food");
			response.getWriter().write(jsonResponse.toString());
		}
	}
}
