package foods.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import foods.model.FoodDao;
import foods.model.FoodResponseDto;
import user.controller.Action;

public class ReadAllFoodAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FoodDao dao = FoodDao.getInstance();

		HttpSession session = request.getSession();
		
//		int userCode = session.getAttribute("userCode");
		int userCode = Integer.parseInt(request.getParameter("userCode"));		
		
		List<FoodResponseDto> food = dao.getAllFoodsByUserCode(userCode);
		
		JSONArray jsonArray = new JSONArray(food);
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        System.out.println(jsonArray);
		
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", 200);
        jsonResponse.put("message", "음식 리스트 가져오기 완료");
        
        response.getWriter().write(jsonResponse.toString());
		
	}
}
