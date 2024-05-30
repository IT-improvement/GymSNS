package feed.controller.action;

import feed.controller.Action;
import feed.model.Feed;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class FeedReadAction
 */
public class FeedReadByQueryAction implements Action{
       
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");

		FeedDAO feedDao = new FeedDAO();

		ArrayList<Feed> list = feedDao.getAllFeedByQuery(query);

		JSONArray feedJsonArr = new JSONArray();
				
		for (Feed feed : list) {
			JSONObject feedObj = new JSONObject();

			feedObj.put("feedIndex", feed.getFeedIndex());
			feedObj.put("title", feed.getTitle());
			feedObj.put("content", feed.getContent());
			feedObj.put("userCode", feed.getUserCode());
			feedObj.put("createDate", feed.getCreateDate());
			feedObj.put("comments", feed.getComments());
					
			feedJsonArr.put(feedObj);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedJsonArr.toString());
	}
}