package friend.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import friendRequest.model.FriendRequestDao;
import friendRequest.model.FriendRequestRequestDto;
import friendRequest.model.FriendRequestResponseDto;

public class FriendRequestReadAllAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

		String userCodeOtherStr = request.getParameter("code");
		FriendRequestDao friendRequestDao = FriendRequestDao.getInstance();
		//FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(user.getCode());

		// 받은 친구 요청들
		if (userCodeOtherStr == null) {
			FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(1);
			List<FriendRequestResponseDto> friendRequests = friendRequestDao.findReceivedFriendRequestAll(friendRequestDto);
			JSONArray friendRequestJsonArr = new JSONArray();
			
			for (FriendRequestResponseDto friendRequest : friendRequests) {
				JSONObject friendRequestObj = new JSONObject();
				friendRequestObj.put("code", friendRequest.getUserCodeOther());
				friendRequestObj.put("id", friendRequest.getUserIdOther());
				friendRequestObj.put("name", friendRequest.getUserNameOther());
				
				friendRequestJsonArr.put(friendRequestObj);
			}

			response.getWriter().write(friendRequestJsonArr.toString());
		} else {
			// 특정 유저에게 내가 친구 요청을 보냈는지 여부
			int code = Integer.parseInt(userCodeOtherStr);
			JSONObject friendRequestObj = new JSONObject();
			//FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(user.getCode(), code);
			FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(1, code);
			friendRequestObj.put("is_user_in_friend_request", friendRequestDao.isUserInSentFriendRequest(friendRequestDto));

			response.getWriter().write(friendRequestObj.toString());
		}
	}
}