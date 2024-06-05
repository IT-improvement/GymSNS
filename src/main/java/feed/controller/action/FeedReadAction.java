package feed.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import feed.controller.PagingManager;
import org.json.JSONArray;
import org.json.JSONObject;

import feed.controller.Action;
import feed.model.Feed;
import feed.model.FeedDAO;
import util.HttpRequestManager;
import util.ImgBB;
import util.ParameterValidator;

/**
 * Servlet implementation class FeedReadAction
 */
public class FeedReadAction implements Action{
       
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub




		String userCodeStr = request.getHeader("Authorization");
		String pageNumber = request.getParameter("pageNumber");

		Integer userCode = -1;
		if (!ParameterValidator.isInteger(userCodeStr)) {
			userCode = null;
		} else {
			userCode = Integer.valueOf(userCodeStr);
		}

		FeedDAO feedDao = new FeedDAO();


		ArrayList<Feed> list = feedDao.getAllFeed(userCode, Integer.parseInt(pageNumber));

		JSONArray feedJsonArr = new JSONArray();
				
		for (Feed feed : list) {
			System.out.println(feed.getFeedIndex());
			System.out.println(feed.getImageURL());
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
			feedObj.put("imageURL", feed.getImageURL());

			System.out.println(feed.getComments().size());
			feedJsonArr.put(feedObj);
		}
		System.out.println(list.size());

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedJsonArr.toString());
	}
}
