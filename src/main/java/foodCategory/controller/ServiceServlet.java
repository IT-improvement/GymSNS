package foodCategory.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.Action;


@MultipartConfig
public class ServiceServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		String foodCategoryIndex = request.getParameter("foodCategoryIndex");
		String userCode = request.getParameter("userCode");
		String categoryName = request.getParameter("categoryName");
		String categoryImageUrl = request.getParameter("categoryImageUrl");
		
		request.setAttribute("foodCategoryIndex", foodCategoryIndex);
		request.setAttribute("userCode", userCode);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("categoryImageUrl", categoryImageUrl);
		
		
		System.out.println("command: "+command);
		
		if (command != null) {
			FoodCategoryActionFactory af = FoodCategoryActionFactory.getInstance();
			Action action = af.getAction(command);

			if (action != null) {
				action.execute(request, response);
			} else {
				response.sendError(404);
			}
		} else {
			response.sendError(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
