package diary.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import diary.controller.Action;
import diary.model.Diary;
import diary.model.DiaryDAO;

public class DiaryReadAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // CORS 헤더 추가
        
        DiaryDAO dao = DiaryDAO.getInstance();
        List<Diary> diaryListItem = dao.readDiary();
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
