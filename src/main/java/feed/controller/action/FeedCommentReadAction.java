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

/**
 * Servlet implementation class FeedCommentAction
 */
public class FeedCommentReadAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url[] = request.getPathInfo().split("/");
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
//			feedObj.put("comment", feed.getComment());
			feedObj.put("midDate", feed.getModDate());

			feedJsonArr.put(feedObj);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String a[] = request.getPathInfo().split("/");
		int b = Integer.parseInt(a[1]);

		Feed[] feeds = {};

		JSONObject feedObj = new JSONObject();

		for (Feed feed : feeds) {
			if (feed.getFeedIndex() == b) {
				feedObj.put("title", feed.getTitle());
				feedObj.put("content", feed.getContent());
				feedObj.put("feedIndex", feed.getFeedIndex());
				feedObj.put("userCode", feed.getUserCode());
			}
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(feedJsonArr.toString());
	}
}
