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
			resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
	    int userCodeOther = Integer.parseInt(userCodeOtherStr);

		FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(userCode, userCodeOther);
		FriendRequestDao friendRequestDao = FriendRequestDao.getInstance();

		if (friendRequestDao.addFriendRequest(friendRequestDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Friend Request Create is finished successfully");
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}
			
		response.getWriter().write(resObj.toString());
	}
}