package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import user.controller.Action;

public class LogoutAction extends HttpServlet implements Action {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonResponse = new JSONObject();
		
		try {
			HttpSession session = request.getSession();
            
            if (session != null && session.getAttribute("id") != null) {
                session.invalidate();
                jsonResponse.put("status", 200);
                jsonResponse.put("message", "로그아웃 성공");
                System.out.println("로그아웃 완료");
                
            } else {
                jsonResponse.put("status", 401);
                jsonResponse.put("message", "로그인되지 않은 사용자입니다.");
                System.out.println("로그아웃 실패: 로그인되지 않은 사용자");
            }
			
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