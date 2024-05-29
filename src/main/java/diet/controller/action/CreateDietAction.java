package diet.controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foods.model.FoodDao;
import org.json.JSONObject;

import diet.model.DietDao;
import diet.model.DietRequestDto;
import user.controller.Action;

public class CreateDietAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
			JSONObject object = new JSONObject(data);

			int userCode = object.getInt("userCode");
			int foodIndex = object.getInt("foodIndex");
			int totalCalories = object.getInt("totalCalories");
			int totalProtein = object.getInt("totalProtein");

			validateInput(userCode, foodIndex, totalCalories, totalProtein);

			DietDao dao = DietDao.getInstance();
			DietRequestDto dietDto = new DietRequestDto(userCode, foodIndex, totalCalories, totalProtein);

			if (dao.isDietExists(dietDto)) {
				throw new ValidationException("동일한 정보의 식단이 이미 존재함");
			}

			dao.createDiet(dietDto);
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", 200);
			jsonResponse.put("message", "식단 생성완료");
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
			jsonResponse.put("message", "식단 생성 실패");
			response.getWriter().write(jsonResponse.toString());
		}
	}

	private void validateInput(int userCode, int foodIndex, int totalCalories, int totalProtein) throws ValidationException, SQLException {
		FoodDao foodDao = FoodDao.getInstance();
		if (userCode <= 0) {
			throw new ValidationException("Invalid user code");
		}
		if (!foodDao.existsById(foodIndex)) {
			throw new ValidationException("Food with the given index does not exist");
		}
		if (totalCalories < 0) {
			throw new ValidationException("Total calories should be a non-negative value");
		}
		if (totalProtein < 0) {
			throw new ValidationException("Total protein should be a non-negative value");
		}
	}

	class ValidationException extends Exception {
		public ValidationException(String message) {
			super(message);
		}
	}
}
