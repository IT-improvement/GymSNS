package feed.controller.action;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import feed.controller.Action;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import feed.model.Feed;
import util.ApiResponseManager;
import util.ParameterValidator;

/**
 * Servlet implementation class FeedCommentAction
 */
public class FeedCommentReadAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userCodeStr = request.getHeader("Authorization");
		String url[] = request.getPathInfo().split("/");


		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(url[1])) {
			JSONObject resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		int feedIndex = Integer.parseInt(url[1]);


		System.out.println(feedIndex);

		FeedRequestDTO feedDto = new FeedRequestDTO(feedIndex);
		FeedDAO feedDao = FeedDAO.getInstance();
		ArrayList<Feed> list = feedDao.commentRead(feedDto);

		JSONArray feedJsonArr = new JSONArray();

		for (Feed feed : list) {
			JSONObject feedObj = new JSONObject();
			feedObj.put("feedIndex", feed.getFeedIndex());
			feedObj.put("userCode", feed.getUserCode());
			feedObj.put("comment", feed.getComment());
			feedObj.put("midDate", feed.getModDate());

			feedJsonArr.put(feedObj);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedJsonArr.toString());
	}


}
