package friend.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import friendRequest.model.FriendRequestDao;
import friendRequest.model.FriendRequestRequestDto;
import util.ApiResponseManager;

public class FriendRequestAddAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String userCodeOtherStr = request.getParameter("code");
	    int userCodeOther = Integer.parseInt(userCodeOtherStr);

		//FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(user.getCode(), userCodeOther);
		FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(1, userCodeOther);
		FriendRequestDao friendRequestDao = FriendRequestDao.getInstance();

		JSONObject resObj = new JSONObject();

		if (friendRequestDao.addFriendRequest(friendRequestDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Friend Request Add is finished successfully");
		} else {
			resObj = ApiResponseManager.getStatusObject(404);
		}
			
		response.getWriter().write(resObj.toString());
	}
}
