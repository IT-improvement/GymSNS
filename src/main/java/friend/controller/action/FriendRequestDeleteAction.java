package friend.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import friendRequest.model.FriendRequestDao;
import friendRequest.model.FriendRequestRequestDto;

public class FriendRequestDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userCodeOtherStr = request.getParameter("code");
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    int userCodeOther = Integer.parseInt(userCodeOtherStr);
	    
		//FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(user.getCode(), userCodeOther);
		FriendRequestRequestDto friendRequestDto = new FriendRequestRequestDto(1, userCodeOther);
		FriendRequestDao friendRequestDao = FriendRequestDao.getInstance();

		JSONObject resObj = new JSONObject();
		
		if (friendRequestDao.deleteFriendRequest(friendRequestDto)) {
			resObj.put("status", 200);
			resObj.put("message", "Friend Request Delete is finished successfully");
		} else {
			resObj.put("status", 404);
			resObj.put("message", "Invalid Request");
		}
			
		response.getWriter().write(resObj.toString());
	}
}