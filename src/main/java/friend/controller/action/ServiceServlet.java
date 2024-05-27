package friend.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
public class ServiceServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		System.out.println("get friends");
		String command = request.getParameter("command");
		ActionFactory af = ActionFactory.getInstance();
		
		Action action = af.getAction(command);
		
		if (action != null) {
			action.execute(request, response);
		} else {
			response.sendRedirect("/");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}