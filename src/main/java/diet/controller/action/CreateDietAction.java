package diet.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import diet.model.DietDao;
import diet.model.DietRequestDto;
import user.controller.Action;

public class CreateDietAction implements Action {

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
		int foodIndex = object.getInt("foodIndex");
		int totalCalories = object.getInt("totalCalories");
		int totalProtein = object.getInt("totalProtein");

		DietDao dao = DietDao.getInstance();
		DietRequestDto dietDto = new DietRequestDto(userCode, foodIndex, totalCalories, totalProtein);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			dao.createDiet(dietDto);
			System.out.println("식단 생성 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("식단 생성 실패");
		}

		JSONObject jsonResponse = new JSONObject();

		jsonResponse.put("status", 200);
		jsonResponse.put("message", "식단 생성완료");

		response.getWriter().write(jsonResponse.toString());
	}
}
