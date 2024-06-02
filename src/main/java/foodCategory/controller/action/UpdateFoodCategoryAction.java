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

		try {
			String indexStr  = request.getParameter("foodCategoryIndex");
			String userCodeStr  = request.getParameter("userCode");

			System.out.println(indexStr);
			System.out.println(userCodeStr);

			if (indexStr  == null || userCodeStr  == null) {
				// 필수 매개변수가 null이면 오류 반환
				JSONObject jsonResponse = ApiResponseManager.getStatusObject(400, "Missing required parameters");
				response.getWriter().write(jsonResponse.toString());
				return;
			}

			int foodCategoryIndex = Integer.parseInt(indexStr );
			int userCode = Integer.parseInt(userCodeStr );

			String categoryName = request.getParameter("categoryName");
			String categoryImageUrl = request.getParameter("categoryImageUrl");

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
