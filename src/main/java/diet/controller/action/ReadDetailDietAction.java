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
		
		DietDao dao = DietDao.getInstance();
		
		int dietIndex = Integer.parseInt(request.getParameter("dietIndex"));
		
		try {
			DietResponseDto diet = dao.getDietByDietIndex(dietIndex);
			
			if(diet != null) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				JSONObject object = new JSONObject(diet);
				System.out.println(object);
				
				JSONObject jsonResponse = new JSONObject();
		        jsonResponse.put("status", 200);
		        jsonResponse.put("message", "식단 상세정보 가져오기 완료");
		        
		        response.getWriter().write(jsonResponse.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
