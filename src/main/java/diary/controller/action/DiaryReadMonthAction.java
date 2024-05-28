package diary.controller.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import diary.controller.Action;
import diary.model.Diary;
import diary.model.DiaryDAO;

public class DiaryReadMonthAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // CORS 헤더 추가
        int userCode = 123;
        Timestamp startMonth = Timestamp.valueOf(request.getParameter("startMonth"));
        Timestamp endMonth = Timestamp.valueOf(request.getParameter("endMonth"));
        DiaryDAO dao = DiaryDAO.getInstance();
        List<Diary> diaryListItem = dao.readDiaryMonth(userCode, startMonth, endMonth);
        JSONArray array = new JSONArray();
        for(Diary diary : diaryListItem) {
        	JSONObject object = new JSONObject();
        	object.put("diary_index", diary.getDairyIndex());
        	object.put("user_cod", diary.getUserCode());
        	object.put("content", diary.getContent());
        	object.put("diary_date", diary.getDiaryDate());
        	array.put(object);
        }
        
        response.getWriter().write(array.toString());
	}

}
