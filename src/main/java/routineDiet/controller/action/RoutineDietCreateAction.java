package routineDiet.controller.action;

import routineDiet.controller.Action;
import routineDiet.model.RoutineDietDAO;
import routineDiet.model.RoutineDietRequestDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RoutineDietCreateAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int userCode = 1;
        String day = request.getParameter("day");
        int foodIndex =Integer.parseInt(request.getParameter("food"));
        String mealTime = request.getParameter("meal_time");

        System.out.println("foodIndex:" + foodIndex);
        System.out.println("meal_time:" + mealTime);

        RoutineDietRequestDTO dto = new RoutineDietRequestDTO();
        dto.setDay(day);
        dto.setFoodIndex(foodIndex);
        dto.setUserCode(userCode);
        dto.setMealTime(mealTime);

        RoutineDietDAO dao = RoutineDietDAO.getInstance();

        dao.inesrtRoutineDiet(dto);
    }
}
