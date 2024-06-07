package routine.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import diary.model.Diary;
import routine.controller.Action;
import routine.model.RoutineDAO;
import routine.model.RoutineResponseDTO;

public class RoutineReadAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
		
		RoutineDAO dao = RoutineDAO.getInstance();
		HttpSession session = request.getSession();
		int userCode = (int)session.getAttribute("code");
		List<RoutineResponseDTO> listItem = dao.readRoutine(userCode);
		
		JSONArray array = new JSONArray();
		
        for(RoutineResponseDTO routine : listItem) {
        	JSONObject object = new JSONObject();
        	object.put("name", routine.getName());
        	object.put("category", routine.getCategory());
        	object.put("day", routine.getDay());
        	object.put("exerciseIndex", routine.getExerciseIndex());
        	object.put("routineIndex", routine.getRoutineIndex());
        	array.put(object);
        }
        
        response.getWriter().write(array.toString());
	}
}
