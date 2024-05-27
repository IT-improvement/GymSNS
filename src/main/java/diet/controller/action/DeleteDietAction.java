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

		int dietIndex = Integer.parseInt(request.getParameter("dietIndex"));
		DietDao dao = DietDao.getInstance();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		JSONObject object = new JSONObject();
		
		try {
			dao.deleteDiet(dietIndex);
			object.put("status", 200);
			object.put("message", "식단 삭제 완료");
			System.out.println("식단 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
			object.put("status", 500);
			object.put("message", "식단 삭제 실패");
			System.out.println("식단 삭제 실패");
		}
		response.getWriter().write(object.toString());
	}
}
