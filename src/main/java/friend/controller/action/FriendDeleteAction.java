package friend.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import friend.model.FriendDao;
import friend.model.FriendRequestDto;

public class FriendDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String friendCodeStr = request.getParameter("code");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JSONObject resObj = new JSONObject();
		
		if (friendCodeStr == null) {
			resObj.put("status", 404);
			resObj.put("message", "Invalid Request");
			
			response.getWriter().write(resObj.toString());
			return;
		}
		
		int friendCode = 0;
		
		try {
			friendCode = Integer.parseInt(friendCodeStr);
		} catch (Exception e) {
			resObj.put("status", 404);
			resObj.put("message", "Invalid Request");
			
			response.getWriter().write(resObj.toString());
			return;
		}
		
		FriendDao friendDao = FriendDao.getInstance();
		//FriendRequestDto friendDto = new FriendRequestDto(user.getCode());
		FriendRequestDto friendDto = new FriendRequestDto(1, friendCode);
		
		if (friendDao.deleteFriendById(friendDto)) {
			resObj.put("status", 200);
			resObj.put("message", "Friend Delete is finished successfully");
		} else {
			resObj.put("status", 500);
			resObj.put("message", "Server Error");
		}
		
		response.getWriter().write(resObj.toString());
	}
}
