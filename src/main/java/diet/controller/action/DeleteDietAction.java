package diet.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import diet.model.DietDao;
import user.controller.Action;

public class DeleteDietAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			int dietIndex = Integer.parseInt(request.getParameter("dietIndex"));

			validateInput(dietIndex);

			DietDao dao = DietDao.getInstance();

			if (!dao.existsById(dietIndex)) {
				throw new ValidationException("Diet with the given index does not exist");
			}

			dao.deleteDiet(dietIndex);

			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", 200);
			jsonResponse.put("message", "식단 삭제 완료");
			response.getWriter().write(jsonResponse.toString());

			System.out.println("식단 삭제 완료");

		} catch (ValidationException e) {
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", 400);
			jsonResponse.put("message", e.getMessage());
			response.getWriter().write(jsonResponse.toString());

			System.out.println("유효성 검사 실패: " + e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", 500);
			jsonResponse.put("message", "식단 삭제 실패");
			response.getWriter().write(jsonResponse.toString());

			System.out.println("식단 삭제 실패");
		}
	}

	private void validateInput(int dietIndex) throws ValidationException {
		if (dietIndex <= 0) {
			throw new ValidationException("Invalid diet index");
		}
	}

	// ValidationException 클래스 정의
	public class ValidationException extends Exception {
		public ValidationException(String message) {
			super(message);
		}
	}
}
