package friend.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import friend.model.FriendDao;
import friend.model.FriendRequestDto;
import util.ApiResponseManager;

public class FriendAddAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String newFriendCodeStr = request.getParameter("code");
		int newFriendCode = Integer.parseInt(newFriendCodeStr);

		FriendDao friendDao = FriendDao.getInstance();
		//FriendRequestDto friendDto = new FriendRequestDto(user.getCode());
		FriendRequestDto friendDto = new FriendRequestDto(1, newFriendCode);
		
		JSONObject resObj = new JSONObject();
		
		if (friendDao.isFriend(friendDto)) {
			resObj = ApiResponseManager.getStatusObject(400);
		} else if (friendDao.addFriend(friendDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Friend Add is finished successfully");
			//new FriendRequestDeleteAction().execute(request, response);
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}
		
		response.getWriter().write(resObj.toString());
	}
}
