package foods.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.Action;


@MultipartConfig
public class ServiceServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String command = request.getParameter("command");
        String foodIndex = request.getParameter("foodIndex");
        String userCode = request.getParameter("userCode");
        String foodCategoryIndex = request.getParameter("foodCategoryIndex");
        String foodName = request.getParameter("foodName");
        String protein = request.getParameter("protein");
        String calory = request.getParameter("calory");
        String carbs = request.getParameter("carbs");
        String size = request.getParameter("size");
        String createDate = request.getParameter("createDate");
        
        request.setAttribute("foodIndex", foodIndex);
        request.setAttribute("userCode", userCode);
        request.setAttribute("foodCategoryIndex", foodCategoryIndex);
        request.setAttribute("foodName", foodName);
        request.setAttribute("protein", protein);
        request.setAttribute("calory", calory);
        request.setAttribute("carbs", carbs);
        request.setAttribute("size", size);
        request.setAttribute("createDate", createDate);
        
        System.out.println("command: " + command);
        
        if (command != null) {
            FoodsActionFactory af = FoodsActionFactory.getInstance();
            Action action = af.getAction(command);

            if (action != null) {
                try {
                    action.execute(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                response.sendError(404);
            }
        } else {
            response.sendError(404);
        }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
