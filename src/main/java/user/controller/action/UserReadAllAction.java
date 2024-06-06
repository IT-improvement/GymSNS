package user.controller.action;

import exercise.model.ExerciseResponseDto;
import friend.model.FriendDao;
import friend.model.FriendRequestDto;
import friend.model.FriendResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;
import user.controller.Action;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
import util.ParameterValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserReadAllAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String limit = request.getParameter("limit");

        UserDao userDao = UserDao.getInstance();

        List<UserResponseDto> users = ParameterValidator.isInteger(limit) ?
                userDao.findUserAllWithLimit(Integer.parseInt(limit)) :
                userDao.findUserAll();
//        List<UserResponseDto> users = userDao.findUserAll();

                JSONArray userJsonArr = new JSONArray();

        for (UserResponseDto user : users) {
            JSONObject userObj = new JSONObject();

            userObj.put("code", user.getCode());
            userObj.put("id", user.getId());
            userObj.put("name", user.getName());
            userObj.put("profileImage", user.getProfileImage());

            System.out.println("name" + user.getName());
            System.out.println("profileimage: " + user.getProfileImage());

            userJsonArr.put(userObj);
        }

        response.getWriter().write(userJsonArr.toString());
    }
}
