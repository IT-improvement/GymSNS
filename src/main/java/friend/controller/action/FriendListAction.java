package friend.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import friend.model.FriendDao;
import friend.model.FriendRequestDto;
import friend.model.FriendResponseDto;

public class FriendListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String friendCodeStr = request.getParameter("code");

		FriendDao friendDao = FriendDao.getInstance();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// 특정 친구 찾기
		if (friendCodeStr != null) {
			try {
				int friendCode = Integer.parseInt(friendCodeStr);
				//FriendRequestDto friendDto = new FriendRequestDto(user.getCode(), friendCode);
				FriendRequestDto friendDto = new FriendRequestDto(1, friendCode);
				FriendResponseDto friend = friendDao.findFriendByUserCode(friendDto);

				JSONObject friendObj = new JSONObject();
				friendObj.put("index", friend.getIndex());
				friendObj.put("user_code_self", friend.getUserCodeSelf());
				friendObj.put("user_code_friend", friend.getUserCodeFriend());

				response.getWriter().write(friendObj.toString());
			} catch (Exception e) {
				response.sendRedirect("/friends");
			}
		} else {
			// 모든 친구 찾기
			//FriendRequestDto friendDto = new FriendRequestDto(user.getCode());
			FriendRequestDto friendDto = new FriendRequestDto(1);
			List<FriendResponseDto> friends = friendDao.findFriendAll(friendDto);
			
			JSONArray friendJsonArr = new JSONArray();
			
			for (FriendResponseDto friend : friends) {
				JSONObject friendObj = new JSONObject();
				friendObj.put("index", friend.getIndex());
				friendObj.put("user_code_self", friend.getUserCodeSelf());
				friendObj.put("user_code_friend", friend.getUserCodeFriend());
				
				friendJsonArr.put(friendObj);
			}
			response.getWriter().write(friendJsonArr.toString());
		}
	}
}
