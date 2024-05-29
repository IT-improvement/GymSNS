package friend.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friend.controller.Action;
import org.json.JSONArray;
import org.json.JSONObject;

import friendRequest.model.FriendRequestDao;
import friendRequest.model.FriendRequestRequestDto;
import friendRequest.model.FriendRequestResponseDto;
import util.ApiResponseManager;
import util.ParameterValidator;

// 유저가 받은 친구 요청들을 반환
public class FriendRequestReceivedReadAllAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

		String userCodeStr = request.getHeader("Authorization");

		if (!ParameterValidator.isInteger(userCodeStr)) {
			JSONObject resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);

		FriendRequestDao friendRequestDao = FriendRequestDao.getInstance();
		FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(userCode);
		List<FriendRequestResponseDto> friendRequests = friendRequestDao.findReceivedFriendRequestAll(friendRequestDto);

		JSONArray friendRequestJsonArr = new JSONArray();

		for (FriendRequestResponseDto friendRequest : friendRequests) {
			JSONObject friendRequestObj = new JSONObject();

			friendRequestObj.put("userCode", friendRequest.getUserCodeOther());
			friendRequestObj.put("userId", friendRequest.getUserIdOther());
			friendRequestObj.put("userName", friendRequest.getUserNameOther());

			friendRequestJsonArr.put(friendRequestObj);
		}

		response.getWriter().write(friendRequestJsonArr.toString());
	}
}