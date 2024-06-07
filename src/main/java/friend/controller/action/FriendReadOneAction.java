package friend.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friend.controller.Action;
import org.json.JSONObject;

import friend.model.FriendDao;
import friend.model.FriendRequestDto;
import friend.model.FriendResponseDto;
import util.ApiResponseManager;
import util.ParameterValidator;

public class FriendReadOneAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String userCodeStr = request.getHeader("Authorization");
		String userCodeFriendStr = request.getParameter("userCodeFriend");

		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(userCodeFriendStr)) {
			response.sendError(400, "Bad Request");
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		int userCodeFriend = Integer.parseInt(userCodeFriendStr);

		FriendDao friendDao = FriendDao.getInstance();
		FriendRequestDto friendDto = new FriendRequestDto(userCode, userCodeFriend);
		FriendResponseDto friend = friendDao.findFriendByUserCode(friendDto);

		JSONObject friendObj = new JSONObject();

		friendObj.put("index", friend.getIndex());
		friendObj.put("userCode", friend.getUserCodeFriend());
		friendObj.put("userId", friend.getUserId());
		friendObj.put("userName", friend.getUserName());
		friendObj.put("userProfileImage", friend.getUserProfileImage());

		response.getWriter().write(friendObj.toString());
	}
}