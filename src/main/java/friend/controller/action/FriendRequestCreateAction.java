package friend.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friend.controller.Action;
import org.json.JSONObject;

import friendRequest.model.FriendRequestDao;
import friendRequest.model.FriendRequestRequestDto;
import util.ApiResponseManager;
import util.ParameterValidator;

public class FriendRequestCreateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String userCodeStr = request.getHeader("Authorization");
		String userCodeOtherStr = request.getParameter("userCodeOther");

		JSONObject resObj = new JSONObject();

		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(userCodeOtherStr)) {
			response.sendError(400, "Bad Request");
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
	    int userCodeOther = Integer.parseInt(userCodeOtherStr);

		FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(userCode, userCodeOther);
		FriendRequestDao friendRequestDao = FriendRequestDao.getInstance();

		if (friendRequestDao.addFriendRequest(friendRequestDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Friend Request Create is finished successfully");
			response.getWriter().write(resObj.toString());
		} else {
			response.sendError(500, "Server Error");
		}
	}
}