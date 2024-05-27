package diary.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import diary.controller.Action;

public class DiaryReadAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // CORS 헤더 추가
        
        JSONObject object1 = new JSONObject();
        object1.put("status", "200");
        object1.put("message", "완료");
        JSONObject object2 = new JSONObject();
        object2.put("status", "400");
        object2.put("message", "badrequest");

        JSONArray array = new JSONArray();
        array.put(object1);
        array.put(object2);

        response.getWriter().write(array.toString());
    }
}
