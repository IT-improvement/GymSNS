package user.controller.action;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserResponseDto;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        
        // JSON 파싱
        JSONObject jsonRequest = new JSONObject(sb.toString());
        String id = jsonRequest.getString("id");
        String password = jsonRequest.getString("password");

        System.out.println("id : " + id + ", password : " + password);
		 
		 boolean isValid = true;
		 if(id == null || id.equals(""))
			 isValid = false;
		 else if(password == null || password.equals(""))
			 isValid = false;
		 
		 JSONObject jsonResponse = new JSONObject();
		 
		 if(isValid) {
			 UserDao dao = UserDao.getInstance();
			 UserResponseDto user = dao.findUserByIdAndPassword(id, password);
			 
			 if(user != null) {
				 jsonResponse.put("status", 200);
				 jsonResponse.put("message", "로그인 성공");
			 } else {
				 jsonResponse.put("status", 401);
				 jsonResponse.put("message", "로그인 실패1");
			 }
		 } else {
			 jsonResponse.put("status", 401);
			 jsonResponse.put("message", "로그인 실패2");
		 }
		 
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().write(jsonResponse.toString());
		 
	}

}
