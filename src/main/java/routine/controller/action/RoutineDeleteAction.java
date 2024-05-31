package routine.controller.action;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import routine.controller.Action;
import routine.model.RoutineDAO;
import routine.model.RoutineRequestDTO;

public class RoutineDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());
		RoutineRequestDTO dto = new RoutineRequestDTO();
		RoutineDAO dao = RoutineDAO.getInstance();
		

        if (json.has("items")) {
            JSONArray items = json.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                String item = items.getString(i);
                System.out.println(item);
                String index[] = item.split("/");
                dto.setRoutineIndex(Integer.parseInt(index[0]));
                dto.setExerciseIndex(Integer.parseInt(index[1]));
                dao.deleteRoutine(dto);
            }
        }
	}
}
