package diet.controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import diet.model.DietDao;
import diet.model.DietResponseDto;
import user.controller.Action;

public class ReadAllDietAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		DietDao dao = DietDao.getInstance();

		int userCode = Integer.parseInt(request.getParameter("userCode"));

		List<DietResponseDto> diets = dao.getAllDietsByUserCode(userCode);

		JSONArray dietJsonArray = new JSONArray();

		for (DietResponseDto diet : diets) {
			JSONObject dietObj = new JSONObject();

			dietObj.put("dietIndex", diet.getDietIndex());
			dietObj.put("userCode", diet.getUserCode());
			dietObj.put("foodIndex", diet.getFoodIndex());
			dietObj.put("totalCalories", diet.getTotalCalories());
			dietObj.put("totalProtein", diet.getTotalProtein());
			dietObj.put("createDate", diet.getCreateDate());
			dietJsonArray.put(dietObj);
		}

		System.out.println(dietJsonArray);
		response.getWriter().write(dietJsonArray.toString());
	}
}
