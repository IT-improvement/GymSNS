package foodCategory.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import foodCategory.model.FoodCategoryDao;
import user.controller.Action;

public class DeleteFoodCategoryAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		JSONObject jsonResponse = new JSONObject();

		try {
			int foodCategoryIndex = Integer.parseInt(request.getParameter("foodCategoryIndex"));

			// 유효성 검사
			validateInput(foodCategoryIndex);

			FoodCategoryDao dao = FoodCategoryDao.getInstance();

			// 인덱스 존재 여부 확인
			if (!dao.existsById(foodCategoryIndex)) {
				jsonResponse.put("status", 404);
				jsonResponse.put("message", "카테고리가 존재하지 않습니다");
				System.out.println("카테고리가 존재하지 않습니다");
			} else {
				dao.deleteFoodCategory(foodCategoryIndex);
				jsonResponse.put("status", 200);
				jsonResponse.put("message", "카테고리 삭제 완료");
				System.out.println("음식 카테고리 삭제 완료");
			}

		} catch (ValidationException e) {
			jsonResponse.put("status", 400);
			jsonResponse.put("message", e.getMessage());
			System.out.println("유효성 검사 실패: " + e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.put("status", 500);
			jsonResponse.put("message", "카테고리 삭제 실패");
			System.out.println("음식 카테고리 삭제 실패");
		}

		response.getWriter().write(jsonResponse.toString());
	}

	private void validateInput(int foodCategoryIndex) throws ValidationException {
		if (foodCategoryIndex <= 0) {
			throw new ValidationException("Invalid food category index");
		}
	}

	// ValidationException 클래스 정의
	public class ValidationException extends Exception {
		public ValidationException(String message) {
			super(message);
		}
	}
}
