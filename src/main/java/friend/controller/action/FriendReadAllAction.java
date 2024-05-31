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
import util.ApiResponseManager;
import util.ParameterValidator;

public class FriendReadAllAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String userCodeStr = request.getHeader("Authorization");

		if (!ParameterValidator.isInteger(userCodeStr)) {
			response.sendError(400, "Bad Request");
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);

		FriendDao friendDao = FriendDao.getInstance();
		FriendRequestDto friendDto = new FriendRequestDto(userCode);

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