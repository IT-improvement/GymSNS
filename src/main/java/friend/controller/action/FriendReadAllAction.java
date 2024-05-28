package friend.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friend.controller.Action;
import org.json.JSONArray;
import org.json.JSONObject;

import friend.model.FriendDao;
import friend.model.FriendRequestDto;
import friend.model.FriendResponseDto;

public class FriendReadAllAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		FriendDao friendDao = FriendDao.getInstance();

		//FriendRequestDto friendDto = new FriendRequestDto(user.getCode());
		FriendRequestDto friendDto = new FriendRequestDto(1001);
		List<FriendResponseDto> friends = friendDao.findFriendAll(friendDto);
		
		JSONArray friendJsonArr = new JSONArray();
		
		for (FriendResponseDto friend : friends) {
			JSONObject friendObj = new JSONObject();

			friendObj.put("index", friend.getIndex());
			friendObj.put("userCode", friend.getUserCodeFriend());
			friendObj.put("userId", friend.getUserId());
			friendObj.put("userName", friend.getUserName());

			friendJsonArr.put(friendObj);
		}

		response.getWriter().write(friendJsonArr.toString());
	}
}
