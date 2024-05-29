package user.controller.action;

import org.json.JSONObject;
import user.controller.Action;
import user.model.User;
import user.model.UserDao;
import user.model.UserResponseDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

public class UserReadOneAction extends HttpServlet implements Action {
    private static final long serialVersionUID = 1L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //HttpSession session = request.getSession();

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();

        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        UserDao userDao = UserDao.getInstance();
        JSONObject jsonRequest = new JSONObject(sb.toString());

        String id = jsonRequest.getString("id");
        System.out.println("id) " + id);
        User user = userDao.findUserById(id);

        JSONObject jsonResponse = new JSONObject();

        if (user != null) {
            JSONObject userObj = new JSONObject();
            userObj.put("code", user.getCode());
            userObj.put("id", user.getId());
            userObj.put("password", user.getPassword());
            userObj.put("email", user.getEmail());
            userObj.put("name", user.getName());
            userObj.put("birth", user.getBirth());
            userObj.put("gender", user.getGender());
            userObj.put("telecom", user.getTelecom());
            userObj.put("phone", user.getPhone());
            userObj.put("profile_image", user.getProfileImage());

            System.out.println("id : " + user.getId());
            System.out.println("name : " + user.getName());
            jsonResponse.put("status", 200);
            jsonResponse.put("message", "유저정보 불러오기 성공");
        } else {
            jsonResponse.put("status", 401);
            jsonResponse.put("message", "User not found");
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
    }
}
