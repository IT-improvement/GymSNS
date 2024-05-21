package foodCategory.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foodCategory.model.FoodCategoryRequestDto;

/**
 * Servlet implementation class CreateFoodCategory
 */
@WebServlet("/CreateFoodCategory")
public class CreateFoodCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryName = request.getParameter("categoryName");
		String categoryImageUrl = request.getParameter("categoryImageUrl");
		
//		FoodCategoryRequestDto newCategory = 
				
	}
}
