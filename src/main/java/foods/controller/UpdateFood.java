package foods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foods.model.Food;
import foods.model.FoodDao;
import foods.model.FoodResponseDto;

/**
 * Servlet implementation class UpdateFood
 */
@WebServlet("/UpdateFood")
public class UpdateFood extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int foodIndex = Integer.parseInt(request.getParameter("foodIndex"));
		FoodDao foodDao = FoodDao.getInstance();
		FoodResponseDto food = foodDao.getFoodByIndex(foodIndex);
		
		request.setAttribute("food", food);
		request.getRequestDispatcher("").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int foodIndex = Integer.parseInt(request.getParameter("foodIndex"));
        String foodName = request.getParameter("foodName");
        int foodCategoryIndex = Integer.parseInt(request.getParameter("foodCategoryIndex"));
        int userCode = Integer.parseInt(request.getParameter("userCode"));
        int protein = Integer.parseInt(request.getParameter("protein"));
        int calory = Integer.parseInt(request.getParameter("calory"));
        int carbs = Integer.parseInt(request.getParameter("carbs"));
        int size = Integer.parseInt(request.getParameter("size"));

        Food food = new Food();
        food.setFoodIndex(foodIndex);
        food.setFoodName(foodName);
        food.setFoodCategoryIndex(foodCategoryIndex);
        food.setUserCode(userCode);
        food.setProtein(protein);
        food.setCalory(calory);
        food.setCarbs(carbs);
        food.setSize(size);

        FoodDao foodDao = new FoodDao();
        foodDao.updateFood(food);
	}
}
