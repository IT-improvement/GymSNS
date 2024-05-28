package friend.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friend.controller.Action;
import org.json.JSONObject;

import friend.model.FriendDao;
import friend.model.FriendRequestDto;
import util.ApiResponseManager;

public class FriendDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String friendCodeStr = request.getParameter("code");
		JSONObject resObj = new JSONObject();
		
		int friendCode = 0;
		
		try {
			friendCode = Integer.parseInt(friendCodeStr);
		} catch (Exception e) {
			resObj = ApiResponseManager.getStatusObject(400);
			
			response.getWriter().write(resObj.toString());
			return;
		}
		
		FriendDao friendDao = FriendDao.getInstance();
		//FriendRequestDto friendDto = new FriendRequestDto(user.getCode());
		FriendRequestDto friendDto = new FriendRequestDto(1, friendCode);
		
		if (friendDao.deleteFriendById(friendDto)) {
			ApiResponseManager.getStatusObject(200, "Friend Delete is finished successfully");
		} else {
			ApiResponseManager.getStatusObject(500);
		}
		
		response.getWriter().write(resObj.toString());
	}
}
