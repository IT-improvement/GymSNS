package diary.controller.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import diary.controller.Action;
import diary.model.DiaryDAO;
import diary.model.DiaryResponseDTO;

public class DiaryReadAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // CORS 헤더 추가
        int userCode = 123;
        Timestamp date = Timestamp.valueOf(request.getParameter("date"));
        System.out.println("date: "+date);
        DiaryDAO dao = DiaryDAO.getInstance();
        DiaryResponseDTO dto =  dao.readDiaryDate(userCode, date);
        JSONObject object = new JSONObject();
        object.put("diary_index", dto.getDairyIndex());
    	object.put("user_cod", dto.getUserCode());
    	object.put("content", dto.getContent());
    	object.put("diary_date", dto.getDiaryDate());
        response.getWriter().write(object.toString());
    }
}
