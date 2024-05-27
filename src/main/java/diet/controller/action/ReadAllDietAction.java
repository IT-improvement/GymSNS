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
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DietDao dao = DietDao.getInstance();
		
//		int userCode = session.getAttribute("userCode");
		int userCode = Integer.parseInt(request.getParameter("userCode"));
		
		List<DietResponseDto> diet;
		try {
			diet = dao.getAllDietsByUserCode(userCode);
			
			JSONArray jsonArray = new JSONArray(diet);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			System.out.println(jsonArray);
			
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", 200);
			jsonResponse.put("message", "식단 리스트 가져오기 완료");
			
			response.getWriter().write(jsonResponse.toString());		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
