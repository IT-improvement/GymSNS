package foodCategory.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import foodCategory.model.FoodCategoryDao;
import foodCategory.model.FoodCategoryRequestDto;
import user.controller.Action;

public class CreateFoodCategoryAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InputStream in = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		String data = "";
		while(br.ready()) {
			data += br.readLine();
		}
		
		System.out.print("data : " + data);
		JSONObject object = new JSONObject(data);
		
		int userCode = object.getInt("userCode");
		String categoryName = object.getString("categoryName");
		String categoryImageUrl = object.getString("categoryImageUrl");
		
		FoodCategoryDao dao = FoodCategoryDao.getInstance();
		FoodCategoryRequestDto foodCategoryDto = new FoodCategoryRequestDto(userCode, categoryName, categoryImageUrl);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		try {
			dao.addFoodCategory(foodCategoryDto);
			System.out.println("음식 카테고리 생성 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("음식 카테고리 생성 실패");
		}
		
		JSONObject jsonResponse = new JSONObject();
		
		jsonResponse.put("status", 200);
		jsonResponse.put("message", "카테고리 생성완료");
		
		response.getWriter().write(jsonResponse.toString());
	}
}
