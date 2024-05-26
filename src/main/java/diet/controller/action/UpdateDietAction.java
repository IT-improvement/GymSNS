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

public class UpdateDietAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream in = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
		
        String data = sb.toString();
        System.out.print("data : " + data);
        JSONObject object = new JSONObject(data);
        
        int dietIndex = object.getInt("dietIndex");
        // 나중에 물어보기 userCode 는 입력받는게 아니라 선택한 foodCategory 객체에 담겨있는
        // userCode 를 그대로 가져와서 쓰고싶은데 session.getAttribute를 써야 하는지
        // 일단 이걸로 postman에서 Body - raw - JSON 에서 정보 넣어서 하니 작동은됨
        int userCode = object.getInt("userCode");
        int foodIndex = object.getInt("foodIndex");
        int totalCalories = object.getInt("totalCalories");
        int totalProtein = object.getInt("totalProtein");
        
        DietDao dao = DietDao.getInstance();
        DietRequestDto dietDto = new DietRequestDto(dietIndex, userCode, foodIndex, totalCalories, totalProtein);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try {
            dao.updateDiet(dietIndex, dietDto);
            System.out.println("식단 업데이트 완료");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("식단 업데이트 실패");
        }
        
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", 200);
        jsonResponse.put("message", "식단 업데이트 완료");
        
        response.getWriter().write(jsonResponse.toString());
	}
}
