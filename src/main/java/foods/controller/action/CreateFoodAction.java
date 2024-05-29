package foods.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foodCategory.model.FoodCategoryDao;
import org.json.JSONObject;

import foods.model.FoodDao;
import foods.model.FoodRequestDto;
import user.controller.Action;

public class CreateFoodAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
			JSONObject object = new JSONObject(data);

			int userCode = object.getInt("userCode");
			int foodCategoryIndex = object.getInt("foodCategoryIndex");
			String foodName = object.getString("foodName");
			int protein = object.getInt("protein");
			int calory = object.getInt("calory");
			int carbs = object.getInt("carbs");
			int size = object.getInt("size");

			validateInput(userCode, foodCategoryIndex, foodName, protein, calory, carbs, size);

			FoodDao dao = FoodDao.getInstance();
			FoodRequestDto foodDto = new FoodRequestDto(userCode, foodCategoryIndex, foodName, protein, calory, carbs, size);

			if (dao.isFoodExists(foodDto)) {
				throw new ValidationException("The food already exists");
			}

			dao.insertFood(foodDto);
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", 200);
			jsonResponse.put("message", "음식 생성완료");
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
			jsonResponse.put("message", "음식 생성 실패");
			response.getWriter().write(jsonResponse.toString());
		}
	}

	private void validateInput(int userCode, int foodCategoryIndex, String foodName, int protein, int calory, int carbs, int size) throws ValidationException {
		FoodCategoryDao categoryDao = FoodCategoryDao.getInstance();
		if (userCode <= 0) {
			throw new ValidationException("Invalid user code");
		}
		if (!categoryDao.existsById(foodCategoryIndex)) {
			throw new ValidationException("Food category with the given index does not exist");
		}
		if (foodName == null || foodName.trim().isEmpty()) {
			throw new ValidationException("Food name is required");
		}
		if (protein < 0) {
			throw new ValidationException("Protein should be a non-negative value");
		}
		if (calory < 0) {
			throw new ValidationException("Calory should be a non-negative value");
		}
		if (carbs < 0) {
			throw new ValidationException("Carbs should be a non-negative value");
		}
		if (size <= 0) {
			throw new ValidationException("Size should be a positive value");
		}
	}

	class ValidationException extends Exception {
		public ValidationException(String message) {
			super(message);
		}
	}
}
