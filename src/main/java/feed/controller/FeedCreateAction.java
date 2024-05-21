package feed.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import feed.model.Feed;
import feed.model.FeedDAO;
import feed.model.FeedResponseDTO;

/**
 * Servlet implementation class FeedAction
 */
public class FeedCreateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedCreateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		FeedDAO feedDao = new FeedDAO();
		ArrayList<Feed> list = feedDao.getFeed();
				
		
				
		JSONArray userJsonArr = new JSONArray();
				
		for (Feed feed : list) {
			JSONObject userObj = new JSONObject();
			userObj.put("title", feed.getTitle());
			userObj.put("content", feed.getContent());
			userObj.put("userCode", feed.getUserCode());
			userObj.put("createDate", feed.getCreateDate());
					
			userJsonArr.put(userObj);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(userJsonArr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
