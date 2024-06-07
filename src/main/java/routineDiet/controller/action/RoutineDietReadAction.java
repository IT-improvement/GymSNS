package routineDiet.controller.action;

import org.json.JSONArray;
import org.json.JSONObject;
import routineDiet.controller.Action;
import routineDiet.model.RoutineDietDAO;
import routineDiet.model.RoutineDietResponseDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoutineDietReadAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        RoutineDietDAO dao = RoutineDietDAO.getInstance();
        int userCode = 123;
        List<RoutineDietResponseDTO> listItem = dao.readRoutineDiet(userCode);

        JSONArray array = new JSONArray();

        for(RoutineDietResponseDTO routine : listItem){
            JSONObject object = new JSONObject();
            object.put("name", routine.getName());
            object.put("day", routine.getDay());
            object.put("foodIndex", routine.getFoodIndex());
            object.put("mealTime", routine.getMealTime());
            object.put("routineIndex", routine.getRoutineIndex());
            array.put(object);
        }
        System.out.println("array:"+ array.toString());
        response.getWriter().write(array.toString());
    }
}
