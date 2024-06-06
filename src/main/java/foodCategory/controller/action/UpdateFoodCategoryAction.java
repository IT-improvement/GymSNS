package foodCategory.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;


import foodCategory.model.FoodCategoryDao;
import foodCategory.model.FoodCategoryRequestDto;
import user.controller.Action;
import util.ApiResponseManager;

public class UpdateFoodCategoryAction implements Action {

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

			int foodCategoryIndex = jsonRequest.getInt("foodCategoryIndex");
			int userCode = jsonRequest.getInt("userCode");
			String categoryName = jsonRequest.getString("categoryName");
			String categoryImageUrl = jsonRequest.getString("categoryImageUrl");

			FoodCategoryDao dao = FoodCategoryDao.getInstance();
			FoodCategoryRequestDto foodCategoryDto = new FoodCategoryRequestDto(userCode, categoryName, categoryImageUrl);

			if (dao.updateFoodCategory(foodCategoryIndex, foodCategoryDto)) {
				JSONObject jsonResponse = ApiResponseManager.getStatusObject(200, "Food Category Update is finished successfully");
				response.getWriter().write(jsonResponse.toString());
			} else {
				JSONObject jsonResponse = ApiResponseManager.getStatusObject(500, "Failed to update food category");
				response.getWriter().write(jsonResponse.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JSONObject jsonResponse = ApiResponseManager.getStatusObject(400, "Invalid input data");
			response.getWriter().write(jsonResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonResponse = ApiResponseManager.getStatusObject(500, "Failed to update food category");
			response.getWriter().write(jsonResponse.toString());
		}
	}
}
