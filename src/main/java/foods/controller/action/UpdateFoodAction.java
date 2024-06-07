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

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"))) {
			StringBuilder jsonString = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jsonString.append(line);
			}

			JSONObject jsonRequest = new JSONObject(jsonString.toString());

			int foodIndex = jsonRequest.getInt("foodIndex");
			int userCode = jsonRequest.getInt("userCode");
			String foodName = jsonRequest.getString("foodName");
			int foodCategoryIndex = jsonRequest.getInt("foodCategoryIndex");
			int protein = jsonRequest.getInt("protein");
			int calory = jsonRequest.getInt("calory");
			int carbs = jsonRequest.getInt("carbs");
			int size = jsonRequest.getInt("size");

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