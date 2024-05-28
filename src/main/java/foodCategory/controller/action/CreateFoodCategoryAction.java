package foodCategory.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import foodCategory.model.FoodCategoryDao;
import foodCategory.model.FoodCategoryRequestDto;
import user.controller.Action;

public class CreateFoodCategoryAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
			JSONObject object = new JSONObject(data);

			int userCode = object.getInt("userCode");
			String categoryName = object.getString("categoryName");
			String categoryImageUrl = object.getString("categoryImageUrl");

			validateInput(userCode, categoryName, categoryImageUrl);

			FoodCategoryDao dao = FoodCategoryDao.getInstance();
			FoodCategoryRequestDto foodCategoryDto = new FoodCategoryRequestDto(userCode, categoryName, categoryImageUrl);

			dao.addFoodCategory(foodCategoryDto);
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", 200);
			jsonResponse.put("message", "카테고리 생성완료");
			response.getWriter().write(jsonResponse.toString());
		} catch (ValidationException e) {
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", 400);
			jsonResponse.put("message", e.getMessage());
			response.getWriter().write(jsonResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", 500);
			jsonResponse.put("message", "카테고리 생성 실패");
			response.getWriter().write(jsonResponse.toString());
		}
	}

	private void validateInput(int userCode, String categoryName, String categoryImageUrl) throws ValidationException {
		if (userCode <= 0) {
			throw new ValidationException("Invalid user code");
		}
		if (categoryName == null || categoryName.trim().isEmpty()) {
			throw new ValidationException("Category name is required");
		}
	}

	class ValidationException extends Exception {
		public ValidationException(String message) {
			super(message);
		}
	}
}
