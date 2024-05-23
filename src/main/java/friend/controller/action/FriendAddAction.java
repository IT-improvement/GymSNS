package friend.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import friend.model.FriendDao;
import friend.model.FriendRequestDto;

public class FriendAddAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newFriendCodeStr = request.getParameter("code");
		
		int newFriendCode = Integer.parseInt(newFriendCodeStr);

		FriendDao friendDao = FriendDao.getInstance();
		//FriendRequestDto friendDto = new FriendRequestDto(user.getCode());
		FriendRequestDto friendDto = new FriendRequestDto(1, newFriendCode);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		JSONObject resObj = new JSONObject();

		if (friendDao.addFriend(friendDto)) {
			resObj.put("status", 200);
			resObj.put("message", "Friend Add is finished successfully");
		} else {
			resObj.put("status", 500);
			resObj.put("message", "Server Error");
		}
		
		response.getWriter().write(resObj.toString());
	}
}
