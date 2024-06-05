package routineDiet.controller.action;

import routineDiet.controller.Action;
import routineDiet.model.RoutineDietDAO;
import routineDiet.model.RoutineDietRequestDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoutineDietCreateAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userCode = 123;
        String day = request.getParameter("day");
        int foodIndex =Integer.parseInt(request.getParameter("food"));

        RoutineDietRequestDTO dto = new RoutineDietRequestDTO();
        dto.setDay(day);
        dto.setFoodIndex(foodIndex);
        dto.setUserCode(userCode);

        RoutineDietDAO dao = RoutineDietDAO.getInstance();
        dao.inesrtRoutineDiet(dto);
    }
}
