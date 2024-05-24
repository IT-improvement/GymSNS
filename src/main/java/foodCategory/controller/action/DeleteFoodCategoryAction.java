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
		
		int foodCategoryIndex = Integer.parseInt(request.getParameter("foodCategoryIndex"));
		
		FoodCategoryDao dao = FoodCategoryDao.getInstance();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		JSONObject object = new JSONObject();
		
		try {
			dao.deleteFoodCategory(foodCategoryIndex);
			object.put("status", 200);
			object.put("message", "카테고리 삭제 완료");
			System.out.println("음식 카테고리 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
			object.put("status", 500);
			object.put("message", "카테고리 삭제 실패");
			System.out.println("음식 카테고리 삭제 실패");
		}
		response.getWriter().write(object.toString());
	}
}
