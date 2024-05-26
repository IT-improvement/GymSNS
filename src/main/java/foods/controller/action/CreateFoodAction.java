package foods.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import foods.model.FoodDao;
import foods.model.FoodRequestDto;
import user.controller.Action;

public class CreateFoodAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		InputStream in = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String data = "";
		while (br.ready()) {
			data += br.readLine();
		}

		System.out.print("data : " + data);
		JSONObject object = new JSONObject(data);

		int userCode = object.getInt("userCode");
		int foodCategoryIndex = object.getInt("foodCategoryIndex");
		String foodName = object.getString("foodName");
		int protein = object.getInt("protein");
		int calory = object.getInt("calory");
		int carbs = object.getInt("carbs");
		int size = object.getInt("size");

		FoodDao dao = FoodDao.getInstance();
		FoodRequestDto foodDto = new FoodRequestDto(userCode, foodCategoryIndex, foodName, protein, calory, carbs,
				size);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			dao.insertFood(foodDto);
			System.out.println("음식 생성 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("음식 생성 실패");
		}

		JSONObject jsonResponse = new JSONObject();

		jsonResponse.put("status", 200);
		jsonResponse.put("message", "음식 생성완료");

		response.getWriter().write(jsonResponse.toString());
	}

}
