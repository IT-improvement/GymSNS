package diary.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import diary.controller.Action;
import diary.model.Diary;
import diary.model.DiaryDAO;
import diary.model.DiaryResponseDTO;

public class DiaryReadGroup implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // CORS 헤더 추가

//        HttpSession session = request.getSession();
//		int userCode = (int)session.getAttribute("code");
        int userCode = 1;
        int number = Integer.parseInt(request.getParameter("number"));
        DiaryDAO dao = DiaryDAO.getInstance();
        List<DiaryResponseDTO> diaryListItem = dao.readDiaryGroup5(userCode, number);
        JSONArray array = new JSONArray();

        for (DiaryResponseDTO diary : diaryListItem) {
            JSONObject object = new JSONObject();
            object.put("diary_index", diary.getDairyIndex());
            object.put("user_code", diary.getUserCode()); 
            object.put("content", diary.getContent());
            object.put("diary_date", diary.getDiaryDate());
            array.put(object);
        }
        boolean check = dao.checkEnd(userCode, number);
        System.out.println("Check:"+check);
        JSONObject responseJson = new JSONObject();
        responseJson.put("diaries", array);
        responseJson.put("end", check);

        response.getWriter().write(responseJson.toString());
    }
}
