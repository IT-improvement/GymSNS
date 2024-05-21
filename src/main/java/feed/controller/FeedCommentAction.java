package feed.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import feed.model.Feed;

/**
 * Servlet implementation class FeedCommentAction
 */
public class FeedCommentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedCommentAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String a[] = request.getPathInfo().split("/");
		int b = Integer.parseInt(a[1]);
		
		Feed[] feeds = {
				new Feed("오계완", "오늘도 계란완료", 1, 1, "안녕하세요~"),
				new Feed("밤양갱", "마싯어", 2, 2, "댓글 남깁니다"),
				new Feed("장추동", "왕족발보쌈", 3, 3, "마싯어요")
		};
		
		JSONObject feedObj = new JSONObject();
				
		for (Feed feed : feeds) {
			if(feed.getFeedIndex() == b) {
				feedObj.put("title", feed.getTitle());
				feedObj.put("content", feed.getContent());
				feedObj.put("feedIndex", feed.getFeedIndex());
				feedObj.put("userCode", feed.getUserCode());	
			}
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedObj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
