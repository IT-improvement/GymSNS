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
import util.ParameterValidator;

public class CreateFoodCategoryAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String userCodeStr = request.getHeader("Authorization");
		String categoryName = request.getParameter("categoryName");
		String categoryImageUrl = request.getParameter("categoryImageUrl");

		JSONObject resObj = new JSONObject();

		try {
			validateInput(userCodeStr, categoryName, categoryImageUrl);

			int userCode = Integer.parseInt(userCodeStr);

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

	private void validateInput(String userCodeStr, String categoryName, String categoryImageUrl) throws ValidationException {
		if (userCodeStr == null || !ParameterValidator.isInteger(userCodeStr)) {
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
