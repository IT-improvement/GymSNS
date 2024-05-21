package foods.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foods.model.Food;
import foods.model.FoodDao;

/**
 * Servlet implementation class FoodList
 */
@WebServlet("/FoodList")
public class FoodList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int userCode = Integer.parseInt(request.getParameter("userCode"));
		
		FoodDao foodDao = FoodDao.getInstance();
		List<Food> foodList = foodDao.getAllFoodsByUserCode(userCode);
		
		request.setAttribute("foodList", foodList);
		request.getRequestDispatcher("").forward(request, response);
	}
}
