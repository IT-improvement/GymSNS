package routineDiet.controller.action;

import org.json.JSONArray;
import org.json.JSONObject;
import routineDiet.controller.Action;
import routineDiet.model.RoutineDietDAO;
import routineDiet.model.RoutineDietRequestDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class RoutineDietDeleteAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());
        RoutineDietRequestDTO dto = new RoutineDietRequestDTO();
        RoutineDietDAO dao = RoutineDietDAO.getInstance();

        if (json.has("items")) {
            JSONArray items = json.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                String item = items.getString(i);
                System.out.println(item);
                String index[] = item.split("/");
                dto.setRoutineIndex(Integer.parseInt(index[0]));
                dto.setFoodIndex(Integer.parseInt(index[1]));
                dao.deleteRoutineDiet(dto);
            }
        }
    }
}
