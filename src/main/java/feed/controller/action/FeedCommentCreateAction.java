package feed.controller.action;

import feed.controller.Action;
import feed.model.Feed;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import util.ApiResponseManager;
import util.ParameterValidator;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FeedCommentCreateAction
 */
public class FeedCommentCreateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userCodeStr = request.getHeader("Authorization");
		String url[] = request.getPathInfo().split("/");


		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(url[1])) {
			JSONObject resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		int feedIndex = Integer.parseInt(url[1]);

		String comment = request.getParameter("comment");

		FeedRequestDTO feedDto = new FeedRequestDTO(feedIndex, userCode, comment);
		FeedDAO feedDao = FeedDAO.getInstance();
		FeedResponseDTO feed = feedDao.createComment(feedDto);

		JSONObject feedObj = new JSONObject();
		feedObj.put("feedIndex", feed.getFeedIndex());
		feedObj.put("userCode", feed.getUserCode());
		feedObj.put("comment", feed.getComments());

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedObj.toString());
	}


}
