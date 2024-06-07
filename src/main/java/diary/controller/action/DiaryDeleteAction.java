package diary.controller.action;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import diary.controller.Action;
import diary.model.DiaryDAO;
import diary.model.DiaryRequestDTO;

public class DiaryDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		int userCode = (int)session.getAttribute("code");
		int userCode = 1;
		StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());
		DiaryRequestDTO dto = new DiaryRequestDTO();
		DiaryDAO dao = DiaryDAO.getInstance();
        if (json.has("items")) {
            JSONArray items = json.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                int item = Integer.parseInt(items.getString(i));
                dao.deleteDiary(item);
            }
        }
	}
}
