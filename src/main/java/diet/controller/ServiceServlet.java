package diet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.Action;

public class ServiceServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		String command = request.getParameter("command");
		String dietIndex = request.getParameter("dietIndex"); 
		String userCode = request.getParameter("userCode");
		String foodIndex = request.getParameter("foodIndex");
		String totalCalories = request.getParameter("totalCalories");
		String totalProtein = request.getParameter("totalProtein");
		String createDate = request.getParameter("createDate");
		String modDate = request.getParameter("modDate");
		
		request.setAttribute("dietIndex", dietIndex);
		request.setAttribute("userCode", userCode);
		request.setAttribute("foodIndex", foodIndex);
		request.setAttribute("totalCalories", totalCalories);
		request.setAttribute("totalProtein", totalProtein);
		request.setAttribute("createDate", createDate);
		request.setAttribute("modDate", modDate);

		
		System.out.println("command: " + command);
		
		if(command != null) {
			DietActionFactory af = DietActionFactory.getInstance();
			Action action = af.getAction(command);
			
			if(action != null) {
				action.execute(request, response);
			}else {
				response.sendError(404);
			}
		}else {
			response.sendError(404);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
}
