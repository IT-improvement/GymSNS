package friend.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import friend.model.FriendDao;
import friend.model.FriendRequestDto;
import friend.model.FriendResponseDto;

public class FriendReadOneAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String friendCodeStr = request.getParameter("code");
		FriendDao friendDao = FriendDao.getInstance();
		
		int friendCode = Integer.parseInt(friendCodeStr);
		//FriendRequestDto friendDto = new FriendRequestDto(user.getCode(), friendCode);
		FriendRequestDto friendDto = new FriendRequestDto(1, friendCode);
		FriendResponseDto friend = friendDao.findFriendByUserCode(friendDto);

		JSONObject friendObj = new JSONObject();

		friendObj.put("index", friend.getIndex());
		friendObj.put("user_code_self", friend.getUserCodeSelf());
		friendObj.put("user_code_friend", friend.getUserCodeFriend());

		response.getWriter().write(friendObj.toString());
	}
}