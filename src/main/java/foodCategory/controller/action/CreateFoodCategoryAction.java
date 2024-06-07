package foodCategory.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import foodCategory.model.FoodCategoryDao;
import foodCategory.model.FoodCategoryRequestDto;
import user.controller.Action;
import util.ParameterValidator;
import util.ApiResponseManager;
import util.HttpRequestManager;
import util.ImgBB;

public class CreateFoodCategoryAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		HttpRequestManager requestManager = HttpRequestManager.getInstance();
		String body = requestManager.getRequestBodyFromClientRequest(request);
		JSONObject jsonObject = new JSONObject(body);

		String userCodeStr = request.getHeader("Authorization");

		System.out.println("userCode"+userCodeStr);

		String categoryName = request.getParameter("categoryName");
		System.out.println("categoryName:"+ categoryName);

		String imageBase64 = jsonObject.getString("image");

		JSONObject resObj = new JSONObject();

		try {
			validateInput(userCodeStr, categoryName, imageBase64);

			int userCode = Integer.parseInt(userCodeStr);

			// Upload the image and get the URL
			String imageURL = ImgBB.uploadImage(imageBase64);
			System.out.println("imageURL"+imageURL);

			FoodCategoryDao dao = FoodCategoryDao.getInstance();
			FoodCategoryRequestDto foodCategoryDto = new FoodCategoryRequestDto(userCode, categoryName, imageURL);

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

	private void validateInput(String userCodeStr, String categoryName, String imageBase64) throws ValidationException {
		if (userCodeStr == null || !ParameterValidator.isInteger(userCodeStr)) {
			throw new ValidationException("Invalid user code");
		}
		if (categoryName == null || categoryName.trim().isEmpty()) {
			throw new ValidationException("Category name is required");
		}
		if (imageBase64 == null || imageBase64.trim().isEmpty()) {
			throw new ValidationException("Image is required");
		}
	}

	class ValidationException extends Exception {
		public ValidationException(String message) {
			super(message);
		}
	}
}