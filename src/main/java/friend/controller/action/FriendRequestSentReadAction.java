package friend.controller.action;

import friend.controller.Action;
import friendRequest.model.FriendRequestDao;
import friendRequest.model.FriendRequestRequestDto;
import friendRequest.model.FriendRequestResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;
import util.ApiResponseManager;
import util.ParameterValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 유저가 다른 유저에게 친구 요청을 보냈는지 여부
public class FriendRequestSentReadAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

		String userCodeStr = request.getHeader("Authorization");
		String userCodeOtherStr = request.getParameter("code");

		JSONObject resObj = new JSONObject();

		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(userCodeOtherStr)) {
			response.sendError(400, "Bad Request");
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		int userCodeOther = Integer.parseInt(userCodeOtherStr);

		FriendRequestDao friendRequestDao = FriendRequestDao.getInstance();
		FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(userCode, userCodeOther);

		resObj.put("isUserInFriendRequest", friendRequestDao.isUserInSentFriendRequest(friendRequestDto));

		response.getWriter().write(resObj.toString());
	}
}