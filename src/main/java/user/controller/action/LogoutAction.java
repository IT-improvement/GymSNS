package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import user.controller.Action;
import user.model.User;
import user.model.UserDao;

public class LogoutAction extends HttpServlet implements Action {
    private static final long serialVersionUID = 1L;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonResponse = new JSONObject();
		
		try {
            jsonResponse.put("status", 200);
            jsonResponse.put("message", "로그아웃 성공");
            System.out.println("로그아웃 완료");

		} catch (Exception e) {
			e.printStackTrace();
			
			jsonResponse.put("status", 500);
            jsonResponse.put("message", "서버 오류");
            System.out.println("로그아웃 실패: 서버 오류");
		}
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
		
	}
	
}