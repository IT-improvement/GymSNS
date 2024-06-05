package feed.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import feed.controller.Action;
import feed.model.Feed;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;
import user.model.User;
import user.model.UserResponseDto;
import util.ApiResponseManager;
import util.HttpRequestManager;
import util.ImgBB;
import util.ParameterValidator;

/**
 * Servlet implementation class FeedAction
 */
public class FeedCreateAction implements Action {
	private static final long serialVersionUID = 1L;

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");


		HttpRequestManager requestManager = HttpRequestManager.getInstance();

		JSONObject jsonObject = new JSONObject();

		String body = requestManager.getRequestBodyFromClientRequest(request);

		jsonObject = new JSONObject(body);

		String imageBase64 = jsonObject.getString("image");

		String imageURL = ImgBB.uploadImage(imageBase64);



		String userCodeStr = request.getHeader("Authorization");
		System.out.println(userCodeStr);
		if (!ParameterValidator.isInteger(userCodeStr)) {
			JSONObject resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String feedImage = request.getParameter("feedImage");
		
		boolean isValid = true;
		
		if(title == null || title.equals(""))
			isValid = false;
		else if(content == null || content.equals(""))
			isValid = false;
		
		if(isValid) {
			JSONObject resObj = new JSONObject();
			FeedRequestDTO feedDto = new FeedRequestDTO(userCode, title, content, imageURL);
			FeedDAO feedDao = FeedDAO.getInstance();
			if(feedDao.createFeed(feedDto)) {
				resObj = ApiResponseManager.getStatusObject(200, "Feed Create is finished successfully");
			}else {
				resObj = ApiResponseManager.getStatusObject(500);
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(resObj.toString());
		}
		else {
			System.out.println("isValid 실패");
		}


	}

}
