package friend.controller.action;

import friend.controller.Action;
import friend.model.FriendDao;
import friend.model.FriendRequestDto;
import friendRequest.model.FriendRequestDao;
import friendRequest.model.FriendRequestRequestDto;
import org.json.JSONObject;
import util.ApiResponseManager;
import util.ParameterValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FriendRequestAcceptAction implements Action {
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

		FriendRequestDao friendRequestDao = FriendRequestDao.getInstance();
		FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(userCode, userCodeOther);

		FriendDao friendDao = FriendDao.getInstance();
		FriendRequestDto friendDto = new FriendRequestDto(userCode, userCodeOther);

		if (!friendDao.isFriend(friendDto) &&
				friendRequestDao.deleteFriendRequest(friendRequestDto) &&
				friendDao.createFriend(friendDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Friend Request Accept is finished successfully");
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}

		response.getWriter().write(resObj.toString());
	}
}