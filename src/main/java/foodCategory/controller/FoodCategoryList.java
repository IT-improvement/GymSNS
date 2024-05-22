package foodCategory.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foodCategory.model.FoodCategory;
import foodCategory.model.FoodCategoryDao;
import foodCategory.model.FoodCategoryResponseDto;

/**
 * Servlet implementation class FoodCategoryList
 */
@WebServlet("/FoodCategoryList")
public class FoodCategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FoodCategoryList() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        int userCode = Integer.parseInt(request.getParameter("userCode"));

        FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
        List<FoodCategoryResponseDto> foodCategories = foodCategoryDao.getAllFoodCategoriesByUserCode(userCode);

        request.setAttribute("foodCategories", foodCategories);

        request.getRequestDispatcher("").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
