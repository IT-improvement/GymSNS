package foods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foods.model.Food;
import foods.model.FoodDao;

/**
 * Servlet implementation class CreateFood
 */
@WebServlet("/CreateFood")
public class CreateFood extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateFood() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String foodName = request.getParameter("foodName");
		int foodCategoryIndex = Integer.parseInt(request.getParameter("foodCategory"));
		int userCode = Integer.parseInt(request.getParameter("userCode"));
		int protein = Integer.parseInt(request.getParameter("protein"));
		int calory = Integer.parseInt(request.getParameter("calory"));
		int carbs = Integer.parseInt(request.getParameter("carbs"));
		int size = Integer.parseInt(request.getParameter("size"));

		Food food = new Food();

		food.setFoodName(foodName);
		food.setFoodCategoryIndex(foodCategoryIndex);
		food.setUserCode(userCode);
		food.setProtein(protein);
		food.setCalory(calory);
		food.setCarbs(carbs);
		food.setSize(size);

		FoodDao foodDao = new FoodDao();
		foodDao.insertFood(food);
		
		response.sendRedirect("");
	}
}
