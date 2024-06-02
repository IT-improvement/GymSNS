package feed.controller.action;

import feed.controller.Action;
import feed.model.Feed;
import feed.model.FeedDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import util.ParameterValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class FeedReadAction
 */
public class FeedReadByUserCodeAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userCodeViewerStr = request.getHeader("Authorization");
		String userCodeFeedWriterStr = request.getParameter("userCodeFeedWriter");

		if (!ParameterValidator.isInteger(userCodeFeedWriterStr)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		int userCodeFeedWriter = Integer.parseInt(userCodeFeedWriterStr);

		Integer userCodeViewer = (ParameterValidator.isInteger(userCodeViewerStr)) ?
									Integer.parseInt(userCodeViewerStr) :
									null;

		FeedDAO feedDao = new FeedDAO();
		ArrayList<Feed> list = feedDao.getAllFeedByUserCode(userCodeFeedWriter, userCodeViewer);

		JSONArray feedJsonArr = new JSONArray();
				
		for (Feed feed : list) {
			JSONObject feedObj = new JSONObject();

			feedObj.put("feedIndex", feed.getFeedIndex());
			feedObj.put("title", feed.getTitle());
			feedObj.put("content", feed.getContent());
			feedObj.put("userCode", feed.getUserCode());
			feedObj.put("createDate", feed.getCreateDate());
			feedObj.put("comments", feed.getComments());
			if(feed.getModDate() == null) {
				feedObj.put("modDate", "");
			}else {
				feedObj.put("modDate", feed.getModDate());
			}
			feedObj.put("userId", feed.getUserId());
			feedObj.put("userName", feed.getUserName());
			feedObj.put("favoriteCount", feed.getFavoriteCount());
			feedObj.put("checkFavorite", feed.getIsFavorite());

			System.out.println(feed.getComments().size());
			feedJsonArr.put(feedObj);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedJsonArr.toString());
	}
}
