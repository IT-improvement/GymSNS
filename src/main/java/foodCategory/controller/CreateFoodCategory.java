package foodCategory.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import foodCategory.model.FoodCategoryDao;
import foodCategory.model.FoodCategoryRequestDto;

/**
 * Servlet implementation class CreateFoodCategory
 */
@WebServlet("/CreateFoodCategory")
public class CreateFoodCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CreateFoodCategory() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/createFoodCategoryForm").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			int userCode = (int) session.getAttribute("userCode");
		
			String categoryName = request.getParameter("categoryName");
	        String categoryImageUrl = request.getParameter("categoryImageUrl");

	        FoodCategoryRequestDto foodCategoryRequestDto = new FoodCategoryRequestDto();
	        foodCategoryRequestDto.setCategoryName(categoryName);
	        foodCategoryRequestDto.setUserCode(userCode);
	        foodCategoryRequestDto.setCategoryImageUrl(categoryImageUrl);

	        FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
	        foodCategoryDao.addFoodCategory(foodCategoryRequestDto);

	        response.sendRedirect("");
	        // create 후 보낼 페이지
	}
}
