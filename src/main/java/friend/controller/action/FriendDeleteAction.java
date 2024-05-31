package friend.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friend.controller.Action;
import org.json.JSONObject;

import friend.model.FriendDao;
import friend.model.FriendRequestDto;
import util.ApiResponseManager;
import util.ParameterValidator;

public class FriendDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String userCodeStr = request.getHeader("Authorization");
		String userCodeFriendStr = request.getParameter("userCodeFriend");

		JSONObject resObj = new JSONObject();

		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(userCodeFriendStr)) {
			response.sendError(400, "Bad Request");
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		int userCodeFriend = Integer.parseInt(userCodeFriendStr);

		FriendDao friendDao = FriendDao.getInstance();
		FriendRequestDto friendDto = new FriendRequestDto(userCode, userCodeFriend);
		
		if (friendDao.deleteFriendById(friendDto)) {
			ApiResponseManager.getStatusObject(200, "Friend Delete is finished successfully");
			response.getWriter().write(resObj.toString());
		} else {
			response.sendError(500, "Server Error");
		}
	}
}
