package diet.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import diet.model.DietDao;
import diet.model.DietResponseDto;
import user.controller.Action;

public class ReadDetailDietAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		int dietIndex = Integer.parseInt(request.getParameter("dietIndex"));
		DietDao dao = DietDao.getInstance();

		try {
			DietResponseDto diet = dao.getDietByDietIndex(dietIndex);

			if (diet != null) {
				JSONObject dietObj = new JSONObject();
				dietObj.put("dietIndex", diet.getDietIndex());
				dietObj.put("userCode", diet.getUserCode());
				dietObj.put("foodIndex", diet.getFoodIndex());
				dietObj.put("totalCalories", diet.getTotalCalories());
				dietObj.put("totalProtein", diet.getTotalProtein());
				dietObj.put("createDate", diet.getCreateDate());

				response.getWriter().write(dietObj.toString());
			} else {
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("status", 404);
				jsonResponse.put("message", "식단을 찾을 수 없습니다.");
				response.getWriter().write(jsonResponse.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", 500);
			jsonResponse.put("message", "식단 상세정보 가져오기 실패");
			response.getWriter().write(jsonResponse.toString());
		}
	}
}
