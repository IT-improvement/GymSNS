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
        String codeString = request.getParameter("code");
        int code = Integer.parseInt(codeString);
        System.out.println(code + "1234");

//        StringBuilder sb = new StringBuilder();
//        BufferedReader reader = request.getReader();
//
//        String line = "";
//        while ((line = reader.readLine()) != null) {
//            sb.append(line);
//        }

        UserDao userDao = UserDao.getInstance();
//        JSONObject jsonRequest = new JSONObject(sb.toString());
        JSONObject jsonRequest = new JSONObject();

        System.out.println("1");
//        String id = jsonRequest.getString("id");
//        System.out.println("id) " + id);
        User user = userDao.findUserByCode(code);
        System.out.println("2");
        System.out.println(user);

        JSONObject jsonResponse = new JSONObject();

        if (user != null) {
//            JSONObject userObj = new JSONObject();
            jsonResponse.put("code", code);
            jsonResponse.put("id", user.getId());
            jsonResponse.put("password", user.getPassword());
            jsonResponse.put("email", user.getEmail());
            jsonResponse.put("name", user.getName());
            jsonResponse.put("birth", user.getBirth());
            jsonResponse.put("gender", user.getGender());
            jsonResponse.put("telecom", user.getTelecom());
            jsonResponse.put("phone", user.getPhone());
            jsonResponse.put("profileImage", user.getProfileImage());

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
